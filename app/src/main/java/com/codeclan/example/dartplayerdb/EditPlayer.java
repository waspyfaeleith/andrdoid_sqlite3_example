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
public class EditPlayer extends AppCompatActivity{
    EditText nameEditText;
    EditText nicknameEditText;
    EditText rankingEditText;
    Button saveButton;
    Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final DBHandler db = ((MainApplication)getApplication()).db;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_player);

        nameEditText = (EditText)findViewById(R.id.player_name);
        nicknameEditText = (EditText)findViewById(R.id.player_nickname);
        rankingEditText = (EditText)findViewById(R.id.player_ranking);
        saveButton = (Button)findViewById(R.id.button_save);
        cancelButton = (Button)findViewById(R.id.button_cancel);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String name = extras.getString("name");
        final String nickname = extras.getString("nickname");
        final int ranking = extras.getInt("ranking");

        nameEditText.setText(name);
        nicknameEditText.setText(nickname);
        rankingEditText.setText(Integer.toString(ranking));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String nickname = nicknameEditText.getText().toString();
                int ranking = Integer.parseInt(rankingEditText.getText().toString());
                DartPlayer playerToUpdate = new DartPlayer(id, name, nickname, ranking);
                db.updatePlayer(playerToUpdate);
                backToPlayerView(id, name, nickname, ranking);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToPlayerView(id, name, nickname, ranking);
            }
        });
    }

    private void backToPlayerView(int id, String name, String nickname, int ranking) {
        Intent intent = new Intent(EditPlayer.this, ViewPlayer.class);
        intent.putExtra("id", id);
        intent.putExtra("name", name);
        intent.putExtra("nickname", nickname);
        intent.putExtra("ranking", ranking);

        startActivity(intent);
    }
}
