package com.nxa.study.planewar.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {

    private static final Map<String, Image> map = new HashMap<>();

    static {
        map.put("bg01", ImageUtil.getImage("com\\nxa\\study\\planewar\\images\\background.png"));
    }

    public static Image getMap(String key) {
        return map.get(key);
    }
}
