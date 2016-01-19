package com.ststudy.client.android.utils.glide.manager;

/**
 * A {@link com.ststudy.client.android.utils.glide.manager.Lifecycle} implementation for tracking and notifying
 * listeners of {@link android.app.Application} lifecycle events.
 *
 * <p> Since there are essentially no {@link android.app.Application} lifecycle events, this class
 * simply defaults to notifying new listeners that they are started. </p>
 */
class ApplicationLifecycle implements Lifecycle {
  @Override
  public void addListener(LifecycleListener listener) {
    listener.onStart();
  }

  @Override
  public void removeListener(LifecycleListener listener) {
    // Do nothing.
  }
}
