package com.example.aircraftwar2024.aircraft;


/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class MobEnemy extends AbstractEnemyAircraft {

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
        this.score = 10;
        this.supplyNum = 0;
    }

    @Override
    public void update() {
        vanish();
    }
}
