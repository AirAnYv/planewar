package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
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
        if (gameFrame.bg.getMoveSum() > 1000 && !gameFrame.gameOver) {    // 待更改数值
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

    }
}
