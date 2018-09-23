# !/WIP\\! AdapterHelper
AdapterHelper is a library to try to simplify the usage of the Adapter/ViewHolder of a RecyclerView

# Usage

## Adapter
```
public class FriendsAdapter extends ArrayAdapterHelper<Friend> {

    FriendsAdapter(@NonNull Context context, @NonNull List<Friend> items) {
        super(context, items);
    }

    @NonNull
    @Override
    public ViewHolder<Friend> getViewHolder() {
        return new FriendsViewHolder();
    }
}
```


## ViewHolder
```
public class MyViewHolder extends ViewHolder<MyObject> {

    TextView text;

    @Override
    public void bind(@NonNull View view) {
        this.text = view.findViewById(R.id.my_adt_text);
    }

    @Override
    public void notify(@NonNull MyObject myObject, int position, @NonNull IAdapterHelper<MyObject> helper) {
        text.setText(myObject.getText());
    }

    @Override
    public int getLayoutRes() {
        return R.layout.adapter_friends;
    }
}
```

## Action Binding

### OnClick
This action will be bind to the root view of the ViewHolder
```
myAdapter.addAction(new RootClickAction<MyObject>((position, myObject) ->
    //Do something
));
```

### OnLongClick
This action will be bind to the root view of the ViewHolder
```
myAdapter.addAction(new RootLongClickAction<MyObject>((position, myObject) -> {
    //Do something
    return false;
}));
```

### Custom binding
You can add your own binding to an view of your ViewHolder
```
public class MyActionAdapter extends AdapterAction<MyObject, MyViewHolder> {

    @Nullable
    private OnClickActionAttach<MyObject> onClickAction;

    MyActionAdapter() {
        super(MyViewHolder.class);
    }

    @NonNull
    public MyActionAdapter setOnClickListener(ItemListener.OnItemClick<MyObject> onClickAction) {
        this.onClickAction = new OnClickActionAttach<>(onClickAction);
        return this;
    }

    @Override
    public void bind(@NonNull MyViewHolder viewHolder) {
        if (onClickAction != null) {
            viewHolder.setOnClickListener(onClickAction.attach(viewHolder));
        }
    }
}
```

Add the method `setOnClickListener` to your ViewHolder
```
public class MyViewHolder extends ViewHolder<MyObject> {

    /****/
    
    public void setOnClickListener(View.OnClickListener onClickListener) {
        myBtn.setOnClickListener(onClickListener);
    }
}
```

Then add the `MyActionAdapter` to your adapter
```
myAdapter.addAction(new MyActionAdapter()
        .setOnClickListener((position, myObject) -> {
                //Do something
            }
        )
);
```

Please check at the [sample](/sample) for more information


## Todo
* Add JavaDoc
* Finish the ReadMe

## Author
* **Quentin LEPRAT** - [CrabeMan](https://github.com/CrabeMan)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
