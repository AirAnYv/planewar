package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;
import com.nxa.study.planewar.util.ImageMap;

import java.util.List;
import java.awt.*;

public class Bullet extends BaseSprite implements Moveable, Drawable {

    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;
    private int type;


    private int count;

    public Bullet() {
        this(0, 0, 1);
    }

    public Bullet(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.count = 0;  // 子弹存活回合
        init();
    }

    private void init() {
        if (type == 1) {
            image = ImageMap.getMap("mb01");
        } else if (type == 2) {
            image = ImageMap.getMap("target");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        borderTesting();
    }

    @Override
    public void move() {
        if (type == 1) {
            setY(getY() - speed);
        } else if (type == 2) {
            setY(getY() + FrameConstant.GAME_SPEED * 3);
        }

    }

    public void borderTesting() {
        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DateStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(List<EnemyPlane> enemyPlaneList) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (type == 1) {
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                    gameFrame.enemyPlanes.remove(enemyPlane);
                    gameFrame.bulletList.remove(this);
                    if (gameFrame.plane.getMagic() != 100) {
                        gameFrame.plane.setMagic(gameFrame.plane.getMagic() + 10);
                    }
                    if (Math.random() > 0.9) {
                        gameFrame.props.add(new Prop(getX(), getY(), ImageMap.getMap("blood")));
                    }
                }
            }
        } else if (type == 2) {
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                    count++;
                    if (count >= 80) {
                        gameFrame.explosions.add(new Explosion(getX(), getY()));
                        gameFrame.enemyPlanes.remove(enemyPlane);
                        gameFrame.bulletList.remove(this);
                }
                }
            }
        }
    }

    public void collisionTesting(Boss boss) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (type == 1) {
            if (this.getRectangle().intersects(boss.getRectangle())) {
                boss.setBlood(boss.getBlood() - 100);
                gameFrame.bulletList.remove(this);
                if (gameFrame.plane.getMagic() != 100) {
                    gameFrame.plane.setMagic(gameFrame.plane.getMagic() + 10);
                }
            }
            if (boss.getBlood() <= 0) {
                boss.setAlive(false);
                gameFrame.gameOver = true;
            }
        }
    }
}
