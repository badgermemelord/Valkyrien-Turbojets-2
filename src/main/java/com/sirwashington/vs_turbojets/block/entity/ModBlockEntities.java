package com.sirwashington.vs_turbojets.block.entity;

import com.sirwashington.vs_turbojets.VSTurbojetsMod;
import com.sirwashington.vs_turbojets.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityType<TurbojetBlockEntity> TURBOJET_BLOCK_ENTITY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(VSTurbojetsMod.MOD_ID, "turbojet_be"),
                    FabricBlockEntityTypeBuilder.create(TurbojetBlockEntity::new,
                            ModBlocks.COMPRESSOR_BLOCK,
                            ModBlocks.TURBINE_BLOCK,
                            ModBlocks.COMBUSTION_CHAMBER_BLOCK
                    ).build());


    public static void registerBlockEntities() {
        VSTurbojetsMod.LOGGER.info("Registering Block Entities for " + VSTurbojetsMod.MOD_ID);
    }

}
