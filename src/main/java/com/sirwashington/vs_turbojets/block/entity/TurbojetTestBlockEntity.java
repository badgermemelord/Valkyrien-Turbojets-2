package com.sirwashington.vs_turbojets.block.entity;

import com.sirwashington.vs_turbojets.block.custom.TurbojetTestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

public class TurbojetTestBlockEntity extends BlockEntity {


    public TurbojetTestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.TURBOJET_TEST_BLOCK_ENTITY, blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TurbojetTestBlockEntity entity) {
        //DO TICK STUFF
        System.out.println("rot: " + level.getBlockState(pos).getValue(TurbojetTestBlock.FACING));
    }

    public static void applyForces(Level level, BlockPos pos, TurbojetTestBlockEntity be) {
        if (!level.isClientSide) {

            if(VSGameUtilsKt.isBlockInShipyard(level, pos))
            {
                ServerShip ship = (ServerShip) VSGameUtilsKt.getShipManagingPos(level, pos);
                Vec3 middle = VSGameUtilsKt.toWorldCoordinates(ship, Vec3.atLowerCornerOf(pos));
                BlockPos block = new BlockPos((int) middle.x, (int) middle.y, (int) middle.z);
                
            }

        }
    }

}
