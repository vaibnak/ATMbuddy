package com.example.user.atmbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class customerActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager mviewPager;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer2);
        final Intent intent = getIntent();
        tabLayout = findViewById(R.id.tablayout);
        appBarLayout = findViewById(R.id.appbar);
        mviewPager = findViewById(R.id.viewpager);
        textView = findViewById(R.id.textview1);
        textView.setText(intent.getStringExtra("bname"));
        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new myATMS(), "My ATMs");
        adapter.AddFragment(new review(), "Review");
        mviewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mviewPager);
    }

    public void popup(View view) {
        PopupMenu popup = new PopupMenu(this, view);

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popup.inflate(R.menu.logout);
        popup.show();
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.user.atmbuddy", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();
                startActivity(intent);
                return true;

            default:
                return false;
        }
    }

    @Override
    public void onBackPressed() {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

}
