package fr.crabeman.adapterhelper.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.crabeman.adapterhelper.action.AbsRootClickAction;
import fr.crabeman.adapterhelper.action.AbsRootLongClickAction;
import fr.crabeman.adapterhelper.viewholder.checkbox.CheckBoxViewHolderManager;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_act_list)
    RecyclerView recyclerView;

    @BindView(R.id.main_act_text_count)
    TextView textCount;

    private FriendsAdapter friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        InputStream inputStream = null;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            inputStream = getResources().openRawResource(R.raw.friends);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            List<Friend> friends = new Gson().fromJson(new String(b), new TypeToken<List<Friend>>() {
            }.getType());
            Collections.sort(friends, (t1, t2) -> t1.getUsername().compareTo(t2.getUsername()));
            friendsAdapter = new FriendsAdapter(getApplicationContext(), friends);
            friendsAdapter.getCheckBoxManager().setListener(new CheckBoxViewHolderManager.Listener<Friend>() {
                @Override
                public void onCheck(int count, int position, @NonNull Friend friend) {
                    Util.logAndToast(getApplicationContext(), "OnCheck Pos=" + position + "   " + friend.toString());
                }

                @Override
                public void onUncheck(int count, int position, @NonNull Friend friend) {
                    Util.logAndToast(getApplicationContext(), "OnUnCheck Pos=" + position + "   " + friend.toString());
                }

                @Override
                public void onUpdate(int count) {
                    textCount.setText(String.valueOf(count));
                }
            });
            friendsAdapter.addAction(new AbsRootClickAction<>(FriendsViewHolder.class, (position, friend) ->
                    Util.logAndToast(getApplicationContext(), "OnClick Pos=" + position + "   " + friend.toString())
            ));

            friendsAdapter.addAction(new AbsRootLongClickAction<>(FriendsViewHolder.class, (position, friend) -> {
                Util.logAndToast(getApplicationContext(), "OnLongClick Pos=" + position + "   " + friend.toString());
                return false;
            }));

            friendsAdapter.addAction(new FriendsActionAdapter()
                    .setOnCallClickListener((position, friend) -> {
                                Util.logAndToast(getApplicationContext(), "Call Pos=" + position + "   " + friend.toString());
                            }
                    )
            );
            recyclerView.setAdapter(friendsAdapter);
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (Exception ignored) {
            }
        }
    }


    @OnClick(R.id.main_act_btn_show)
    public void onClickShowCheckBox() {
        friendsAdapter.getCheckBoxManager().showCheckBoxs(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animation animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animation animation) {

            }
        });
    }

    @OnClick(R.id.main_act_btn_hide)
    public void onClickHideCheckBox() {
        friendsAdapter.getCheckBoxManager().hideCheckBoxs(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(@NonNull Animation animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animation animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animation animation) {

            }
        });
    }
}
