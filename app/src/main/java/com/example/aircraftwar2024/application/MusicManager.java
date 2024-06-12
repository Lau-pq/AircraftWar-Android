package com.example.aircraftwar2024.application;

import android.content.Context;

import com.example.aircraftwar2024.R;
import com.example.aircraftwar2024.message.MusicMessage;

import java.util.HashMap;
import java.util.Map;

public class MusicManager {
    public static boolean isActive = false;

    private static final Map<String, Integer> EVENT_MUSIC_PATH_MAP = new HashMap<>();

    public static int MUSIC_BGM_PATH = R.raw.bgm;
    public static int MUSIC_BGM_BOSS_PATH = R.raw.bgm_boss;
    public static int MUSIC_BOMB_EXPLOSION_PATH = R.raw.bomb_explosion;
    public static int MUSIC_BULLET_HIT_PATH = R.raw.bullet_hit;
    public static int MUSIC_GAME_OVER_PATH = R.raw.game_over;
    public static int GET_SUPPLY_PATH = R.raw.get_supply;

    private static MusicPlayer musicPlayer;
    private static MusicPool musicPool;

    static {
        EVENT_MUSIC_PATH_MAP.put(MusicMessage.begin, MUSIC_BGM_PATH);
        EVENT_MUSIC_PATH_MAP.put(MusicMessage.boss, MUSIC_BGM_BOSS_PATH);
        EVENT_MUSIC_PATH_MAP.put(MusicMessage.bomb, MUSIC_BOMB_EXPLOSION_PATH);
        EVENT_MUSIC_PATH_MAP.put(MusicMessage.hit, MUSIC_BULLET_HIT_PATH);
        EVENT_MUSIC_PATH_MAP.put(MusicMessage.over, MUSIC_GAME_OVER_PATH);
        EVENT_MUSIC_PATH_MAP.put(MusicMessage.supply, GET_SUPPLY_PATH);
    }

    public static void initial(Context context) {
        musicPlayer = new MusicPlayer(context);
        musicPool = new MusicPool(context);
        musicPool.put(EVENT_MUSIC_PATH_MAP);
    }

    public static void action(String event) {
        if(isActive) {
            switch (event) {
                case MusicMessage.begin -> musicPlayer.playBgm();
                case MusicMessage.boss -> musicPlayer.playBossBgm();
                case MusicMessage.bomb, MusicMessage.hit, MusicMessage.supply -> musicPool.play(event);
                case MusicMessage.boss_defeated -> musicPlayer.stopBossBgm();
                case MusicMessage.over -> {
                    musicPool.play(event);
                    musicPlayer.release();
                }
            }
        }

    }

}
