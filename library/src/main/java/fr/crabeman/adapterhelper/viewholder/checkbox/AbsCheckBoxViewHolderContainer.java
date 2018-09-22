package fr.crabeman.adapterhelper.viewholder.checkbox;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.widget.CheckBox;

import fr.crabeman.adapterhelper.IAdapterHelper;
import fr.crabeman.adapterhelper.viewholder.ViewHolder;
import fr.crabeman.adapterhelper.viewholder.ViewHolderContainerManager;

public abstract class AbsCheckBoxViewHolderContainer<O> extends ViewHolderContainerManager<CheckBoxViewHolderManager<O>, O> {

    protected CheckBox checkBox;

    public AbsCheckBoxViewHolderContainer(ViewHolder<O> subViewHolder, CheckBoxViewHolderManager<O> manager) {
        super(subViewHolder, manager);
    }

    @Override
    public void notify(@NonNull O obj, int position, @NonNull IAdapterHelper<O> helper) {
        /*checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                getManager().check(obj);
            } else {
                getManager().uncheck(obj);
            }
        });*/
        checkBox.setChecked(getManager().getCheckedItems().contains(obj));
        checkBox.setVisibility(getManager().isCheckBoxVisible() ? View.VISIBLE : View.GONE);
    }


    void showCheckBox(Animation.AnimationListener animationListener) {
        checkBox.setChecked(false);
        /*checkBox.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetWidth = checkBox.getMeasuredWidth();

        checkBox.getLayoutParams().width = 1;
        checkBox.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                checkBox.getLayoutParams().width = interpolatedTime == 1
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : (int) (targetWidth * interpolatedTime);
                checkBox.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setAnimationListener(animationListener);

        a.setDuration((int) (targetWidth / checkBox.getContext().getResources().getDisplayMetrics().density) * 2);
        checkBox.startAnimation(a);*/
        checkBox.setVisibility(View.VISIBLE);

    }

    void hideCheckBox(Animation.AnimationListener animationListener) {
        checkBox.setChecked(false);
        /*final int initialWidth = checkBox.getMeasuredWidth();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    checkBox.setVisibility(View.GONE);
                } else {
                    checkBox.getLayoutParams().width = initialWidth - (int) (initialWidth * interpolatedTime);
                    checkBox.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setAnimationListener(animationListener);

        a.setDuration((int) (initialWidth / checkBox.getContext().getResources().getDisplayMetrics().density) * 2);
        checkBox.startAnimation(a);*/
        checkBox.setVisibility(View.GONE);
    }

}
