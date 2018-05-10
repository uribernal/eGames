package com.example.obernalp.e_games.activities;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myDialog = new Dialog(this);

        infiltrado_controler = new Infiltrado(players, num_infiltrados, database);

        roles = infiltrado_controler.getRoles();

        RecyclerView recyclerView = findViewById(R.id.game_rv_container);

        final PlayersGameAdapter adapter = new PlayersGameAdapter(this, players, infiltrado_controler.getStartingPlayer());
        RecyclerView.LayoutManager mLayoutManager;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //portrait
            mLayoutManager = new GridLayoutManager(getApplicationContext(), 1); //num columnes
        } else {
            //landscape
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
}
