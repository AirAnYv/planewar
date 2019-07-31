package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.*;

public class Background extends BaseSprite implements Drawable, Moveable {

    private Image image;
    private int moveSum = 0;

    public Background() {
        this(0, FrameConstant.FRAME_HEIGHT - ImageMap.getMap("bg01").getHeight(null), ImageMap.getMap("bg01"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public int getMoveSum() {
        return moveSum;
    }

    public void setMoveSum(int moveSum) {
        this.moveSum = moveSum;
    }

    @Override
    public void move() {
        setY(getY() + FrameConstant.GAME_SPEED);
        setMoveSum(getMoveSum() + FrameConstant.GAME_SPEED);
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
    }

    public void drawInfo(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(730, 660, 30, (int) ((moveSum / FrameConstant.PROCESS) * 200));
    }
}
