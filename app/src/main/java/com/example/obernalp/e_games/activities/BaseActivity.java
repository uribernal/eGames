package com.example.obernalp.e_games.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.design.widget.Snackbar;

import com.example.obernalp.e_games.R;
import com.example.obernalp.e_games.Values;
import com.example.obernalp.e_games.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Arrays;

public class BaseActivity extends AppCompatActivity implements Values {

    // Variables del joc
    protected ArrayList<String> players;
    protected int num_infiltrados;
    protected int database;
    protected int game;
    protected DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        // Recuperem els paràmetres del joc
        setSettings();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Si tornem a la pantalla principal guardem el jugadors i els parametres
        if (requestCode == CODE_ACTIVITY) {
            if (resultCode == RESULT_CHANGES) {
                this.players = data.getStringArrayListExtra(PLAYERS_CODE);
                this.num_infiltrados = data.getIntExtra(NUM_INFILTRADOS_CODE, DEFAULT_NUMBER_INFILTRADOS);
                this.database = data.getIntExtra(DATABASE_CODE, DEFAULT_DATABASE);
            }
        }
    }

    protected void changeActivity2SetRules(int game) {
        this.game = game;
        Intent intent = new Intent(getApplicationContext(), SetRulesActivity.class);
        changeActivity(intent);
    }

    protected void changeActivity2SelectPlayers() {
        Intent intent = new Intent(getApplicationContext(), SelectPlayersActivity.class);
        changeActivity(intent);
    }

    protected void changeActivity2Game() {
        if (game == GAME_INFILTRADO){
            Intent intent = new Intent(getApplicationContext(), InfiltradoGameActivity.class);
            changeActivity(intent);
        }else if (game == GAME_ESPIA){
            Intent intent = new Intent(getApplicationContext(), EspiaGameActivity.class);
            changeActivity(intent);
        }else if (game == GAME_LOBO){
            Intent intent = new Intent(getApplicationContext(), LoboGameActivity.class);
            changeActivity(intent);
        }else if (game == GAME_ASESINO){
            Intent intent = new Intent(getApplicationContext(), EspiaGameActivity.class);
            changeActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(), InfiltradoGameActivity.class);
            changeActivity(intent);
        }
    }

    protected void changeActivity2SetDatabase(String db_name) {
        Intent intent = new Intent(getApplicationContext(), CreateDatabaseActivity.class);
        intent.putExtra(NEW_DB_NAME, db_name);
        changeActivity(intent);
    }

    protected void changeActivity(Intent intent) {
        // Afegim els parametres
        intent.putStringArrayListExtra(PLAYERS_CODE, players);
        intent.putExtra(NUM_INFILTRADOS_CODE, num_infiltrados);
        intent.putExtra(DATABASE_CODE, database);
        intent.putExtra(GAME_CODE, game);

        // Start activity with code for return
        startActivityForResult(intent, CODE_ACTIVITY);
    }

    protected void showSnackBarMessage(String message, View parentView) {
        // Imprimir meesage per un snackbar
        final Snackbar snackbar = Snackbar
                .make(parentView, message, Snackbar.LENGTH_LONG);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.YELLOW);
        snackbar.show();
    }

    protected void setDefaultSettings() {
        this.num_infiltrados = DEFAULT_NUMBER_INFILTRADOS;
        this.database = RANDOM_DATABASE;
        this.players = new ArrayList<>(Arrays.asList(PLAYERS));
    }

    protected void setSettings() {
        players = getIntent().getStringArrayListExtra(PLAYERS_CODE);
        num_infiltrados = getIntent().getIntExtra(NUM_INFILTRADOS_CODE, DEFAULT_NUMBER_INFILTRADOS);
        database = getIntent().getIntExtra(DATABASE_CODE, DEFAULT_DATABASE);
        game = getIntent().getIntExtra(GAME_CODE, DEFAULT_GAME);
        databaseManager = new DatabaseManager(getApplicationContext());
    }


}