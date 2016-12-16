package com.codeclan.example.dartplayerdb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sandy on 05/09/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "dartPlayerInfo";
    // Players table name
    private static final String TABLE_DARTPLAYERS = "dart_players";
    // Players Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NICKNAME = "nickname";
    private static final String KEY_RANKING = "ranking";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*
            CREATE TABLE dart_players (
                id INTEGER PRIMARY KEY,
                name TEXT,
                nickname TEXT,
                ranking INTEGER
        )
        */
        String CREATE_TABLE = "CREATE TABLE " + TABLE_DARTPLAYERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_NICKNAME + " TEXT, " + KEY_RANKING + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        /*
            DROP TABLE IF EXISTS dart_players;
        */

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DARTPLAYERS);
        // Creating tables again
        onCreate(db);
    }

    private void runSQL(String sql) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
    }

    public void addPlayer (DartPlayer player) {
        String name = player.getName();
        String nickname = player.getNickname();
        int ranking = player.getRanking();

        String sql = "INSERT INTO " + TABLE_DARTPLAYERS +
                        "(" + KEY_NAME + "," + KEY_NICKNAME + "," + KEY_RANKING + " ) VALUES ('"
                        + name + "','" + nickname + "'," + Integer.toString(ranking) + ")";
        runSQL(sql);
    }

    public void updatePlayer(DartPlayer player) {
        int id = player.getId();
        String name = player.getName();
        String nickname = player.getNickname();
        int ranking = player.getRanking();

        String sql = "UPDATE " + TABLE_DARTPLAYERS + " SET "
                        + KEY_NAME + " = '" + name + "',"
                        + KEY_NICKNAME + " = '" + nickname + "',"
                        + KEY_RANKING + " = " + ranking + " WHERE " + KEY_ID + " = " + id;

        Log.d("Running SQL: ", sql);
        runSQL(sql);
    }

    public DartPlayer getPlayer(int id) {
        String sql = "SELECT * FROM " + TABLE_DARTPLAYERS + " WHERE " + KEY_ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();

            /*DartPlayer dartPlayer = new DartPlayer(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));

            return dartPlayer;*/
            DartPlayer dartPlayer = getPlayerFromDBCursor(cursor);
            return dartPlayer;
        }
        return null;
    }

    public DartPlayer getPlayer(String name) {
        String sql = "SELECT * FROM " + TABLE_DARTPLAYERS + " WHERE " + KEY_NAME + " = '" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();

            //DartPlayer dartPlayer = new DartPlayer(Integer.parseInt(cursor.getString(0)),
            //        cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));

            //return dartPlayer;
            DartPlayer dartPlayer = getPlayerFromDBCursor(cursor);
            return dartPlayer;
        }
        return null;
    }

    public ArrayList<DartPlayer> getAllPlayers() {
        ArrayList<DartPlayer> dartPlayerList = new ArrayList<DartPlayer>();

        String sql = "SELECT * FROM " + TABLE_DARTPLAYERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                /*DartPlayer dartPlayer = new DartPlayer();
                dartPlayer.setId(Integer.parseInt(cursor.getString(0)));
                dartPlayer.setName(cursor.getString(1));
                dartPlayer.setNickname(cursor.getString(2));
                dartPlayer.setRanking(Integer.parseInt(cursor.getString(3)));
                */
                DartPlayer dartPlayer = getPlayerFromDBCursor(cursor);
                // Adding contact to list
                dartPlayerList.add(dartPlayer);
            } while (cursor.moveToNext());
        }

        return dartPlayerList;
    }

    public void deletePlayer(DartPlayer player) {
        int id = player.getId();

        String sql = "DELETE FROM " + TABLE_DARTPLAYERS + " WHERE " + KEY_ID + " = " + id;
        runSQL(sql);
    }

    public void deletePlayer(int id) {
        String sql = "DELETE FROM " + TABLE_DARTPLAYERS + " WHERE " + KEY_ID + " = " + id;
        runSQL(sql);
    }

    public void deleteAllPlayers() {
        String sql = "DELETE FROM " + TABLE_DARTPLAYERS;
        runSQL(sql);
    }

    private DartPlayer getPlayerFromDBCursor(Cursor cursor) {

        // Get the column index for each column in the table
        int idColumnNum = cursor.getColumnIndex(KEY_ID);
        int nameColumNum = cursor.getColumnIndex(KEY_NAME);
        int nicknameColumnNum = cursor.getColumnIndex(KEY_NICKNAME);
        int rankingColumNum = cursor.getColumnIndex(KEY_RANKING);

        // get the data in the fields at each column
        int id = Integer.parseInt(cursor.getString(idColumnNum));
        String name = cursor.getString(nameColumNum);
        String nickname = cursor.getString(nicknameColumnNum);
        int ranking = Integer.parseInt(cursor.getString(rankingColumNum));

        DartPlayer dartPlayer = new DartPlayer(id, name, nickname, ranking);
        /*dartPlayer.setId(id);
        dartPlayer.setName(name);
        dartPlayer.setNickname(nickname);
        dartPlayer.setRanking(ranking);*/

        return dartPlayer;
    }
}