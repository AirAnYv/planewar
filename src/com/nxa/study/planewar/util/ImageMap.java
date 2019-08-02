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


        map.put("bb01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bossbullet\\01.png"));
        map.put("bb02", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bossbullet\\02.png"));
        map.put("bb03", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bossbullet\\03.png"));
        map.put("bb04", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bossbullet\\04.png"));
        map.put("bb05", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bossbullet\\05.png"));


        map.put("e1", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e1.png"));
        map.put("e2", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e2.png"));
        map.put("e3", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e3.png"));
        map.put("e4", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e4.png"));
        map.put("e5", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e5.png"));
        map.put("e6", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e6.png"));
        map.put("e7", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e7.png"));
        map.put("e8", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e8.png"));
        map.put("e9", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\explosion\\e9.png"));


        map.put("w1", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\warning\\w1.png"));
        map.put("w2", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\warning\\w2.png"));
        map.put("w3", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\warning\\w3.png"));
        map.put("w4", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\warning\\w4.png"));
        map.put("w5", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\warning\\w5.png"));
        map.put("w6", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\warning\\w6.png"));
        map.put("w7", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\warning\\w7.png"));
        map.put("w8", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\warning\\w8.png"));
        map.put("w9", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\warning\\w9.png"));

        /**
         *  画游戏结束的图案
         */
        map.put("g01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\0.png"));
        map.put("g02", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\1.png"));
        map.put("g03", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\2.png"));
        map.put("g04", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\3.png"));
        map.put("g05", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\4.png"));
        map.put("g06", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\5.png"));
        map.put("g07", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\6.png"));
        map.put("g08", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\7.png"));
        map.put("g09", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\8.png"));
        map.put("g10", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\9.png"));
        map.put("g11", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\10.png"));
        map.put("g12", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\11.png"));
        map.put("g13", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\12.png"));
        map.put("g14", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\13.png"));
        map.put("g15", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\14.png"));
        map.put("g16", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\15.png"));
        map.put("g17", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\16.png"));
        map.put("g18", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\17.png"));
        map.put("g19", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\18.png"));
        map.put("g20", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\19.png"));
        map.put("g21", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\20.png"));
        map.put("g22", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\21.png"));
        map.put("g23", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\22.png"));
        map.put("g24", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\gamestatus\\23.png"));

    }

    public static Image getMap(String key) {
        return map.get(key);
    }
}
