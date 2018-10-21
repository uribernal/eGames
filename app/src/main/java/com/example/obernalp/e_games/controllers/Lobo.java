package com.example.obernalp.e_games.controllers;

import com.example.obernalp.e_games.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Random;

public class Lobo extends Controller {

    private ArrayList<String> players;
    private ArrayList<Boolean> blocked_players;
    private ArrayList<String> roles;
    private int num_infiltrados; // num_lobos

    public Lobo(ArrayList<String> players, int num_infiltrados) {
        this.players = players;
        this.num_infiltrados = num_infiltrados;
        this.roles = new ArrayList<>();
        this.blocked_players = new ArrayList<>();
        this.defineRoles();
    }


    private void defineRoles() {
        int number_players = players.size();
        //String c =
        Random rand = new Random();

        for (int i = 0; i < number_players; i++) {
            roles.add(OVEJA);
            blocked_players.add(false);
        }

        if (num_infiltrados == 0) {
            rand = new Random();
            num_infiltrados = rand.nextInt(number_players) + 1;
        }

        for (int i = 0; i < num_infiltrados; i++) {
            rand = new Random();
            int infiltrado = rand.nextInt(number_players);
            roles.set(infiltrado, LOBO);
        }
    }

    public ArrayList<String> getInfiltradosArray(){
        ArrayList<String> infiltrados = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if(roles.get(i).equals(LOBO))
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
        return (roles.get(player).equals(LOBO));

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
}
