package fr.crabeman.adapterhelper;

import android.support.annotation.NonNull;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class ViewHolderManager<O> {
    private ArrayAdapterHelper<O> helper;

    public ViewHolderManager(ArrayAdapterHelper<O> helper) {
        this.helper = helper;
    }

    public <T extends ViewHolder> void performOnViewHolder(Class<T> clazz, @NonNull Performer<T> performer) {
        for (Object rootViewHolder : helper.getViewHolders()) {
            if (rootViewHolder instanceof ViewHolder) {
                ViewHolder viewHolder = ((ViewHolder) rootViewHolder).getViewHolderRecursive(clazz);
                if (viewHolder != null) {
                    performer.perform((T) viewHolder);
                }
            }
        }
    }

    public ArrayAdapterHelper<O> getHelper() {
        return helper;
    }

    public interface Performer<T extends ViewHolder> {
        void perform(T t);
    }
}
