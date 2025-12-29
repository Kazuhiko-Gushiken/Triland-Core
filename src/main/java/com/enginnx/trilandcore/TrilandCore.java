package com.enginnx.trilandcore;

import com.enginnx.trilandcore.lighting.LightingConfigManager;
// import com.enginnx.trilandcore.util.TopClock;
import net.fabricmc.api.DedicatedServerModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
// import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.apache.logging.log4j.core.jmx.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enginnx.trilandcore.commands.MiscCommands;
import com.enginnx.trilandcore.commands.OocCommands;

public class TrilandCore implements DedicatedServerModInitializer {
	public static final String MOD_ID = "triland-core";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeServer() {

		OocCommands.register();

		MiscCommands.register();

		// ServerTickEvents.END_SERVER_TICK.register(TopClock.INSTANCE::onServerTick);

		ServerLifecycleEvents.SERVER_STARTING.register(server -> {
			LightingConfigManager.loadAll();
		});

		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, manager, success) -> {
			if (success) {
				LightingConfigManager.loadAll();
			}
		});



		LOGGER.info("Loaded Custom Commands for Triland!");
	}
}