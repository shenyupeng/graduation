package com.ststudy.client.android.utils.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;

import com.ststudy.client.android.utils.glide.Glide;
import com.ststudy.client.android.utils.glide.load.Transformation;
import com.ststudy.client.android.utils.glide.load.engine.Resource;
import com.ststudy.client.android.utils.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ststudy.client.android.utils.glide.load.resource.bitmap.BitmapResource;
import com.ststudy.client.android.utils.glide.util.Preconditions;

import java.security.MessageDigest;

/**
 * An {@link com.ststudy.client.android.utils.glide.load.Transformation} that wraps a transformation for a
 * {@link Bitmap} and can apply it to every frame of any
 * {@link com.ststudy.client.android.utils.glide.load.resource.gif.GifDrawable}.
 */
public class GifDrawableTransformation implements Transformation<GifDrawable> {
  private final Transformation<Bitmap> wrapped;
  private final BitmapPool bitmapPool;

  public GifDrawableTransformation(Context context, Transformation<Bitmap> wrapped) {
    this(wrapped, Glide.get(context).getBitmapPool());
  }

  public GifDrawableTransformation(Transformation<Bitmap> wrapped, BitmapPool bitmapPool) {
    this.wrapped = Preconditions.checkNotNull(wrapped);
    this.bitmapPool = Preconditions.checkNotNull(bitmapPool);
  }

  @Override
  public Resource<GifDrawable> transform(Resource<GifDrawable> resource, int outWidth,
      int outHeight) {
    GifDrawable drawable = resource.get();

    // The drawable needs to be initialized with the correct width and height in order for a view
    // displaying it to end up with the right dimensions. Since our transformations may arbitrarily
    // modify the dimensions of our gif, here we create a stand in for a frame and pass it to the
    // transformation to see what the final transformed dimensions will be so that our drawable can
    // report the correct intrinsic width and height.
    Bitmap firstFrame = drawable.getFirstFrame();
    Resource<Bitmap> bitmapResource = new BitmapResource(firstFrame, bitmapPool);
    Resource<Bitmap> transformed = wrapped.transform(bitmapResource, outWidth, outHeight);
    if (!bitmapResource.equals(transformed)) {
      bitmapResource.recycle();
    }
    Bitmap transformedFrame = transformed.get();

    drawable.setFrameTransformation(wrapped, transformedFrame);
    return resource;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof GifDrawableTransformation) {
      GifDrawableTransformation other = (GifDrawableTransformation) o;
      return wrapped.equals(other.wrapped);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return wrapped.hashCode();
  }

  @Override
  public void updateDiskCacheKey(MessageDigest messageDigest) {
    wrapped.updateDiskCacheKey(messageDigest);
  }
}
