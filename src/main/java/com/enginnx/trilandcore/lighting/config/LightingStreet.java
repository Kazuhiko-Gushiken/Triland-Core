package com.enginnx.trilandcore.lighting.config;


import java.util.List;

public class LightingStreet {
    public String profile;
    public List<LampSegment> lampSegments;

    public static class LampSegment {
        public int index;

        public List<List<Integer>> lamps;
        public List<List<Integer>> lights;
    }
}
