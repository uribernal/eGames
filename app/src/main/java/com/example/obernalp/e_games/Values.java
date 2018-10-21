package com.example.obernalp.e_games;

public interface Values {

    // Games
    int NO_GAME = -1;
    int GAME_INFILTRADO = 1;
    int GAME_ESPIA = 2;
    int GAME_LOBO = 3;
    int GAME_ASESINO = 4;
    int GAME_PSICOLOGO = 5;

    // Database
    int RANDOM_DATABASE = 0;

    // Default parameters
    int DEFAULT_NUMBER_INFILTRADOS = 1;
    int DEFAULT_NUMBER_PLAYERS = 4;
    int DEFAULT_DATABASE = RANDOM_DATABASE;
    int DEFAULT_GAME = NO_GAME;

    // CODES for parameters
    String PLAYERS_CODE = "PLAYERS";
    String NUM_INFILTRADOS_CODE = "NUM_INFILTRADOS";
    String DATABASE_CODE = "DATABASE";
    String GAME_CODE = "GAME";
    String NEW_DB_NAME = "NEW_DB";

    int CODE_ACTIVITY = 0;

    int RESULT_NO_CHANGES = 0;
    int RESULT_CHANGES = 1;

    // Codes fro GAMES
    String INFILTRADO = "INFILTRADO";
    String ASESINO = "ASESINO";
    String LOBO = "LOBO";
    String OVEJA = "OVEJA";

    // ESCRIBANO MODE
    boolean DEFAULT_MODO_ESCRIBANO = false;
    String[] ESCRIBANOS = {"Ariel", "Claudia", "Edu", "Luiggi", "Uri"};
    String[] PLAYERS = {"Player 1", "Player 2", "Player 3", "Player 4", "Player 5"};
}
