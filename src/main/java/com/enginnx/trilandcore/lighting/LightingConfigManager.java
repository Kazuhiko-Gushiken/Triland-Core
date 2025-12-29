package com.enginnx.trilandcore.lighting;

import com.enginnx.trilandcore.TrilandCore;
import com.enginnx.trilandcore.lighting.config.LightingProfile;
import com.enginnx.trilandcore.lighting.config.LightingRootConfig;
import com.enginnx.trilandcore.lighting.config.LightingSeason;
import com.enginnx.trilandcore.lighting.config.LightingStreet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LightingConfigManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //root paths
    public static final Path TRILAND_ROOT = FabricLoader.getInstance().getConfigDir().resolve("trilandcore");

    public static final Path LIGHTING_ROOT = TRILAND_ROOT.resolve("lighting");

    //loaded data
    public static LightingRootConfig ROOT;

    public static final Map<String, LightingProfile> PROFILES = new HashMap<>();
    public static final Map<String, LightingSeason> SEASONS = new HashMap<>();
    public static final Map<String, LightingStreet> STREETS = new HashMap<>();

    private LightingConfigManager() {}

    private static void ensureDirectories() {
        try {
            Files.createDirectories(LIGHTING_ROOT.resolve("profiles"));
            Files.createDirectories(LIGHTING_ROOT.resolve("seasons"));
            Files.createDirectories(LIGHTING_ROOT.resolve("streets"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create lighting config directories", e);
        }
    }

    private static void loadRoot() {
        Path file = LIGHTING_ROOT.resolve("lighting.json");

        try {
            if (!Files.exists(file)) {
                Files.writeString(file, DEFAULT_LAMPS_JSON);
            }

            ROOT = GSON.fromJson(Files.readString(file), LightingRootConfig.class);

            if (ROOT == null) {
                throw new RuntimeException("lighting.json parsed to null");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load lighting.json", e);
        }
    }

    private static final String DEFAULT_LAMPS_JSON = """
            {
                "profiles": ["downtown", "industrial", "residential"],
                "seasons": ["summer", "winter", "spring", "autumn"],
                "streets": ["main_street"]
            }
            """;

    private static void loadProfiles() {
        PROFILES.clear();

        for (String id : ROOT.profiles) {
            Path file = LIGHTING_ROOT
                    .resolve("profiles")
                    .resolve(id + ".json");

            if (!Files.exists(file)) {
                TrilandCore.LOGGER.warn(
                        "Lighting profile '{}' not found at {}, skipping",
                        id,
                        file.toAbsolutePath()
                );
                continue;
            }


            try {
                LightingProfile profile = GSON.fromJson(Files.readString(file), LightingProfile.class);

                if (profile == null) {
                    throw new IllegalStateException("Parsed to null");
                }

                PROFILES.put(id, profile);
            } catch (Exception e) {
                TrilandCore.LOGGER.warn(
                        "Failed to load lighting profile '{}', skipping. Reason: {}",
                        id,
                        e.getMessage()
                );
            }
        }

        if (PROFILES.isEmpty()) {
            TrilandCore.LOGGER.warn("No lighting profiles loaded!");
        }
    }

    private static void loadSeasons() {
        SEASONS.clear();

        for (String id : ROOT.seasons) {
            Path file = LIGHTING_ROOT
                    .resolve("seasons")
                    .resolve(id + ".json");

            if (!Files.exists(file)) {
                TrilandCore.LOGGER.warn(
                        "Lighting season '{}' not found at {}, skipping",
                        id,
                        file.toAbsolutePath()
                );
                continue;
            }

            try {
                LightingSeason season = GSON.fromJson(Files.readString(file), LightingSeason.class);

                if (season == null) {
                    throw new IllegalStateException("Parsed to null");
                }

                SEASONS.put(id, season);
            } catch (Exception e) {

                TrilandCore.LOGGER.warn(
                        "Failed to load lighting season '{}', skipping. Reason: {}",
                        id,
                        e.getMessage()
                );
            }
        }

        if (SEASONS.isEmpty()) {
            TrilandCore.LOGGER.warn("No lighting seasons loaded!");
        }
    }

    private static void loadStreets() {
        STREETS.clear();

        for (String id : ROOT.streets) {
            Path file = LIGHTING_ROOT
                    .resolve("streets")
                    .resolve(id + ".json");

            if (!Files.exists(file)) {
                TrilandCore.LOGGER.warn(
                        "Lighting street '{}' not found at {}, skipping",
                        id,
                        file.toAbsolutePath()
                );
                continue;
            }

            try {
                LightingStreet street = GSON.fromJson(Files.readString(file), LightingStreet.class);

                if (street == null) {
                    throw new IllegalStateException("Parsed to null");
                }

                if (!PROFILES.containsKey(street.profile)) {
                    TrilandCore.LOGGER.warn(
                            "Street '{}' references missing profile '{}', skipping street",
                            id,
                            street.profile
                    );
                    continue;
                }

                STREETS.put(id, street);
            } catch (Exception e) {
                TrilandCore.LOGGER.warn(
                        "Failed to load lighting street '{}', skipping. Reason: {}",
                        id,
                        e.getMessage()
                );
            }
        }

        if (STREETS.isEmpty()) {
            TrilandCore.LOGGER.warn("No lighting streets loaded!");
        }
    }

    public static void loadAll() {
        ensureDirectories();

        loadRoot();
        loadProfiles();
        loadSeasons();
        loadStreets();

        TrilandCore.LOGGER.info(
                "Loaded lighting config: {} profiles, {} seasons, {} streets",
                PROFILES.size(), SEASONS.size(), STREETS.size()
        );

        debugDump();
    }

    public static void debugDump() {
        TrilandCore.LOGGER.info("=== Lighting Config Debug Dump ===");

        TrilandCore.LOGGER.info("Profiles:");
        for (var entry : PROFILES.entrySet()) {
            String id = entry.getKey();
            LightingProfile profile = entry.getValue();

            TrilandCore.LOGGER.info(" - {}", id);

            if (profile.config != null) {
                TrilandCore.LOGGER.info(
                        "    delay: {}-{} ticks",
                        profile.config.minDelayTicks,
                        profile.config.maxDelayTicks
                );

                if (profile.config.flicker != null) {
                    var f = profile.config.flicker;
                    TrilandCore.LOGGER.info(
                            "    flicker: lampsPerFlicker={}, minFlickerLamps={}, selectionChance={}, count=({},{}), onTicks={}, offTicks={}",
                            f.lampsPerFlicker,
                            f.minFlickerLamps,
                            f.selectionChance,
                            f.flickerCount.min,
                            f.flickerCount.max,
                            f.onTicks,
                            f.offTicks
                    );
                }
            }
        }

        TrilandCore.LOGGER.info("Seasons:");
        for (var entry : SEASONS.entrySet()) {
            String id = entry.getKey();
            LightingSeason season = entry.getValue();

            var f = season;
            TrilandCore.LOGGER.info(" - id={}, minDelayModifier={}, maxDelayModifier={}, lampsPerFlickerModifier={}, minFlickerLampsModifier={}, selectionChanceModifier={}, flickerCountModifier=({},{})",
                    id,
                    f.minDelayModifier,
                    f.maxDelayModifier,
                    f.lampsPerFlickerModifier,
                    f.minFlickerLampsModifier,
                    f.selectionChanceModifier,
                    f.flickerCountMinModifier,
                    f.flickerCountMaxModifier
                    );
        }

        TrilandCore.LOGGER.info("Streets:");
        for (var entry: STREETS.entrySet()) {
            String id = entry.getKey();
            LightingStreet street = entry.getValue();

            TrilandCore.LOGGER.info(" - {} (profile={})", id, street.profile);

            for (LightingStreet.LampSegment seg : street.lampSegments) {
                TrilandCore.LOGGER.info("    index {}", seg.index);

                if (seg.lamps != null) {
                    for (int i = 0; i < seg.lamps.size(); i++) {
                        List<Integer> pos = seg.lamps.get(i);
                        TrilandCore.LOGGER.info(
                                "        lamp[{}] = ({}, {}, {})",
                                i,
                                pos.get(0),
                                pos.get(1),
                                pos.get(2)
                        );
                    }
                } else {
                    TrilandCore.LOGGER.info("        lamps = null");
                }

                if (seg.lights != null) {
                    for (int i = 0; i < seg.lights.size(); i++) {
                        List<Integer> pos = seg.lights.get(i);
                        TrilandCore.LOGGER.info(
                                "        light[{}] = ({}, {}, {})",
                                i,
                                pos.get(0),
                                pos.get(1),
                                pos.get(2)
                        );
                    }
                } else {
                    TrilandCore.LOGGER.info("        lights = null");
                }
            }
        }

        TrilandCore.LOGGER.info("=== End Lighting Config Dump ===");
    }
}
