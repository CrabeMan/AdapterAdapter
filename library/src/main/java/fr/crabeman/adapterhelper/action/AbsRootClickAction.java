package fr.crabeman.adapterhelper.action;

import android.support.annotation.NonNull;

import fr.crabeman.adapterhelper.action.attach.ItemListener;
import fr.crabeman.adapterhelper.action.attach.OnClickActionAttach;
import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class AbsRootClickAction<O, VH extends ViewHolder<O>> extends AdapterAction<O, VH> {

    @NonNull
    private final OnClickActionAttach<O> onRootClick;

    public AbsRootClickAction(@NonNull Class<VH> viewHolderClass, @NonNull ItemListener.OnItemClick<O> itemClickListener) {
        super(viewHolderClass);
        this.onRootClick = new OnClickActionAttach<>(itemClickListener);
    }

    @Override public void bind(@NonNull VH viewHolder) {
        if (viewHolder.getRootView() != null) {
            viewHolder.getRootView().setOnClickListener(onRootClick.attach(viewHolder));
        }
    }
}
