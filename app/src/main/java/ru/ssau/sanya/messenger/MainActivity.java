package ru.ssau.sanya.messenger;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;


import java.util.ArrayList;
import java.util.List;

import ru.ssau.sanya.messenger.Adapter.TabsPagerAdapter;
import ru.ssau.sanya.messenger.Data.Contact;
import ru.ssau.sanya.messenger.Data.User;
import ru.ssau.sanya.messenger.Fragments.ContactsFragment;
import ru.ssau.sanya.messenger.Fragments.MainFragment;
import ru.ssau.sanya.messenger.Fragments.MessagesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Drawer drawerResult = null;
    protected User user;

    public List<Contact> contactList = new ArrayList<>();



    private ViewPager viewPager;
    private TabsPagerAdapter mTabsPagerAdaptFer;
    private ActionBar actionBar;

    private FragmentManager fragmentManager;
    private Fragment fragment = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //drawer
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //fragments
        fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new MainFragment();
        fragmentTransaction.replace(R.id.main_container_wrapper,fragment);
        fragmentTransaction.commit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView profileName = header.findViewById(R.id.name);
        profileName.setText(R.string.profile_name);
        navigationView.setNavigationItemSelectedListener(this);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ProfilePage.class);
                startActivity(intent);
            }
        });



    }












    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            Intent intent = new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(intent);
        }
        else if (id ==R.id.nav_about){
            Intent intent = new Intent(MainActivity.this,AboutPage.class);
            startActivity(intent);
        }
        else {
            if (id == R.id.nav_contacts) {
                fragment = new ContactsFragment();

            } else if (id == R.id.nav_messages) {
                fragment = new MessagesFragment();
            }


            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container_wrapper, fragment);
            transaction.commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer != null)
                drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }






}
