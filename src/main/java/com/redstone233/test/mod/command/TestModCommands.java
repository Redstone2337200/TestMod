package com.redstone233.test.mod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.command.argument.ColorArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public final class TestModCommands {
    
    @SuppressWarnings("unused")
    private static final char UNICODE_TICK = '✔'; 
    @SuppressWarnings("unused")
    private static final char UNICODE_CROSS = 'X';

    @SuppressWarnings("unchecked")
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("info")
            .requires(source -> source.hasPermissionLevel(2))
            .then(argument("color", ColorArgumentType.color())
                .then(argument("message", greedyString())
                    .executes(ctx -> info((ServerCommandSource) ctx.getSource(), getColor(ctx,"color"), getString(ctx,"massage")))
                )
            )          
        );
    }

    @SuppressWarnings("rawtypes")
    private static String getString(CommandContext ctx, String string) {
        throw new UnsupportedOperationException("Unimplemented method 'getString'");
    }

    private static Formatting getColor(@SuppressWarnings("rawtypes") CommandContext ctx, String string) {

        throw new UnsupportedOperationException("Unimplemented method 'getColor'");
    }

    @SuppressWarnings("rawtypes")
    private static ArgumentType greedyString() {
        throw new UnsupportedOperationException("Unimplemented method 'greedyString'");
    }

    public static int info(ServerCommandSource source, Formatting formatting, String message) {
        @SuppressWarnings("unused")
        final MutableText text = net.minecraft.text.Text.literal(message).formatted(formatting);
        return Command.SINGLE_SUCCESS;
    }
}
