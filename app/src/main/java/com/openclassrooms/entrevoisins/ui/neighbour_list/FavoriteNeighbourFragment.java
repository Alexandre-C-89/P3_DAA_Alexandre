package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteNeighbourFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteNeighbourFragment extends Fragment {

    private List<Neighbour> mNeighbour;
    private NeighbourApiService mApiService;

    public FavoriteNeighbourFragment() {
        // Required empty public constructor
    }

    public static FavoriteNeighbourFragment newInstance() {
        FavoriteNeighbourFragment fragment = new FavoriteNeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_neighbour, container, false);
    }
}