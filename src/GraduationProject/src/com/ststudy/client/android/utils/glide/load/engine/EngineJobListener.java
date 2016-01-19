package com.ststudy.client.android.utils.glide.load.engine;

import com.ststudy.client.android.utils.glide.load.Key;

interface EngineJobListener {

  void onEngineJobComplete(Key key, EngineResource<?> resource);

  void onEngineJobCancelled(EngineJob<?> engineJob, Key key);
}
