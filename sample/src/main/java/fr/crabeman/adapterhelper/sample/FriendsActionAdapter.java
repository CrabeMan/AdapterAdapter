package fr.crabeman.adapterhelper.sample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import fr.crabeman.adapterhelper.action.AdapterAction;
import fr.crabeman.adapterhelper.action.attach.ItemListener;
import fr.crabeman.adapterhelper.action.attach.OnClickActionAttach;

public class FriendsActionAdapter extends AdapterAction<Friend, FriendsViewHolder> {

    @Nullable
    private OnClickActionAttach<Friend> onCallClickAction;

    FriendsActionAdapter() {
        super(FriendsViewHolder.class);
    }

    @NonNull
    public FriendsActionAdapter setOnCallClickListener(ItemListener.OnItemClick<Friend> onCallClickAction) {
        this.onCallClickAction = new OnClickActionAttach<>(onCallClickAction);
        return this;
    }

    @Override
    public void bind(@NonNull FriendsViewHolder viewHolder) {
        if (onCallClickAction != null) {
            viewHolder.setOnCallClickListener(onCallClickAction.attach(viewHolder));
        }
    }
}
