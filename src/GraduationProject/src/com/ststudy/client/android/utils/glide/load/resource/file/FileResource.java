package com.ststudy.client.android.utils.glide.load.resource.file;

import java.io.File;

import com.ststudy.client.android.utils.glide.load.resource.SimpleResource;

/**
 * A simple {@link com.ststudy.client.android.utils.glide.load.engine.Resource} that wraps a {@link File}.
 */
public class FileResource extends SimpleResource<File> {
  public FileResource(File file) {
    super(file);
  }
}
