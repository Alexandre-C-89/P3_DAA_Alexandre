package com.openclassrooms.entrevoisins.model;

import java.util.ArrayList;
import java.util.List;

public class FavoriteNeighbour {

    private List<Neighbour> mFavoriteNeighbours;

    public FavoriteNeighbour() {
        mFavoriteNeighbours = new ArrayList<>();
    }

    public List<Neighbour> getFavoriteNeighbours() {
        return mFavoriteNeighbours;
    }

    public void addNeighbour(Neighbour neighbour) {
        mFavoriteNeighbours.add(neighbour);
    }

    /**
     * remove to FavoriteNeighbourList
     *
     * @param neighbour
     */
    public void removeNeighbour(Neighbour neighbour) {
        mFavoriteNeighbours.remove(neighbour);
    }

}
