package com.example.android.sharekaro;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

public class SignupLoginActivity extends AppCompatActivity {

    TabLayout tab;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);

        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);

        MyFragmentAdapter mfa = new MyFragmentAdapter();
        mfa.al1.add(new LoginFragment());
        mfa.al1.add(new SignupFragment());
        mfa.al2.add("Log_In");
        mfa.al2.add("Sign_In");
        viewPager.setAdapter(mfa);
        tab.setupWithViewPager(viewPager);
    }

    public class MyFragmentAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> al1 = new ArrayList<Fragment>();
        ArrayList<String> al2 = new ArrayList<>();

        MyFragmentAdapter(){
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position){
            return al1.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position){
            return al2.get(position);
        }

        @Override
        public int getCount(){
            return al1.size();
        }
    }
}

