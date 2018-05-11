package com.example.obernalp.e_games.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.obernalp.e_games.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends BaseActivity {
    // Imatge del final
    private ImageView iv_infiltrado;

    // Mode especial
    protected boolean modo_escribano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Seteja les variables per defecte
        setDefaultSettings();

        GridLayout gl = findViewById(R.id.main_gl_container);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gl.setRowCount(1);
            gl.setColumnCount(4);
        } else {
            gl.setRowCount(2);
            gl.setColumnCount(2);
        }

        // Botons dels jocs
        final CardView infiltrado = findViewById(R.id.main_cv_infiltrado);
        final CardView asesino = findViewById(R.id.main_cv_espia);
        final CardView psicologo = findViewById(R.id.main_cv_psicologo);
        final CardView ladron = findViewById(R.id.main_cv_ladron);

        // imagtge amb modo escribano
        iv_infiltrado = findViewById(R.id.main_iv_infiltrado);

        // Canviem a l'Activity on definir les regles del joc
        infiltrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity2SetRules(GAME_INFILTRADO);
            }
        });

        asesino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackBarMessage("Juego no disponible!", iv_infiltrado);
                //changeActivity2SetRules(GAME_ASESINO);
            }
        });

        psicologo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackBarMessage("Juego no disponible!", iv_infiltrado.getRootView());
                //changeActivity2SetRules(GAME_PSICOLOG);
            }
        });

        ladron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackBarMessage("Juego no disponible!", iv_infiltrado);
                //changeActivity2SetRules(GAME_LADRON);
            }
        });

        iv_infiltrado.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showSnackBarMessage("Modo escribano", iv_infiltrado);
                setModoEscribano();
                return false;
            }

        });
    }

    @Override
    public void setDefaultSettings() {
        super.setDefaultSettings();
        this.modo_escribano = DEFAULT_MODO_ESCRIBANO;
    }

    @Override
    public void onBackPressed() {
        // Si click enrere preguntem si vol sortir
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).create().show();
    }

    private void setModoEscribano() {
        this.modo_escribano = true;
        // Canviem el nom per defecte dels jugadors
        this.players = new ArrayList<>(Arrays.asList(ESCRIBANOS));
    }
}
