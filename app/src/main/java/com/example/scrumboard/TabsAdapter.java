package com.example.scrumboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.scrumboard.Backlogs;
import com.example.scrumboard.Completed;
import com.example.scrumboard.Doing;
import com.example.scrumboard.Users;


public class TabsAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public TabsAdapter(FragmentManager fm, int NoofTabs) {
            super(fm);
            this.mNumOfTabs = NoofTabs;
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }

        @Override
        public  Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Users users = Users.newInstance("hello","hello");
                    return users;
                case 1:
                    Backlogs back = Backlogs.newInstance("hello","hello");
                    return back;
                case 2:
                    Doing doing= Doing.newInstance("HELO","HELO");
                    return doing;
                case 3:
                    Completed com=Completed.newInstance("hello","hello");
                    return com;
                default:
                    return null;
            }
        }
    }

