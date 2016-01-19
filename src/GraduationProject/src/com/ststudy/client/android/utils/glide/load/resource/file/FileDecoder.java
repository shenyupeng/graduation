package com.ststudy.client.android.utils.glide.load.resource.file;

import com.ststudy.client.android.utils.glide.load.Options;
import com.ststudy.client.android.utils.glide.load.ResourceDecoder;
import com.ststudy.client.android.utils.glide.load.engine.Resource;

import java.io.File;

/**
 * A simple {@link com.ststudy.client.android.utils.glide.load.ResourceDecoder} that creates resource for a given {@link
 * java.io.File}.
 */
public class FileDecoder implements ResourceDecoder<File, File> {

  @Override
  public boolean handles(File source, Options options) {
    return true;
  }

  @Override
  public Resource<File> decode(File source, int width, int height, Options options) {
    return new FileResource(source);
  }
}
