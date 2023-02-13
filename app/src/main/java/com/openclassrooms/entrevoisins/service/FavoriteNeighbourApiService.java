package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.FavoriteNeighbour;

import java.util.List;

public interface FavoriteNeighbourApiService {

    /**
     * Get all favorites Neighbours
     * @return {@link List}
     */
    List<FavoriteNeighbour> getFavoritesNeighbours();

    /**
     * Deletes a neighbour
     * @return favoriteNeighbour
     */
    void deleteFavoriteNeighbour(FavoriteNeighbour favoriteNeighbour);

    /**
     * Add a neighbour
     * @return favoriteNeighbour
     */
    void addFavoriteNeighbour(FavoriteNeighbour favoriteNeighbour);

}
