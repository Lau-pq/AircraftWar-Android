package com.example.aircraftwar2024.application;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import java.util.HashMap;

public class MusicPool {
    private final SoundPool musicPool;
    private final HashMap<Integer, Integer> soundPoolMap;
    private final Context context;

    public MusicPool(Context context){
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        musicPool = new SoundPool.Builder()
                .setMaxStreams(10)
                .setAudioAttributes(audioAttributes)
                .build();
        soundPoolMap = new HashMap<>();
        this.context = context;
    }

    public void put(int id, int sound){
        soundPoolMap.put(id, musicPool.load(context, sound, 1));
    }

    public void play(int id){
            musicPool.play(soundPoolMap.get(id), 1, 1, 0, 0, 1.0f);
    }
}
