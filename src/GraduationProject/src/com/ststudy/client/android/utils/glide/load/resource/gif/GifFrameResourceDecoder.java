package com.ststudy.client.android.utils.glide.load.resource.gif;

import android.graphics.Bitmap;

import com.ststudy.client.android.utils.glide.gifdecoder.GifDecoder;
import com.ststudy.client.android.utils.glide.load.Options;
import com.ststudy.client.android.utils.glide.load.ResourceDecoder;
import com.ststudy.client.android.utils.glide.load.engine.Resource;
import com.ststudy.client.android.utils.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ststudy.client.android.utils.glide.load.resource.bitmap.BitmapResource;

/**
 * Decodes {@link Bitmap}s from {@link GifDecoder}s representing a particular frame of a particular
 * GIF image.
 */
public final class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
  private final BitmapPool bitmapPool;

  public GifFrameResourceDecoder(BitmapPool bitmapPool) {
    this.bitmapPool = bitmapPool;
  }

  @Override
  public boolean handles(GifDecoder source, Options options) {
    return true;
  }

  @Override
  public Resource<Bitmap> decode(GifDecoder source, int width, int height,
      Options options) {
    Bitmap bitmap = source.getNextFrame();
    return BitmapResource.obtain(bitmap, bitmapPool);
  }
}
