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
    Direction networkFacing;
    Long LongPos;

    public void attemptNetworkCreation(BlockPos sourcePos, Level world, TurbojetTestBlockEntity networkCreator) {

        networkFacing = world.getBlockState(sourcePos).getValue(FACING);
        Vec3i facingVector = networkFacing.getNormal();
        LongPos = sourcePos.asLong();
        System.out.println("started network creation from: " + sourcePos);
        memberBlocks.add(sourcePos);
        //Forward loop
        for (int i = 1; i <= maxRange; i++) {
            System.out.println("forward");
            BlockPos currentPos = sourcePos.offset(facingVector.multiply(i));
            BlockState currentState = world.getBlockState(currentPos);
            if (isValidEngineBlock(currentState)) {
                deleteLesserNetwork(currentPos, world);
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
            if (isValidEngineBlock(currentState)) {
                deleteLesserNetwork(currentPos, world);
                System.out.println("found an engine part");
                memberBlocks.add(currentPos);
            }
            else {
                System.out.println("broke2");
                break;
            }
        }
        NetworkManager.addNetworkToList(this, LongPos);
        System.out.println("finished creation, list: " + memberBlocks);

    }

    public void performBlockCheck() {

    }

    public boolean isValidEngineBlock(BlockState state) {
        return state.is(ModTags.Blocks.TURBOJET_PART_BLOCKS) && state.getValue(FACING).equals(networkFacing);
    }

    public void deleteLesserNetwork(BlockPos pos, Level level) {
        TurbojetTestBlockEntity internalEntity = (TurbojetTestBlockEntity)level.getBlockEntity(pos);
        if (internalEntity != null) {
            internalEntity.clearNetwork();
            NetworkManager.removeNetworkFromList(internalEntity.getNetwork().LongPos);
        }
    }

    public static void tick(Level level, Long key) {

        System.out.println("Started tick of network: " + NetworkManager.networkList.get(key));

    }

}
