package fr.crabeman.adapterhelper.sample;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.crabeman.adapterhelper.IAdapterHelper;
import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public class FriendsViewHolder extends ViewHolder<Friend> {

    @BindView(R.id.friends_adt_text_username)
    TextView username;

    @BindView(R.id.friends_adt_text_first_last_name)
    TextView firstLastName;

    @BindView(R.id.friends_adt_text_email)
    TextView email;

    @BindView(R.id.friends_adt_btn_call)
    ImageView btnCall;

    @Override
    public void bind(@NonNull View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    public void notify(@NonNull Friend friend, int position, @NonNull IAdapterHelper<Friend> helper) {
        username.setText(friend.getUsername());
        firstLastName.setText(String.format("%s %s", friend.getFirstname(), friend.getLastname()));
        email.setText(friend.getEmail());
    }

    public void setOnCallClickListener(View.OnClickListener onClickListener) {
        btnCall.setOnClickListener(onClickListener);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_friends;
    }
}
