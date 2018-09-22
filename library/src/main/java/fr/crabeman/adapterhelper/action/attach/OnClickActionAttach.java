package fr.crabeman.adapterhelper.action.attach;

import android.support.annotation.NonNull;
import android.view.View;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class OnClickActionAttach<T> extends ActionAttach<T, View.OnClickListener, ItemListener.OnItemClick<T>> {

    public OnClickActionAttach(@NonNull ItemListener.OnItemClick<T> onItemClick) {
        super(onItemClick);
    }

    @NonNull
    @Override public View.OnClickListener attach(@NonNull ViewHolder<T> viewHolder) {
        return v -> getLocal().onItemClick(viewHolder.getPosition(), viewHolder.getT());
    }
}
