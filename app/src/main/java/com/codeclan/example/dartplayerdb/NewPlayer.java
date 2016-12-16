package com.codeclan.example.dartplayerdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sandy on 05/09/2016.
 */
public class NewPlayer extends AppCompatActivity {
    EditText nameEditText;
    EditText nicknameEditText;
    EditText rankingEditText;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DBHandler db = ((MainApplication)getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_player);

        nameEditText = (EditText)findViewById(R.id.player_name);
        nicknameEditText = (EditText)findViewById(R.id.player_nickname);
        rankingEditText = (EditText)findViewById(R.id.player_ranking);
        saveButton = (Button)findViewById(R.id.button_add);
        cancelButton = (Button)findViewById(R.id.button_cancel);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String nickname = nicknameEditText.getText().toString();
                int ranking = Integer.parseInt(rankingEditText.getText().toString());

                DartPlayer newPlayer = new DartPlayer(name, nickname, ranking);
                db.addPlayer(newPlayer);
                backToMainView();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainView();
            }
        });
    }

    private void backToMainView() {
        Intent intent = new Intent(NewPlayer.this, MainActivity.class);
        startActivity(intent);
    }
}
