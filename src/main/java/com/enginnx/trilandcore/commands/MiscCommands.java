package com.enginnx.trilandcore.commands;

import com.enginnx.trilandcore.lighting.LightingConfigManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class MiscCommands {

    public static void register() {
        
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
					CommandManager.literal("commands")
							.requires(source -> source.hasPermissionLevel(4))
							.executes(context -> {
								ServerCommandSource original = context.getSource();
								MinecraftServer server = original.getServer();

								ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p {\"text\":\"Command List\",\"bold\":true,\"color\":\"gray\"}");
								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"/dim \",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/dim\"}},{\"text\":\" - \",\"color\":\"white\"},{\"text\":\"List the building/development worlds.\",\"color\":\"gray\"}]");
								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"/resources \",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/resources\"}},{\"text\":\" - \",\"color\":\"white\"},{\"text\":\"List the building resources collected.\",\"color\":\"gray\"}]");
								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"/block [0-15]\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/block\"}},{\"text\":\" - \",\"color\":\"white\"},{\"text\":\"Give light blocks, with light level 0 to 15.\",\"color\":\"gray\"}]");
								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"/clr\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/clr\"}},{\"text\":\" - \",\"color\":\"white\"},{\"text\":\"Set weather to clear.\",\"color\":\"gray\"}]");
								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"/rain\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/rain\"}},{\"text\":\" - \",\"color\":\"white\"},{\"text\":\"Set weather to rain.\",\"color\":\"gray\"}]");
								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"/thun\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/thun\"}},{\"text\":\" - \",\"color\":\"white\"},{\"text\":\"Set weather to thunder.\",\"color\":\"gray\"}]");
								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"/lag\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/lag\"}},{\"text\":\" - \",\"color\":\"white\"},{\"text\":\"Run a 30 second server profiler.\",\"color\":\"gray\"}]");
								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"/clag\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/clag\"}},{\"text\":\" - \",\"color\":\"white\"},{\"text\":\"Run a 30 second client profiler.\",\"color\":\"gray\"}]");
								server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"/head\",\"color\":\"white\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"/head\"}},{\"text\":\" - \",\"color\":\"white\"},{\"text\":\"Open custom head database.\",\"color\":\"gray\"}]");
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
										server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"[\",\"color\":\"dark_red\"},{\"text\":\"System\",\"color\":\"red\"},{\"text\":\"] \",\"color\":\"dark_red\"},{\"text\":\"Use this to make floating carpets!\",\"color\":\"white\"}]");
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
										server.getCommandManager().executeWithPrefix(elevated, "tellraw @p [\"\",{\"text\":\"[\",\"color\":\"dark_red\"},{\"text\":\"System\",\"color\":\"red\"},{\"text\":\"] \",\"color\":\"dark_red\"},{\"text\":\"Use this sparingly. You don't want random bright lights coming from thin air!\",\"color\":\"white\"}]");
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

								ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

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

								ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

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

								ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition()).withSilent();

								server.getCommandManager().executeWithPrefix(elevated, "weather thunder");

								return 1;
							})
			);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					CommandManager.literal("lag")
							.requires(source -> source.hasPermissionLevel(4))
							.executes(context -> {
								ServerCommandSource original = context.getSource();
								MinecraftServer server = original.getServer();

								ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition());

								server.getCommandManager().executeWithPrefix(elevated, "spark profiler start --timeout 30");

								return 1;
							})
			);
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			dispatcher.register(
					CommandManager.literal("clag")
							.requires(source -> source.hasPermissionLevel(4))
							.executes(context -> {
								ServerCommandSource original = context.getSource();
								MinecraftServer server = original.getServer();

								ServerCommandSource elevated = server.getCommandSource().withLevel(4).withWorld(original.getWorld()).withPosition(original.getPosition());

								server.getCommandManager().executeWithPrefix(elevated, "cspark profiler start --timeout 30");

								return 1;
							})
			);
		});
    }
    
}
