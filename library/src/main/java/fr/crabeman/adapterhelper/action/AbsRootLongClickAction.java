package fr.crabeman.adapterhelper.action;

import android.support.annotation.NonNull;

import fr.crabeman.adapterhelper.action.attach.ItemListener;
import fr.crabeman.adapterhelper.action.attach.OnLongClickActionAttach;
import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class AbsRootLongClickAction<O, VH extends ViewHolder<O>> extends AdapterAction<O, VH> {

    @NonNull
    private final OnLongClickActionAttach<O> onRootLongClick;

    public AbsRootLongClickAction(@NonNull Class<VH> viewHolderClass, @NonNull ItemListener.OnItemLongClick<O> itemLongClickListener) {
        super(viewHolderClass);
        this.onRootLongClick = new OnLongClickActionAttach<>(itemLongClickListener);
    }


    @Override
    public void bind(@NonNull VH viewHolder) {
        if (viewHolder.getRootView() != null) {
            viewHolder.getRootView().setOnLongClickListener(onRootLongClick.attach(viewHolder));
        }
    }
}
