package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;

import java.awt.*;

public class EnemyBullet extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;

    public EnemyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public EnemyBullet() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() + speed);
    }

    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.enemyBullets.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyBullets.remove(this);
            gameFrame.gameOver = true;
        }
    }
}
