package com.example.starwarsbt.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starwarsbt.R;
import com.example.starwarsbt.model.Match;
import com.example.starwarsbt.model.Player;
import com.example.starwarsbt.view.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MatchDetailsAdapter extends RecyclerView.Adapter<MatchDetailsAdapter.ViewHolder> {
    List<Match> matchList = new ArrayList<>();
    Context context;
    Player currentPlayer;

    public MatchDetailsAdapter(List<Match> list, Context c, Player player) {
        matchList.addAll(list);
        context = c;
        currentPlayer = player;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.match_detail_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matchList.get(position);
        Player playerOne = match.getPlayer1();
        Player playerTwo = match.getPlayer2();
        holder.nameOne.setText(playerOne.getName());
        holder.nameTwo.setText(playerTwo.getName());
        holder.score.setText(playerOne.getScore() + " - " + playerTwo.getScore());
        if (playerOne.getScore() == playerTwo.getScore()) {
            holder.detailItem.setBackground(null);
        } else {
            boolean looser = false;
            if (playerOne.getScore() > playerTwo.getScore()) {
                if (playerOne.getId() == currentPlayer.getId()) {
                    looser = false;
                } else {
                    looser = true;
                }
            } else if (playerOne.getScore() < playerTwo.getScore()) {
                if (playerTwo.getId() == currentPlayer.getId()) {
                    looser = false;
                } else {
                    looser = true;
                }
            }
            if (looser) {
                holder.detailItem.setBackgroundColor(context.getColor(R.color.red));
            } else {
                holder.detailItem.setBackgroundColor(context.getColor(R.color.green));

            }
        }
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameOne, nameTwo, score;
        LinearLayout detailItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameOne = itemView.findViewById(R.id.player_one);
            detailItem = itemView.findViewById(R.id.detail_list_item);
            nameTwo = itemView.findViewById(R.id.player_two);
            score = itemView.findViewById(R.id.points);

        }
    }
}
