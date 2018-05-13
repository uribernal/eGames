package com.example.obernalp.e_games.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.obernalp.e_games.R;
import com.example.obernalp.e_games.adapters.PlayersGameAdapter;
import com.example.obernalp.e_games.controllers.Infiltrado;

import java.util.ArrayList;

public class GameActivity extends BaseActivity {

    private ArrayList<String> roles;
    private Infiltrado infiltrado_controler;
    private Dialog myDialog;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myDialog = new Dialog(this);

        infiltrado_controler = new Infiltrado(players, num_infiltrados, database, databaseManager);

        roles = infiltrado_controler.getRoles();

        recyclerView = findViewById(R.id.game_rv_container);

        final PlayersGameAdapter adapter = new PlayersGameAdapter(this, players, infiltrado_controler.getStartingPlayer());
        RecyclerView.LayoutManager mLayoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLayoutManager = new GridLayoutManager(getApplicationContext(), 2); //num columnes
        } else {
            mLayoutManager = new GridLayoutManager(getApplicationContext(), 1); //num columnes
        }

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        // Click on listener
        recyclerView.addOnItemTouchListener(new PlayersGameAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new PlayersGameAdapter.ClickListener() {
            @Override
            public void onClick(final View view, final int position) {
                if (!infiltrado_controler.getBlockedPlayer(position)) {
                    TextView txtclose;
                    TextView name;
                    TextView role;
                    Button btnBlock;
                    ImageView image;
                    myDialog.setContentView(R.layout.popup_reveal_role);
                    txtclose = myDialog.findViewById(R.id.game_popup_tv_close);
                    name = myDialog.findViewById(R.id.game_popup_player_name);
                    role = myDialog.findViewById(R.id.game_popup_player_role);
                    btnBlock = myDialog.findViewById(R.id.game_popup_btn_block);
                    image = myDialog.findViewById(R.id.game_popup_iv_image);
                    name.setText(players.get(position));
                    role.setText(roles.get(position));
                    if (!infiltrado_controler.isInfiltrado(position)) {
                        image.setImageResource(R.drawable.ic_cesar);
                    }
                    btnBlock.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            infiltrado_controler.blockPlayer(position);
                            myDialog.dismiss();
                        }
                    });
                    txtclose.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            infiltrado_controler.blockPlayer(position);
                            myDialog.dismiss();
                        }
                    });
                    //myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    myDialog.show();
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.edit_players:
                Intent intent = new Intent(getApplicationContext(), SelectPlayersActivity.class);
                intent.putStringArrayListExtra(PLAYERS_CODE, players);
                startActivityForResult(intent, CODE_ACTIVITY);
                return true;
            case R.id.help:
                return true;
            case R.id.infi:
                showSnackBarMessage(num_infiltrados + " infiltrados. " + players.get(infiltrado_controler.getStartingPlayer()) + " starts", recyclerView);
                return true;
            case R.id.action_new_game:
                new AlertDialog.Builder(this)
                        .setTitle("New Game?")
                        .setMessage("Are you sure you want to finish this game?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                newGame();
                                if (num_infiltrados > 1)
                                    showSnackBarMessage(infiltrado_controler.getInfiltrados() + " eran infiltrados", recyclerView);
                                else
                                    showSnackBarMessage(infiltrado_controler.getInfiltrados() + " era infiltrado", recyclerView);
                            }
                        }).create().show();

                return true;
            case android.R.id.home:
                new AlertDialog.Builder(this)
                        .setTitle("Really Exit?")
                        .setMessage("Are you sure you want to exit?")
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent intent = getIntent();
                                intent.putStringArrayListExtra(PLAYERS_CODE, players);
                                setResult(RESULT_CHANGES, intent);
                                finish();
                            }
                        }).create().show();
                return true;
            case R.id.action_settings:
                // launch settings activity
                //startActivity(new Intent(GameActivity.this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newGame() {
        infiltrado_controler = new Infiltrado(players, num_infiltrados, database, databaseManager);

        roles = infiltrado_controler.getRoles();
        final PlayersGameAdapter adapter = new PlayersGameAdapter(this, players, infiltrado_controler.getStartingPlayer());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = getIntent();
                        intent.putStringArrayListExtra(PLAYERS_CODE, players);
                        setResult(RESULT_CHANGES, intent);
                        finish();
                    }
                }).create().show();
    }
}
