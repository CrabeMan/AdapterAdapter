package fr.crabeman.adapterhelper;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import java.util.List;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public abstract class SpinnerAdapterHelper<T> implements SpinnerAdapter, IAdapterHelper<T> {

    @NonNull
    private Context context;

    @NonNull
    private LayoutInflater inflater;

    @NonNull
    private List<T> items;

    public SpinnerAdapterHelper(@NonNull Context context, @NonNull List<T> items) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Nullable
    @Override
    public View getView(int i, @Nullable View view, ViewGroup viewGroup) {
        ViewHolder<T> viewHolder;
        if (view == null) {
            viewHolder = getViewHolder();
            view = viewHolder.generate(inflater, viewGroup);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder<T>) view.getTag();
        }

        //Todo: Fix dat please
        viewHolder.notify__(getItem(i), i, this);
        return view;
    }

    @Nullable
    @Override
    public View getDropDownView(int i, @Nullable View view, ViewGroup viewGroup) {
        ViewHolder<T> viewHolder;
        if (view == null) {
            viewHolder = getDropDownViewHolder();
            view = viewHolder.generate(inflater, viewGroup);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder<T>) view.getTag();
        }

        viewHolder.notify__(getItem(i), i, this);
        return view;
    }

    @Override
    public int getCount() {
        return getItemSize();
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return getItemSize() == 0;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    @NonNull
    public Context getContext() {
        return context;
    }

    @Override
    @NonNull
    public List<T> getItems() {
        return this.items;
    }

    @Override
    public void setItems(@NonNull List<T> ts) {
        items = ts;
    }

    @Override
    public int getItemSize() {
        return this.items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public boolean isSelectedIndex(int pos) {
        return false;
    }

    @NonNull
    public abstract ViewHolder<T> getViewHolder();

    @NonNull
    public abstract ViewHolder<T> getDropDownViewHolder();
}
