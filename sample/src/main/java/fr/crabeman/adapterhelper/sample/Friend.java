package fr.crabeman.adapterhelper.sample;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import fr.crabeman.adapterhelper.viewholder.header.Headerable;


public class Friend implements Headerable<Friend> {
    @NonNull
    private String username;

    @NonNull
    private String firstname;

    @NonNull
    private String lastname;

    @NonNull
    private String email;

    public Friend(@NonNull String username, @NonNull String firstname, @NonNull String lastname, @NonNull String email) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    @NonNull
    public String getFirstname() {
        return firstname;
    }

    @NonNull
    public String getLastname() {
        return lastname;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    @Override
    public String getHeader(@NonNull Context context) {
        return String.valueOf(username.charAt(0));
    }

    @Override
    public boolean showHeader(@NonNull Context context, @NonNull Friend friend) {
        return getHeader(context).compareTo(friend.getHeader(context)) != 0;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        return username.equals(friend.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return "Friend{" + username + '}';
    }
}
