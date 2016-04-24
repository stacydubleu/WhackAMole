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

    private static boolean start = false;
    private static boolean finished=false;
    private static final int COLS = 4;
    private static final int ROWS = 6;
    private int squareSize;
    private int player=1;
    private Random rand = new Random();
    private ArrayList<ArrayList<Tile>> tiles;

    int remainingMoles;

    public Grid(Context context, RelativeLayout container, ScoreBoard scoreBoard) {
        this.context = context;
        this.container = container;
        this.scoreBoard = scoreBoard;
        this.grid = (RelativeLayout) container.findViewById(R.id.grid);
        this.tiles = new ArrayList<ArrayList<Tile>>();
    }

    public void buildTiles(int screenWidth) {
        squareSize = getSquareSize(screenWidth);

        for (int c = 0; c < COLS; c++) {
            tiles.add(new ArrayList<Tile>());
            for (int r = 0; r < ROWS; r++) {
                int xCo = getXCoord(c);
                int yCo = getYCoord(r);
                tiles.get(c).add(new Tile(context,c,r));
                setOnTouch(c, r);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(squareSize, squareSize);
                params.leftMargin = xCo;
                params.topMargin = yCo;
                grid.addView(tiles.get(c).get(r), params);
            }
        }
        Log.d("test",tiles.size()+"");
        Log.d("test",tiles.get(0).size()+"");
    }

    public void placeMoles(){
        int numOfMoles = rand.nextInt(9)+3;
        remainingMoles = numOfMoles;
        for (int i = 0; i < numOfMoles; i++) {
            int x = rand.nextInt(COLS);
            int y = rand.nextInt(ROWS);
            if(tiles.get(x).get(y).getTarget()){
                i--;
                continue;
            }
            tiles.get(x).get(y).setTarget(true);
            tiles.get(x).get(y).updateTile();
        }
    }

    public void playGame() throws Exception {
        CountDownTimer time = new CountDownTimer(33000,1000) {

            public void onTick (long millisUntilFinished){
                EditText edit = (EditText) container.findViewById(R.id.timer);
                if(millisUntilFinished > 31000){
                   edit.setText("Ready?");
               } else if( millisUntilFinished > 30000){
                   edit.setText("Go!");
                   start = true;
                   placeMoles();
               } else {
                    EditText text = (EditText) container.findViewById(R.id.timer);
                    text.setText("Seconds Remaining: " + millisUntilFinished / 1000);
                }
            }

            public void onFinish() {
                EditText text = (EditText) container.findViewById(R.id.timer);
                text.setText("Time's up!");
                finished=true;
            }
        };
        //FIX
        time.start();

    }

    private void setOnTouch(final int x, final int y){
        tiles.get(x).get(y).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("state1", "xCoord: " + x + " yCoord: " + y);
                if(finished || !start){
                    return;
                }
                if (tiles.get(x).get(y).getTarget()) {
                    int candidate=tiles.get(x).get(y).getCandidate();
                    tiles.get(x).get(y).isHit();
                    scoreBoard.updateScore(player, candidate);
                    remainingMoles--;
                }
                if(remainingMoles == 0) {
                    Log.d("test","here");
                    placeMoles();
                };
            }
        });
    }

    private int getSquareSize(final int width) {
        return width / COLS;
    }

    private int getXCoord(final int x) {
        return squareSize * x;
    }

    private int getYCoord(final int y) {
        return squareSize * y;
    }


}

