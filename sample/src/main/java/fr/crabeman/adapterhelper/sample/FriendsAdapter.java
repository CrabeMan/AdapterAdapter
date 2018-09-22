package fr.crabeman.adapterhelper.sample;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import fr.crabeman.adapterhelper.ArrayAdapterHelper;
import fr.crabeman.adapterhelper.viewholder.ViewHolder;
import fr.crabeman.adapterhelper.viewholder.checkbox.CheckBoxViewHolderManager;

public class FriendsAdapter extends ArrayAdapterHelper<Friend> {

    private CheckBoxViewHolderManager<Friend> checkBoxManager;

    FriendsAdapter(@NonNull Context context, @NonNull List<Friend> items) {
        super(context, items);
        checkBoxManager = new CheckBoxViewHolderManager<>(this);
    }

    @NonNull
    @Override
    public ViewHolder<Friend> getViewHolder() {
        return new HeaderViewHolderContainer<>(new CheckBoxViewHolderContainer<>(new FriendsViewHolder(), checkBoxManager));
    }


    public CheckBoxViewHolderManager<Friend> getCheckBoxManager() {
        return checkBoxManager;
    }
}
