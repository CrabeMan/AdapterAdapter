package fr.crabeman.adapterhelper.action.attach;

import android.support.annotation.NonNull;
import android.view.View;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class OnLongClickActionAttach<T> extends ActionAttach<T, View.OnLongClickListener, ItemListener.OnItemLongClick<T>> {

    public OnLongClickActionAttach(@NonNull ItemListener.OnItemLongClick<T> onItemLongClick) {
        super(onItemLongClick);
    }

    @NonNull
    @Override public View.OnLongClickListener attach(@NonNull ViewHolder<T> viewHolder) {
        return v -> getLocal().onItemLongClick(viewHolder.getPosition(), viewHolder.getT());
    }
}
