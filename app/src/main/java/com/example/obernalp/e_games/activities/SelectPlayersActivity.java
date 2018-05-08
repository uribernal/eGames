package com.example.obernalp.e_games.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.obernalp.e_games.R;

public class SelectPlayersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_players);

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Save data")
                .setMessage("Do you want to save?")
                .setNegativeButton("DON'T SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        setResult(RESULT_NO_CHANGES, intent);
                        finish();                    }
                })

                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = getIntent();
                        intent.putStringArrayListExtra(PLAYERS_CODE, players);
                        setResult(RESULT_CHANGES, intent);
                        finish();
                    }
                }).create().show();
    }
}
