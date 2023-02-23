package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.entrevoisins.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DetailNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.name_detail_neighbour)
    TextView mNameDetailNeighbour;

    @BindView(R.id.address_detail_neighbour)
    TextView mAddressDetailNeighbour;

    @BindView(R.id.phone_detail_neighbour)
    TextView mPhoneDetailNeighbour;

    @BindView(R.id.texte_detail_neighbour)
    TextView mTextDetailNeighbour;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager());
        //mViewPager.setAdapter(mPagerAdapter);
        //mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        //mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        // Au click j'attéris sur la page de détails


    }


    @OnClick(R.id.add_favorite)
    void addFavourite() {
        AddNeighbourActivity.navigate(this);
    }

}