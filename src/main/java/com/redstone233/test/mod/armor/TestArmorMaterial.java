package com.redstone233.test.mod.armor;

import com.redstone233.test.mod.TestMod;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorItem.Type;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import net.minecraft.util.Util;

@SuppressWarnings("deprecation")
public enum TestArmorMaterial implements ArmorMaterial {
    TEST("test_item", 5, Util.make(new EnumMap<>(ArmorItem.Type.class), (map) -> {
        map.put(Type.BOOTS, 1);
        map.put(Type.LEGGINGS, 2);
        map.put(Type.CHESTPLATE, 3);
        map.put(Type.HELMET, 1);
    }), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F, 0.0F, () -> {
        return Ingredient.ofItems(new ItemConvertible[] { TestMod.TEST_ITEM });
    });

    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionAmounts;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredientSupplier;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static final EnumMap<ArmorItem.Type, Integer> BASE_DURABILITY = Util.make(new EnumMap(ArmorItem.Type.class),
            (map) -> {
                map.put(Type.BOOTS, 13);
                map.put(Type.LEGGINGS, 15);
                map.put(Type.CHESTPLATE, 16);
                map.put(Type.HELMET, 11);
            });

    @SuppressWarnings({ "rawtypes", "unchecked" })
    TestArmorMaterial(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionAmounts,
            int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance,
            java.util.function.Supplier repairIngredientSupplier) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredientSupplier = (Supplier<Ingredient>) new Lazy(repairIngredientSupplier);
    }

    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY.get(type) * this.durabilityMultiplier;
    }

    public int getProtection(ArmorItem.Type type) {
        return this.protectionAmounts.get(type);
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredientSupplier.get();
    }

    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String asString() {
        return this.name;
    }
}
