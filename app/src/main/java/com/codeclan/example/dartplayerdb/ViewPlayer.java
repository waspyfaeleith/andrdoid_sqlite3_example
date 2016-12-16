package com.codeclan.example.dartplayerdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by sandy on 05/09/2016.
 */
public class ViewPlayer extends AppCompatActivity {
    TextView nameEditText;
    TextView nicknameText;
    TextView rankingText;
    Button editButton;
    Button deleteButton;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //final DBHandler db = new DBHandler(this);
        final DBHandler db = ((MainApplication)getApplication()).db;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_player);

        nameEditText = (TextView)findViewById(R.id.player_name);
        nicknameText = (TextView)findViewById(R.id.player_nickname);
        rankingText = (TextView)findViewById(R.id.player_ranking);
        editButton = (Button)findViewById(R.id.button_edit);
        deleteButton = (Button)findViewById(R.id.button_delete);
        backButton = (Button)findViewById(R.id.button_back);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final int id = extras.getInt("id");
        final String name = extras.getString("name");
        final String nickname = extras.getString("nickname");
        final int ranking = extras.getInt("ranking");

        nameEditText.setText(name);
        nicknameText.setText(nickname);
        rankingText.setText(Integer.toString(ranking));


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ViewPlayer: ", "editing player with id " + id);
                Intent intent = new Intent(ViewPlayer.this, EditPlayer.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("nickname", nickname);
                intent.putExtra("ranking", ranking);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ViewPlayer: ", "deleting player with id " + id);
                db.deletePlayer(id);
                backToMainActivity();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });
    }

    private void backToMainActivity() {
        Intent intent = new Intent(ViewPlayer.this, MainActivity.class);
        startActivity(intent);
    }
}
