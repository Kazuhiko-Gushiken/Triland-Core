package com.enginnx.trilandcore.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

public class OocCommands {

    private static void sendOocMessage(ServerPlayerEntity player, String message) {
        Text output = Text.literal("[").formatted(Formatting.DARK_GRAY)
                .append(Text.literal("OOC").formatted(Formatting.GRAY).formatted(Formatting.BOLD))
                .append(Text.literal("] ").formatted(Formatting.DARK_GRAY))
                .append(Text.literal("\uE1B2 ").formatted(Formatting.WHITE))
                .append(Text.literal(player.getName().getString() + ": ").styled(style -> style.withColor(0x9dba9a)))
                .append(Text.literal(message).formatted(Formatting.GRAY).formatted(Formatting.BOLD));

        Objects.requireNonNull(player.getServer()).getPlayerManager().broadcast(output, false);
    }

    public static void sendLoocMessage(ServerPlayerEntity sender, String message) {
        Text output = Text.literal("[").formatted(Formatting.DARK_GRAY)
                .append(Text.literal("LOOC").formatted(Formatting.GRAY))
                .append(Text.literal("] ").formatted(Formatting.DARK_GRAY))
                .append(Text.literal("\uE1B2 ").formatted(Formatting.WHITE))
                .append(Text.literal(sender.getName().getString() + ": ").styled(style -> style.withColor(0x9dba9a)))
                .append(Text.literal(message).formatted(Formatting.GRAY));

        var server = sender.getServer();
        if (server == null) return;

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

            if (player.getWorld() != sender.getWorld()) continue;

            if (player.squaredDistanceTo(sender) <= 16.0 * 16.0) {
                player.sendMessage(output, false);
            }
        }
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    CommandManager.literal("ooc")
                            .then(CommandManager.argument("message", StringArgumentType.greedyString())
                                    .executes(context -> {
                                        ServerPlayerEntity player = context.getSource().getPlayer();
                                        String message = StringArgumentType.getString(context, "message");
                                        assert player != null;
                                        sendOocMessage(player, message);
                                        return 1;
                                    }))
            );
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    CommandManager.literal("looc")
                            .then(CommandManager.argument("message", StringArgumentType.greedyString())
                                    .executes(context -> {
                                        ServerPlayerEntity player = context.getSource().getPlayer();
                                        String message = StringArgumentType.getString(context, "message");
                                        assert player != null;
                                        sendLoocMessage(player, message);
                                        return 1;
                                    }))
            );
        });
    }


}
