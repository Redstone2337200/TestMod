package com.redstone233.test.mod.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public abstract class TestFluid extends FlowableFluid {
    // 覆写方法：判断是否是流体
    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }
    // 覆写方法：判断是否形成无限源头
    @Override
    protected boolean isInfinite(World world) {
        return true;
    }
    // 覆写方法：是否破坏流体经过的方块
    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
        Block.dropStacks(state, world, pos, blockEntity);
    }
    // 覆写方法：是否可以被替换掉
    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid,
            Direction direction) {
        return false;
    }
    // 覆写方法：流体的速度
    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4;
    }
    // 覆写方法：判断流体是否为水或岩浆
    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        // 因为是水，所以返回整数1
        return 1;
    }
    // 覆写方法：流体的更新频率（可自定义，默认5）
    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }
    // 覆写方法：流体的爆炸抗性（可自定义，默认100.0f）
    @Override
    protected float getBlastResistance() {
        return 100;
    }
}