package com.openclassrooms.entrevoisins.ui.favorite_neighbour_list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourPagerAdapter;

public class ListFavoriteNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FavoriteNeighbourFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }

}
