package ru.ssau.sanya.messenger.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.ssau.sanya.messenger.R;



public class ContactsViewHolder extends RecyclerView.ViewHolder {

    public TextView contactName;
    public ImageView contactAvatar;

    ContactsViewHolder(View view) {
        super(view);
        contactName = view.findViewById(R.id.cnt_contact_Name);
        contactAvatar = itemView.findViewById(R.id.cnt_contact_avatar);

    }

}
