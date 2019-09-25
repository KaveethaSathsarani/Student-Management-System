package com.example.studentmanagementsystem.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.studentmanagementsystem.AdminNoticesTab1;
import com.example.studentmanagementsystem.AdminNoticesTab2;
import com.example.studentmanagementsystem.AdminNoticesTab3;
import com.example.studentmanagementsystem.AdminReferenseMaterialsTab1;
import com.example.studentmanagementsystem.AdminReferenseMaterialsTab2;
import com.example.studentmanagementsystem.AdminReferenseMaterialsTab3;
import com.example.studentmanagementsystem.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapterReferenceMaterials extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapterReferenceMaterials(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){

            case 0:
                AdminReferenseMaterialsTab1 tab1=new AdminReferenseMaterialsTab1();
                return tab1;
            case 1:
                AdminReferenseMaterialsTab2 tab2=new AdminReferenseMaterialsTab2();
                return tab2;
            case 2:
                AdminReferenseMaterialsTab3 tab3=new AdminReferenseMaterialsTab3();
                return tab3;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "View";
            case 1:
                return "Add";
            case 2:
                return "Delete/Update";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }
}