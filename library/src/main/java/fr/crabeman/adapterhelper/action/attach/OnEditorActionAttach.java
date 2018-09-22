package fr.crabeman.adapterhelper.action.attach;

import android.support.annotation.NonNull;
import android.widget.TextView;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class OnEditorActionAttach<T> extends ActionAttach<T, TextView.OnEditorActionListener, ItemListener.OnItemEditor<T>> {

    public OnEditorActionAttach(@NonNull ItemListener.OnItemEditor<T> onItemEditor) {
        super(onItemEditor);
    }

    @NonNull
    @Override public TextView.OnEditorActionListener attach(@NonNull ViewHolder<T> viewHolder) {
        return (v, actionId, event) -> getLocal().onItemEditor(viewHolder.getPosition(), viewHolder.getT(), actionId, event);
    }
}
