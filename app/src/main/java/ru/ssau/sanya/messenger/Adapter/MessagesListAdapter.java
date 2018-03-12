package ru.ssau.sanya.messenger.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ru.ssau.sanya.messenger.Data.Contact;
import ru.ssau.sanya.messenger.R;


public class MessagesListAdapter extends RecyclerView.Adapter<MessagesViewHolder>{

    private List<Contact> contactList;
    private Context context;


    public MessagesListAdapter(Context context,List<Contact> contactList){
        Collections.sort(contactList,Contact.Comparators.LASTMESSAGE);//fixme move comparator
        this.contactList = contactList;
        this.context=context;

    }


    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.messages_list_layout, parent, false);

        return new MessagesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MessagesViewHolder messagesViewHolder, final int position) {
        Contact contact = contactList.get(position);
        messagesViewHolder.contactName.setText(contact.getFirstName()+" "+contact.getLastName());
        if (contact.getLastMessage()!=null) {
            messagesViewHolder.lastMessage.setText(contact.getLastMessage().getMessageText());
            messagesViewHolder.lastMessageTime.setText(DateFormat.format("hh:mm:ss\nEEE, dd-MMM-yy", contact.getLastMessage().getMessageTime()));

        }
        messagesViewHolder.contactAvatar.setImageResource(contact.getAvatar());

        /*messagesViewHolder.msg_contact_name.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(messagesViewHolder.itemView.context(),ChatActivity.class);
                intent.putExtra("contact_id",acontact.getId());
                startActivity(intent);

            }
        });*/

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }



}
