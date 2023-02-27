package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class onClickNeighbourEvent {

    public Neighbour neighbour;

    public onClickNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }

}
