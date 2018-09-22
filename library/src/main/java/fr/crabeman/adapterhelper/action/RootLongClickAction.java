package fr.crabeman.adapterhelper.action;

import android.support.annotation.NonNull;

import fr.crabeman.adapterhelper.action.attach.ItemListener;
import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class RootLongClickAction<O> extends AbsRootLongClickAction<O, ViewHolder<O>> {

    public RootLongClickAction(@NonNull ItemListener.OnItemLongClick<O> itemLongClickListener) {
        super((Class) ViewHolder.class, itemLongClickListener);
    }
}
