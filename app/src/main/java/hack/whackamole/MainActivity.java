package hack.whackamole;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.*;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private Grid hackGrid;
    private ScoreBoard scoreBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        scoreBoard = new ScoreBoard(this,container);
        hackGrid= new Grid(this,container,scoreBoard);
        hackGrid.buildTiles(screenWidth);
        try{
            hackGrid.playGame();
        }
        catch(Exception e){}
    }
}
