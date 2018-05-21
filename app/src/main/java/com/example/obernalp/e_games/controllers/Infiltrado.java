package com.example.obernalp.e_games.controllers;

import com.example.obernalp.e_games.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by obernalp on 01/04/2018.
 */

public class Infiltrado extends Controller {



    public Infiltrado(ArrayList<String> players, int num_infiltrados, int database, DatabaseManager databaseManager) {
        this.players = players;
        this.num_infiltrados = num_infiltrados;
        this.database = database;
        this.roles = new ArrayList<>();
        this.data = new ArrayList<>();
        this.blocked_players = new ArrayList<>();
        this.databaseManager = databaseManager;
        setDatabase(database);
        setStartingPlayer();
        this.defineRoles();
    }

    public boolean isInfiltrado(int player) {
        return (roles.get(player).equals(INFILTRADO));

    }

    private void defineRoles() {
        int number_players = players.size();

        Random rand = new Random();
        int word = rand.nextInt(data.size());


        for (int i = 0; i < number_players; i++) {
            roles.add(data.get(word));
            blocked_players.add(false);
        }

        if (num_infiltrados == 0) {
            rand = new Random();
            num_infiltrados = rand.nextInt(number_players) + 1;
        }

        for (int i = 0; i < num_infiltrados; i++) {
            rand = new Random();
            int infiltrado = rand.nextInt(number_players);
            roles.set(infiltrado, INFILTRADO);
        }
    }

    private void setStartingPlayer() {
        int number_players = players.size();

        Random rand = new Random();
        this.startingPlayer = rand.nextInt(number_players);
    }

    public int getStartingPlayer() {

        return this.startingPlayer;
    }

    @Override
    public ArrayList<String> getRoles() {
        return this.roles;
    }

    /*public ArrayList<Boolean> getBlockedPlayers(){
        return this.blocked_players;
    }*/

    public Boolean getBlockedPlayer(int player) {
        return this.blocked_players.get(player);
    }

    public void blockPlayer(int player) {
        this.blocked_players.set(player, true);
    }

    private void setDatabase(int database) {
        if (database != 0){
            data = databaseManager.getWords(databaseManager.getListOfDatabase().get(database-1));
        }else{
            this.data = getAllData();
        }
    }

    public ArrayList<String> getInfiltradosArray(){
        ArrayList<String> infiltrados = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if(roles.get(i).equals(INFILTRADO))
                infiltrados.add(players.get(i));
        }
        return infiltrados;
    }

    public String getInfiltrados(){
        String infiltrados = "";
        if (getInfiltradosArray().size() >2) {
            for (int i = 0; i < getInfiltradosArray().size() - 2; i++) {
                infiltrados += getInfiltradosArray().get(i) + ", ";
            }
            infiltrados += getInfiltradosArray().get(getInfiltradosArray().size() - 2) + " y ";
            infiltrados += getInfiltradosArray().get(getInfiltradosArray().size() - 1);
        }else if (getInfiltradosArray().size() >1){
            infiltrados += getInfiltradosArray().get(0) + " y ";
            infiltrados += getInfiltradosArray().get(1);
        }else{
            infiltrados += getInfiltradosArray().get(0);
        }
        return infiltrados;
    }

    private ArrayList<String> getAllData() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> files = databaseManager.getListOfDatabase();
        for (String db: files) {
            result.removeAll(databaseManager.getWords(db));
            result.addAll(databaseManager.getWords(db));
        }
        Collections.sort(result);

        return result;
    }
}
