package com.example.obernalp.e_games.controllers;

import com.example.obernalp.e_games.Values;
import com.example.obernalp.e_games.database.DatabaseManager;

import java.util.ArrayList;

public class Espia extends Controller {

    private ArrayList<String> players;
    private ArrayList<Boolean> blocked_players;
    private ArrayList<String> roles;
    private ArrayList<String> data;
    private int num_infiltrados;
    //private int database;
    private int startingPlayer;
    private DatabaseManager databaseManager;

    public Espia(ArrayList<String> players, int num_infiltrados, int database, DatabaseManager databaseManager) {
        this.players = players;
        this.num_infiltrados = num_infiltrados;
        //this.database = database;
        this.roles = new ArrayList<>();
        this.data = new ArrayList<>();
        this.blocked_players = new ArrayList<>();
        this.databaseManager = databaseManager;
        //setDatabase(database);
        //setStartingPlayer();
        //this.defineRoles();
    }
}
