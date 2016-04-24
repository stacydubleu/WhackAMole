package hack.whackamole;

import android.widget.RelativeLayout;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScoreBoard {
    Context context;
    RelativeLayout container;
    private double score=0;
    private TextView totalscore;

    public ScoreBoard(Context context, RelativeLayout container) {
        this.context=context;
        this.container=container;

        totalscore= (TextView)container.findViewById(R.id.score2);
    }

    public void updateScore(int player, int candidate){ //will use candidate according to which party chosen etc
        if (candidate==2||candidate==3||candidate==4) {
            score += 100;
        }
        else {
            score -=100;
        }
    }
}

