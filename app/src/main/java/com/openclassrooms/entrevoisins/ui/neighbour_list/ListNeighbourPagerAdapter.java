package com.openclassrooms.entrevoisins.ui.neighbour_list;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     */
    @Override
    public Fragment getItem(int position) {
        // switch pour afficher soit un fragment ou l'autre
        if (position == 0) {
            // case 1 NeighbourFragment
            return NeighbourFragment.newInstance();
        } else {
            //case 2 FavoriteFragment
            return FavoriteNeighbourFragment.newInstance();
        }

    }

    /**
     * @return the number of pages
     */
    @Override
    public int getCount() {
        return 2;
    }
}