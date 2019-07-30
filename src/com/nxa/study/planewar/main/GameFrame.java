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
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    private Background bg = new Background();
    private Plane plane = new Plane();
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    public final List<EnemyPlane> enemyPlanes = new CopyOnWriteArrayList<>();

    public final List<EnemyBullet> enemyBullets = new CopyOnWriteArrayList<>();

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

            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlanes);
            }
            for (EnemyBullet enemyBullet : enemyBullets) {
                enemyBullet.collisionTesting(plane);
            }
        }


//        g.setColor(Color.red);
//        g.drawString("" + bulletList.size(), 100, 100);
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

        enemyPlanes.add(new EnemyPlane(300, 30, ImageMap.getMap("ep01")));
        enemyPlanes.add(new EnemyPlane(700, -20, ImageMap.getMap("ep01")));
        enemyPlanes.add(new EnemyPlane(20, 20, ImageMap.getMap("ep01")));
        enemyPlanes.add(new EnemyPlane(600, 10, ImageMap.getMap("ep01")));
        enemyPlanes.add(new EnemyPlane(100, 60, ImageMap.getMap("ep01")));

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
