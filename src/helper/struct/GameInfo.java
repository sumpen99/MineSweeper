package helper.struct;

import helper.player.GamePlayer;

public class GameInfo {
    public SMTimer timer;
    public boolean quit,foundAMine,aborted;
    public float runningTime;
    public GamePlayer player;
    public int gamesPlayed;
    public GameInfo(){
        timer = new SMTimer();
    }

    public void reset(){
        aborted = false;
        foundAMine = false;
        quit = false;
    }
}
