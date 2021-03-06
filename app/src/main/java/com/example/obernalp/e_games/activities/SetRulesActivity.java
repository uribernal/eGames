package com.example.obernalp.e_games.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.obernalp.e_games.R;

import java.util.ArrayList;
import java.util.Objects;

public class SetRulesActivity extends BaseActivity {


    private TextView tv_num_players;
    private TextView tv_num_infiltrados;
    private TextView tv_num_database;

    private Button start_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_rules);


        // Posar la fletxa al nav bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Recuperem els paràmetres del joc
        //setSettings();

        // Botons per editar els paràmetres
        final LinearLayout rl_players = findViewById(R.id.rules_rl_players);
        final LinearLayout rl_infiltrados = findViewById(R.id.rules_rl_infiltrados);
        final LinearLayout rl_database = findViewById(R.id.rules_rl_database);

        // Text per veure els paràmetres
        tv_num_players = findViewById(R.id.rules_tv_players);
        tv_num_infiltrados = findViewById(R.id.rules_tv_infiltrados);
        tv_num_database = findViewById(R.id.rules_tv_database);

        // Boto per començar a jugar
        start_game = findViewById(R.id.rules_btn_start_game);

        // Escriure els paràmetres
        setTextViewTexts();

        rl_players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity2SelectPlayers();
            }
        });

        rl_infiltrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNumberInfiltradosSetter();
            }
        });

        rl_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showSnackBarMessage("Opción no disponible", start_game);
                showDatabaseSetter();

            }
        });

        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeActivity2Game();
            }
        });
    }

    private void showNumberInfiltradosSetter() {
        final RelativeLayout relativeLayout = new RelativeLayout(SetRulesActivity.this);
        final NumberPicker aNumberPicker = new NumberPicker(SetRulesActivity.this);
        aNumberPicker.setMaxValue(players.size());
        aNumberPicker.setMinValue(0);
        ArrayList<String> list = new ArrayList<>();
        list.add("Random");
        for (int i = 1; i <= players.size(); i++) {
            list.add(i + "");
        }
        aNumberPicker.setDisplayedValues(list.toArray(new String[list.size()]));
        aNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        // Valor seleccionat per defecte
        aNumberPicker.setValue(num_infiltrados);


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        relativeLayout.setLayoutParams(params);
        relativeLayout.addView(aNumberPicker, numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SetRulesActivity.this);
        alertDialogBuilder.setTitle("Select the number");
        alertDialogBuilder.setView(relativeLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                num_infiltrados = aNumberPicker.getValue();
                                setNumberInfiltradosText();

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void showDatabaseSetter() {
        final RelativeLayout relativeLayout = new RelativeLayout(SetRulesActivity.this);
        final NumberPicker aNumberPicker = new NumberPicker(SetRulesActivity.this);

        ArrayList<String> list = databaseManager.getListOfDatabase();
        list.add(0, "All");
        aNumberPicker.setDisplayedValues(list.toArray(new String[list.size()]));
        aNumberPicker.setMaxValue(list.size() - 1);
        aNumberPicker.setMinValue(0);
        aNumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        // Valor seleccionat per defecte
        aNumberPicker.setValue(database);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
        RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        relativeLayout.setLayoutParams(params);
        relativeLayout.addView(aNumberPicker, numPicerParams);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SetRulesActivity.this);
        alertDialogBuilder.setTitle("Select the set");
        alertDialogBuilder.setView(relativeLayout);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                database = aNumberPicker.getValue();
                                setDatabaseText();

                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                dialog.cancel();
                            }
                        })
                .setNeutralButton("new Database",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                AlertDialog.Builder builderSingle = new AlertDialog.Builder(SetRulesActivity.this);
                                builderSingle.setIcon(R.drawable.ic_assassin);
                                builderSingle.setTitle("Enter name:");


                                final EditText edittext = new EditText(SetRulesActivity.this);
                                edittext.setHint("YOUR_DATABASE");

                                builderSingle.setView(edittext).setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        //showSnackBarMessage("Opción no disponible", start_game);
                                        String a = edittext.getText().toString();
                                        if (!a.equals("")) {
                                            changeActivity2SetDatabase(edittext.getText().toString());
                                        }else {
                                            showSnackBarMessage("Please enter a name", start_game);
                                        }
                                    }
                                }).show();

                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Si tornem a la pantalla principal guardem el jugadors i els parametres
        if (requestCode == CODE_ACTIVITY) {
            if (resultCode == RESULT_CHANGES) {
                this.players = data.getStringArrayListExtra(PLAYERS_CODE);
                this.num_infiltrados = data.getIntExtra(NUM_INFILTRADOS_CODE, DEFAULT_NUMBER_INFILTRADOS);
                this.database = data.getIntExtra(DATABASE_CODE, DEFAULT_DATABASE);
                setTextViewTexts();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setTextViewTexts() {
        setNumberInfiltradosText();
        setPlayersText();
        setDatabaseText();
    }

    private void setNumberInfiltradosText() {
        if (num_infiltrados == 0) {
            tv_num_infiltrados.setText(R.string.rules_random_infiltrados);
        } else if (num_infiltrados == 1) {
            tv_num_infiltrados.setText(R.string.rules_infiltrados);
        } else {
            tv_num_infiltrados.setText(String.format(getResources().getString(R.string.rules_num_infiltrados), num_infiltrados));
        }
    }

    private void setPlayersText() {
        if (players.size() == 1) {
            tv_num_players.setText(R.string.rules_1_player);
        } else {
            tv_num_players.setText(String.format(getResources().getString(R.string.rules_num_players), players.size()));
        }
    }

    private void setDatabaseText() {
        if (database == 0) {
            tv_num_database.setText(R.string.rules_random);
        } else {
            ArrayList<String> dbNames = databaseManager.getListOfDatabase();
            tv_num_database.setText(dbNames.get(database - 1));
        }
    }

}
