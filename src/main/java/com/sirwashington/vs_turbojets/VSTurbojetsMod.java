package com.sirwashington.vs_turbojets;

import com.sirwashington.vs_turbojets.block.ModBlocks;
import com.sirwashington.vs_turbojets.block.entity.ModBlockEntities;
import com.sirwashington.vs_turbojets.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.valkyrienskies.mod.fabric.common.ValkyrienSkiesModFabric;

public class VSTurbojetsMod implements ModInitializer {
	public static final String MOD_ID = "vs_turbojets";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		new ValkyrienSkiesModFabric().onInitialize();

		ModItems.RegisterModItems();
		ModBlocks.registerModblocks();
		ModBlockEntities.registerBlockEntities();

		LOGGER.info("Hello Fabric world!");
	}
}