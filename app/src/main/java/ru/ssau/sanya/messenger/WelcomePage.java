package ru.ssau.sanya.messenger;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



public class WelcomePage extends AppCompatActivity {

    private Button btnOk;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check first msg_time launch
        preferenceManager = new PreferenceManager(this);
        if (!preferenceManager.isFirstTimeLaunch()){
            launchHomeScreen();
            finish();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.welcome_page);

        btnOk = (Button) findViewById(R.id.btnOk);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });



    }
    private void launchHomeScreen() {
        preferenceManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomePage.this, MainActivity.class));
        finish();
    }
}
