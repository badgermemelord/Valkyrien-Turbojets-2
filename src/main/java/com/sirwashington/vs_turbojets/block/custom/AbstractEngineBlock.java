package com.sirwashington.vs_turbojets.block.custom;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public abstract class AbstractEngineBlock extends BaseEntityBlock {

    public static final DirectionProperty FACING;


    protected AbstractEngineBlock(Properties properties) {
        super(properties);

    }



    static {
        FACING = HorizontalDirectionalBlock.FACING;
    }
}
