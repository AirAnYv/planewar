package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.Graphics;
import java.awt.Image;

public class Background extends BaseSprite implements Drawable, Moveable {

    private Image image;

    public Background() {
        this(0, 0, ImageMap.getMap("bg01"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        setY(getY() + 1);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
    }
}
