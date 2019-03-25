package com.example.user.atmbuddy;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class customerActivity extends AppCompatActivity {

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
}
