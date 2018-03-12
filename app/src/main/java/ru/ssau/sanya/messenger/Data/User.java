package ru.ssau.sanya.messenger.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Sanya on 13.10.2017.
 */

public class User extends Human {
    private List<Contact> contactList;

    public User(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        contactList = new ArrayList<>();
    }

    public List<Contact> getContactList() {
        if (contactList==null){
            contactList = new ArrayList<>();
        }
        else return contactList;
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
