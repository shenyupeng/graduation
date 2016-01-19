package com.ststudy.client.android.utils.glide.load.resource.gif;

import android.util.Log;

import com.ststudy.client.android.utils.glide.load.EncodeStrategy;
import com.ststudy.client.android.utils.glide.load.Options;
import com.ststudy.client.android.utils.glide.load.ResourceEncoder;
import com.ststudy.client.android.utils.glide.load.engine.Resource;
import com.ststudy.client.android.utils.glide.util.ByteBufferUtil;

import java.io.File;
import java.io.IOException;

/**
 * Writes the original bytes of a {@link com.ststudy.client.android.utils.glide.load.resource.gif.GifDrawable} to an
 * {@link java.io.OutputStream}.
 */
public class GifDrawableEncoder implements ResourceEncoder<GifDrawable> {
  private static final String TAG = "GifEncoder";

  @Override
  public EncodeStrategy getEncodeStrategy(Options options) {
    return EncodeStrategy.SOURCE;
  }

  @Override
  public boolean encode(Resource<GifDrawable> data, File file, Options options) {
    GifDrawable drawable = data.get();
    boolean success = false;
    try {
      ByteBufferUtil.toFile(drawable.getBuffer(), file);
      success = true;
    } catch (IOException e) {
      if (Log.isLoggable(TAG, Log.WARN)) {
        Log.w(TAG, "Failed to encode gif drawable data", e);
      }
    }
    return success;
  }
}
