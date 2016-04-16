package com.ewintory.yandex.mobilization.ui.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

import com.ewintory.yandex.mobilization.utils.Utility;

public final class ArtistItemAnimator extends DefaultItemAnimator {

    private int lastAddAnimatedItem = -6;

    public ArtistItemAnimator(int spanCount) {
        lastAddAnimatedItem = -3 * spanCount;
    }

    @Override
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder.getLayoutPosition() > lastAddAnimatedItem) {
            lastAddAnimatedItem++;
            runEnterAnimation((ArtistsAdapter.ArtistHolder) viewHolder);
            return false;
        }

        dispatchAddFinished(viewHolder);
        return false;
    }

    private void runEnterAnimation(final ArtistsAdapter.ArtistHolder holder) {
        final int screenHeight = Utility.getScreenHeight(holder.itemView.getContext());
        holder.itemView.setTranslationY(screenHeight);
        holder.itemView.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(1400)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        dispatchAddFinished(holder);
                    }
                })
                .start();
    }
}
