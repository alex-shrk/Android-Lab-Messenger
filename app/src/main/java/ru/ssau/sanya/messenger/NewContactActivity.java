package ru.ssau.sanya.messenger;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Sanya on 14.10.2017.
 */

public class  NewContactActivity extends AppCompatActivity {
    Button btnOk;
    EditText newContactName;
    AlertDialog.Builder ad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new_contact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnOk= (Button)findViewById(R.id.btnCreateNewContact);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ad = new AlertDialog.Builder(NewContactActivity.this);
                ad.setTitle(R.string.accept_action);
                ad.setMessage(R.string.accept_add_new_contact);
                ad.setPositiveButton(R.string.yes,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(NewContactActivity.this,MainActivity.class);
                                newContactName=(EditText)findViewById(R.id.newContactEditText);
                                intent.putExtra("newContactName",newContactName.getText().toString());
                                startActivity(intent);
                            }
                        });
                ad.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ad.show();

            }
        });

    }

    // Заглушка, работа с меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        if (newContactName!=null && newContactName.isActivated()) {
            //inputMessage.clearFocus();
            newContactName.requestFocus(EditText.FOCUS_DOWN);
        }
        else{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }


    }
}
