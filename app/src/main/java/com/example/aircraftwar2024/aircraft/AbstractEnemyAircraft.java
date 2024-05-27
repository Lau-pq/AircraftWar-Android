package com.example.aircraftwar2024.aircraft;



import android.util.Log;

import com.example.aircraftwar2024.activity.GameActivity;
import com.example.aircraftwar2024.basic.FlyingsObserver;
import com.example.aircraftwar2024.factory.supply_factory.BombSupplyFactory;
import com.example.aircraftwar2024.factory.supply_factory.FireSupplyFactory;
import com.example.aircraftwar2024.factory.supply_factory.HpSupplyFactory;
import com.example.aircraftwar2024.factory.supply_factory.SupplyFactory;
import com.example.aircraftwar2024.supply.AbstractFlyingSupply;

import java.util.LinkedList;
import java.util.List;


/**
 * 敌机父类
 * 敌机：BOSS, ELITE, MOB
 *
 * @author hitsz
 */
public abstract class AbstractEnemyAircraft extends AbstractAircraft implements FlyingsObserver {

    public AbstractEnemyAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);

    }
    protected int score;
    protected int supplyNum;

    /**
     * 道具工厂
     */
    private SupplyFactory flyingSupplyFactory;

    /**
     * 获得敌机分数，击毁敌机时，调用该方法获得分数。
     * @return 敌机的分数
     */
    public int getScore() {
        return score;
    };

    /**
     * 敌机坠毁时以一定概率掉落道具
     * @return 道具
     */
    public List<AbstractFlyingSupply> generateSupplies() {
        List<AbstractFlyingSupply> res = new LinkedList<>();

        //boss机坠落随机掉落3个道具
        for (int i = 0; i < supplyNum; i++) {
            flyingSupplyFactory = null;
            double prob = Math.random();
            if (prob < 0.3) {
                flyingSupplyFactory = new BombSupplyFactory();
                Log.d(this.getClass().getName(),"generate bomb supply");
            } else if (prob < 0.5) {
                flyingSupplyFactory = new FireSupplyFactory();
                Log.d(this.getClass().getName(),"generate fire supply");
            } else if (prob < 0.8) {
                flyingSupplyFactory = new HpSupplyFactory();
                Log.d(this.getClass().getName(),"generate hp supply");
            } else {
                System.out.println("No supply generated!");
            }

            if (flyingSupplyFactory != null) {
                int dropLocationX = locationX + (i*2 - supplyNum + 1)*60;
                res.add(flyingSupplyFactory.createFlyingSupply(dropLocationX, locationY));
            }
        }
        return res;
    }

    /**
     * 敌机向下飞行出界后，标记无效
     */
    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= GameActivity.screenHeight) {
            vanish();
        }
    }


}
