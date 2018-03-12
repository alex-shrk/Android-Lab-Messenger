package ru.ssau.sanya.messenger.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.ssau.sanya.messenger.R;

/**
 * Created by Sanya on 27.10.2017.
 */

public class MessagesViewHolder extends RecyclerView.ViewHolder {

    public TextView contactName,lastMessage,lastMessageTime;
    public ImageView contactAvatar;

    MessagesViewHolder(View view) {
        super(view);
        contactName = view.findViewById(R.id.msg_contact_name);
        contactAvatar = itemView.findViewById(R.id.msg_contact_avatar);
        lastMessage = view.findViewById(R.id.msg_last_message);
        lastMessageTime = view.findViewById(R.id.msg_time);
    }

}