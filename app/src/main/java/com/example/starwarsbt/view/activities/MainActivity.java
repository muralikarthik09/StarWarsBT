package com.example.starwarsbt.view.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.starwarsbt.R;
import com.example.starwarsbt.Utils;
import com.example.starwarsbt.model.Player;
import com.example.starwarsbt.view.OnItemClickListener;
import com.example.starwarsbt.view.adapters.PointsAdapter;

import java.io.Serializable;
import java.util.List;

import static com.example.starwarsbt.Constants.PLAYER;


public class MainActivity extends BaseActivity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusAndNavigationBarColor();
        setContentView(R.layout.activity_main);
        setTitle(R.string.star_wars_tour);
        initView();
    }

    private void initView() {
        List<Player> playerList = Utils.getPlayersScores(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        PointsAdapter adapter = new PointsAdapter(playerList, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void OnItemClickListener(Player player) {
        Intent intent = new Intent(this, MatchDetailsActivity.class);
        intent.putExtra(PLAYER, (Serializable) player);
        startActivity(intent);
    }
}