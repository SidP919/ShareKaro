package com.example.android.sharekaro;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;

public class SharingActivity extends AppCompatActivity {
    TabLayout stab;
    ViewPager sviewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);

        stab = findViewById(R.id.sharingTab);
        sviewPager = findViewById(R.id.sharingViewPager);

        MySharingFragment msf = new MySharingFragment();
        msf.sal1.add(new ShareFileFragment());
        msf.sal1.add(new SharedWithMeFragment());
        msf.sal2.add("Share Files");
        msf.sal2.add("Shared With Me");

        sviewPager.setAdapter(msf);
        stab.setupWithViewPager(sviewPager);
    }

    public class MySharingFragment extends FragmentPagerAdapter{
        ArrayList<Fragment> sal1 = new ArrayList<>();
        ArrayList<String> sal2 = new ArrayList<>();

        public MySharingFragment() {
            super(getSupportFragmentManager());
        }

        @Override
        public Fragment getItem(int position) {
            return sal1.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position){
            return sal2.get(position);
        }

        @Override
        public int getCount() {
            return sal1.size();
        }
    }

}
