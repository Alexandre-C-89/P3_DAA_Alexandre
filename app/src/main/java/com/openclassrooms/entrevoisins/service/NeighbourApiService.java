package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Get all my Favorite Neighbours
     * @return {@link List}
     */
    List<Neighbour> getFavoriteNeighbours();

    /**
     * add my Favorite Neighbours
     * @return {@link List}
     */
    List<Neighbour> addFavoriteNeighbour(Neighbour neighbour);

    /**
     * delete my Favorite Neighbours
     * @return {@link List}
     */
    List<Neighbour> deleteFavoriteNeighbour(Neighbour neighbour);
}