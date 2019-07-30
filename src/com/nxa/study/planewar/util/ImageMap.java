package com.nxa.study.planewar.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\background\\bg.png"));

        map.put("my01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\plane\\my1.png"));

        map.put("mb01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bullet\\mb1.png"));

        map.put("ep01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\plane\\ep1.png"));

        map.put("eb01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\bullet\\eb1.png"));
    }

    public static Image getMap(String key) {
        return map.get(key);
    }
}
