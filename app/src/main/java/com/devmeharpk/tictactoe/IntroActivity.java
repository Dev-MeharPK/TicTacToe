package com.devmeharpk.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Find views by ID
        TextView tvGameTitle = findViewById(R.id.tvGameTitle);
        Button btnStartGame = findViewById(R.id.btnStartGame);
        Button btnHelp = findViewById(R.id.btnHelp);
        Button btnAbout = findViewById(R.id.btnAbout);
        Button btnExit = findViewById(R.id.btnExit);

        // Load fade-in animation
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        tvGameTitle.startAnimation(fadeIn);
        btnStartGame.startAnimation(fadeIn);
        btnHelp.startAnimation(fadeIn);
        btnAbout.startAnimation(fadeIn);
        btnExit.startAnimation(fadeIn);

        // Start Game button
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fadeOut = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.fade_out);
                findViewById(android.R.id.content).startAnimation(fadeOut);

                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }
        });

        // Help button
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });

        // About button
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        // Exit button with confirmation dialog
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitDialog();
            }
        });
    }

    // Handle back button press for exit confirmation
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showExitDialog();  // Only show the exit dialog, do NOT call super.onBackPressed()
    }

    // Method to show exit confirmation dialog
    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("❌ Exit Game")
                .setMessage("Are you sure you want to quit?")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // Close the app
                    }
                })
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();

        // Customizing the dialog
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.parseColor("#303F9F"))); // Dark Blue Background
            dialog.getWindow().setGravity(Gravity.CENTER); // Centered Dialog
        }

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(android.graphics.Color.RED); // Red exit button
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(android.graphics.Color.WHITE); // White cancel button
            }
        });

        dialog.show();
    }
}
