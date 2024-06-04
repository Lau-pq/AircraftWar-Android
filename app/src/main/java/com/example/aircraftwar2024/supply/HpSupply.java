package com.example.aircraftwar2024.supply;


import android.util.Log;

import com.example.aircraftwar2024.aircraft.HeroAircraft;
import com.example.aircraftwar2024.application.MusicManager;

import java.util.Objects;

/**
 * 加血道具：增加HP值。
 * 自动触发
 *
 * @author hitsz
 */
public class HpSupply extends AbstractFlyingSupply {
    private int increasedHp = 30;

    public HpSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void activate() {
        Log.d("HP", "1");
        HeroAircraft.getHeroAircraft().increaseHp(increasedHp);
        System.out.println("HpSupply active");
        MusicManager.action("supply");
    }

}
