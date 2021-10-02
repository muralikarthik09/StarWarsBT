package com.example.starwarsbt.model;

import com.google.gson.annotations.SerializedName;

public class Match {
    @SerializedName("match")
    private int match;
    @SerializedName("player1")
    private Player player1;
    @SerializedName("player2")
    private Player player2;

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
