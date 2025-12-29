package com.enginnx.trilandcore.lighting.config;

import java.util.List;

public class LightingProfile {
    public ProfileConfig config;

    public static class ProfileConfig {
        public int minDelayTicks;
        public int maxDelayTicks;
        public FlickerConfig flicker;
    }

    public static class FlickerConfig {
        public int lampsPerFlicker;
        public int minFlickerLamps;
        public double selectionChance;
        public FlickerCount flickerCount;
        public int onTicks;
        public int offTicks;
    }

    public static class FlickerCount {
        public int min;
        public int max;
    }
}
