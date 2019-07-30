package com.nxa.study.planewar.main;

import com.nxa.study.planewar.constant.FrameConstant;
import com.nxa.study.planewar.runtime.Background;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {

    Background bg = new Background();
    @Override
    public void paint(Graphics g) {
        bg.draw(g);
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
