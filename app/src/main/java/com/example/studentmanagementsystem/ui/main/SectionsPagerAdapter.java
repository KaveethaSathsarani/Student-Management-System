package com.example.studentmanagementsystem.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.studentmanagementsystem.R;
import com.example.studentmanagementsystem.adminProfile.FragmentAdminProfile1;
import com.example.studentmanagementsystem.adminProfile.FragmentAdminProfile2;
import com.example.studentmanagementsystem.adminProfile.FragmentAdminProfile3;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentAdminProfile1 p1 = new FragmentAdminProfile1();
                return p1;
            case 1:
                FragmentAdminProfile2 p2 = new FragmentAdminProfile2();
                return p2;

                default:
                    return null;

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Student";
            case 1:
                return "Admin";

                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}