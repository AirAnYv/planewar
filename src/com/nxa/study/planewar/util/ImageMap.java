package com.nxa.study.planewar.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\background\\bg.jpg"));

        map.put("my01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\plane\\my1.png"));

        map.put("mb01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bullet\\mb1.png"));
        map.put("target", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bullet\\target.png"));

        map.put("ep01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\plane\\ep1.png"));
        map.put("ep02", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\plane\\ep2.png"));
        map.put("ep03", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\plane\\ep3.png"));
        map.put("ep04", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\plane\\ep4.png"));

        map.put("eb01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bullet\\eb1.png"));
        map.put("eb02", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bullet\\eb2.png"));

        map.put("blood", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\prop\\blood.png"));


        map.put("boss01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\boss\\boss_A_01.png"));
        map.put("boss02", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\boss\\boss_A_02.png"));
        map.put("boss03", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\boss\\boss_A_03.png"));
        map.put("boss04", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\boss\\boss_A_04.png"));
        map.put("boss05", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\boss\\boss_A_05.png"));
        map.put("boss06", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\boss\\boss_A_06.png"));
        map.put("boss07", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\boss\\boss_A_07.png"));
        map.put("boss08", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\boss\\boss_A_08.png"));
        map.put("boss09", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\boss\\boss_A_09.png"));


        map.put("e1", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e1.png"));
        map.put("e2", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e2.png"));
        map.put("e3", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e3.png"));
        map.put("e4", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e4.png"));
        map.put("e5", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e5.png"));
        map.put("e6", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e6.png"));
        map.put("e7", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e7.png"));
        map.put("e8", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e8.png"));
        map.put("e9", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e9.png"));

    }

    public static Image getMap(String key) {
        return map.get(key);
    }
}
