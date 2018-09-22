package fr.crabeman.adapterhelper.action.attach;

import android.support.annotation.NonNull;
import android.view.KeyEvent;

public interface ItemListener {


    interface OnItemClick<T> extends ItemListener {
        void onItemClick(int position, @NonNull T t);
    }

    interface OnItemEditor<T> extends ItemListener {
        boolean onItemEditor(int pos, @NonNull T t, int actionId, @NonNull KeyEvent event);
    }

    interface OnItemLongClick<T> extends ItemListener {
        boolean onItemLongClick(int position, @NonNull T t);
    }

    interface OnItemCheckChange<T> extends ItemListener {
        void onItemCheckChange(int position, @NonNull T t, boolean isChecked);
    }
}
