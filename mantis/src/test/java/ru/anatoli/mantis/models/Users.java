package ru.anatoli.mantis.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.ForwardingSet;

/**
 * Created by anatoli.anukevich on 6/2/2017.
 */
public class Users extends ForwardingSet<UserData> {
    private Set<UserData> delegate;

    public Users(Collection<UserData> users) {
        this.delegate = new HashSet<UserData>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }
}
