package com.enginnx.trilandcore.util;

import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
/*
public class TopClock {

    public static final TopClock INSTANCE = new TopClock();

    //config
    private static final int TICKS_PER_CUSTOM_MINUTE = 300; //15s @ 20tps
    private static final boolean SLOW_SKY_TO_6H = true; //+1 dayTime every 18 ticks

    // time states
    private int tickCounterMinute = 0;
    private int minutes = 0; //0..59
    private int hours = 0; //0..23
    private int days = 0; //0..6 (Mon..Sun)

    //elapsed natural minutes
    private long elapsedMinutes = 0;

    // sky pacing
    private int tickCounterSky = 0;

    private final ServerBossBar bossBar = new ServerBossBar(
        Text.literal(""),
        BossBar.Color.WHITE,
        BossBar.Style.PROGRESS
    );

    public void onServerTick(MinecraftServer server) {
        //15s cadence for 1 "natrual" minute
        tickCounterMinute++;
        if (tickCounterMinute >= TICKS_PER_CUSTOM_MINUTE) {
            tickCounterMinute = 0;
            advanceCustomMinute(server);
        }

        // sky pacing to 6-hour mc day
        if (SLOW_SKY_TO_6H) {
            tickCounterSky++;
            if (tickCounterSky >= 18) {
                tickCounterSky = 0;
                ServerWorld overworld = server.getOverworld();
                if (overworld != null) {
                    overworld.setTimeOfDay(overworld.getTimeOfDay() + 1L);
                }
            }
        }
    }


    private void advanceCustomMinute(MinecraftServer server) {
        minutes++;
        if (minutes >= 60) {
            minutes = 0;
            hours++;
            if (hours >= 24) {
                hours = 0;
                days = (days + 1) % 7;
            }
        }

        elapsedMinutes++;

        if (hours == 0 && minutes ==0) snapMidnight(server);

        refreshBossBar();
    }
}
*/