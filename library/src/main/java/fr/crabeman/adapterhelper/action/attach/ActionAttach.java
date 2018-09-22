package fr.crabeman.adapterhelper.action.attach;

import android.support.annotation.NonNull;

import fr.crabeman.adapterhelper.viewholder.ViewHolder;

public abstract class ActionAttach<T, Remote, Local> {

    @NonNull
    private Local local;

    public ActionAttach(@NonNull Local local) {
        this.local = local;
    }

    @NonNull
    public Local getLocal() {
        return local;
    }

    public abstract Remote attach(@NonNull ViewHolder<T> viewHolder);
}
