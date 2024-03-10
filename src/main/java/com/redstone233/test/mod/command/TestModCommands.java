package com.redstone233.test.mod.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.redstone233.test.mod.TestModInfos;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.ServerTickManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import static net.minecraft.server.command.CommandManager.literal;

import static net.minecraft.server.command.CommandManager.argument;

public class TestModCommands {

    public static String[] typeArray = { "trifunc", "deffunc" };
    public static String[] mainArray = { "book", "text" };
    public static String[] nameArray = { "sin", "cos", "tan", "asin", "acos", "atan" };
    public static String[] infoArray = { "abs", "sqrt", "cbrt", "ceil", "floor", "round" };

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal("nbt")
            .requires(src -> src.hasPermissionLevel(4))
            .then(argument("slot", IntegerArgumentType.integer()))
            .requires(src -> src.hasPermissionLevel(4))
            .executes(run -> getSuccess(IntegerArgumentType.getInteger(run, "slot"),
                run.getSource().getPlayer())
            )
        );

        dispatcher.register(literal("info")
            .requires(c -> c.hasPermissionLevel(4))
            .then(literal(mainArray[0])
                .executes(b -> executeStepBook(b.getSource()))
                .then(argument("value", IntegerArgumentType.integer(TestModInfos.TestModValues.min,TestModInfos.TestModValues.max)))
                .executes(run -> getText(mainArray[0], IntegerArgumentType.getInteger(run, "value"),
                    run.getSource().getPlayer())
                )
            )
            .then(literal(mainArray[1])
                .executes(a -> executeStepText(a.getSource()))
                .then(argument("value", IntegerArgumentType.integer(TestModInfos.TestModValues.min,TestModInfos.TestModValues.max)))
                .executes(run -> getText(mainArray[1], IntegerArgumentType.getInteger(run, "value"),
                    run.getSource().getPlayer())
                )
            )
        );

        dispatcher.register(literal("math")
            .requires(t -> t.hasPermissionLevel(2))
            .then(literal(typeArray[0]))
            .executes(a -> executeStepTrig(a.getSource()))
                .then(literal(typeArray[0])).executes(b -> executeStepTrig(b.getSource()))
                .then(argument("angle", IntegerArgumentType.integer())
                    .executes(run -> runTrig(typeArray[0], nameArray[0],
                        IntegerArgumentType.getInteger(run, "angle"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(nameArray[1])).executes(b -> executeStepTrig(b.getSource()))
                .then(argument("angle", IntegerArgumentType.integer())
                    .executes(run -> runTrig(typeArray[0], nameArray[1],
                        IntegerArgumentType.getInteger(run, "angle"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(nameArray[2])).executes(b -> executeStepTrig(b.getSource()))
                .then(argument("angle", IntegerArgumentType.integer())
                    .executes(run -> runTrig(typeArray[0], nameArray[2],
                        IntegerArgumentType.getInteger(run, "angle"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(nameArray[3])).executes(b -> executeStepTrig(b.getSource()))
                .then(argument("angle", IntegerArgumentType.integer())
                    .executes(run -> runTrig(typeArray[0], nameArray[3],
                        IntegerArgumentType.getInteger(run, "angle"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(nameArray[4])).executes(b -> executeStepTrig(b.getSource()))
                .then(argument("angle", IntegerArgumentType.integer())
                    .executes(run -> runTrig(typeArray[0], nameArray[4],
                        IntegerArgumentType.getInteger(run, "angle"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(nameArray[5])).executes(b -> executeStepTrig(b.getSource()))
                    .then(argument("angle", IntegerArgumentType.integer())
                    .executes(run -> runTrig(typeArray[0], nameArray[5],
                        IntegerArgumentType.getInteger(run, "angle"),
                            run.getSource().getPlayer())
                )
            )
            .requires(src -> src.hasPermissionLevel(2))
            .then(literal(typeArray[1]))
            .executes(a -> executeStep(a.getSource()))
                .then(literal(infoArray[0])).executes(b -> executeStep(b.getSource()))
                .then(argument("value", IntegerArgumentType.integer())
                    .executes(run -> runDefe(typeArray[1], infoArray[0],
                        IntegerArgumentType.getInteger(run, "value"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(infoArray[1])).executes(b -> executeStep(b.getSource()))
                .then(argument("value", IntegerArgumentType.integer())
                    .executes(run -> runDefe(typeArray[1], infoArray[1],
                        IntegerArgumentType.getInteger(run, "value"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(infoArray[2])).executes(b -> executeStep(b.getSource()))
                .then(argument("value", IntegerArgumentType.integer())
                    .executes(run -> runDefe(typeArray[2], infoArray[1],
                        IntegerArgumentType.getInteger(run, "value"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(infoArray[3])).executes(b -> executeStep(b.getSource()))
                .then(argument("value", IntegerArgumentType.integer())
                    .executes(run -> runDefe(typeArray[1], infoArray[3],
                        IntegerArgumentType.getInteger(run, "value"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(infoArray[4])).executes(b -> executeStep(b.getSource()))
                .then(argument("value", IntegerArgumentType.integer())
                    .executes(run -> runDefe(typeArray[1], infoArray[4],
                        IntegerArgumentType.getInteger(run, "value"),
                            run.getSource().getPlayer())
                        )
                    )
                .then(literal(infoArray[5])).executes(b -> executeStep(b.getSource()))
                    .then(argument("value", IntegerArgumentType.integer())
                    .executes(run -> runDefe(typeArray[1], infoArray[5],
                        IntegerArgumentType.getInteger(run, "value"),
                            run.getSource().getPlayer())
                )
            )
        );
    }

    private static int executeStepTrig(ServerCommandSource source) {
        ServerTickManager serverTickManager = source.getServer().getTickManager();
        boolean bl = serverTickManager.stopStepping();
        if (bl) {
        source.sendFeedback(() -> {
            return Text.translatable("commands.math.trip.success");
        }, true);
        return 1;
        } else {
            source.sendError(Text.translatable("commands.math.trip.fail"));
            return 0;
        }
    }

    private static int executeStep(ServerCommandSource source) {
        ServerTickManager serverTickManager = source.getServer().getTickManager();
        boolean bl = serverTickManager.stopStepping();
        if (bl) {
        source.sendFeedback(() -> {
            return Text.translatable("commands.math.defa.success");
        }, true);
        return 1;
        } else {
            source.sendError(Text.translatable("commands.math.defa.fail"));
            return 0;
        }
    }

    private static int executeStepText(ServerCommandSource source) {
        ServerTickManager serverTickManager = source.getServer().getTickManager();
        boolean bl = serverTickManager.stopStepping();
        if (bl) {
        source.sendFeedback(() -> {
            return Text.translatable("commands.info.text.success");
        }, true);
        return 1;
        } else {
            source.sendError(Text.translatable("commands.info.text.fail"));
            return 0;
        }
    }

    private static int executeStepBook(ServerCommandSource source) {
        ServerTickManager serverTickManager = source.getServer().getTickManager();
        boolean bl = serverTickManager.stopStepping();
        if (bl) {
        source.sendFeedback(() -> {
            return Text.translatable("commands.info.book.success");
        }, true);
        return 1;
        } else {
            source.sendError(Text.translatable("commands.info.book.fail"));
            return 0;
        }
    }


    private static int getText(String type, int value, PlayerEntity player) {
        if (type == null) {
            player.sendMessage((Text) new LiteralMessage(type));
            player.sendMessage((Text) new LiteralMessage("This Commands running failed!"), false);
            return 0;
        } else if (type == "book") {
            player.sendMessage((Text) new LiteralMessage("BOOK?"), false);
            if (value == 1) {
                player.sendMessage(
                    (Text) new LiteralMessage(
                        TestModInfos.TestModTexts.TextArray[0] + "\n" + TestModInfos.TestModTexts.InfoArray[0]),
                            false);
            } else if (value == 2) {
                player.sendMessage(
                    (Text) new LiteralMessage(
                        TestModInfos.TestModTexts.TextArray[1] + "\n" + TestModInfos.TestModTexts.InfoArray[1]),
                            false);
            } else if (value == 3) {
                player.sendMessage(
                    (Text) new LiteralMessage(
                        TestModInfos.TestModTexts.TextArray[2] + "\n" + TestModInfos.TestModTexts.InfoArray[2]),
                            false);
            } else {
                player.sendMessage((Text) new LiteralMessage("This command has some errors?"), false);
                //return Command.SINGLE_SUCCESS;
            }
        } else if (type == "text") {
            player.sendMessage((Text) new LiteralMessage(TestModInfos.TestModTexts.a));
            //return Command.SINGLE_SUCCESS;
        }
        return 1;
    }

    private static int runTrig(String type, String name, double angle, PlayerEntity player) {
        // String[] typeArray = {"trifunc","deffunc"};
        // String[] nameArray = { "sin", "cos", "tan", "asin", "acos", "atan" };
        double value = Math.toRadians(angle);
        if (type == null || name == null || angle == value) {
            player.sendMessage((Text) new LiteralMessage("This command runninng error!"), false);
        } else if (type == typeArray[0]) {
            if (name == nameArray[0]) {
                player.sendMessage((Text) new LiteralMessage("Angle" + angle + "sine is" + Math.sin(value)), false);
            }
            if (name == nameArray[1]) {
                player.sendMessage((Text) new LiteralMessage("Angle" + angle + "cosine is" + Math.cos(value)), false);
            }
            if (name == nameArray[2]) {
                player.sendMessage((Text) new LiteralMessage("Angle" + angle + "tangent is" + Math.tan(value)), false);
            }
            if (name == nameArray[3]) {
                player.sendMessage((Text) new LiteralMessage("Angle" + angle + "arcsine is" + Math.asin(value)), false);
            }
            if (name == nameArray[4]) {
                player.sendMessage((Text) new LiteralMessage("Angle" + angle + "arccosine is" + Math.acos(value)),
                    false);
            }
            if (name == nameArray[5]) {
                player.sendMessage((Text) new LiteralMessage("Angle" + angle + "arctangent is" + Math.atan(value)),
                    false);
            }
            return Command.SINGLE_SUCCESS;
        }
        return 0;
    }

    private static int runDefe(String type, String name, double value, PlayerEntity player) {
        //String[] infoArray = { "abs", "sqrt", "cbrt", "ceil", "floor", "round" };
        if (type == null || name == null || value == 0) {
            player.sendMessage((Text) new LiteralMessage("This command runninng error!"), false);
        } else if (type == typeArray[1]) {
            if (name == infoArray[0]) {
                player.sendMessage((Text) new LiteralMessage("Value" + value + "absolute value is" + Math.abs(value)),
                    false);
            }
            if (name == infoArray[1]) {
                player.sendMessage((Text) new LiteralMessage("Value" + value + "square root is" + Math.sqrt(value)),
                    false);
            }
            if (name == infoArray[2]) {
                player.sendMessage((Text) new LiteralMessage("Value" + value + "cube root is" + Math.cbrt(value)),
                    false);
            }
            if (name == infoArray[3]) {
                player.sendMessage((Text) new LiteralMessage("Value" + value + "round up is" + Math.ceil(value)),
                    false);
            }
            if (name == infoArray[4]) {
                player.sendMessage((Text) new LiteralMessage("Value" + value + "round down is" + Math.floor(value)),
                    false);
            }
            if (name == infoArray[5]) {
                player.sendMessage(
                    (Text) new LiteralMessage("Value" + value + "nearest integer is" + Math.round(value)), false);
            }
            return Command.SINGLE_SUCCESS;
        }
        return 0;
    }

    public static int getSuccess(int slot, PlayerEntity player) {
        if (slot >= 0 && slot <= 40) {
            ItemStack stack = player.getInventory().getStack(slot);
            if (stack.hasNbt()) {
                assert stack.getNbt() != null;
                String s = stack.getNbt().toString();
                player.sendMessage((Text) new LiteralMessage(s), false);
                return Command.SINGLE_SUCCESS;
            } else {
                player.sendMessage((Text) new LiteralMessage("This item has no NBT!"), false);
            }
            return 0;
        } else if (slot < 0) {
            player.sendMessage((Text) new LiteralMessage("This value you entered is too small!"), false);
        } else if (slot > 40) {
            player.sendMessage((Text) new LiteralMessage("This value you entered is too large!"), false);
        }
        return 0;
    }
}
