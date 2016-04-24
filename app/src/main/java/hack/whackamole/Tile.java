package hack.whackamole;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.ImageView;
import java.util.Random;
import hack.whackamole.R;
import java.util.*;

public class Tile extends ImageView {

    private String candidate;
    private int xCoord;
    private int yCoord;
    private boolean Target = false;

    public Tile(Context context, int x, int y) {
        super(context);
        xCoord = x;
        yCoord = y;
        setImageResource(R.drawable.tile);
    }

    public void updateTile(String candidate) {

        switch (candidate){
            case "Bernie Sanders":
                setImageResource(R.drawable.bernie_sanders100x100);
                this.candidate = candidate;
                break;
            case "Hilary Clinton":
                setImageResource(R.drawable.hilary_clinton100x100);
                this.candidate = candidate;
                break;
            case "Donald Trump":
                setImageResource(R.drawable.donald_trump100x100);
                this.candidate = candidate;
                break;
            case "Ted Cruz":
                setImageResource(R.drawable.ted_cruz100x100);
                this.candidate = candidate;
                break;
        }


    }

    public void isHit(){
        setImageResource(R.drawable.tile);
        Target=false;
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

    public String getCandidate(){
        return candidate;
    }

}
