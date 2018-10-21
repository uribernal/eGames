package com.example.obernalp.e_games.controllers;

import com.example.obernalp.e_games.R;
import com.example.obernalp.e_games.Values;
import com.example.obernalp.e_games.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Espia extends Controller {

    private ArrayList<String> players;
    private ArrayList<Boolean> blocked_players;
    private ArrayList<String> roles;
    private ArrayList<String> cargos;
    private ArrayList<String> cargos2;
    private ArrayList<String> data;
    private int num_infiltrados;
    private int database;
    private int startingPlayer;
    private DatabaseManager databaseManager;

    public Espia(ArrayList<String> players, int num_infiltrados, int database, DatabaseManager databaseManager) {
        this.players = players;
        this.num_infiltrados = num_infiltrados;
        this.database = database;
        this.roles = new ArrayList<>();
        this.cargos = new ArrayList<>();
        this.cargos2 = new ArrayList<>();
        this.data = new ArrayList<>();
        this.blocked_players = new ArrayList<>();
        this.databaseManager = databaseManager;
        setDatabase(database);
        setStartingPlayer();
        this.defineRoles();
    }

    private void setDatabase(int database) {
        data = databaseManager.getRolesEspia(databaseManager.getEspiaDatabase());
    }


    private void defineRoles() {
        int number_players = players.size();
        //String c =
        Random rand = new Random();
        int word = rand.nextInt(data.size());
        cargos = databaseManager.getCargosEspia(databaseManager.getEspiaDatabase(), word);


        for (int i = 0; i < number_players; i++) {
            roles.add(data.get(word));

            blocked_players.add(false);

            rand = new Random();
            // Peta aqui
            //java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.obernalp.e_games/com.example.obernalp.e_games.activities.EspiaGameActivity}: java.lang.IllegalArgumentException: bound must be positive
            //at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:2817)
            cargos2.add(cargos.get(rand.nextInt(cargos.size())));
        }

        if (num_infiltrados == 0) {
            rand = new Random();
            num_infiltrados = rand.nextInt(number_players) + 1;
        }

        for (int i = 0; i < num_infiltrados; i++) {
            rand = new Random();
            int infiltrado = rand.nextInt(number_players);
            roles.set(infiltrado, INFILTRADO);
            cargos2.set(infiltrado, "");

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

    public boolean isInfiltrado(int player) {
        return (roles.get(player).equals(INFILTRADO));

    }

    public void setStartingPlayer() {
        int number_players = players.size();
        Random rand = new Random();
        this.startingPlayer = rand.nextInt(number_players);
    }

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

    public ArrayList<String> getCargos2() {
        return cargos2;
    }
}
