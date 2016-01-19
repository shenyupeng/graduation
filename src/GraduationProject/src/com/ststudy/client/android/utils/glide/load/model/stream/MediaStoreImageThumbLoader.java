package com.ststudy.client.android.utils.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;

import com.ststudy.client.android.utils.glide.load.Options;
import com.ststudy.client.android.utils.glide.load.data.mediastore.MediaStoreUtil;
import com.ststudy.client.android.utils.glide.load.data.mediastore.ThumbFetcher;
import com.ststudy.client.android.utils.glide.load.model.ModelLoader;
import com.ststudy.client.android.utils.glide.load.model.ModelLoaderFactory;
import com.ststudy.client.android.utils.glide.load.model.MultiModelLoaderFactory;
import com.ststudy.client.android.utils.glide.signature.ObjectKey;

import java.io.InputStream;

/**
 * Loads {@link InputStream}s from media store image {@link Uri}s that point to pre-generated
 * thumbnails for those {@link Uri}s in the media store.
 */
public class MediaStoreImageThumbLoader implements ModelLoader<Uri, InputStream> {
  public final Context context;

  public MediaStoreImageThumbLoader(Context context) {
    this.context = context.getApplicationContext();
  }

  @Override
  public LoadData<InputStream> buildLoadData(Uri model, int width, int height, Options options) {
    if (MediaStoreUtil.isThumbnailSize(width, height)) {
      return new LoadData<>(new ObjectKey(model), ThumbFetcher.buildImageFetcher(context, model));
    } else {
      return null;
    }
  }

  @Override
  public boolean handles(Uri model) {
    return MediaStoreUtil.isMediaStoreImageUri(model);
  }

  /**
   * Factory that loads {@link InputStream}s from media store image {@link Uri}s.
   */
  public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

    @Override
    public ModelLoader<Uri, InputStream> build(Context context,
        MultiModelLoaderFactory multiFactory) {
      return new MediaStoreImageThumbLoader(context);
    }

    @Override
    public void teardown() {
      // Do nothing.
    }
  }
}
