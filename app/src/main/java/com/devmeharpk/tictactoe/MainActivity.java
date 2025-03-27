package com.devmeharpk.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = red
    int activePlayer = 0;
    boolean gameIsActive = true;

    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // Winning positions
    int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}  // Diagonals
    };

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            // Mark the move
            gameState[tappedCounter] = activePlayer;

            // Animation
            counter.setTranslationY(-1000f);
            counter.setImageResource(activePlayer == 0 ? R.drawable.yellow : R.drawable.red);
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            // Switch player
            activePlayer = (activePlayer == 0) ? 1 : 0;

            // Check for winner
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // Someone has won!
                    gameIsActive = false;
                    String winner = (gameState[winningPosition[0]] == 0) ? "Yellow" : "Red";

                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                    return; // Stop checking further
                }
            }

            // Check for a draw (if no moves left)
            boolean gameIsOver = true;
            for (int counterState : gameState) {
                if (counterState == 2) {
                    gameIsOver = false;
                    break;
                }
            }

            if (gameIsOver) {
                TextView winnerMessage = findViewById(R.id.winnerMessage);
                winnerMessage.setText("It's a draw!");

                LinearLayout layout = findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.VISIBLE);
            }
        }
    }

    public void playAgain(View view) {
        gameIsActive = true;

        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        // Reset game state
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        // Reset the board
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
