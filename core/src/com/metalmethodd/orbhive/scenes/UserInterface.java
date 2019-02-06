package com.metalmethodd.orbhive.scenes;

public class UserInterface {

    private int score = 0;

    //MUST BE A FLOAT FROM 0 TO 100
    private float progress = 0;

    public UserInterface(){

    }

    public int getScore() {
        return score;
    }

    public float getProgress() {
        return progress;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setProgress(float progress){
        this.progress = progress;
    }
}
