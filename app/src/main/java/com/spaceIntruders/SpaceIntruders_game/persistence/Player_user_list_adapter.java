package com.spaceIntruders.SpaceIntruders_game.persistence;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class Player_user_list_adapter extends ListAdapter<Player_user, Player_userViewHolder>  {

    public Player_user_list_adapter(@NonNull DiffUtil.ItemCallback<Player_user> diffCallback) {
        super(diffCallback);
    }

    @Override
    public Player_userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return Player_userViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(Player_userViewHolder holder, int position) {
        Player_user current = getItem(position);
        holder.bind(current.getUid(), current.getFirstName4db(), current.getHighScore4db());
    }

    public static class PlayerUserDiff extends DiffUtil.ItemCallback<Player_user> {

        @Override
        public boolean areItemsTheSame(@NonNull Player_user oldItem, @NonNull Player_user newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Player_user oldItem, @NonNull Player_user newItem) {
            return oldItem.getFirstName4db().equals(newItem.getFirstName4db());
        }

    }
}
