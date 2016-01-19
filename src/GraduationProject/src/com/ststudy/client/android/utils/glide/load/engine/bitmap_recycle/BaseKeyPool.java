package com.ststudy.client.android.utils.glide.load.engine.bitmap_recycle;

import java.util.Queue;

import com.ststudy.client.android.utils.glide.util.Util;

abstract class BaseKeyPool<T extends Poolable> {
  private static final int MAX_SIZE = 20;
  private final Queue<T> keyPool = Util.createQueue(MAX_SIZE);

  protected T get() {
    T result = keyPool.poll();
    if (result == null) {
      result = create();
    }
    return result;
  }

  public void offer(T key) {
    if (keyPool.size() < MAX_SIZE) {
      keyPool.offer(key);
    }
  }

  protected abstract T create();
}
