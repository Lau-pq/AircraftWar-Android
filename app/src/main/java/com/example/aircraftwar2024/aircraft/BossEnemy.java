package com.example.aircraftwar2024.aircraft;


import com.example.aircraftwar2024.application.MusicManager;
import com.example.aircraftwar2024.message.MusicMessage;

/**
 * BOSS 敌机
 * 可射击
 * 工厂构造
 *
 * @author hitsz
 */
public class BossEnemy extends AbstractEnemyAircraft {

    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 3;
        this.power = 10;
        this.direction = 1;
        this.rate = 1.2;
        this.score = 100;
        this.supplyNum = 3;
        MusicManager.action(MusicMessage.boss);
    }

    @Override
    public void update() {}

    @Override
    public void vanish() {
        super.vanish();
        MusicManager.action(MusicMessage.boss_defeated);
    }
}
