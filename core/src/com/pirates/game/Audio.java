package com.pirates.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by Amelia on 2016-03-20.
 */
public class Audio {

    //sound effects
    private Sound humanScream = Gdx.audio.newSound(Gdx.files.internal("data/human_scream.mp3"));
    private Sound gunshot = Gdx.audio.newSound(Gdx.files.internal("data/gunshot.mp3"));
    private Sound mobDeath = Gdx.audio.newSound(Gdx.files.internal("data/mob_death.wav"));

    //background music
    private Music music;

    public Audio() {
        music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
        music.play();
        music.setVolume(1.0f);
    }

    public void stop() {
        music.stop();
    }

    public void playerDeath() {humanScream.play();}
    public void gunshot() {gunshot.play();}
    public void mobDeath() {mobDeath.play();}
}
