package com.nxa.study.planewar.main;

import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.runtime.*;
import com.nxa.study.planewar.util.ImageMap;

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
    private Background bg = new Background();
    public final Plane plane = new Plane();
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    public final List<EnemyPlane> enemyPlanes = new CopyOnWriteArrayList<>();

    public final List<EnemyBullet> enemyBullets = new CopyOnWriteArrayList<>();

    //private Prop blood = new Prop(30,50,ImageMap.getMap("blood"));
    public final List<Prop> props = new CopyOnWriteArrayList<>();

    public boolean gameOver = false;

    public GameFrame() throws HeadlessException {
    }

    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
            bg.draw(g);
            plane.draw(g);
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }
            for (EnemyPlane enemyPlane : enemyPlanes) {
                enemyPlane.draw(g);
            }
            for (EnemyBullet enemyBullet : enemyBullets) {
                enemyBullet.draw(g);
            }


            /**
             * 我方子弹击中敌人的方法
             */
            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlanes);
            }

            /**
             * 敌方子弹攻击我方飞机的方法  关闭后无敌
             */
            for (EnemyBullet enemyBullet : enemyBullets) {
                enemyBullet.collisionTesting(plane);
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


        }

        /**
         * 绘制血条 在屏幕右上角
         */
        g.setColor(Color.red);
        g.drawString("生命值:" ,640,68);
        g.drawRect(680, 60, 70, 10);
        g.setColor(Color.white);
        g.drawString("蓄能:" ,640,88);
        g.drawRect(680, 80, 70, 10);


        createEnemyPlane();

//        g.setColor(Color.red);
//        g.drawString("" + bulletList.size(), 100, 100);
    }

    private void createEnemyPlane() {
        boolean flag = true;   //  是否能添加
        if (random.nextInt(1000) > 990) {
            int epX = (int) (Math.random() * 720);
            int epY = (int) (Math.random() * 200);
            EnemyPlane enemyPlane = new EnemyPlane(epX, epY, ImageMap.getMap("ep01"));
            if (enemyPlanes.size() == 0) {
                enemyPlanes.add(enemyPlane);
                flag = false;
            } else {
                for (EnemyPlane enemyP : enemyPlanes) {
                    if (enemyPlane.getRectangle().intersects(enemyP.getRectangle())) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    enemyPlanes.add(enemyPlane);
                }

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

        /*enemyPlanes.add(new EnemyPlane(300, 30, ImageMap.getMap("ep01")));
        enemyPlanes.add(new EnemyPlane(700, -20, ImageMap.getMap("ep01")));
        enemyPlanes.add(new EnemyPlane(20, 20, ImageMap.getMap("ep01")));
        enemyPlanes.add(new EnemyPlane(600, 10, ImageMap.getMap("ep01")));
        enemyPlanes.add(new EnemyPlane(100, 60, ImageMap.getMap("ep01")));*/

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
