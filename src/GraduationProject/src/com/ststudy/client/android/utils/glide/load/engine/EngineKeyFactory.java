package com.ststudy.client.android.utils.glide.load.engine;

import java.util.Map;

import com.ststudy.client.android.utils.glide.load.Key;
import com.ststudy.client.android.utils.glide.load.Options;
import com.ststudy.client.android.utils.glide.load.Transformation;

class EngineKeyFactory {

  public EngineKey buildKey(Object model, Key signature, int width, int height,
      Map<Class<?>, Transformation<?>> transformations, Class<?> resourceClass,
      Class<?> transcodeClass, Options options) {
    return new EngineKey(model, signature, width, height, transformations, resourceClass,
        transcodeClass, options);
  }
}
