package fr.crabeman.adapterhelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.crabeman.adapterhelper.action.AdapterAction;
import fr.crabeman.adapterhelper.viewholder.ViewHolder;


public abstract class ArrayAdapterHelper<T> extends RecyclerView.Adapter<ViewHolderHelper<T>> implements IAdapterHelper<T> {
    @NonNull
    private final Context context;

    @NonNull
    private final LayoutInflater inflater;

    @NonNull
    private List<T> items;

    @NonNull
    private final List<ViewHolder<T>> viewHolders;

    @NonNull
    private final List<AdapterAction<T, ? extends ViewHolder<T>>> actions;

    @Nullable
    private ViewHolder<T> viewHolderHeader;

    @Nullable
    private ViewHolder<T> viewHolderFooter;

    private int selectedIndex = -1;

    private static final int ROW_TYPE = 0;
    private static final int FOOTER_TYPE = 1;
    private static final int HEADER_TYPE = 2;

    public ArrayAdapterHelper(@NonNull Context context, @NonNull List<T> items) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.viewHolders = new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolderHelper<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder<T> viewHolder;
        switch (viewType) {
            case ROW_TYPE:
                viewHolder = getViewHolder();
                break;
            case FOOTER_TYPE:
                viewHolder = viewHolderFooter;
                break;
            case HEADER_TYPE:
                viewHolder = viewHolderHeader;
                break;
            default:
                throw new RuntimeException("Unknown type " + viewType);
        }

        assert viewHolder != null;
        for (AdapterAction<T, ? extends ViewHolder<T>> action : actions) {
            Class<? extends ViewHolder<T>> clazz = action.getViewHolderClass();
            ViewHolder<T> viewHolderAction;
            if (clazz.equals(ViewHolder.class)) {
                viewHolderAction = viewHolder;
            } else {
                viewHolderAction = viewHolder.getViewHolderRecursive(clazz);
            }
            if (viewHolderAction != null) {
                viewHolderAction.addAction((AdapterAction) action);
            }
        }

        View view = viewHolder.generate(inflater, parent);
        if (viewType == ROW_TYPE)
            viewHolders.add(viewHolder);
        return new ViewHolderHelper<>(view, viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHelper<T> holder, int position) {
        if (isHeaderAt(position)) {
            assert viewHolderHeader != null;
            viewHolderHeader.notify__(null, position, this);
            return;
        }

        if (isFooterAt(position)) {
            assert viewHolderFooter != null;
            viewHolderFooter.notify__(null, position, this);
            return;
        }

        T item;
        try {
            item = getItem(hasHeader() ? position - 1 : position);
        } catch (IndexOutOfBoundsException e) {
            item = null;
        }

        assert item != null;
        holder.getViewHolder().notify__(item, position, this);

        applySelected(holder.getViewHolder().getRootView(), position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderAt(position)) {
            return HEADER_TYPE;
        } else if (isFooterAt(position)) {
            return FOOTER_TYPE;
        } else {
            return ROW_TYPE;
        }
    }

    /**
     * @return int The Item Count w/ header & footer
     * @deprecated Use getItemSize() instead
     */
    @SuppressWarnings("DeprecatedIsStillUsed")
    @Deprecated
    @Override
    public int getItemCount() {
        return items.size()
                + (viewHolderFooter == null ? 0 : 1)
                + (viewHolderHeader == null ? 0 : 1);
    }

    @Override
    public final int getItemSize() {
        return items.size();
    }

    public void setItems(@NonNull List<T> ts) {
        items = ts;
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
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public boolean isEmpty() {
        return items.size() == 0;
    }

    @NonNull
    public abstract ViewHolder<T> getViewHolder();

    @NonNull
    public List<ViewHolder<T>> getViewHolders() {
        return viewHolders;
    }

    @Nullable
    public ViewHolder<T> getViewHolderFooter() {
        return viewHolderFooter;
    }

    public void setViewHolderFooter(@Nullable ViewHolder<T> viewHolderFooter) {
        this.viewHolderFooter = viewHolderFooter;
    }

    public void setFooterVisibility(int visibility) {
        if (hasFooter() && viewHolderFooter.getRootView() != null) {
            viewHolderFooter.getRootView().setVisibility(visibility);
        }
    }

    @Nullable
    public ViewHolder<T> getViewHolderHeader() {
        return viewHolderHeader;
    }

    public void setViewHolderHeader(@Nullable ViewHolder<T> viewHolderHeader) {
        this.viewHolderHeader = viewHolderHeader;
    }

    public boolean hasFooter() {
        return viewHolderFooter != null;
    }

    public boolean isFooterAt(int position) {
        return hasFooter() && position == getItemCount() - 1;
    }

    public boolean hasHeader() {
        return viewHolderHeader != null;
    }

    public boolean isHeaderAt(int position) {
        return hasHeader() && position == 0;
    }

    private void applySelected(@NonNull View view, int position) {
        view.setSelected(position == selectedIndex);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public boolean isSelectedIndex(int pos) {
        return selectedIndex == pos;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
        notifyDataSetChanged();
    }

    public void setUnSelect() {
        selectedIndex = -1;
    }

    public void addAction(AdapterAction<T, ? extends ViewHolder<T>> action) {
        actions.add(action);
    }

    public void clearActions() {
        actions.clear();
    }


}
