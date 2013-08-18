package com.cheruku.android.minesweeperhapticmultiplayer;

import android.view.View;

import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by cherukumilli on 8/17/13.
 */
public class MineBlockOnClickListener implements View.OnClickListener {
    private ArrayList<MainActivity.CellLocation> myMines;
    //private enum GameState {STOP, SETUP, START, PAUSE};
    private GameState mGameState;

    public MineBlockOnClickListener(ArrayList<MainActivity.CellLocation> myMines, GameState gameState)
    {
        this.myMines = myMines;
        this.mGameState = gameState;
    }

    @Override
    public void onClick(View v) {
        ImageView img = (ImageView)v;
        MainActivity.CellLocation cellLocation = (MainActivity.CellLocation)v.getTag();
        switch (mGameState.getGameState())
        {
            case SETUP:
                img.setImageResource(R.drawable.mines);
                cellLocation.setMinePresent(true);
                myMines.add(cellLocation);
                break;
            case START:
                if (cellLocation.getMinePresent())
                {
                    img.setImageResource(R.drawable.mines);
                }
                else
                {
                    img.setImageResource(R.drawable.smiley1);
                }
                break;
            case PAUSE:
                break;
            case STOP:
                break;
        }
    }
}
