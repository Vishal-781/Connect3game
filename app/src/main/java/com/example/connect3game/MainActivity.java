package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //    o is for yellow,1 is for red,2 is for empty;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int activePlayer = 0;
    int[][] winningPostions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        if (gameActive) {

            int tappedCounter = Integer.parseInt(counter.getTag().toString());
            if (gameState[tappedCounter] == 2) {
                counter.setTranslationY(-1500);
                gameState[tappedCounter] = activePlayer;


                Log.i("Tag", counter.getTag().toString());
                if (activePlayer == 0) {
                    activePlayer = 1;
                    counter.setImageResource(R.drawable.circle);
                    counter.animate().translationYBy(1500).rotation(360).setDuration(1000);
                } else {
                    activePlayer = 0;
                    counter.setImageResource(R.drawable.cross);
                    counter.animate().translationYBy(1500).rotation(360).setDuration(1000);

                }
                boolean flag = false;
                int k, res = 0;
                for (int i = 0; i < 8; i++) {
                    k = 0;
                    res = gameState[winningPostions[i][0]];
                    if (res != 2) {
                        for (int j = 0; j < 3; j++) {
                            if (gameState[winningPostions[i][j]] == res)
                                k++;
                        }
                        if (k == 3) {
                            flag = true;
                            break;
                        }
                    }
                }

                if (flag) {
                    String message;
                    gameActive = false;
                    if (res == 0)
                        message = "Circle Wins!";
                    else
                        message = "Cross Wins !";

                    Button playagainbutton = (Button) findViewById(R.id.button);
                    TextView winnerTextview = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextview.setText(message);
                    winnerTextview.setVisibility((int) 1);
                    playagainbutton.setVisibility((int) 1);


//                   Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                } else {
                    boolean fuck = true;
                    for (int i = 0; i < 9; i++) {
                        if (gameState[i] == 2)
                            fuck = false;
                    }
                    if (fuck) {
                        Button playagainbutton = (Button) findViewById(R.id.button);
                        TextView winnerTextview = (TextView) findViewById(R.id.winnerTextView);
                        winnerTextview.setText("No one wins !");
                        winnerTextview.setVisibility((int) 1);
                        playagainbutton.setVisibility((int) 1);

//                        Toast.makeText(this, "No one wins !", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

    }

    public void playAgain(View view)
    {
        Log.i("Info","Button Pressed");
        Button playagainbutton = (Button) findViewById(R.id.button);
        TextView winnerTextview = (TextView) findViewById(R.id.winnerTextView);
        winnerTextview.setVisibility(view.INVISIBLE);
        playagainbutton.setVisibility(view.INVISIBLE);
        GridLayout gridLayout=(androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter =(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);


        }
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }

        activePlayer = 0;
        gameActive=true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}