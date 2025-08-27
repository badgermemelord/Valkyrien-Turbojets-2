package com.sirwashington.vs_turbojets.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TurbojetTestBlockEntity extends BlockEntity {


    public TurbojetTestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.TURBOJET_TEST_BLOCK_ENTITY, blockPos, blockState);
    }




    public static void tick(Level level, BlockPos pos, BlockState state, TurbojetTestBlockEntity entity) {
        //DO TICK STUFF
        System.out.println("e");
    }

}
