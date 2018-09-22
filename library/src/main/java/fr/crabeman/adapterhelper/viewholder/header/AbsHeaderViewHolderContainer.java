package fr.crabeman.adapterhelper.viewholder.header;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import fr.crabeman.adapterhelper.IAdapterHelper;
import fr.crabeman.adapterhelper.viewholder.ViewHolder;
import fr.crabeman.adapterhelper.viewholder.ViewHolderContainer;

public abstract class AbsHeaderViewHolderContainer<T extends Headerable> extends ViewHolderContainer<T> {

    protected TextView header;

    public AbsHeaderViewHolderContainer(ViewHolder<T> subViewHolder) {
        super(subViewHolder);
    }

    @Override
    public void notify(@NonNull T t, int position, @NonNull IAdapterHelper<T> helper) {
        if ((position == 0 || t.showHeader(getContext(), helper.getItem(position - 1)))) {
            header.setVisibility(View.VISIBLE);
            header.setText(String.valueOf(t.getHeader(getContext())));
        } else {
            header.setVisibility(View.GONE);
        }
    }
}
