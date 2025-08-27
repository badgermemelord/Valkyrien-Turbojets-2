package com.sirwashington.vs_turbojets.block;

import com.sirwashington.vs_turbojets.VSTurbojetsMod;
import com.sirwashington.vs_turbojets.block.custom.TurbojetTestBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ModBlocks {

    public static final Block TURBOJET_TEST_BLOCK = registerBlock("turbojet_test_block",
            new TurbojetTestBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(VSTurbojetsMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(VSTurbojetsMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModblocks() {
        VSTurbojetsMod.LOGGER.info("Registering Valkyrien-Turbojets blocks");
    }

/*    private static Block registerBlock(String name, Block block, CreativeModeTab group) {
        registerBlockItem(name, block, group);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(VSTurbojetsMod.MODID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, CreativeModeTab group) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(VSTurbojetsMod.MODID, name),
                new BlockItem(block, new FabricItemSettings()));
    }*/

}
