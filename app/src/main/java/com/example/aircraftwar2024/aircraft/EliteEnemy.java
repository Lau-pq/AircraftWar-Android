package com.example.aircraftwar2024.aircraft;


/**
 * 精英敌机
 * 可射击
 *
 * @author hitsz
 */
public class EliteEnemy extends AbstractEnemyAircraft {

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp ) {
        super(locationX, locationY, speedX, speedY, hp);
        this.shootNum = 1;
        this.power = 10;
        this.direction = 1;
        this.rate = 1.2;
        this.score = 30;
        this.supplyNum = 1;
    }

    @Override
    public void update() {
        vanish();
    }
}
