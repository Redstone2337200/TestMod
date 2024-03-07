package com.redstone233.test.mod.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.BlockView;

public class TestBlock extends Block {
    public TestBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext options) {
        // super.appendTooltip(stack, world, tooltip, options);
        tooltip.add(Text.translatable("item.testmod.test_block"));
    }
}
