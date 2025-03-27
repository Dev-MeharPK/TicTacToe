package com.devmeharpk.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0; // 0 = yellow, 1 = red
    boolean gameIsActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 2 means unplayed

    int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}  // Diagonals
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable action bar back button
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            counter.setImageResource(activePlayer == 0 ? R.drawable.yellow : R.drawable.red);
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            activePlayer = (activePlayer == 0) ? 1 : 0;

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    gameIsActive = false;
                    String winner = (gameState[winningPosition[0]] == 0) ? "Yellow" : "Red";

                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");

                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);

                    return;
                }
            }

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
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, IntroActivity.class);
        startActivity(intent);
        finish(); // Close the current activity
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(MainActivity.this, IntroActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
