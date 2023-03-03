package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailNeighbourActivity extends AppCompatActivity {

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
    Button mAddFavorite;

    // ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        ButterKnife.bind(this);

        // setSupportActionBar(mToolbar);
        // mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());

        // Récupérez les données du voisin sélectionné à partir de l'extra
        Neighbour neighbour = (Neighbour) getIntent().getParcelableExtra("neighbour");

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
            void addFavorite(Neighbour neighbour) {

            // 3- changer l'icône pour indiquer qu'il est favoris
            if(neighbour.isFavorite())  {
                // alors je passe a false -> étoile non rempli
            } else {
                // alors je passe a true -> étoile rempli
            }

        }


}