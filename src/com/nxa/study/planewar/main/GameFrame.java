package com.nxa.study.planewar.main;

import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.runtime.*;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.*;
import java.awt.event.*;
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

    private GameStatus gameStatus = new GameStatus(210, 400);

    public boolean gameOver = true;  // 一开始给游戏为不开局
    public int game = 5;    // 一开始为准备阶段

    public GameFrame() throws HeadlessException {

    }

    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
            // 游戏开始
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


            /**
             *  boss没有死 但是地图结束的方法
             */
            if (bg.getMoveSum() > FrameConstant.MAP_OVER) {
                gameOver = true;
            }

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

        } else {
            if (game == 1) {
                // 游戏准备阶段
                bg.setMoveSum(0);
                bg.setY(FrameConstant.FRAME_HEIGHT - ImageMap.getMap("bg01").getHeight(null));
                enemyBullets.clear();
                enemyPlanes.clear();
                plane.setMagic(0);
                plane.setBlood(100);
                boss.setAlive(false);
                bossBullets.clear();
                plane.setX((FrameConstant.FRAME_WIDTH - ImageMap.getMap("my01").getWidth(null)) / 2);
                plane.setY(FrameConstant.FRAME_HEIGHT - ImageMap.getMap("my01").getHeight(null));
            } else if (boss.isAlive() && bg.getMoveSum() > FrameConstant.PROCESS) {
                // 游戏结束 但是 boss存活 走过的路程大于总路程  时间超时
                // 你输了
                game = 3;
                gameStatus.setType(game);
            } else if (!boss.isAlive() && bg.getMoveSum() < FrameConstant.BOSS_APPEARANCE && game == 2) {
                // BOSS没出现 就被敌机打死  输了
                gameStatus.setType(4);
            } else if (boss.isAlive() && bg.getMoveSum() < FrameConstant.BOSS_APPEARANCE) {
                //  BOSS活着  但是游戏还在继续 就死了 说明是被BOSS关被打死
                //  输了
                game = 4;
                gameStatus.setType(4);
            } else if (!boss.isAlive() && bg.getMoveSum() > FrameConstant.BOSS_APPEARANCE) {
                // BOSS死了  BOSS出现过
                // 赢了
                game = 2;
                gameStatus.setType(game);
            } else if (game == 5) {

            }
            gameStatus.draw(g);
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
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 点击开始
                game = 1;
                gameStatus.setType(game);
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
