package com.example.jayesh.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer = 0;//0 is for yellow $ 1 for red
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};//2 means unplayyed
    int[][] winningpositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6},{0,3,6},{1,4,7},{2,5,8}};
    boolean gameIsActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2 && gameIsActive) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(500);

            for (int[] winningpostion : winningpositions) {
                if (gamestate[winningpostion[0]] != 2
                        && gamestate[winningpostion[0]] == gamestate[winningpostion[1]]
                        && gamestate[winningpostion[1]] == gamestate[winningpostion[2]]
                        ) {
                    //Someone has won
                    gameIsActive = false;
                    String winner = "Player 2";
                    if (gamestate[winningpostion[0]] == 0)
                        winner = "Player 1";

                    TextView winnermessage = (TextView) findViewById(R.id.winnermessage);
                    winnermessage.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playagain);

                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsOver = true;
                    for (int counterstate : gamestate)
                        if (counterstate == 2) {
                            gameIsOver = false;
                        }
                    if (gameIsOver) {
                        TextView winnermessage = (TextView) findViewById(R.id.winnermessage);
                        winnermessage.setText("Its a draw!Both played well give another try");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playagain);

                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playAgain(View view){
        gameIsActive=true;
        LinearLayout layout=(LinearLayout) findViewById(R.id.playagain);

        layout.setVisibility(View.INVISIBLE);
        activeplayer=0;

        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        GridLayout gridLayout =(GridLayout) findViewById(R.id.Grid);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}