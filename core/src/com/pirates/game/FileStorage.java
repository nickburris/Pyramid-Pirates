package com.pirates.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;

import java.util.prefs.PreferenceChangeEvent;

/**
 * Created by Amelia on 2016-03-20.
 */
public class FileStorage {
    //public variables/constants
    public final static boolean isLocAvailable = Gdx.files.isLocalStorageAvailable();
    public final static String locRoot = Gdx.files.getLocalStoragePath();

    //private variables;
    Preferences prefs;

    //constructor
    public FileStorage() {
//        FileHandle[] files = Gdx.files.local(locRoot).list();
//        System.out.println("#Files: " + files.length);
//
//        localHandle = Gdx.files.local("highscore.txt");
//
//        System.out.println("#Files: " + files.length);

        prefs = Gdx.app.getPreferences("Persistent Data");
    }

    public int getHighScore() {
        return prefs.getInteger("highscore", 0);
    }

    public void setHighScore(int highscore) {

        prefs.putInteger("highscore", highscore);
        prefs.flush();
    }
}
