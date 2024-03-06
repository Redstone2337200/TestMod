package com.redstone233.test.mod.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

public class TestModEnchantment extends Enchantment{

    public TestModEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(rarity, target, slotTypes);
    }
    
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        // super.onTargetDamaged(user, target, level);
        
    }
}
