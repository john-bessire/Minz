package com.cheruku.android.minesweeperhapticmultiplayer;

/**
 * Created by cherukumilli on 8/17/13.
 */
public class GameState
{
    public enum GameStateEnum {STOP, SETUP, START, PAUSE};
    private GameStateEnum gameState = GameStateEnum.STOP;

    public GameState(GameStateEnum gameState)
    {
        this.gameState = gameState;
    }

    public GameStateEnum getGameState()
    {
        return gameState;
    }

    public void setGameState(GameStateEnum gameState)
    {
        if (gameState == GameStateEnum.STOP || gameState == GameStateEnum.SETUP || gameState == GameStateEnum.START || gameState == GameStateEnum.PAUSE)
            this.gameState = gameState;
    }
}
