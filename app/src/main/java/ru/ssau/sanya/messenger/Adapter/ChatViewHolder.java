package ru.ssau.sanya.messenger.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;
import ru.ssau.sanya.messenger.R;



public class ChatViewHolder extends RecyclerView.ViewHolder {

    public TextView user,messageTime;
    public EmojiconTextView messageText;
    public ImageView userAvatar;

    ChatViewHolder(View view) {
        super(view);
        user = view.findViewById(R.id.message_user);
        messageText = view.findViewById(R.id.message_text);
        messageTime = view.findViewById(R.id.message_time);
        userAvatar = view.findViewById(R.id.message_user_avatar);
    }

}