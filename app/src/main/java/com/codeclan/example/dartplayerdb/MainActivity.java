package com.codeclan.example.dartplayerdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by sandy on 05/09/2016.
 */
public class MainActivity extends AppCompatActivity {

    ListView mListView;
    Button mNewPlayerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView)findViewById(R.id.player_list);
        mNewPlayerButton = (Button)findViewById(R.id.button_new);

        //final DBHandler db = new DBHandler(this);
        final DBHandler db = ((MainApplication)getApplication()).db;

        /*db.deleteAllPlayers();

        Log.d("Insert: ", "Inserting..");
        db.addPlayer(new DartPlayer("Phil Taylor", "The Power", 4));
        db.addPlayer(new DartPlayer("Gary Anderson", "The Flying Scotsman", 2));
        db.addPlayer(new DartPlayer("Adrian Lewis", "Jackpot", 3));
        db.addPlayer(new DartPlayer("Michael van Gerwen", "Mighty Mike", 1));
        db.addPlayer(new DartPlayer("Peter Wright", "Snakebite", 5));

        listAllPlayers(db);

        Log.d("Deleting: ", "Deleting player with id 1");
        db.deletePlayer(1);
        listAllPlayers(db);

        Log.d("Updating:", "Updating player with id 5");
        DartPlayer dartPlayerToUpdate = db.getPlayer(5);
        if (dartPlayerToUpdate != null) {
            dartPlayerToUpdate.setRanking(4);
            db.updatePlayer(dartPlayerToUpdate);
        }

        listAllPlayers(db);*/


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getAllPlayerNames(db));
        mListView.setAdapter(adapter);

        mNewPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DartPlayers: ", "new player button clicked");
                Intent intent = new Intent(MainActivity.this, NewPlayer.class);
                startActivity(intent);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedPlayer = (String) mListView.getItemAtPosition(position);
                Log.d("DartPlayers: ", selectedPlayer + " clicked!");
                DartPlayer dartPlayer = db.getPlayer(selectedPlayer);

                Intent intent = new Intent(MainActivity.this, ViewPlayer.class);

                intent.putExtra("id", dartPlayer.getId());
                intent.putExtra("name", dartPlayer.getName());
                intent.putExtra("nickname", dartPlayer.getNickname());
                intent.putExtra("ranking", dartPlayer.getRanking());

                startActivity(intent);

            }

        });
    }

    /*private void listAllPlayers(DBHandler db) {
        // Reading all shops
        Log.d("Reading: ", "Reading all players..");
        ArrayList<DartPlayer> players = db.getAllPlayers();

        for (DartPlayer player : players) {
            String log = "Id: " + player.getId() + ", Name: "
                    + player.getName() + ", Nickname: " + player.getNickname()
                    + ", Ranking: " + player.getRanking();
            // Writing shops to log
            Log.d("Dart Player: : ", log);
        }
    }*/

    private ArrayList<String> getAllPlayerNames(DBHandler db) {
        ArrayList<String> playerNames = new ArrayList<String>();

        ArrayList<DartPlayer> players = db.getAllPlayers();
        for (DartPlayer player : players) {
            playerNames.add(player.getName());
        }
        return playerNames;
    }
}
