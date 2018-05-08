package com.example.obernalp.e_games;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.design.widget.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class BaseActivity extends AppCompatActivity implements Values {

    // Variables del joc
    protected ArrayList<String> players;
    protected int num_infiltrados;
    protected int database;
    protected int game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Si tornem a la pantalla principal guardem el jugadors i els parametres
        if (requestCode == CODE_ACTIVITY) {
            this.players = data.getStringArrayListExtra(PLAYERS_CODE);
            this.num_infiltrados = data.getIntExtra(NUM_INFILTRADOS_CODE, DEFAULT_NUMBER_INFILTRADOS);
            this.database = data.getIntExtra(DATABASE_CODE, DEFAULT_DATABASE);
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
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        changeActivity(intent);
    }

    protected void back2MainActivity() {
        Intent intent = getIntent();
        intent.putStringArrayListExtra(PLAYERS_CODE, players);
        intent.putExtra(NUM_INFILTRADOS_CODE, num_infiltrados);
        intent.putExtra(DATABASE_CODE, database);
        intent.putExtra(GAME_CODE, game);
        setResult(RESULT_CHANGES, intent);
        finish();
    }

    protected void back2MSetRules(boolean changes) {
        Intent intent = getIntent();
        if (changes) {
            setResult(RESULT_CHANGES, intent);
            intent.putStringArrayListExtra(PLAYERS_CODE, players);
            intent.putExtra(NUM_INFILTRADOS_CODE, num_infiltrados);
            intent.putExtra(DATABASE_CODE, database);
            intent.putExtra(GAME_CODE, game);
        } else {
            setResult(RESULT_NO_CHANGES, intent);

        }
        finish();
    }

    protected void changeActivity(Intent intent) {
        // Afegim els parametres
        intent.putStringArrayListExtra(PLAYERS_CODE, players);
        intent.putExtra(NUM_INFILTRADOS_CODE, num_infiltrados);
        intent.putExtra(DATABASE_CODE, database);
        intent.putExtra(GAME_CODE, game);

        // Start activity with code for return
        startActivity(intent);
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
    }


}