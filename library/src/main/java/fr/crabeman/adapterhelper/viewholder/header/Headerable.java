package fr.crabeman.adapterhelper.viewholder.header;

import android.content.Context;
import android.support.annotation.NonNull;

public interface Headerable<T> {
    @NonNull
    String getHeader(@NonNull Context context);

    boolean showHeader(@NonNull Context context, @NonNull T t);
}
