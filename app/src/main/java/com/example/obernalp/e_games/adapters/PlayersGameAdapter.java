package com.example.obernalp.e_games.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.obernalp.e_games.R;

import java.util.List;

/**
 * Created by Uri on 21/09/2016.
 */
public class PlayersGameAdapter extends RecyclerView.Adapter<PlayersGameAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> players;
    private int startingPlayer;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView player_name;
        public TextView startingPlayer;
        public CardView viewForeground;

        public MyViewHolder(View view) {
            super(view);

            player_name = view.findViewById(R.id.game_player_name);
            startingPlayer = view.findViewById(R.id.game_starting_player);
            viewForeground = view.findViewById(R.id.game_cv_container);
        }

    }

    public PlayersGameAdapter(Context mContext, List<String> players, int startingPlayer) {
        this.mContext = mContext;
        this.players = players;
        this.startingPlayer = startingPlayer;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game_player, parent, false);


        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        String player = players.get(position);

        holder.player_name.setText(player);

        if (position != startingPlayer) {
            holder.startingPlayer.setBackgroundResource(0);

        }
        holder.viewForeground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //The selected card is set to colorSelected
                holder.viewForeground.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.blockedPlayerColor));
                holder.viewForeground.setCardElevation(0);

            }
        });
    }


    @Override
    public int getItemCount() {
        if (players == null) {
            return 0;
        } else {
            return players.size();
        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private PlayersGameAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final PlayersGameAdapter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}