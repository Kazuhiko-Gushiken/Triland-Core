package com.enginnx.trilandcore;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrilandCore implements ModInitializer {
	public static final String MOD_ID = "triland-core";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					CommandManager.literal("dim")
							.requires(source -> source.hasPermissionLevel(4))
							.executes(context -> {
								ServerCommandSource source = context.getSource().withSilent();
								MinecraftServer server = source.getServer();

								server.getCommandManager().executeWithPrefix(source, "function triland:dim");

								return 1;
							})
			);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					CommandManager.literal("resources")
							.requires(source -> source.hasPermissionLevel(1))
							.executes(context -> {
								ServerCommandSource original = context.getSource();
								MinecraftServer server = original.getServer();

								ServerCommandSource elevated = server.getCommandSource().withLevel(2).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

								server.getCommandManager().executeWithPrefix(elevated, "function triland:buildingresources");

								return 1;
							})
			);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					CommandManager.literal("block")
							.requires(source -> source.hasPermissionLevel(4))
							.executes(context -> {
								ServerCommandSource source = context.getSource().withSilent();
								MinecraftServer server = source.getServer();

								server.getCommandManager().executeWithPrefix(source, "function triland:buildingblocks");

								return 1;
							})
							.then(CommandManager.literal("0")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"0\",\"italic\":false}]',block_state={level:\"0\"}]");
										server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"[\",\"color\":\"dark_red\"},{\"text\":\"System\",\"color\":\"red\"},{\"text\":\"]\",\"color\":\"dark_red\"},{\"text\":\"Use this to make floating carpets!\",\"color\":\"white\"}]");
										return 1;
							}))
							.then(CommandManager.literal("1")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"1\",\"italic\":false}]',block_state={level:\"1\"}]");
										return 1;
							}))
							.then(CommandManager.literal("2")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"2\",\"italic\":false}]',block_state={level:\"2\"}]");
										return 1;
							}))
							.then(CommandManager.literal("3")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"3\",\"italic\":false}]',block_state={level:\"3\"}]");
										return 1;
							}))
							.then(CommandManager.literal("4")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"4\",\"italic\":false}]',block_state={level:\"4\"}]");
										return 1;
							})).then(CommandManager.literal("5")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"5\",\"italic\":false}]',block_state={level:\"5\"}]");
										return 1;
							})).then(CommandManager.literal("6")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"6\",\"italic\":false}]',block_state={level:\"6\"}]");
										return 1;
							})).then(CommandManager.literal("7")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"7\",\"italic\":false}]',block_state={level:\"7\"}]");
										return 1;
							})).then(CommandManager.literal("8")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"8\",\"italic\":false}]',block_state={level:\"8\"}]");
										return 1;
							})).then(CommandManager.literal("9")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"9\",\"italic\":false}]',block_state={level:\"9\"}]");
										return 1;
							})).then(CommandManager.literal("10")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"10\",\"italic\":false}]',block_state={level:\"10\"}]");
										return 1;
							})).then(CommandManager.literal("11")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"11\",\"italic\":false}]',block_state={level:\"11\"}]");
										return 1;
							})).then(CommandManager.literal("12")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"12\",\"italic\":false}]',block_state={level:\"12\"}]");
										return 1;
							})).then(CommandManager.literal("13")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"13\",\"italic\":false}]',block_state={level:\"13\"}]");
										return 1;
							})).then(CommandManager.literal("14")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"14\",\"italic\":false}]',block_state={level:\"14\"}]");
										return 1;
							})).then(CommandManager.literal("15")
									.executes(context -> {
										ServerCommandSource original = context.getSource();
										MinecraftServer server = original.getServer();

										ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

										server.getCommandManager().executeWithPrefix(elevated, "give @p light[custom_name='[\"\",{\"text\":\"15\",\"italic\":false}]',block_state={level:\"15\"}]");
										server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"[\",\"color\":\"dark_red\"},{\"text\":\"System\",\"color\":\"red\"},{\"text\":\"]\",\"color\":\"dark_red\"},{\"text\":\"Use this sparingly. You don't want random bright lights coming from thin air!\",\"color\":\"white\"}]");
										return 1;
							}))


			);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					CommandManager.literal("clr")
							.requires(source -> source.hasPermissionLevel(4))
							.executes(context -> {
								ServerCommandSource original = context.getSource();
								MinecraftServer server = original.getServer();

								ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition());

								server.getCommandManager().executeWithPrefix(elevated, "weather clear");

								return 1;
							})
			);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					CommandManager.literal("rain")
							.requires(source -> source.hasPermissionLevel(4))
							.executes(context -> {
								ServerCommandSource original = context.getSource();
								MinecraftServer server = original.getServer();

								ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition());

								server.getCommandManager().executeWithPrefix(elevated, "weather rain");

								return 1;
							})
			);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					CommandManager.literal("thun")
							.requires(source -> source.hasPermissionLevel(4))
							.executes(context -> {
								ServerCommandSource original = context.getSource();
								MinecraftServer server = original.getServer();

								ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition());

								server.getCommandManager().executeWithPrefix(elevated, "weather thunder");

								return 1;
							})
			);
		});

		LOGGER.info("Loaded Custom Commands for Triland!");
	}
}