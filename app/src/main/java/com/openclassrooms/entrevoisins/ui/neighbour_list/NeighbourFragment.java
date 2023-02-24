package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * J'appelle la méthode SetOnItemClickListener
 * dans mon fragment car il contient le RecyclerView.
 */
public class NeighbourFragment extends Fragment implements MyNeighbourRecyclerViewAdapter.OnItemClickListener {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private RecyclerView mRecyclerView;

    private MyNeighbourRecyclerViewAdapter mAdapter;


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     */
    public static NeighbourFragment newInstance() {
        NeighbourFragment fragment = new NeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    /**
     * Ici la vue est inflate
     * pour pouvoir affiché les données
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();

        /**
         * Ici je récupère le RecyclerView
         * pour pouvoir ensuite lui transmettre les données
         * que je veux afficher
         */
        mRecyclerView = (RecyclerView) view;
        mRecyclerView = view.findViewById(R.id.list_neighbours);
        mAdapter = new MyNeighbourRecyclerViewAdapter(mNeighbours, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        return view;

    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mNeighbours = mApiService.getNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours, this));
        // J'informe l'adapter qu'il y a eu des changements
        // pour qu'il puisse affiché la nouvelle
        // liste qui contient ces même changements
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

    /**
     * Lorsqu'un utilisateur clique sur un neighbour dans la liste
     * j'appelle la méthode OnItemClick.
     * Dans cette méthode j'envoi les infos du neighbour
     * pour pouvoir les récupérés dans ma class DetailNeighbourActivity
     *
     */
    @Override
    public void onItemClick(Neighbour neighbour) {
        Intent intent = new Intent(getActivity(), DetailNeighbourActivity.class);
        intent.putExtra("selected_neighbour", neighbour);
        startActivity(intent);
    }
}
