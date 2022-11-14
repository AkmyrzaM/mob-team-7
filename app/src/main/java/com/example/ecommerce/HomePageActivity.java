package com.example.ecommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class HomePageActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentStateAdapter adapter;
    Button phoneBack;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        String welcomeText = "Welcome, " + MainActivity.FullName;
        getSupportActionBar().setTitle(welcomeText);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager2);
        phoneBack = (Button) findViewById(R.id.phoneBack);

        //this is one of the new functions that customers may need. Namely saying it lets the user to make a phone calls.
        phoneBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:87074754801"));
                startActivity(callIntent);
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentPageAdapter(fm, getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Mobiles"));
        tabLayout.addTab(tabLayout.newTab().setText("Cameras"));
        tabLayout.addTab(tabLayout.newTab().setText("Laptops"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:
                sharedPreferences = getSharedPreferences("remember me", MODE_PRIVATE);
                Intent intent2 = new Intent(HomePageActivity.this, MyProfile.class);
                intent2.putExtra("name", sharedPreferences.getString("username", "Username"));
                intent2.putExtra("pass", sharedPreferences.getString("password", "Password"));
                startActivity(intent2);
                return true;


            case  R.id.logoutmenu:
                // clear remember me
                sharedPreferences = getSharedPreferences("remember me", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putString("username", "");
                editor.putString("fullName", "");
                editor.putString("password", "");
                editor.putBoolean("login", false);
                editor.apply();

                // Back to login page
                Intent intent5 = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(intent5);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}