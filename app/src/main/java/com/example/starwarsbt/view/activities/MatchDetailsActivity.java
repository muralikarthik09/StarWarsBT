package com.example.starwarsbt.view.activities;


import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarsbt.R;
import com.example.starwarsbt.Utils;
import com.example.starwarsbt.model.Match;
import com.example.starwarsbt.model.Player;
import com.example.starwarsbt.view.adapters.MatchDetailsAdapter;

import java.util.List;

import static com.example.starwarsbt.Constants.PLAYER;

public class MatchDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusAndNavigationBarColor();
        setContentView(R.layout.activity_main);
        setToolbar();
        initView();
    }


    private void initView() {
        if (getIntent() != null) {
            Player player = (Player) getIntent().getSerializableExtra(PLAYER);
            setTitle(player.getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            TextView textView = findViewById(R.id.ponts_table_tx);
            textView.setText(getString(R.string.macthes));
            List<Match> matchList = Utils.getMatchListByID(this, player.getId());
            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            MatchDetailsAdapter adapter = new MatchDetailsAdapter(matchList, this, player);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

}
