package com.example.starwarsbt.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starwarsbt.R;
import com.example.starwarsbt.model.Player;
import com.example.starwarsbt.view.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.ViewHolder> {
    List<Player> playerList = new ArrayList<>();
    Context context;
    OnItemClickListener onItemClickListener;

    public PointsAdapter(List<Player> list, Context c, OnItemClickListener listener) {
        playerList.addAll(list);
        context = c;
        onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.point_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Player player = playerList.get(position);
        if (player != null) {
            holder.name.setText(player.getName());
            holder.score.setText(String.valueOf(player.getPoints()));
            String url = player.getIcon();
            Glide.with(context)
                    .load(url)
                    .centerCrop()
                    .into(holder.imageView);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClickListener(player);
            }
        });
    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, score;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.player_name);
            score = itemView.findViewById(R.id.points);

        }
    }
}
