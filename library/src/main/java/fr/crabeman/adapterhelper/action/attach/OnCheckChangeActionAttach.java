package fr.crabeman.adapterhelper.action.attach;

import android.support.annotation.NonNull;
import android.widget.CompoundButton;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class OnCheckChangeActionAttach<T> extends ActionAttach<T, CompoundButton.OnCheckedChangeListener, ItemListener.OnItemCheckChange<T>> {

    public OnCheckChangeActionAttach(@NonNull ItemListener.OnItemCheckChange<T> onItemCheckChange) {
        super(onItemCheckChange);
    }

    @NonNull
    @Override public CompoundButton.OnCheckedChangeListener attach(@NonNull ViewHolder<T> viewHolder) {
        return (buttonView, isChecked) -> getLocal().onItemCheckChange(viewHolder.getPosition(), viewHolder.getT(), isChecked);
    }
}
