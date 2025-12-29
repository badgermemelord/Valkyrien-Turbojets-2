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
    private float lastTickTime;

    public void attemptNetworkCreation(BlockPos sourcePos, Level level, TurbojetTestBlockEntity networkCreator) {
        System.out.println("started network creation from: " + sourcePos);

        networkFacing = level.getBlockState(sourcePos).getValue(FACING);
        Vec3i facingVector = networkFacing.getNormal();
        memberBlocks.add(sourcePos);

        //Forward loop
        for (int i = 1; i <= maxRange; i++) {
            BlockPos currentPos = sourcePos.offset(facingVector.multiply(i));
            BlockState currentState = level.getBlockState(currentPos);
            if (isValidEngineBlock(currentState)) {
                deleteLesserNetwork(currentPos, level);
                memberBlocks.add(currentPos);
            }
            else break;
        }
        //Backwards loop
        for (int i = -1; i >= -maxRange; i--) {
            BlockPos currentPos = sourcePos.offset(facingVector.multiply(i));
            BlockState currentState = level.getBlockState(currentPos);
            if (isValidEngineBlock(currentState)) {
                deleteLesserNetwork(currentPos, level);
                memberBlocks.add(currentPos);
            }
            else break;

        }
        spreadNetworkToMembers(level);

        System.out.println("finished creation, list: " + memberBlocks);
    }

    public float getLastTickTime() {
        return lastTickTime;
    }

    private void resetNetwork() {
        memberBlocks = new ArrayList<>();
        lastTickTime = 0;
    }

    public void setCurrentTickTime(float newTime) {
        lastTickTime = newTime;
    }

    private boolean hasTickedThisTick(float currentTime) {
        return currentTime == lastTickTime;
    }

    public boolean isValidEngineBlock(BlockState state) {
        return state.is(ModTags.Blocks.TURBOJET_PART_BLOCKS) && state.getValue(FACING).equals(networkFacing);
    }

    public void spreadNetworkToMembers(Level level) {
        for (BlockPos pos : memberBlocks) {
            TurbojetTestBlockEntity internal = (TurbojetTestBlockEntity) level.getBlockEntity(pos);
                if (internal != null) {
                    internal.setNetwork(this);
                }
        }
    }

    public void deleteLesserNetwork(BlockPos pos, Level level) {
        TurbojetTestBlockEntity internalEntity = (TurbojetTestBlockEntity)level.getBlockEntity(pos);
        if (internalEntity != null) {
            internalEntity.clearNetwork();
        }
    }

    public void tick(Level level) {
        if (hasTickedThisTick(level.getTimeOfDay(1f)))
            return;
        setCurrentTickTime(level.getTimeOfDay(1f));
    }

}
