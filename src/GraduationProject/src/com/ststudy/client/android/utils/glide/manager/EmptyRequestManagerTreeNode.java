package com.ststudy.client.android.utils.glide.manager;

import java.util.Collections;
import java.util.Set;

import com.ststudy.client.android.utils.glide.RequestManager;

/**
 * A {@link RequestManagerTreeNode} that returns no relatives.
 */
final class EmptyRequestManagerTreeNode implements RequestManagerTreeNode {
    @Override
    public Set<RequestManager> getDescendants() {
        return Collections.emptySet();
    }
}
