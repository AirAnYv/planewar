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

public class Boss extends BaseSprite implements Moveable, Drawable {

    private List<Image> imageList = new ArrayList<>();
    private int index = 0;
    private boolean alive = false;
    private int blood = 1000;
    private int speed = FrameConstant.GAME_SPEED * 2;
    private boolean right = true;
    private boolean attack = false;
    private int timer;
    private int angle = 0;
    private int fireIndex;

    public int getBlood() {
        return blood;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public Boss() {
        this(0, 0);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Boss(int x, int y) {
        super(x, y);
        init();
    }

    public void init() {
        for (int i = 1; i < 10; i++) {
            imageList.add(ImageMap.getMap("boss0" + i));
        }
    }

    @Override
    public void draw(Graphics g) {
        if (alive) {
            g.drawRect(30, 70, 200, 20);
            g.fillRect(30, 70, (int) (getBlood() / 1000.0 * 200), 20);
            g.setColor(Color.WHITE);
            g.drawString("血量: " + getBlood(), 50, 85);
            fire();
            move();
            if (Math.random() > 0.998 && !attack) {
                attack = true;
            }
            g.drawImage(imageList.get(index++ / 5), getX(), getY(), (int) (imageList.get(0).getWidth(null) / 1.5),
                    (int) (imageList.get(0).getHeight(null) / 1.5), null);
            if (index >= 45) {
                index = 0;
            }
        }
        Appearance();
    }

    public void Appearance() {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (gameFrame.bg.getMoveSum() > FrameConstant.BOSS_APPEARANCE && !gameFrame.gameOver) {
            setAlive(true);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), (int) (imageList.get(0).getWidth(null) / 1.5),
                (int) (imageList.get(0).getHeight(null) / 1.5));
    }

    @Override
    public void move() {
        if (getY() < 150) {
            setY(getY() + speed);
        }
        if (!attack) {
            //  BOSS不攻击移动的方法
            if (right && getY() >= 50) {
                setX(getX() + speed);
            }
            if (!right && getY() >= 50) {
                setX(getX() - speed);
            }
            if (getY() > 200) {
                setY(getY() - speed);
            }
        } else {
            // BOSS 主动攻击的移动方法
            setY(getY() + speed * 2);

        }
        borderTesting();
    }

    private void borderTesting() {
        if (!attack) {
            // BOSS不攻击的移动监测
            if (getX() < 0) {
                right = true;
            } else if (getX() + (int) (imageList.get(0).getWidth(null) / 1.5) > FrameConstant.FRAME_WIDTH) {
                right = false;
            }

        } else {
            // BOSS主动攻击的移动监测
            if (getY() >= 600) {
                setY(600);
                if (timer == 0) {
                    skill();
                }
                timer++;
                if (timer > 180) {
                    attack = false;
                    timer = 0;
                }
            }
        }
    }

    public void fire() {
        angle = (30 + angle) % 360;
        GameFrame gameFrame = DateStore.get("gameFrame");
        fireIndex++;
        if (fireIndex == 10) {
            // 增加普通打击方法
            gameFrame.bossBullets.add(new BossBullet(getX() + (int) (imageList.get(0).getWidth(null) / 1.5 - 75),
                    getY() + (int) (imageList.get(0).getHeight(null) / 1.5) - 50, 1, angle));
        }
        if (fireIndex > 10) {
            fireIndex = 0;
        }
    }

    public void skill() {
        GameFrame gameFrame = DateStore.get("gameFrame");
        gameFrame.bossBullets.add(new BossBullet(getX() + (imageList.get(0).getWidth(null) / 2) -
                ImageMap.getMap("bb01").getWidth(null) / 2 - 50,
                getY() + (imageList.get(0).getHeight(null) / 2), 2, 0));
    }
}
