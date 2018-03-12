package ru.ssau.sanya.messenger.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.ssau.sanya.messenger.Adapter.MessagesListAdapter;
import ru.ssau.sanya.messenger.ChatActivity;
import ru.ssau.sanya.messenger.Data.Contact;
import ru.ssau.sanya.messenger.Data.Message;
import ru.ssau.sanya.messenger.Data.User;
import ru.ssau.sanya.messenger.DividerItemDecoration;
import ru.ssau.sanya.messenger.R;
import ru.ssau.sanya.messenger.RecyclerTouchListener;

/**
 * Created by Sanya on 26.10.2017.
 */

public class MessagesFragment extends Fragment {

    private MessagesListAdapter messagesListAdapter;
    protected User user;
    private RecyclerView recyclerView;

    public List<Contact> contactsList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messages,container,false);

        //recyclerView
        recyclerView = rootView.findViewById(R.id.recycler_view_messages);

        setTestData();
        messagesListAdapter = new MessagesListAdapter(getActivity(),contactsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(messagesListAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Contact contact = contactsList.get(position);
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                intent.putExtra("msg_contact_name",contact.getFirstName()+" "+contact.getLastName());
                intent.putExtra("msg_userName",user.getFirstName()+" "+user.getLastName());
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), contact.getFirstName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        return rootView;
    }
    private void setTestData(){
        user = new User("Ivan", "Ivanov","123@mail.ru");
        for (int i=1;i<20;i++){
            Contact c = new Contact("firstName"+i,"lastName"+i,"email"+i);
            c.setId(i);
            List<Message> messageList = new ArrayList<>();
            for (int j=1;j<20;j++) {
                messageList.add(new Message("test message"+i,c.getFirstName()+c.getLastName()));
            }
            c.setMessageList(messageList);
            contactsList.add(c);

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = getActivity().getIntent();
        if (intent!=null) {
            Bundle b = intent.getExtras();
            String contactName;
            if (b != null) {
                contactName = (String) b.get("newContactName");
                contactsList.add(new Contact(contactName,"",""));
            }
            messagesListAdapter.notifyDataSetChanged();
        }


    }
}
