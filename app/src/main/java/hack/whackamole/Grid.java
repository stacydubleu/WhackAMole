package hack.whackamole;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.os.CountDownTimer;
import java.util.Random;
import java.util.ArrayList;

public class Grid {
    Context context;
    RelativeLayout container;
    RelativeLayout grid;
    ScoreBoard scoreBoard;

    private static boolean finished=false;
    private static final int COLS = 3;
    private static final int ROWS = 4;
    private int squareSize;
    private int player=1;
    private ArrayList<ArrayList<Tile>> tiles;

    public Grid(Context context, RelativeLayout container, ScoreBoard scoreBoard) {
        this.context = context;
        this.container = container;
        this.scoreBoard = scoreBoard;
        this.grid = (RelativeLayout) container.findViewById(R.id.grid);
        this.tiles = new ArrayList<ArrayList<Tile>>();
    }

    public void buildTiles(int screenWidth) {
        squareSize = getSquareSize(screenWidth);

        for (int c = 0; c < 3; c++) {
            ArrayList<Tile> rows = new ArrayList<Tile>();
            for (int r = 0; r < 4; r++) {
                int xCo = getXCoord(c);
                int yCo = getYCoord(c);
                rows.add(new Tile(context, c, r));
                setOnTouch(c, r);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(squareSize, squareSize);
                params.leftMargin = xCo;
                params.topMargin = yCo;
                grid.addView(tiles.get(c).get(r), params);
            }
            tiles.add(rows);
        }
    }

    public void playGame() throws Exception {
        CountDownTimer time = new CountDownTimer(30000,1000) {
            public void onTick (long millisUntilFinished){
               EditText text = (EditText) container.findViewById(R.id.timer);
               text.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                EditText text = (EditText) container.findViewById(R.id.timer);
                text.setText("Time's up!");
                finished=true;
            }
        };

        //FIX
        EditText edit = (EditText) container.findViewById(R.id.timer);
        edit.setText("Ready?");
        wait(2000);
        edit.setText("Go!");
        time.start();
        while (finished==false){
            Random rand = new Random();
            Random rand2= new Random();
            int row=rand.nextInt(((3-0)+1)+0);
            int col=rand2.nextInt(((2-0)+1)+0);
            tiles.get(row).get(col).updateTile();
        }
    }
    private void setOnTouch(final int x, final int y){
        tiles.get(x).get(y).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("state1", "xCoord: " + x + " yCoord: " + y);
                if (tiles.get(x).get(y).getTarget()) {
                    int candidate=tiles.get(x).get(y).getCandidate();
                    tiles.get(x).get(y).isHit();
                    scoreBoard.updateScore(player, candidate);
                }
            }
        });
    }

    private int getSquareSize(final int width) {
        return width / 12;
    }

    private int getXCoord(final int x) {
        return squareSize * x;
    }

    private int getYCoord(final int y) {
        return squareSize * y;
    }


}

