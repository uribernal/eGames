package com.example.obernalp.e_games.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.obernalp.e_games.R;
import com.example.obernalp.e_games.adapters.RecyclerItemTouchHelper;
import com.example.obernalp.e_games.adapters.SelectPlayersAdapter;

import java.util.Objects;

public class SelectPlayersActivity extends BaseActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private SelectPlayersAdapter adapter;
    private RelativeLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_players);
        // Posar la fletxa al nav bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        coordinatorLayout = findViewById(R.id.players_layout);

        final FloatingActionButton addPlayerBtn = findViewById(R.id.players_floatingButton);
        final RecyclerView recyclerView = findViewById(R.id.players_rv_list);

        adapter = new SelectPlayersAdapter(this, players);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);

        // Click on listener
        recyclerView.addOnItemTouchListener(new SelectPlayersAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new SelectPlayersAdapter.ClickListener() {
            @Override
            public void onClick(View view, final int position) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(SelectPlayersActivity.this);
                builderSingle.setIcon(R.drawable.ic_assassin);
                builderSingle.setTitle("Enter name:");


                final EditText edittext = new EditText(SelectPlayersActivity.this);
                String name = players.get(position);
                edittext.setHint(name);

                builderSingle.setView(edittext);

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (edittext.getText().toString().equals("")) {
                            players.set(position, edittext.getHint().toString());

                        } else {
                            players.set(position, edittext.getText().toString());

                        }
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                builderSingle.show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        addPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(SelectPlayersActivity.this);
                builderSingle.setIcon(R.drawable.ic_assassin);
                builderSingle.setTitle("Enter name:");


                final EditText edittext = new EditText(SelectPlayersActivity.this);
                final String name = "Player " + (players.size() + 1);
                edittext.setHint(name);

                builderSingle.setView(edittext);

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (edittext.getText().toString().equals("")) {
                            players.add(name);
                        } else {
                            players.add(edittext.getText().toString());
                        }
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                builderSingle.show();
            }
        });
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof SelectPlayersAdapter.MyViewHolder) {
            if (players.size() > 4) {
                // get the removed item name to display it in snack bar
                String name = players.get(viewHolder.getAdapterPosition());

                // backup of removed item for undo purpose
                final String deletedItem = players.get(viewHolder.getAdapterPosition());
                final int deletedIndex = viewHolder.getAdapterPosition();

                // remove the item from recycler view
                adapter.removeItem(viewHolder.getAdapterPosition());

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, name + " se ha eliminado", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // undo is selected, restore the deleted item
                        adapter.restoreItem(deletedItem, deletedIndex);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            } else {
                //Swipe back
                adapter.notifyItemChanged(position);
                Toast.makeText(getApplicationContext(), "The minimum of players is 4!", Toast.LENGTH_SHORT).show();
            }
        }
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
                        finish();
                    }
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
