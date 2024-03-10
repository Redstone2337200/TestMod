package com.redstone233.test.mod.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class TestPotion extends InstantStatusEffect {

    public TestPotion() {
        super(StatusEffectCategory.BENEFICIAL, 0xEDC234);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player) {
            player.removeStatusEffect(StatusEffects.POISON);
        }
        super.applyUpdateEffect(entity, amplifier);
    }
    
}
