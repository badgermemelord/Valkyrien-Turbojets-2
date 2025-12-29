package com.sirwashington.vs_turbojets.network;

import com.sirwashington.vs_turbojets.block.entity.TurbojetTestBlockEntity;
import com.sirwashington.vs_turbojets.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;

import static com.sirwashington.vs_turbojets.block.custom.TurbojetTestBlock.FACING;

public class NetworkUtil {

    public static void updateNeighbours(Level level, BlockPos pos) {
        System.out.println("performerd neighbour uzpdate");
        for (Direction dir : Direction.values()) {
            BlockPos internal = pos.offset(dir.getNormal());
            if (level.getBlockState(internal).is(ModTags.Blocks.TURBOJET_PART_BLOCKS)) {
                TurbojetTestBlockEntity entity = (TurbojetTestBlockEntity) level.getBlockEntity(internal);
                entity.createNewNetwork();
                entity.getNetwork().attemptNetworkCreation(internal, level, entity);
            }
        }
    }



}
