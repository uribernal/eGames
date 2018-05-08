package com.example.obernalp.e_games;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class SelectPlayersActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Save data")
                .setMessage("Do you want to save?")
                .setNegativeButton("DON'T SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        back2MSetRules(false);
                    }
                })

                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        back2MSetRules(true);
                    }
                }).create().show();
    }
}
