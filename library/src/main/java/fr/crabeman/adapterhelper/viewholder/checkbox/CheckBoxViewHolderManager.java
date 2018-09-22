package fr.crabeman.adapterhelper.viewholder.checkbox;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.Animation;

import java.util.HashSet;
import java.util.Set;

import fr.crabeman.adapterhelper.ArrayAdapterHelper;
import fr.crabeman.adapterhelper.ViewHolderManager;
import fr.crabeman.adapterhelper.action.AdapterAction;
import fr.crabeman.adapterhelper.action.attach.OnCheckChangeActionAttach;

public class CheckBoxViewHolderManager<O> extends ViewHolderManager<O> {

    private boolean checkBoxVisible = false;
    private Set<O> checkedItems;

    @Nullable
    private Listener<O> listener;

    public CheckBoxViewHolderManager(@NonNull ArrayAdapterHelper<O> helper) {
        super(helper);
        checkedItems = new HashSet<>();
        OnCheckChangeActionAttach<O> onCheckChangeActionAttach = new OnCheckChangeActionAttach<>((position, o, isChecked) -> {
            if (isChecked) {
                check(position, o);
            } else {
                uncheck(position, o);
            }
        });
        helper.addAction(new AdapterAction<O, AbsCheckBoxViewHolderContainer<O>>((Class<AbsCheckBoxViewHolderContainer<O>>) (Class<?>) AbsCheckBoxViewHolderContainer.class) {
            @Override
            public void bind(@NonNull AbsCheckBoxViewHolderContainer<O> viewHolder) {
                viewHolder.checkBox.setOnCheckedChangeListener(onCheckChangeActionAttach.attach(viewHolder));
            }
        });
    }

    public void showCheckBoxs(Animation.AnimationListener animationListener) {
        affectCheckBoxs(true, animationListener);
    }

    public void hideCheckBoxs(Animation.AnimationListener animationListener) {
        affectCheckBoxs(false, animationListener);
    }

    /*public void confirmRemove() {
        if (checkBoxVisible) {
            Animation applyAnim = AnimationUtils.loadAnimation(getHelper().getContext(), android.R.applyAnim.slide_out_right);
            applyAnim.setDuration(200);
            applyAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    for (Long id : checkedItems)
                        getHelper().getItems().remove(new CallLog(id));
                    hideCheckBoxs(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            for (final CallLogAdapter_.ViewHolder viewHolder : viewHolders) {
                if (checkedItems.contains(viewHolder.item.getIdentifer())) {
                    viewHolder.subRoot.startAnimation(applyAnim);
                }
            }
        }
    }*/

    private void affectCheckBoxs(boolean visible, final Animation.AnimationListener animationListener) {
        checkBoxVisible = visible;
        checkedItems.clear();
        performOnViewHolder(AbsCheckBoxViewHolderContainer.class, checkBoxViewHolderContainer -> {
            if (checkBoxVisible) checkBoxViewHolderContainer.showCheckBox(animationListener);
            else checkBoxViewHolderContainer.hideCheckBox(animationListener);
        });
        if (listener != null)
            listener.onUpdate(0);
    }

    public void check(int position, @NonNull O o) {
        checkedItems.add(o);
        if (listener != null) {
            int count = checkedItems.size();
            listener.onCheck(count, position, o);
            listener.onUpdate(count);
        }
    }

    public void uncheck(int position, @NonNull O o) {
        checkedItems.remove(o);
        if (listener != null) {
            int count = checkedItems.size();
            listener.onUncheck(count, position, o);
            listener.onUpdate(count);
        }
    }

    public boolean isCheckBoxVisible() {
        return checkBoxVisible;
    }

    public Set<O> getCheckedItems() {
        return checkedItems;
    }

    public void setListener(@Nullable Listener<O> listener) {
        this.listener = listener;
    }

    public interface Listener<T> {
        void onCheck(int count, int position, @NonNull T t);

        void onUncheck(int count, int position, @NonNull T t);

        void onUpdate(int count);
    }
}
