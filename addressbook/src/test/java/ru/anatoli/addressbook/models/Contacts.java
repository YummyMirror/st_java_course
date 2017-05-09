package ru.anatoli.addressbook.models;

import com.google.common.collect.ForwardingSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by anatoli.anukevich on 5/9/2017.
 */
public class Contacts extends ForwardingSet<ContactData> {
    private Set<ContactData> delegate;

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contactData) {
        Contacts contacts = new Contacts(this);
        contacts.add(contactData);
        return contacts;
    }

    public Contacts without(ContactData contactData) {
        Contacts contacts = new Contacts(this);
        contacts.remove(contactData);
        return contacts;
    }
}
