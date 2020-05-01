package com.sagarannaldas.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 : redheart, 1 : blackheart, 2 : empty;

    int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int activePlayer = 0;

    boolean gameActive = true;

    boolean boardIsEmpty = true;
    int count = 0;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        Log.i("info", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameActive) {

            count++;

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.redheart);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.blackheart);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winningPosition : winningPosition) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    String message = "";

                    gameActive = false;

                    if (activePlayer == 1) {
                        message = "Red Heart has Won!";
                    } else {
                        message = "Black Heart has Won!";
                    }

//                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.textView);

                    winnerTextView.setText(message);

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }

            }
        }

        if(count == gameState.length){

            String  message = "Game is Tie. Press Button to Play Again!";

            Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

            TextView winnerTextView = (TextView) findViewById(R.id.textView);

            winnerTextView.setText(message);

            playAgainButton.setVisibility(View.VISIBLE);

            winnerTextView.setVisibility(View.VISIBLE);


        }


    }

    public void playAgain(View view){

        count = 0;

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.textView);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);

//        GridLayout gridLayout =  findViewById(R.id.gridLayout);

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i ++){

            ImageView blocks = (ImageView) gridLayout.getChildAt(i);

            blocks.setImageDrawable(null);

        }

        for(int i = 0; i < gameState.length; i ++) {

            gameState[i] = 2;
        }

        activePlayer = 0;

        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
