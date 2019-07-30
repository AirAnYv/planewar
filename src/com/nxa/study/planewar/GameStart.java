package com.nxa.study.planewar;

import com.nxa.study.planewar.main.GameFrame;
import com.nxa.study.planewar.util.DateStore;

public class GameStart {
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        DateStore.put("gameFrame", gameFrame);
        gameFrame.init();
    }
}
