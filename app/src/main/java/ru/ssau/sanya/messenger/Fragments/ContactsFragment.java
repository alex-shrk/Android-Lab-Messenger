package ru.ssau.sanya.messenger.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.ssau.sanya.messenger.Adapter.ContactsListAdapter;
import ru.ssau.sanya.messenger.ChatActivity;
import ru.ssau.sanya.messenger.Data.Contact;
import ru.ssau.sanya.messenger.Data.User;
import ru.ssau.sanya.messenger.DividerItemDecoration;
import ru.ssau.sanya.messenger.NewContactActivity;
import ru.ssau.sanya.messenger.R;
import ru.ssau.sanya.messenger.RecyclerTouchListener;


public class ContactsFragment extends Fragment {
    protected User user;
    private List<Contact> contactsList = new ArrayList<>();
    ContactsListAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts,container,false);

        //floationActionButton
        FloatingActionButton fab = rootView.findViewById(R.id.main_fab);
        //fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NewContactActivity.class);
                startActivity(intent);
            }
        });
        RecyclerView contactsRecyclerView = rootView.findViewById(R.id.recycler_view_contacts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        contactsRecyclerView.setLayoutManager(linearLayoutManager);
        //contactsRecyclerView.setHasFixedSize(true);
        contactsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        contactsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        setTestData();
        mAdapter = new ContactsListAdapter(getActivity(),contactsList );
        contactsRecyclerView.setAdapter(mAdapter);


        contactsRecyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getContext(), contactsRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Contact contact = contactsList.get(position);
                Intent intent = new Intent(getActivity(),ChatActivity.class);
                intent.putExtra("cnt_contact_name",contact.getFirstName()+" "+contact.getLastName());
                intent.putExtra("cnt_userName",user.getFirstName()+" "+user.getLastName());
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), contact.getFirstName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



        return rootView;
    }
    public void setTestData(){
        user = new User("Ivan", "Ivanov","123@mail.ru");
        for (int i=1;i<20;i++){
            Contact c = new Contact("firstName"+i,"lastName"+i,"email"+i);
            c.setId(i);
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
            mAdapter.notifyDataSetChanged();
        }


    }


}
