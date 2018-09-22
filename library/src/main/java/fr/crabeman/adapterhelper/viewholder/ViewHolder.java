package fr.crabeman.adapterhelper.viewholder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.crabeman.adapterhelper.IAdapterHelper;
import fr.crabeman.adapterhelper.action.AdapterAction;


public abstract class ViewHolder<T> {

    @Nullable
    private View rootView;

    @Nullable
    private Context context;

    @Nullable
    private T t;

    private int position;

    @NonNull
    private List<AdapterAction<T, ViewHolder<T>>> actions = new ArrayList<>();

    public View generate(@NonNull LayoutInflater inflater, ViewGroup parent) {
        if (context == null) context = inflater.getContext();
        rootView = inflater.inflate(getLayoutRes(), parent, false);
        bind(rootView);

        for (AdapterAction<T, ViewHolder<T>> action : actions) {
            action.bind(this);
        }
        return rootView;
    }

    public void notify__(@NonNull T t, final int position, @NonNull IAdapterHelper<T> helper) {
        this.t = t;
        this.position = position;
        notify(t, position, helper);
    }

    public abstract void bind(View view);

    public abstract void notify(@NonNull T t, int position, @NonNull IAdapterHelper<T> helper);

    public abstract @LayoutRes
    int getLayoutRes();

    @Nullable
    public ViewHolder<T> getViewHolderRecursive(@NonNull Class<? extends ViewHolder> clazz) {
        return (getClass() == clazz || getClass().getSuperclass() == clazz) ? this : null;
    }

    @Nullable
    public View getRootView() {
        return rootView;
    }

    @Nullable
    public Context getContext() {
        return context;
    }

    @Nullable
    public T getT() {
        return t;
    }

    public int getPosition() {
        return position;
    }

    public void addAction(AdapterAction<T, ViewHolder<T>> action) {
        actions.add(action);
    }
}
