package fr.crabeman.adapterhelper.sample;

import android.support.annotation.NonNull;
import android.view.View;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;
import fr.crabeman.adapterhelper.viewholder.header.AbsHeaderViewHolderContainer;
import fr.crabeman.adapterhelper.viewholder.header.Headerable;

public class HeaderViewHolderContainer<T extends Headerable> extends AbsHeaderViewHolderContainer<T> {

    HeaderViewHolderContainer(ViewHolder<T> subViewHolder) {
        super(subViewHolder);
    }

    @Override
    public void bind(@NonNull View view) {
        header = view.findViewById(R.id.header_adt_text);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_header;
    }
}
