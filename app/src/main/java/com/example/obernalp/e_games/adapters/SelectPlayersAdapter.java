package com.example.obernalp.e_games.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.obernalp.e_games.R;

import java.util.List;

/**
 * Created by Uri on 21/09/2016.
 */
public class SelectPlayersAdapter extends RecyclerView.Adapter<SelectPlayersAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> players;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView player_name;
        public RelativeLayout viewBackground, viewForeground;

        private MyViewHolder(View view) {
            super(view);

            this.player_name = view.findViewById(R.id.player_name);
            this.viewBackground = view.findViewById(R.id.view_background);
            this.viewForeground = view.findViewById(R.id.view_foreground);
        }
    }

    public SelectPlayersAdapter(Context mContext, List<String> players) {
        this.mContext = mContext;
        this.players = players;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_select_player, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String player =  players.get(position);

        holder.player_name.setText(player);

    }


    @Override
    public int getItemCount() {
        if (players == null){
            return 0;
        }else {
            return players.size();
        }
    }
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }
    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SelectPlayersAdapter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SelectPlayersAdapter.ClickListener clickListener) {
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

    public void removeItem(int position) {
        players.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(String name, int position) {
        players.add(position, name);
        // notify item added by position
        notifyItemInserted(position);
    }

}