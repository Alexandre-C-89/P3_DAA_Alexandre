package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteNeighbours = new ArrayList<>();

        for (int i = 0; i < neighbours.size(); i++) {

            if (neighbours.get(i).isFavorite()) {
                favoriteNeighbours.add(neighbours.get(i));
            }
        }

        return favoriteNeighbours;

    }

    // addFavoriteNeighbour
    @Override
    public List<Neighbour> addFavoriteNeighbour(Neighbour neighbour) {
        List<Neighbour> ListfavoriteNeighbours = new ArrayList<>();
        neighbour.setFavorite(true);
        ListfavoriteNeighbours.add(neighbour);
        return ListfavoriteNeighbours;
    }

    // DeleteFavoriteNeighbour
    @Override
    public List<Neighbour> deleteFavoriteNeighbour(Neighbour neighbour) {
        List<Neighbour> favoriteNeighbours = getFavoriteNeighbours();
        if (favoriteNeighbours.contains(neighbour)) {
            neighbour.setFavorite(false);
            favoriteNeighbours.remove(neighbour);
        }
        return favoriteNeighbours;
    }

}
