package com.example.obernalp.e_games.controllers;

import com.example.obernalp.e_games.Values;
import com.example.obernalp.e_games.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Random;

public class Controller implements Values {

    protected ArrayList<String> players;
    protected ArrayList<Boolean> blocked_players;
    protected ArrayList<String> roles;
    protected ArrayList<String> data;
    protected int num_infiltrados;
    protected int database;
    protected int startingPlayer;
    protected DatabaseManager databaseManager;


    public int getStartingPlayer() {
        return this.startingPlayer;
    }

    public ArrayList<String> getRoles(){
        return this.roles;
    }

    public Boolean getBlockedPlayer(int player) {
        return this.blocked_players.get(player);
    }

    public void blockPlayer(int player) {
        this.blocked_players.set(player, true);
    }
}
