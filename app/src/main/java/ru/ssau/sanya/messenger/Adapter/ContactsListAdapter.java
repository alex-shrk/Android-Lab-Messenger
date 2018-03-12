package ru.ssau.sanya.messenger.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.ssau.sanya.messenger.Data.Contact;
import ru.ssau.sanya.messenger.Data.Message;
import ru.ssau.sanya.messenger.R;


public class ContactsListAdapter extends RecyclerView.Adapter<ContactsViewHolder> implements Filterable{

    private List<Contact> contactList;
    private Context context;


    public ContactsListAdapter(Context context,List<Contact> contactList){
        this.contactList = contactList;
        this.context=context;

    }


    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.contacts_list_layout, parent, false);

        return new ContactsViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ContactsViewHolder contactViewHolder, final int position) {
        Contact contact = contactList.get(position);
        contactViewHolder.contactName.setText(contact.getFirstName()+" "+contact.getLastName());
        contactViewHolder.contactAvatar.setImageResource(R.drawable.emoji_food);

        /*contactViewHolder.msg_contact_name.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(contactViewHolder.itemView.getContext(),ChatActivity.class);
                intent.putExtra("contact_id",acontact.getId());
                startActivity(intent);

            }
        });*/

    }
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint != null && constraint.length() > 0) {
                    List<Contact> filterList = new ArrayList<>();
                    for (int i = 0; i < contactList.size(); i++) {
                        if (((contactList.get(i).getFirstName()+contactList.get(i).getLastName()).toUpperCase()).contains(constraint.toString().toUpperCase())) {
                            filterList.add(contactList.get(i));
                        }
                    }
                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    results.count = contactList.size();
                    results.values = contactList;
                }
                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                contactList = (ArrayList<Contact>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }



}
