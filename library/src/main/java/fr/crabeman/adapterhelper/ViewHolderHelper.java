package fr.crabeman.adapterhelper;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class ViewHolderHelper<T> extends RecyclerView.ViewHolder {
    private ViewHolder<T> viewHolder;

    public ViewHolderHelper(@NonNull View itemView, ViewHolder<T> viewHolder) {
        super(itemView);
        this.viewHolder = viewHolder;
    }

    public ViewHolder<T> getViewHolder() {
        return viewHolder;
    }
}
