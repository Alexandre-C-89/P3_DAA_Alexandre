package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.FavoriteNeighbour;

import java.util.List;

public class DummyFavoriteNeighbourApiService implements FavoriteNeighbourApiService {

        private List<FavoriteNeighbour> favoritesNeighbours = DummyFavoriteNeighbourGenerator.generateFavoritesNeighbours();


        @Override
        public List<FavoriteNeighbour> getFavoritesNeighbours() {
                return favoritesNeighbours;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void deleteFavoriteNeighbour(FavoriteNeighbour favoriteNeighbour) {
            favoritesNeighbours.remove(favoriteNeighbour);
        }

        @Override
        public void addFavoriteNeighbour(FavoriteNeighbour favoriteNeighbour) {
                favoritesNeighbours.add(favoriteNeighbour);
        }




}
