package ru.ssau.sanya.messenger;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;


import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import hani.momanii.supernova_emoji_library.Actions.EmojIconActions;
import hani.momanii.supernova_emoji_library.Helper.EmojiconEditText;
import ru.ssau.sanya.messenger.Adapter.ChatListAdapter;
import ru.ssau.sanya.messenger.Data.Message;

public class ChatActivity extends AppCompatActivity {

    private List<Message> messageList = new ArrayList<>();
    private ChatListAdapter chatListAdapter;
    private RecyclerView recyclerView;
    View rootView;
    ImageView emojiImageView;
    EmojIconActions emojIconActions;
    EmojiconEditText emojiconEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_chat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //recyclerView
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_chat);

        rootView = findViewById(R.id.root_chat);
        //fab
        FloatingActionButton fabChat = (FloatingActionButton)findViewById(R.id.chat_fab);
        //fabChat.attachToRecyclerView(recyclerView);
        chatListAdapter = new ChatListAdapter(getApplicationContext(),messageList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        //LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        //llm.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(chatListAdapter);

        emojiconEditText = (EmojiconEditText)findViewById(R.id.edit_text);
        emojiImageView = (ImageView) findViewById(R.id.emoji_btn);
        emojIconActions = new EmojIconActions(this,rootView,emojiconEditText,emojiImageView);
        emojIconActions.ShowEmojIcon();
        emojIconActions.setIconsIds(R.drawable.ic_action_keyboard, R.drawable.smiley);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String contactName = "contact_test";
        String userName = "testUser";
        if (b!=null) {
            if (b.get("cnt_contact_name")!=null)
                contactName = (String) b.get("cnt_contact_name");
            else if (b.get("msg_contact_name")!=null)
                contactName = (String) b.get("msg_contact_name");

            if (b.get("cnt_userName")!=null)
                userName = (String) b.get("cnt_userName");
            else if (b.get("msg_userName")!=null)
                userName = (String) b.get("msg_userName");
            setTitle(contactName);//set title toolbar
            initializeChatMessages(contactName, userName);
        }
        recyclerView.scrollToPosition(messageList.size()-1);

        final String finalUserName = userName;
        fabChat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                messageList.add(new Message(emojiconEditText.getText().toString(), finalUserName));
                recyclerView.scrollToPosition(messageList.size()-1);
                chatListAdapter.notifyDataSetChanged();


            }
        });

    }

    private void initializeChatMessages(String contactName,String userName){

        for (int i=1;i<10;i++){
            Message m1 = new Message("test message"+i,contactName );
            Message m2 = new Message("test message"+i,userName);
            messageList.add(m1);
            messageList.add(m2);
        }
        chatListAdapter.notifyDataSetChanged();


    }





    // Заглушка, работа с меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        final MenuItem search = menu.findItem(R.id.action_chat_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){
                    search.collapseActionView();
                    searchView.setQuery("",false);
                }
            }
        });
        chatListAdapter.notifyDataSetChanged();
        return true;
    }

    // Заглушка, работа с меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            onBackPressed();
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_chat_search) {
            return true;
        }
        if (id == R.id.action_chat_search_by_date){
            final Calendar myCalendar = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                }
            };
            new DatePickerDialog(ChatActivity.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }


        return super.onOptionsItemSelected(item);
    }



    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                chatListAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                chatListAdapter.getFilter().filter(newText);
                chatListAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (emojiconEditText.isActivated()) {
            //inputMessage.clearFocus();
            emojiconEditText.requestFocus(EditText.FOCUS_DOWN);
        }
        else{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }


    }


}
