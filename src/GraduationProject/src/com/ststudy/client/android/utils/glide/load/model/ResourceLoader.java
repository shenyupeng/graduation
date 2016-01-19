package com.ststudy.client.android.utils.glide.load.model;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.InputStream;

import com.ststudy.client.android.utils.glide.load.Options;

/**
 * A model loader for handling Android resource files. Model must be an Android resource id in the
 * package of the given context.
 *
 * @param <Data> The type of data that will be loaded for the given android resource.
 */
public class ResourceLoader<Data> implements ModelLoader<Integer, Data> {
  private static final String TAG = "ResourceLoader";
  private final ModelLoader<Uri, Data> uriLoader;
  private final Resources resources;

  public ResourceLoader(Context context, ModelLoader<Uri, Data> uriLoader) {
    this(context.getResources(), uriLoader);
  }

  public ResourceLoader(Resources resources, ModelLoader<Uri, Data> uriLoader) {
    this.resources = resources;
    this.uriLoader = uriLoader;
  }

  @Override
  public LoadData<Data> buildLoadData(Integer model, int width, int height, Options options) {

    Uri uri = getResourceUri(model);
    return uri == null ? null : uriLoader.buildLoadData(uri, width, height, options);
  }

  @Nullable
  private Uri getResourceUri(Integer model) {
    try {
      return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
          + resources.getResourcePackageName(model) + '/'
          + resources.getResourceTypeName(model) + '/'
          + resources.getResourceEntryName(model));
    } catch (Resources.NotFoundException e) {
      if (Log.isLoggable(TAG, Log.WARN)) {
        Log.w(TAG, "Received invalid resource id: " + model, e);
      }
      return null;
    }
  }

  @Override
  public boolean handles(Integer model) {
    // TODO: check that this is in fact a resource id.
    return true;
  }

  /**
   * Factory for loading {@link InputStream}s from Android resource ids.
   */
  public static class StreamFactory implements ModelLoaderFactory<Integer, InputStream> {

    @Override
    public ModelLoader<Integer, InputStream> build(Context context,
        MultiModelLoaderFactory multiFactory) {
      return new ResourceLoader<>(context, multiFactory.build(Uri.class, InputStream.class));
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }

  /**
   * Factory for loading {@link ParcelFileDescriptor}s from Android resource ids.
   */
  public static class FileDescriptorFactory
      implements ModelLoaderFactory<Integer, ParcelFileDescriptor> {

    @Override
    public ModelLoader<Integer, ParcelFileDescriptor> build(Context context,
        MultiModelLoaderFactory multiFactory) {
      return new ResourceLoader<>(context,
          multiFactory.build(Uri.class, ParcelFileDescriptor.class));
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}