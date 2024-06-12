package com.example.aircraftwar2024.supply;


import com.example.aircraftwar2024.application.MusicManager;
import com.example.aircraftwar2024.basic.FlyingsObserver;
import com.example.aircraftwar2024.message.MusicMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 炸弹道具，自动触发
 * <p>
 * 使用效果：清除界面上除BOSS机外的所有敌机（包括子弹）
 * <p>
 * 【观察者模式】
 *
 * @author hitsz
 */
public class BombSupply extends AbstractFlyingSupply {


    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    private List<FlyingsObserver> flyingsObserverList = new ArrayList<>();

    public void addflyingsObserver(FlyingsObserver flyingsObserver) {
        flyingsObserverList.add(flyingsObserver);
    }

    public void notifyAllFlyings() {
        for (FlyingsObserver flyingsObserver : flyingsObserverList) {
            flyingsObserver.update();
        }
    }

    @Override
    public void activate() {
        MusicManager.action(MusicMessage.bomb);
        System.out.println("BombSupply active");
        notifyAllFlyings();
    }

}
