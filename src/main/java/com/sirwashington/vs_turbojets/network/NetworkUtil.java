package com.sirwashington.vs_turbojets.network;

import com.sirwashington.vs_turbojets.block.entity.TurbojetBlockEntity;
import com.sirwashington.vs_turbojets.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class NetworkUtil {

    public static void updateNeighbours(Level level, BlockPos pos) {
        System.out.println("performerd neighbour uzpdate");
        for (Direction dir : Direction.values()) {
            BlockPos internal = pos.offset(dir.getNormal());
            if (level.getBlockState(internal).is(ModTags.Blocks.TURBOJET_PART_BLOCKS)) {
                TurbojetBlockEntity entity = (TurbojetBlockEntity) level.getBlockEntity(internal);
                entity.createNewNetwork();
                entity.getNetwork().attemptNetworkCreation(internal, level, (TurbojetBlockEntity) entity);
            }
        }
    }



}
