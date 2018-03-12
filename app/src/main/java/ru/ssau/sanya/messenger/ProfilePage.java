package ru.ssau.sanya.messenger;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Sanya on 13.10.2017.
 */

public class ProfilePage extends AppCompatActivity {

    private Button btnOk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.profile_page);
        TextView profileName = (TextView) findViewById(R.id.profile_name);
        profileName.setText(R.string.profile_name);
        TextView profileEmail = (TextView) findViewById(R.id.profile_email);
        profileName.setText(R.string.profile_email);

        btnOk = (Button) findViewById(R.id.btnProfileOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this, MainActivity.class));
                finish();
            }
        });

    }

}
