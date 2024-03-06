package com.redstone233.test.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.CommandDispatcher;
import com.redstone233.test.mod.armor.TestArmorMaterial;
import com.redstone233.test.mod.block.TestBlock;
import com.redstone233.test.mod.item.TestItem;
import com.redstone233.test.mod.tool.TestToolMaterial;

public class TestMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("testmod");
	public static final Item TEST_ITEM = Registry.register(Registries.ITEM, new Identifier("testmod", "test_item"),
			new TestItem(new FabricItemSettings()));

	private static final Block TEST_BLOCK = Registry.register(Registries.BLOCK, new Identifier("testmod", "test_block"),
			new TestBlock(FabricBlockSettings.create().strength(4.0F)));

	private static final BlockItem TEST_BLOCK_ITEM = Registry.register(Registries.ITEM,
			new Identifier("testmod", "test_block"),
			new BlockItem(TEST_BLOCK, new FabricItemSettings()));

	private static final Item TEST_HELMET = Registry.register(Registries.ITEM, new Identifier("testmod", "test_helmts"),
			new ArmorItem(TestArmorMaterial.TEST, ArmorItem.Type.HELMET, new FabricItemSettings()));
	private static final Item TEST_CHESTPLATE = Registry.register(Registries.ITEM,
			new Identifier("testmod", "test_chestplates"),
			new ArmorItem(TestArmorMaterial.TEST, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
	private static final Item TEST_LEGGINGS = Registry.register(Registries.ITEM,
			new Identifier("testmod", "test_leggings"),
			new ArmorItem(TestArmorMaterial.TEST, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
	private static final Item TEST_BOOTS = Registry.register(Registries.ITEM, new Identifier("testmod", "test_boots"),
			new ArmorItem(TestArmorMaterial.TEST, ArmorItem.Type.BOOTS, new FabricItemSettings()));

	private static final Item TEST_SWORD = Registry.register(Registries.ITEM, new Identifier("testmod", "test_sword"),
			new SwordItem(TestToolMaterial.TEST, 4, 0F, new FabricItemSettings()));

	@SuppressWarnings("unused")
	private static final ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
			new Identifier("testmod", "item_group"),
			FabricItemGroup.builder()
					.icon(() -> new ItemStack(TEST_ITEM))
					.displayName(Text.translatable("itemGroup.testmod.item_group"))
					.entries((content, entries) -> {
						entries.add(TEST_ITEM);
						entries.add(TEST_BLOCK_ITEM);
						entries.add(TEST_HELMET);
						entries.add(TEST_CHESTPLATE);
						entries.add(TEST_LEGGINGS);
						entries.add(TEST_BOOTS);
						entries.add(TEST_SWORD);
					})
					.build());

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Hello Fabric world!");
		CommandRegistrationCallback.EVENT.register((dispatcher,registryAccess,enviroment) -> register(dispatcher));
	}
	
	private Object register(CommandDispatcher<ServerCommandSource> dispatcher) {
		throw new UnsupportedOperationException("Unimplemented method 'register'");
	}
	
}