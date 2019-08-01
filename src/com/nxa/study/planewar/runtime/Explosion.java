package com.nxa.study.planewar.runtime;

import com.nxa.study.planewar.base.BaseSprite;
import com.nxa.study.planewar.base.Drawable;
import com.nxa.study.planewar.base.Moveable;
import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;
import com.nxa.study.planewar.util.ImageMap;

import java.awt.Image;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Explosion extends BaseSprite implements  Drawable {

    private List<Image> expList = new ArrayList<>();
    private int index;

    public Explosion() {
        this(0, 0);
    }

    public Explosion(int x, int y) {
        super(x, y);
        init();
    }

    public void init() {
        for (int i = 1; i < 10; i++) {
            expList.add(ImageMap.getMap("e" + i));
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(expList.get(index++%45/5), getX(), getY(),  (expList.get(0).getWidth(null)),
                 (expList.get(0).getHeight(null) ), null);
        GameFrame gameFrame = DateStore.get("gameFrame");
        if (index >= 90) {
            gameFrame.explosions.remove(this);
        }
    }

}
