package com.cheruku.android.minesweeperhapticmultiplayer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private boolean mGridLayoutCompleted = false;
    private GameState mGameState = new GameState(GameState.GameStateEnum.STOP);

    GridLayout gridLayout = null;
    TextView txtTimeTaken = null;
    TextView txtScore = null;
    ImageView[][] imageList = new ImageView[8][8];
    ArrayList<CellLocation> myMines;
    ArrayList<CellLocation> opponentMines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        txtTimeTaken = (TextView) findViewById(R.id.txtTimeTaken);
        txtScore = (TextView) findViewById(R.id.txtScore);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void setGameState(View view) {
        ImageView imgGameState = (ImageView) findViewById(R.id.btnSmileState);

        switch (mGameState.getGameState())
        {
            case STOP:
                imgGameState.setImageResource(R.drawable.smiley2);
                myMines = new ArrayList<CellLocation>();
                if (mGridLayoutCompleted)
                    coverScreenWithGrass();
                else
                    initialSetupOfScreen();
                mGameState.setGameState(GameState.GameStateEnum.SETUP);
                break;
            case SETUP:
                imgGameState.setImageResource(R.drawable.smiley1);
                coverScreenWithGrass();
                mGameState.setGameState(GameState.GameStateEnum.START);
                break;
            case START:
                imgGameState.setImageResource(R.drawable.smiley3);
                mGameState.setGameState(GameState.GameStateEnum.PAUSE);
                break;
            case PAUSE:
                imgGameState.setImageResource(R.drawable.smiley3);
                mGameState.setGameState(GameState.GameStateEnum.STOP);
                break;
        }
    }

    private void initialSetupOfScreen() {
        int imageWidth = gridLayout.getWidth()/gridLayout.getRowCount();
        int imageHeight = gridLayout.getHeight()/gridLayout.getColumnCount();

        txtTimeTaken.setText("W: " + imageWidth);
        txtScore.setText("H: " + imageHeight);
        for (int i = 0; i < gridLayout.getRowCount(); i++){
            for (int j=0; j<gridLayout.getColumnCount(); j++){
                imageList[i][j]= new ImageView(this);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(imageWidth, imageHeight);
                imageList[i][j].setLayoutParams(lp);
                imageList[i][j].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageList[i][j].setImageResource(R.drawable.grass);
                imageList[i][j].setFocusable(true);
                imageList[i][j].setClickable(true);
                imageList[i][j].setTag(new CellLocation(i,j));
                MineBlockOnClickListener mineBlockOnClickListener = new MineBlockOnClickListener(myMines, mGameState);
                imageList[i][j].setOnClickListener(mineBlockOnClickListener);
                gridLayout.addView(imageList[i][j]);
            }
        }
    }

    private void coverScreenWithGrass()
    {
        for (int i = 0; i < gridLayout.getRowCount(); i++)
        {
            for (int j=0; j<gridLayout.getColumnCount(); j++)
            {
                imageList[i][j].setImageResource(R.drawable.grass);
            }
        }
    }

    public class CellLocation
    {
        private int mRow = 0;
        private int mCol = 0;
        private boolean mMinePresent = false;
        public CellLocation (int row, int col)
        {
            this.mRow = row;
            this.mCol = col;
        }

        public int getRow()
        {
            return mRow;
        }

        public int getColumn()
        {
            return mCol;
        }

        public boolean getMinePresent()
        {
            return mMinePresent;
        }

        public void setMinePresent(boolean minePresent)
        {
            this.mMinePresent = minePresent;
        }
    }

}
