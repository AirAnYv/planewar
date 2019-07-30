package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 3;
    Random random = new Random();

    public EnemyPlane() {
        this(0, 0, ImageMap.getMap("ep01"));
    }

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        fire();
    }

    @Override
    public void move() {
        setY(getY() + speed);
        borderTesting();
    }

    public void borderTesting() {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            gameFrame.enemyPlanes.remove(this);
        }

    }

    public void fire() {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (random.nextInt(1000) > 990) {
            gameFrame.enemyBullets.add(new EnemyBullet(
                    getX() + (image.getWidth(null) / 2) - ImageMap.getMap("eb01").getWidth(null) / 2
                    , getY() + ImageMap.getMap("eb01").getHeight(null), ImageMap.getMap("eb01")));
        }

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}
