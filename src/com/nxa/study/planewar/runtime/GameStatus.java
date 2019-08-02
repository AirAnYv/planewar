package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GameStatus extends BaseSprite implements Drawable {

    private List<Image> imageList = new ArrayList<>();
    private int index;

    private int type;

    public GameStatus() {
        this(0, 0);
    }

    public GameStatus(int x, int y) {
        super(x, y);
        init();
    }

    private void init() {
        for (int i = 1; i < 10; i++) {
            imageList.add(ImageMap.getMap("g0" + i));
        }
        for (int i = 10; i < 25; i++) {
            imageList.add(ImageMap.getMap("g。" + i));
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imageList.get(index++ / 5), getX(), getY(), (int) (imageList.get(0).getWidth(null) / 1.5),
                (int) (imageList.get(0).getHeight(null) / 1.5), null);
        if (index >= imageList.size() * 5) {
            index = 0;
        }
    }
}
