package com.devmeharpk.tictactoe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Find the Back button
        Button btnBack = findViewById(R.id.btnBack);
        // Find the GitHub button
        Button btnGitHub = findViewById(R.id.btnGitHub);

        // Set click listener to close activity
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Closes AboutActivity and returns to the previous screen
            }
        });

        // Set click listener for GitHub button
        btnGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the URL with your actual GitHub profile or repository link
                String githubUrl = "https://github.com/Dev-MeharPK";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl));
                startActivity(intent); // Opens the link in a web browser
            }
        });
    }
}
