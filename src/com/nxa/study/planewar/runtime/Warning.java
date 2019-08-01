package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Warning extends BaseSprite implements Drawable {

    private List<Image> imageList = new ArrayList<>();
    private int index = 0;

    public Warning(int x, int y) {
        super(x, y);
        init();
    }

    public void init() {
        for (int i = 1; i < 10; i++) {
            imageList.add(ImageMap.getMap("w" + i));
        }
    }

    public Warning() {
        this(0, 0);
    }

    @Override
    public void draw(Graphics g) {
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (gameFrame.bg.getMoveSum() > FrameConstant.BOSS_APPEARANCE - 300 && !gameFrame.boss.isAlive()) {
            g.drawImage(imageList.get(index++ % 45 / 5), getX(), getY(), (imageList.get(0).getWidth(null)),
                    (imageList.get(0).getHeight(null)), null);
            if (index>135){
                index = 0;
            }
        }
    }
}
