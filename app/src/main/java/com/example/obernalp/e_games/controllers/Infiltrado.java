package com.example.obernalp.e_games.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by obernalp on 01/04/2018.
 */

public class Infiltrado {

    private ArrayList<String> players;
    private ArrayList<Boolean> blocked_players;
    private ArrayList<String> roles;
    private ArrayList<String> data;
    private int num_infiltrados;
    private int database;
    private int startingPlayer;

    public Infiltrado(ArrayList<String> players, int num_infiltrados, int database){
        this.players = players;
        this.num_infiltrados = num_infiltrados;
        this.database = database;
        this.roles = new ArrayList<>();
        this.data = new ArrayList<>();
        this.blocked_players = new ArrayList<>();
        setDatabase(database);
        setStartingPlayer();
        this.defineRoles();
    }

    public boolean isInfiltrado(int player){
        if (roles.get(player).equals("INFILTRADO")){
            return true;
        }
        return false;
    }
    public void defineRoles(){
        int number_players = players.size();

        Random rand = new Random();
        int word = rand.nextInt(data.size());


        for(int i = 0; i < number_players; i++) {
            roles.add(data.get(word));
            blocked_players.add(false);
        }

        if (num_infiltrados == 0){
            rand = new Random();
            num_infiltrados = rand.nextInt(number_players) + 1;
        }

        for(int i = 0; i < num_infiltrados; i++) {
            rand = new Random();
            int infiltrado = rand.nextInt(number_players);
            roles.set(infiltrado, "INFILTRADO");
        }

    }
    public void setStartingPlayer(){
        int number_players = players.size();

        Random rand = new Random();
        this.startingPlayer = rand.nextInt(number_players);

    }
    public int getStartingPlayer(){

        return this.startingPlayer;

    }
    public ArrayList<String> getRoles(){
        return this.roles;
    }
    public ArrayList<Boolean> getBlockedPlayers(){
        return this.blocked_players;
    }
    public Boolean getBlockedPlayer(int player){
        return this.blocked_players.get(player);
    }

    public void blockPlayer(int player){
        this.blocked_players.set(player, true);
    }

    public void setDatabase(int database){
        if(database == 0){
            this.data = getAllData();
        }else if (database == 1){
            this.data = new ArrayList<String>(Arrays.asList(getUbicaciones()));
        }else{
            this.data = new ArrayList<String>(Arrays.asList(getBadass()));

        }
        
    }

    private ArrayList<String> getAllData() {
        ArrayList<String> a1 = new ArrayList<String>(Arrays.asList(getUbicaciones()));
        ArrayList<String> a2 = new ArrayList<String>(Arrays.asList(getBadass()));
        a1.removeAll(a2);
        a1.addAll(a2);
        Collections.sort(a1);

        return a1;

    }

    private String[] getUbicaciones() {
        return new String[] {"Avión",
                "Banco",
                "Playa",
                "Catedral",
                "Carpa de Circo",
                "Fiesta de Empresa",
                "Las cruzadas",
                "Casino",
                "Spa / Balneario Matutino",
                "Embajada",
                "Hospital",
                "Hotel",
                "Base Militar",
                "Estudio de cine",
                "Crucero Marítimo",
                "Tren de Pasajeros",
                "Barco pirata",
                "Estación Polar",
                "Comisaría de Policía",
                "Restaurante",
                "Colegio",
                "Garage",
                "Estación Espacial",
                "Submarino",
                "Supermercado",
                "Teatro",
                "Universidad",
                "Pelotón de la Segunda Guerra Mundial"};
    }

    private String[] getBadass() {
        return new String[] {"Yonki", "Mamada", "Karma", "Cerilla",
                "Don Limpio",
                "Jupiter",
                "Dios",
                "Caos",
                "Plutón",
                "Colilla",
                "Moco",
                "Camping",
                "LSD",
                "Doritos",
                "Malabarista",
                "Concierto",
                "Vómito",
                "Vacío",
                "Resaca",
                "Barbacoa",
                "Pikachu",
                "Fifa",
                "Frodo Bolsón",
                "Simpson",
                "Mantel",
                "Embarazada",
                "Derrapar",
                "Buzo",
                "Incendio",
                "Barco pirata",
                "Náufrago",
                "Catán",
                "Ikea",
                "Bate",
                "Váter",
                "Decathon",
                "Ronaldinho",
                "Hulk",
                "Guacamole",
                "69",
                "Ping pong",
                "Rosa",
                "Sant Jordi",
                "Ajedrez",
                "Rey",
                "Estrella"
        };
    }
}
