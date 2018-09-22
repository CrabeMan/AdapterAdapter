package fr.crabeman.adapterhelper.viewholder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.crabeman.adapterhelper.IAdapterHelper;


public abstract class ViewHolderContainer<O> extends ViewHolder<O> {

    @NonNull
    private ViewHolder<O> subViewHolder;

    public ViewHolderContainer(@NonNull ViewHolder<O> subViewHolder) {
        this.subViewHolder = subViewHolder;
    }

    @Override
    public View generate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent) {
        View root = super.generate(inflater, parent);
        int resourceId = getContext().getResources().getIdentifier("viewholder_container_inject", "id", getContext().getPackageName());
        View injectView = root.findViewById(resourceId);
        if (injectView != null && injectView instanceof ViewGroup) {
            View child = subViewHolder.generate(inflater, parent);
            ((ViewGroup) injectView).addView(child);
            onGenerateChild(child);
            return root;
        } else {
            throw new RuntimeException("The XML layout need to contain a FrameLayout w/ id 'viewholder_container_inject' for injection of the child layout");
        }
    }

    @Override
    public void notify__(@NonNull O o, int position, @NonNull IAdapterHelper<O> helper) {
        super.notify__(o, position, helper);
        subViewHolder.notify__(o, position, helper);
    }

    @Nullable
    @Override
    public ViewHolder<O> getViewHolderRecursive(@NonNull Class<? extends ViewHolder> clazz) {
        ViewHolder viewHolder = super.getViewHolderRecursive(clazz);
        return (viewHolder != null) ? viewHolder : subViewHolder.getViewHolderRecursive(clazz);
    }

    public void onGenerateChild(View child) {

    }

    public ViewHolder<O> getSubViewHolder() {
        return subViewHolder;
    }
}