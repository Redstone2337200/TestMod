package com.redstone233.test.mod.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.redstone233.test.mod.TestMod;

@Mixin(BrewingRecipeRegistry.class)
public class BrewingMixin {
	@Inject(at = @At("HERD"), method = "registerDefaults")
	private static void registerDefaults(CallbackInfo ci) {
		BrewingMixin.registerPotionRecipes(Potions.AWKWARD, Items.DIAMOND, TestMod.XP_POTION);
	}
	@Invoker
	public static void registerPotionRecipes(Potion input,Item item,Potion output){

	}
}