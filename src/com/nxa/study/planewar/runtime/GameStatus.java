package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GameStatus extends BaseSprite implements Drawable {

    private List<Image> gameOverList = new ArrayList<>();   // 游戏结束显示
    private List<Image> winList = new ArrayList<>();    // 获胜界面
    private List<Image> timeOutList = new ArrayList<>();  // 超时警告
    private List<Image> readyList = new ArrayList<>(); // 准备
    private int index;

    private int type;  //  1为游戏准备  2游戏结束（赢了） 3游戏结束（时间超过 输了） 4游戏结束（输了）

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public GameStatus() {
        this(0, 0);
    }

    public GameStatus(int x, int y) {
        super(x, y);
        init();
    }

    private void init() {
        /**
         * 游戏结束
         */
        for (int i = 1; i < 10; i++) {
            gameOverList.add(ImageMap.getMap("g0" + i));
        }
        for (int i = 10; i < 25; i++) {
            gameOverList.add(ImageMap.getMap("g" + i));
        }

        /**
         *  游戏赢了
         */
        for (int i = 1; i < 10; i++) {
            winList.add(ImageMap.getMap("gw0" + i));
        }
        for (int i = 10; i < 15; i++) {
            winList.add(ImageMap.getMap("gw" + i));
        }

        /**
         * 游戏超时
         */
        for (int i = 1; i < 10; i++) {
            timeOutList.add(ImageMap.getMap("gl0" + i));
        }
        for (int i = 10; i < 14; i++) {
            timeOutList.add(ImageMap.getMap("gl" + i));
        }

        /**
         * 游戏准备
         */
        for (int i = 0; i < 10; i++) {
            readyList.add(ImageMap.getMap("gr0" + i));
        }
        for (int i = 10; i < 18; i++) {
            readyList.add(ImageMap.getMap("gr" + i));
        }

    }

    @Override
    public void draw(Graphics g) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (type == 1) {
            // 准备
            g.drawImage(readyList.get(index++ / 10), getX() + 150, getY(), (readyList.get(0).getWidth(null)),
                    (readyList.get(0).getHeight(null)), null);
            if (index >= readyList.size() * 10) {
                gameFrame.gameOver = false;
                gameFrame.game = 2;
                type = 2;
                index = 0;
            }
        } else if (type == 2) {
            // 赢了
            g.drawImage(winList.get(index++ / 5), getX(), getY(), (winList.get(0).getWidth(null)),
                    (winList.get(0).getHeight(null)), null);
            if (index >= winList.size() * 5) {
                index = 0;
                gameFrame.game = 5;
            }
        } else if (type == 3) {
            // 超时
            g.drawImage(timeOutList.get(index++ / 5), getX(), getY(), (timeOutList.get(0).getWidth(null)),
                    (timeOutList.get(0).getHeight(null)), null);
            if (index >= timeOutList.size() * 5) {
                index = 0;
                gameFrame.game = 5;
            }
        } else if (type == 4) {
            //  输了
            g.drawImage(gameOverList.get(index++ / 5), getX(), getY(), (int) (gameOverList.get(0).getWidth(null) / 1.5),
                    (int) (gameOverList.get(0).getHeight(null) / 1.5), null);
            if (index >= gameOverList.size() * 5) {
                index = 0;
                gameFrame.game = 5;
            }
        }

    }
}
