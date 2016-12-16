package com.codeclan.example.dartplayerdb;

/**
 * Created by sandy on 05/09/2016.
 */
public class DartPlayer {
    private int mId;
    private String mName;
    private String mNickname;
    private int mRanking;

    public DartPlayer() {};

    public DartPlayer(int id, String name, String nickname, int ranking) {
        mId = id;
        mName = name;
        mNickname = nickname;
        mRanking = ranking;
    }

    public DartPlayer(String name, String nickname, int ranking) {
        mName = name;
        mNickname = nickname;
        mRanking = ranking;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getNickname() {
        return mNickname;
    }

    public void setNickname(String nickname) {
        mNickname = nickname;
    }

    public int getRanking() {
        return mRanking;
    }

    public void setRanking(int ranking) {
        mRanking = ranking;
    }
}
