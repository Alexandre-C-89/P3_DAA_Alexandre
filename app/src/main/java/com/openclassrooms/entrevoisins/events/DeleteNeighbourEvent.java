package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user deletes a Neighbour
 */
public class DeleteNeighbourEvent {

    /**
     * Neighbour to delete
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     * @param neighbour
     */
    public DeleteNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

    /**
     * Constructor.
     * @param neighbour
     */
    /**public FavoriteNeighbourEvent(Neighbour neighbour) {
        this.neighbour.setFavorite(false);
    }*/

}
