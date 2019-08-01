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

    public Image getImage() {
        return image;
    }

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public EnemyPlane() {
        this(0, 0, 1);
    }

    /**
     * 这里要增加 飞机类型
     *
     * @param x
     * @param y
     * @param type
     */
    public EnemyPlane(int x, int y, int type) {
        super(x, y);
        this.type = type;
        init(type);
    }

    public void init(int type) {
        if (type == 1) {
            this.image = ImageMap.getMap("ep01");
        } else if (type == 2) {
            this.image = ImageMap.getMap("ep02");
        } else if (type == 3) {
            this.image = ImageMap.getMap("ep03");
        } else if (type == 4) {
            this.image = ImageMap.getMap("ep04");
        }
    }

    @Override
    public void draw(Graphics g) {
        move();
        fire();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }

    @Override
    public void move() {
        if (type == 1) {
            setY(getY() + speed);
        } else if (type == 2) {
            setY(getY() + speed);
        } else if (type == 3) {
            setY(getY() + speed);
        } else if (type == 4) {
            setY(getY() + speed);
        }
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
            if (type == 1) {
                gameFrame.enemyBullets.add(new EnemyBullet(
                        getX() + (image.getWidth(null) / 2) - ImageMap.getMap("eb01").getWidth(null) / 2
                        , getY() + ImageMap.getMap("eb01").getHeight(null), ImageMap.getMap("eb01")));
            } else if (type == 2) {
                gameFrame.enemyBullets.add(new EnemyBullet(
                        getX() + (image.getWidth(null) / 2) - ImageMap.getMap("eb02").getWidth(null) / 2
                        , getY() + ImageMap.getMap("eb02").getHeight(null), ImageMap.getMap("eb02")));

            } else if (type == 3) {
                gameFrame.enemyBullets.add(new EnemyBullet(
                        getX() + (image.getWidth(null) / 2) - ImageMap.getMap("eb01").getWidth(null) / 2
                        , getY() + ImageMap.getMap("eb01").getHeight(null), ImageMap.getMap("eb01")));

            } else if (type == 4) {
                gameFrame.enemyBullets.add(new EnemyBullet(
                        getX() + (image.getWidth(null) / 2) - ImageMap.getMap("eb02").getWidth(null) / 2
                        , getY() + ImageMap.getMap("eb02").getHeight(null), ImageMap.getMap("eb02")));
            }
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}
