package com.redstone233.test.mod.armor;

import net.minecraft.recipe.Ingredient;

@SuppressWarnings("hiding")
public interface Supplier<T> {

    Ingredient get();

}
