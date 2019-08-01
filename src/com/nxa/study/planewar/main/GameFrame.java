package com.nxa.study.planewar.main;

import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.runtime.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    private Random random = new Random(0);
    public final Background bg = new Background();
    public final Plane plane = new Plane();
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    public final List<EnemyPlane> enemyPlanes = new CopyOnWriteArrayList<>();

    public final List<EnemyBullet> enemyBullets = new CopyOnWriteArrayList<>();

    public final List<Prop> props = new CopyOnWriteArrayList<>();

    public final List<Explosion> explosions = new CopyOnWriteArrayList<>();

    public final List<BossBullet> bossBullets = new CopyOnWriteArrayList<>();

    public final Boss boss = new Boss(300, -50);

    private Warning warning = new Warning(100, 400);

    public boolean gameOver = false;

    public GameFrame() throws HeadlessException {
    }

    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
            bg.draw(g);
            for (EnemyBullet enemyBullet : enemyBullets) {
                enemyBullet.draw(g);
            }
            for (EnemyPlane enemyPlane : enemyPlanes) {
                enemyPlane.draw(g);
            }
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }
            /**
             * 画出爆炸
             */
            for (Explosion explosion : explosions) {
                explosion.draw(g);
            }
            plane.draw(g);
            /**
             * 我方子弹击中敌人的方法
             */
            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlanes);
            }

            /**
             * 敌方子弹攻击我方飞机的方法  关闭后无敌
             */
//            for (EnemyBullet enemyBullet : enemyBullets) {
//                enemyBullet.collisionTesting(plane);
//            }

            /**
             * 我方子弹击中BOSS的方法
             */
            if (boss.isAlive()) {
                for (Bullet bullet : bulletList) {
                    bullet.collisionTesting(boss);
                }
            }

            /**
             * 画出道具
             */
            for (Prop prop : props) {
                prop.draw(g);
            }
            /**
             * 我方吃道具的方法
             */
            for (Prop prop : props) {
                prop.collisionTesting(plane);
            }

            /**
             * 画出警告
             */
            warning.draw(g);

            /**
             * 绘制血条 在屏幕右上角
             */
            g.setColor(Color.red);
            g.drawString("生命值:", 640, 68);
            g.drawRect(680, 60, 70, 10);
            g.setColor(Color.white);
            g.drawString("蓄能:", 640, 88);
            g.drawRect(680, 80, 70, 10);

            g.setColor(Color.YELLOW);
            g.drawString("进度:", 733, 650);
            g.drawRect(730, 660, 30, 200);


            createEnemyPlane();
            bg.drawInfo(g);

            g.setColor(Color.red);
            g.fillRect(730, 780, 32, 2);


            for (BossBullet bossBullet : bossBullets) {
                bossBullet.draw(g);
            }
            /**
             * boss 子弹攻击我的方法
             */
//            for (BossBullet bossBullet : bossBullets) {
//                bossBullet.collisionTesting(plane);
//            }
            boss.draw(g);


//        g.setColor(Color.red);
//        g.drawString("" + bulletList.size(), 100, 100);

        }


    }

    private void createEnemyPlane() {
        boolean flag = true;   //  是否能添加
        if (random.nextInt(1000) > 985) {
            int epX = (int) (Math.random() * 720);
            int epY = (int) (Math.random() * 50);
            int type = random.nextInt(4) + 1;
            EnemyPlane enemyPlane = new EnemyPlane(epX, epY, type);
            if (enemyPlanes.size() == 0) {
                enemyPlanes.add(enemyPlane);
                flag = false;
            } else {
                for (EnemyPlane enemyP : enemyPlanes) {
                    if (enemyP.getY() < 300 && enemyPlane.getRectangle().intersects(enemyP.getRectangle())) {  // 不能添加的情况
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                enemyPlanes.add(enemyPlane);
            }
        }
    }

    public void init() {
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        enableInputMethods(false);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        setVisible(true);

    }


    private Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
}
