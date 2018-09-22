package fr.crabeman.adapterhelper.action;

import android.support.annotation.NonNull;

import fr.crabeman.adapterhelper.action.attach.ItemListener;
import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class RootClickAction<O> extends AbsRootClickAction<O, ViewHolder<O>> {

    public RootClickAction(@NonNull ItemListener.OnItemClick<O> itemClickListener) {
        super((Class) ViewHolder.class, itemClickListener);
    }
}
