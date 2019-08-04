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

public class BossBullet extends BaseSprite implements Drawable, Moveable {

    private List<Image> imageList = new ArrayList<>();
    private int index = 0;
    private int type;
    private int speed = FrameConstant.GAME_SPEED * 5;
    private int angle;


    public BossBullet() {
        this(0, 0, 1, 0);
    }

    public BossBullet(int x, int y, int type, int angle) {
        super(x, y);
        this.type = type;
        this.angle = angle;
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
        if (type == 1) {
            setX(getX() + (int) (Math.cos(Math.toRadians(angle)) * speed));
            setY(getY() + (int) (Math.sin(Math.toRadians(angle)) * speed));
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), imageList.get(0).getWidth(null), imageList.get(0).getHeight(null));
    }

    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {

            if (plane.isDodge()) {
                // 闪避打开
            } else {
                // 闪避关闭时
                gameFrame.bossBullets.remove(this);
                gameFrame.plane.setBlood(gameFrame.plane.getBlood() - 20);
            }
            if (gameFrame.plane.getBlood() <= 0) {
                gameFrame.gameOver = true;
            }
        }
    }

}
