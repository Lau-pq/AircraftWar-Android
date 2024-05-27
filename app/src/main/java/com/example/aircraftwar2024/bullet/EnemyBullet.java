package com.example.aircraftwar2024.bullet;

import com.example.aircraftwar2024.basic.FlyingsObserver;

/**
 * @Author hitsz
 */
public class EnemyBullet extends AbstractBullet implements FlyingsObserver {


    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }

    @Override
    public void update() {
        vanish();
    }
}
