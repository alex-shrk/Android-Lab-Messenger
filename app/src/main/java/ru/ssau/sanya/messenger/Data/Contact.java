package ru.ssau.sanya.messenger.Data;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;



public class Contact extends Human implements Comparable<Contact> {
    private int id;
    private List<Message> messageList;
    private Context context;
    public Contact(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
        messageList = new ArrayList<>();
    }
    public Message getLastMessage(){
        int size=messageList.size();
        if (size!=0)
            return messageList.get(size-1);
        else return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    //todo fix comparator
    @Override
    public int compareTo(@NonNull Contact contact) {
        return Comparators.LASTMESSAGE.compare(this,contact);
    }

    public static class Comparators {

        public static Comparator<Contact> NAME = new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return o1.getFirstName().compareTo(o2.getFirstName());
            }
        };
        public static Comparator<Contact> LASTMESSAGE = new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return (int) (o1.getLastMessage().getMessageTime() - o2.getLastMessage().getMessageTime());
            }
        };

    }
}
