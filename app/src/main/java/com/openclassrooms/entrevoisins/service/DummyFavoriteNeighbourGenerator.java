package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.FavoriteNeighbour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyFavoriteNeighbourGenerator {

    public static List<FavoriteNeighbour> DUMMY_FAVORITES_NEIGHBOURS = Arrays.asList(
    );


    static List<FavoriteNeighbour> generateFavoritesNeighbours() {
        return new ArrayList<>(DUMMY_FAVORITES_NEIGHBOURS);
    }

}
