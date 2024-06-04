package com.example.aircraftwar2024.application;


import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.aircraftwar2024.R;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private MediaPlayer bgmMediaPlayer;
    private MediaPlayer bgmBossMediaPlayer;
    private Context context;

    private List<MediaPlayer> mediaPlayerList = new ArrayList<>();

    public MusicPlayer(Context context) {
        this.context = context;
    }

    public void playBgm() {
        if (bgmMediaPlayer == null) {
            bgmMediaPlayer = MediaPlayer.create(context, R.raw.bgm);
            bgmMediaPlayer.setLooping(true);
            mediaPlayerList.add(bgmMediaPlayer);
        }
        bgmMediaPlayer.start();
    }

    public void playBossBgm() {
        bgmMediaPlayer.pause();
        if (bgmBossMediaPlayer == null) {
            bgmBossMediaPlayer = MediaPlayer.create(context, R.raw.bgm_boss);
            bgmBossMediaPlayer.setLooping(true);
            mediaPlayerList.add(bgmBossMediaPlayer);
        }
        bgmBossMediaPlayer.start();
    }


    public void stopBossBgm() {
        if (bgmBossMediaPlayer != null && bgmBossMediaPlayer.isPlaying()) {
            bgmBossMediaPlayer.pause();
            bgmMediaPlayer.seekTo(bgmMediaPlayer.getCurrentPosition());
            bgmMediaPlayer.start();
        }
    }

    public void release() {
        for (MediaPlayer mediaPlayer: mediaPlayerList) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }

}
