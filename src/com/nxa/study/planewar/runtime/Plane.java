package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private boolean up, right, down, left;
    private boolean fire;
    private int speed = FrameConstant.GAME_SPEED * 3;
    private int index = 0;
    private int blood = 100;
    private int magic = 90;
    private boolean skill;


    public void setMagic(int magic) {
        this.magic = magic;
    }

    public int getMagic() {
        return magic;
    }

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public Plane() {
        this((FrameConstant.FRAME_WIDTH - ImageMap.getMap("my01").getWidth(null)) / 2,
                FrameConstant.FRAME_HEIGHT - ImageMap.getMap("my01").getHeight(null),
                ImageMap.getMap("my01"));
    }

    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        fire();
        skill();
        if (fire) { // 控制开火速度
            index++;
            if (index >= 10) {
                index = 0;
            }
        }
        drawInfo(g);
    }

    public void drawInfo(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(FrameConstant.BLOOD_X, FrameConstant.BLOOD_Y, (int) ((blood / 100.0) * FrameConstant.BLOOD_WIDTH), FrameConstant.BLOOD_HEIGHT);
        g.setColor(Color.WHITE);
        g.fillRect(FrameConstant.MAGIC_X, FrameConstant.MAGIC_Y, (int) ((magic / 100.0) * FrameConstant.MAGIC_WIDTH), FrameConstant.MAGIC_HEIGHT);
    }

    public void fire() {
        if (fire && index == 0) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList.add(new Bullet(
                    getX() + (image.getWidth(null) / 2 - ImageMap.getMap("mb01").getWidth(null) / 2)
                    , getY() - (image.getHeight(null) / 2)
                    , 1));
        }
    }

    public void skill() {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (skill && getMagic() == 100) {
            for (EnemyPlane enemyPlane : gameFrame.enemyPlanes) {
                gameFrame.bulletList.add(new Bullet(
                        enemyPlane.getX() + (enemyPlane.getImage().getWidth(null) / 2 - ImageMap.getMap("target").getWidth(null) / 2)
                        , enemyPlane.getY() + enemyPlane.getImage().getHeight(null) / 2 - ImageMap.getMap("target").getHeight(null) / 2
                        , 2)
                );
            }
            // 待写没有能量
            setMagic(90);
        }
    }

    @Override
    public void move() {
        if (up) {
            setY(getY() - speed);
        }
        if (right) {
            setX(getX() + speed);
        }
        if (down) {
            setY(getY() + speed);
        }
        if (left) {
            setX(getX() - speed);
        }
        borderTesting();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            skill = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_K) {
            skill = false;
        }
    }

    public void borderTesting() {
        if (getX() < 0) {
            setX(0);
        }
        if (getX() > FrameConstant.FRAME_WIDTH - image.getWidth(null)) {
            setX(FrameConstant.FRAME_WIDTH - image.getWidth(null));
        }
        if (getY() < 30) {
            setY(30);
        }
        if (getY() > FrameConstant.FRAME_HEIGHT - image.getHeight(null)) {
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null));
        }
    }


    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }


}
