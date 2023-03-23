package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailNeighbourActivity extends AppCompatActivity {

    public static final String EXTRA_NEIGHBOUR = "com.openclassrooms.entrevoisins.ui.neighbour_list.EXTRA_NEIGHBOUR";
    private Neighbour neighbour;
    @BindView(R.id.image_detail_neighbour)
    ImageView mImageDetailNeighbour;

    @BindView(R.id.name_detail_neighbour)
    TextView mNameDetailNeighbour;

    @BindView(R.id.address_detail_neighbour)
    TextView mAddressDetailNeighbour;

    @BindView(R.id.phone_detail_neighbour)
    TextView mPhoneDetailNeighbour;

    @BindView(R.id.texte_detail_neighbour)
    TextView mTextDetailNeighbour;

    @BindView(R.id.add_favorite)
    ImageButton mAddFavorite;

    @Override
    protected void onStart() {
        super.onStart();
        if(!neighbour.isFavorite())  {
            mAddFavorite.setImageResource(R.drawable.ic_star_border_white_24dp);
        }else {
            mAddFavorite.setImageResource(R.drawable.ic_star_24);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Récupérez les données du voisin sélectionné à partir de l'extra
        Long neighbourId = getIntent().getLongExtra("neighbour", 0);
        NeighbourApiService mApiService = DI.getNeighbourApiService();
        neighbour = mApiService.getFromId(neighbourId);

        // Affichez les données dans votre mise en page
        mNameDetailNeighbour.setText(neighbour.getName());
        mAddressDetailNeighbour.setText(neighbour.getAddress());
        mPhoneDetailNeighbour.setText(neighbour.getPhoneNumber());
        mTextDetailNeighbour.setText(neighbour.getAboutMe());
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .centerCrop()
                .into(mImageDetailNeighbour);
    }

    // Au clique je ajouter un neighbour au favoris
    // 1 - Créer la fonction
    @OnClick(R.id.add_favorite)
            // 2- dans la fonction injecté la logique pour que au clique cela ajoute le neighbour au favoris
            void addFavorite() {
            Log.e("tag", "Je suis dans la fonction !");
            Boolean neighbourIsFavorite = neighbour.isFavorite();
            Log.e("tag", neighbourIsFavorite.toString());

            // 3- changer l'icône pour indiquer qu'il est favoris (backgroundTint)
            if(!neighbour.isFavorite())  {
                // alors je passe a false -> étoile non rempli
                mAddFavorite.setImageResource(R.drawable.ic_star_24);
                Log.e("tag", "isTrue");
            } else {
                // alors je passe a true -> étoile rempli
                mAddFavorite.setImageResource(R.drawable.ic_star_border_white_24dp);
                Log.d("tag", "isFalse");
            }

            // Changer l'attribut isFavorite
            neighbour.setFavorite(!neighbour.isFavorite());

        }

}