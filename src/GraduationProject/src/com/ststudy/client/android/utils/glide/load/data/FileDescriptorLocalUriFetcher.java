package com.ststudy.client.android.utils.glide.load.data;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Fetches an {@link android.os.ParcelFileDescriptor} for a local {@link android.net.Uri}.
 */
public class FileDescriptorLocalUriFetcher extends LocalUriFetcher<ParcelFileDescriptor> {
  public FileDescriptorLocalUriFetcher(Context context, Uri uri) {
    super(context, uri);
  }

  @Override
  protected ParcelFileDescriptor loadResource(Uri uri, ContentResolver contentResolver)
      throws FileNotFoundException {
    AssetFileDescriptor assetFileDescriptor = contentResolver.openAssetFileDescriptor(uri, "r");
    if (assetFileDescriptor == null) {
      throw new FileNotFoundException("FileDescriptor is null for: " + uri);
    }
    return assetFileDescriptor.getParcelFileDescriptor();
  }

  @Override
  protected void close(ParcelFileDescriptor data) throws IOException {
    data.close();
  }

  @Override
  public Class<ParcelFileDescriptor> getDataClass() {
    return ParcelFileDescriptor.class;
  }
}
