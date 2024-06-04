package com.example.aircraftwar2024.application;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.Map;

public class MusicPool {
    private final SoundPool musicPool;
    private final HashMap<String, Integer> soundPoolMap;
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

    public void put(Map<String, Integer> map){
        for (String key: map.keySet()) {
            soundPoolMap.put(key, musicPool.load(context, map.get(key), 1));
        }

    }

    public void play(String key){
            musicPool.play(soundPoolMap.get(key), 1, 1, 0, 0, 1.0f);
    }
}
