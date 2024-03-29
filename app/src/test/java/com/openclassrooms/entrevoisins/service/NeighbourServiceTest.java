package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;
    private MyNeighbourRecyclerViewAdapter mMyNeighbourRecyclerViewAdapter;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourSuccess() {
        Neighbour newNeigbhour = new Neighbour(60453,"Joe","avatarUrl","Jump Street", "+33 64764865737","Salut c'est joe");
        service.createNeighbour(newNeigbhour);
        assertEquals(newNeigbhour, service.getNeighbours().get(12));
        //assertTrue(service.getNeighbours().contains(newNeigbhour));
    }

    @Test
    public void favoriteNeighbourSuccess() {
        Neighbour newFavoriteNeighbour = service.getNeighbours().get(2);
        assertFalse(newFavoriteNeighbour.isFavorite());
        newFavoriteNeighbour.setFavorite(true);
        //service.getFavoriteNeighbours();
        assertTrue(service.getFavoriteNeighbours().contains(newFavoriteNeighbour));
    }

    @Test
    public void getNeighbourWithIdSuccess() {
        Neighbour newNeigbhour = new Neighbour(12,"Joe","avatarUrl","Jump Street", "+33 64764865737","Salut c'est joe");
        service.createNeighbour(newNeigbhour);
        assertTrue(service.getNeighbours().contains(newNeigbhour));
    }

}
