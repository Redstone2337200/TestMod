package com.redstone233.test.mod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.ColorArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import static net.minecraft.server.command.CommandManager.literal;

import static net.minecraft.server.command.CommandManager.argument;

public final class TestModCommands {

    

    @SuppressWarnings("unchecked")
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("test")
            .requires(source -> source.hasPermissionLevel(0))
            .then(argument("color", ColorArgumentType.color())
                .then(argument("message", greedyString())
                    .executes(ctx -> test((ServerCommandSource) ctx.getSource(), getColor(ctx,"color"), getString(ctx,"massage")))
                )
            )          
        );
        dispatcher.register(literal("info")
            .requires(src -> src.hasPermissionLevel(2))
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

    public static int test(ServerCommandSource source, Formatting formatting, String message) {
        @SuppressWarnings("unused")
        final MutableText text = net.minecraft.text.Text.literal(message).formatted(formatting);
        return Command.SINGLE_SUCCESS;
    }
}
