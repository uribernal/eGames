package com.example.obernalp.e_games;

public interface Values {

    // Games
    int GAME_INFILTRADO = 1;
    int GAME_ASESINO = 2;
    int GAME_PSICOLOGO = 3;
    int GAME_LADRON = 4;

    // Database
    int RANDOM_DATABASE = 0;

    // Default parameters
    int DEFAULT_NUMBER_INFILTRADOS = 1;
    int DEFAULT_NUMBER_PLAYERS = 4;
    int DEFAULT_DATABASE = RANDOM_DATABASE;
    int DEFAULT_GAME = 1;

    // CODES for parameters
    String PLAYERS_CODE = "PLAYERS";
    String NUM_INFILTRADOS_CODE = "NUM_INFILTRADOS";
    String DATABASE_CODE = "DATABASE";
    String GAME_CODE = "GAME";

    int CODE_ACTIVITY = 0;

    int RESULT_NO_CHANGES = 0;
    int RESULT_CHANGES = 1;

    // Codes fro GAMES
    String INFILTRADO = "INFILTRADO";
    String ASESINO = "ASESINO";

    // ESCRIBANO MODE
    boolean DEFAULT_MODO_ESCRIBANO = false;
    String[] ESCRIBANOS = {"Ariel", "Claudia", "Edu", "Luiggi", "Uri"};
    String[] PLAYERS = {"Player 1", "Player 2", "Player 3", "Player 4", "Player 5"};
}
