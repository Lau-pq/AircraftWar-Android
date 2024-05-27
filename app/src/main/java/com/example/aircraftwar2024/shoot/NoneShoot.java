package com.example.aircraftwar2024.shoot;

import com.example.aircraftwar2024.aircraft.AbstractAircraft;
import com.example.aircraftwar2024.bullet.AbstractBullet;

import java.util.LinkedList;
import java.util.List;

public class NoneShoot implements ShootStrategy{
    @Override
    public List<AbstractBullet> shootWithStrategy(AbstractAircraft abstractAircraft) {
        return new LinkedList<>();
    }
}
