package hack.whackamole;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.ImageView;
import java.util.Random;
import hack.whackamole.R;
import java.util.*;

public class Tile extends ImageView {

    private int xCoord;
    private int yCoord;
    private int Candidate = 0; //0 if available empty tile, any other number otherwise
    private int randomNum;
    private boolean Target = false;
    private static Timer timer = new Timer();

    public Tile(Context context, int x, int y) {
        super(context);
        xCoord = x;
        yCoord = y;
        setImageResource(R.drawable.tile);
    }

    public void updateTile() {
        Random rand = new Random();
        randomNum = rand.nextInt(4);
//        if (randomNum == 0) {
//            setImageResource(R.drawable.tile);
//            Target = false;
//        } else {
//            Target = true;
            if (randomNum == 0) {
                setImageResource(R.drawable.bernie_sanders100x100);
            } else
            if (randomNum == 1) {
                setImageResource(R.drawable.donald_trump100x100);
            } else
            if (randomNum == 2) {
                setImageResource(R.drawable.hilary_clinton100x100);
            } else {
                setImageResource(R.drawable.ted_cruz100x100);
//            }
        }
    }

    public void isHit(){
        setImageResource(R.drawable.tile);
        Target=false;
        randomNum=0;
    }

    public int getxCoord(){
        return xCoord;
    }
    public int getyCoord(){
        return yCoord;
    }

    public boolean getTarget(){
        return Target;
    }
    public void setTarget(boolean b){
        Target = b;
    }

    public int getCandidate(){
        return randomNum;
    }

}
