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

public class EngineNetwork {

    public static int maxRange = 10;

    ArrayList<BlockPos> memberBlocks = new ArrayList<>();

    public void attemptNetworkCreation(BlockPos sourcePos, Level world, TurbojetTestBlockEntity networkCreator) {

        Direction networkFacing = world.getBlockState(sourcePos).getValue(FACING);
        Vec3i facingVector = networkFacing.getNormal();

        System.out.println("started network creation");
/*        for (int i = -maxRange; i <= maxRange; i++) {
            BlockPos currentPos = sourcePos.offset(facingVector.multiply(i));
            BlockState currentState = world.getBlockState(currentPos);
            System.out.println("pos: " + currentPos + " state: " + currentState);
            if (currentState.is(ModTags.Blocks.TURBOJET_PART_BLOCKS)) {
                System.out.println("found an engine part");
            }
        }*/

        //Forward loop
        for (int i = 0; i <= maxRange; i++) {
            System.out.println("forward");
            BlockPos currentPos = sourcePos.offset(facingVector.multiply(i));
            BlockState currentState = world.getBlockState(currentPos);
            if (currentState.is(ModTags.Blocks.TURBOJET_PART_BLOCKS)) {
                System.out.println("found an engine part");
                memberBlocks.add(currentPos);
            }
            else {
                System.out.println("broke1");
                break;
            }
        }
        //Backwards loop
        for (int i = -1; i >= -maxRange; i--) {
            System.out.println("rearward");
            BlockPos currentPos = sourcePos.offset(facingVector.multiply(i));
            BlockState currentState = world.getBlockState(currentPos);
            if (currentState.is(ModTags.Blocks.TURBOJET_PART_BLOCKS)) {
                System.out.println("found an engine part");
                memberBlocks.add(currentPos);
            }
            else {
                System.out.println("broke2");
                break;
            }
        }

        System.out.println("finished creation, list: " + memberBlocks);

    }

}
