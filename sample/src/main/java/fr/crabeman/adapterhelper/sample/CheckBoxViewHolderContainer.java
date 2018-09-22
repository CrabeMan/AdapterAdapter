package fr.crabeman.adapterhelper.sample;

import android.support.annotation.NonNull;
import android.view.View;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;
import fr.crabeman.adapterhelper.viewholder.checkbox.AbsCheckBoxViewHolderContainer;
import fr.crabeman.adapterhelper.viewholder.checkbox.CheckBoxViewHolderManager;

public class CheckBoxViewHolderContainer<O> extends AbsCheckBoxViewHolderContainer<O> {


    CheckBoxViewHolderContainer(ViewHolder<O> subViewHolder, CheckBoxViewHolderManager<O> manager) {
        super(subViewHolder, manager);
    }

    @Override
    public void bind(@NonNull View view) {
        checkBox = view.findViewById(R.id.checkbox_adt_checkbox);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_checkbox;
    }
}
