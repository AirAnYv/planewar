package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.*;

public class Prop extends BaseSprite implements Moveable, Drawable {

    private Image image;

    public Prop() {
        this(0, 0, ImageMap.getMap("blood"));
    }

    public Prop(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null) / 7, image.getHeight(null) / 5, null);
    }

    @Override
    public void move() {

    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null)/7, image.getHeight(null)/5);
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (this.getRectangle().intersects(plane.getRectangle())) {
            gameFrame.props.remove(this);
            if (gameFrame.plane.getBlood() - FrameConstant.INCREMENT_BLOOD >= 80) {
                gameFrame.plane.setBlood(100);
            } else {
                gameFrame.plane.setBlood(gameFrame.plane.getBlood() + 20);
            }
        }
    }

}
