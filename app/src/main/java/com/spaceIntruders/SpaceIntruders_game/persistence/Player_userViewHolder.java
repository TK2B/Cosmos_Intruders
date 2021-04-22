package com.spaceIntruders.SpaceIntruders_game.persistence;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.spaceIntruders.basicAplication.R;

public class Player_userViewHolder extends RecyclerView.ViewHolder {


        private final TextView playerIDItemView;
        private final TextView playerNameItemView;
        private final TextView playerScoreItemView;

        private Player_userViewHolder(View itemView) {
            super(itemView);
            playerIDItemView = itemView.findViewById(R.id.playerID);
            playerNameItemView = itemView.findViewById(R.id.player_name);
            playerScoreItemView = itemView.findViewById(R.id.player_higscore);
        }

        public void bind(int id, String name, int score) {
            playerIDItemView.setText(String.valueOf(id));
            playerNameItemView.setText(name);
            playerScoreItemView.setText(String.valueOf(score));
        }

        static Player_userViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyvlerview_item, parent, false);
            return new Player_userViewHolder(view);
        }

}
