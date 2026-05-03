package com.sirwashington.vs_turbojets.network;

import com.sirwashington.vs_turbojets.block.entity.TurbojetBlockEntity;
import com.sirwashington.vs_turbojets.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;
import org.valkyrienskies.mod.common.ValkyrienSkiesMod;
import org.valkyrienskies.mod.common.util.GameToPhysicsAdapter;

import java.util.ArrayList;

import static com.sirwashington.vs_turbojets.block.custom.AbstractEngineBlock.FACING;

public class EngineNetwork {

    public static int maxRange = 10;
    ArrayList<BlockPos> memberBlocks = new ArrayList<>();
    Direction networkFacing;
    private float lastTickTime;

    public void attemptNetworkCreation(BlockPos sourcePos, Level level, TurbojetBlockEntity networkCreator) {
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
            TurbojetBlockEntity internal = (TurbojetBlockEntity) level.getBlockEntity(pos);
                if (internal != null) {
                    internal.setNetwork(this);
                }
        }
    }

    public void deleteLesserNetwork(BlockPos pos, Level level) {
        TurbojetBlockEntity internalEntity = (TurbojetBlockEntity) level.getBlockEntity(pos);
        if (internalEntity != null) {
            internalEntity.clearNetwork();
        }
    }

    public void tick(Level level) {
        if (hasTickedThisTick(level.getTimeOfDay(1f)))
            return;
        setCurrentTickTime(level.getTimeOfDay(1f));
    }

    public static void applyForces(Level level, BlockPos pos, TurbojetBlockEntity be) {
        if (!level.isClientSide) {

            if(VSGameUtilsKt.isBlockInShipyard(level, pos))
            {
                ServerShip ship = (ServerShip) VSGameUtilsKt.getShipManagingPos(level, pos);

                Vec3 middle = VSGameUtilsKt.toWorldCoordinates(ship, Vec3.atLowerCornerOf(pos));

                GameToPhysicsAdapter forces = ValkyrienSkiesMod.getOrCreateGTPA(ship.getChunkClaimDimension());

                BlockPos block = new BlockPos((int) middle.x, (int) middle.y, (int) middle.z);
                Vector3dc enginePos = new Vector3d((int) middle.x, (int) middle.y, (int) middle.z);
                Vector3dc forceToApply = new Vector3d(0,0,1000000.0);
                //forces.applyInvariantForceToPos(ship.getChunkClaim().toLong(), forceToApply, enginePos);
                forces.applyInvariantForce(ship.getId(), forceToApply);

                System.out.println("applied forces to ship: " + ship.getSlug());

            }

        }
    }

}
