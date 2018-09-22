package fr.crabeman.adapterhelper.viewholder;


import android.support.annotation.NonNull;

import fr.crabeman.adapterhelper.ViewHolderManager;

public abstract class ViewHolderContainerManager<M extends ViewHolderManager<O>, O> extends ViewHolderContainer<O> {

    @NonNull
    private M manager;

    public ViewHolderContainerManager(@NonNull ViewHolder<O> subViewHolder, @NonNull M manager) {
        super(subViewHolder);
        this.manager = manager;
    }


    @NonNull
    public M getManager() {
        return manager;
    }
}

