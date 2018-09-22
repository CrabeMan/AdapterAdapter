package fr.crabeman.adapterhelper;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

public interface IAdapterHelper<T> {

    boolean isEmpty();

    long getItemId(int i);

    @NonNull
    Context getContext();

    @NonNull
    List<T> getItems();

    void setItems(@NonNull List<T> ts);

    int getItemSize();

    T getItem(int position);

    void clear();

    boolean isSelectedIndex(int pos);
}
