package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossBullet extends BaseSprite implements Drawable, Moveable {

    private List<Image> imageList = new ArrayList<>();
    private int index = 0;
    private int type;
    private int speed = FrameConstant.GAME_SPEED * 5;
    private double angle;
    private boolean direction; // false 向左 true 向右

    public BossBullet() {
        this(0, 0, 1, false);
    }

    public BossBullet(int x, int y, int type, boolean direction) {
        super(x, y);
        this.type = type;
        this.direction = direction;
        init();
    }

    public void init() {
        if (type == 1) {
            imageList.add(ImageMap.getMap("eb02"));
        } else if (type == 2) {
            for (int i = 1; i < 6; i++) {
                imageList.add(ImageMap.getMap("bb0" + i));
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        move();
        if (type == 1) {
            g.drawImage(imageList.get(0), getX(), getY(), (imageList.get(0).getWidth(null)),
                    (imageList.get(0).getHeight(null)), null);
        } else if (type == 2) {
            g.drawImage(imageList.get(index++ % 25 / 5), getX(), getY(), imageList.get(0).getWidth(null),
                    imageList.get(0).getHeight(null), null);
            if (index >= 50) {
                gameFrame.bossBullets.remove(this);
            }
        }

    }

    @Override
    public void move() {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (type == 1) {
            // 错误
            int x = gameFrame.plane.getX();
            int y = gameFrame.plane.getY();
            angle = Math.acos(Math.abs(getX() - x) / Math.sqrt(Math.pow(Math.abs(getX() - x), 2) + Math.pow(Math.abs(getY() - y), 2)));
            if (direction) {
                setX((int) (getX() + speed * Math.cos(speed)));
            } else {
                setX((int) (getX() - speed * Math.cos(speed)));
            }
            setY((int) (getY() - speed * Math.sin(speed)));   ///待检测 普通子弹运动方法
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), imageList.get(0).getWidth(null), imageList.get(0).getHeight(null));
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBullets.remove(this);
            gameFrame.plane.setBlood(gameFrame.plane.getBlood() - 50);
            if (gameFrame.plane.getBlood() == 0) {
                gameFrame.gameOver = true;
            }
        }
    }

}
