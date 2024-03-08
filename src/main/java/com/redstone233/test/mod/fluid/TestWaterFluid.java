package com.redstone233.test.mod.fluid;

import com.redstone233.test.mod.TestMod;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager.Builder;

public abstract class TestWaterFluid extends TestFluid {
    // 方法：获取静止的流体
    @Override
    public Fluid getStill() {
        return TestMod.STILL_TEST_WATER;
    }
    // 方法：获取流动的流体
    @Override
    public Fluid getFlowing() {
        return TestMod.FLOWABLE_TEST_WATER;
    }
    // 方法：获取流体桶物品
    @Override
    public Item getBucketItem() {
        return TestMod.TEST_WATER_BUCKET;
    }
    // 方法：获取方块状态
    @Override
    protected BlockState toBlockState(FluidState state) {
        return TestMod.TEST_WATER.getDefaultState()
            .with(FluidBlock.LEVEL, TestWaterFluid.getBlockStateLevel(state));
    }
    // 内部类：是否为流动状态
    public static class Flowing extends TestWaterFluid {
        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }

        @Override
        protected void appendProperties(Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }   
    }
    // 内部类：是否为静态流体
    public static class Still extends TestWaterFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}