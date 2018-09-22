package fr.crabeman.adapterhelper.action;

import android.support.annotation.NonNull;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public abstract class AdapterAction<O, VH extends ViewHolder<O>> {

    @NonNull
    private final Class<VH> viewHolderClass;

    public AdapterAction(@NonNull Class<VH> viewHolderClass) {
        this.viewHolderClass = viewHolderClass;
    }

    public abstract void bind(@NonNull VH viewHolder);

    @NonNull
    public Class<VH> getViewHolderClass() {
        return viewHolderClass;
    }
}
