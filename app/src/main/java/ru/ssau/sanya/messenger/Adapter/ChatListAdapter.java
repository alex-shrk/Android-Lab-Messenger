package ru.ssau.sanya.messenger.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

import ru.ssau.sanya.messenger.Data.Message;
import ru.ssau.sanya.messenger.R;


public class ChatListAdapter extends RecyclerView.Adapter<ChatViewHolder> implements Filterable{

    protected List<Message> messageList;
    protected List<Message> defaultMessageList;
    private Context context;

    public ChatListAdapter(Context context, List<Message> messageList){
        this.messageList = messageList;
        this.defaultMessageList=messageList;
        this.context=context;

    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.messages_list_row, parent, false);

        return new ChatViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final ChatViewHolder chatViewHolder, final int position) {
        Message message = messageList.get(position);

        chatViewHolder.user.setText(message.getMessageUser());
        chatViewHolder.messageText.setText(message.getMessageText());
        chatViewHolder.messageTime.setText(DateFormat.format("(hh:mm:ss)\nEEE, dd-MM-yy", message.getMessageTime()));


    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                messageList=defaultMessageList;
                if (constraint != null && constraint.length() > 0) {
                    List<Message> filterList = new ArrayList<>();
                    for (int i = 0; i < messageList.size(); i++) {
                        if ((messageList.get(i).getMessageText().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                            filterList.add(messageList.get(i));
                        }
                    }
                    results.count = filterList.size();
                    results.values = filterList;
                } else {
                    results.count = messageList.size();
                    results.values = messageList;
                }
                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                messageList = (ArrayList<Message>) results.values;
                notifyDataSetChanged();
            }
        };
    }


}
