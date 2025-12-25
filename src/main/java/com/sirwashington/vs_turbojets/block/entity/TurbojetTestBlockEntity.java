package com.sirwashington.vs_turbojets.block.entity;

import com.sirwashington.vs_turbojets.network.EngineNetwork;
import com.sirwashington.vs_turbojets.network.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.core.impl.shadow.En;
import org.valkyrienskies.mod.common.VSGameUtilsKt;
import org.valkyrienskies.mod.common.ValkyrienSkiesMod;
import org.valkyrienskies.mod.common.util.GameToPhysicsAdapter;


public class TurbojetTestBlockEntity extends BlockEntity {

    private int lifetime = 0;


    public TurbojetTestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.TURBOJET_TEST_BLOCK_ENTITY, blockPos, blockState);
    }

    public int getLifeTime() {
        return lifetime;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TurbojetTestBlockEntity entity) {

        EngineNetwork network = new EngineNetwork();

        if (!level.isClientSide) {
            if (entity.lifetime == 0)
                //NetworkUtil.attemptNetworkCreation(pos, level, entity);
                network.attemptNetworkCreation(pos, level, entity);

            //DO TICK STUFF
            if (level.getBlockState(pos).getBlock() != Blocks.VOID_AIR) {
                //System.out.println("rot: " + level.getBlockState(pos).getValue(TurbojetTestBlock.FACING));
                //applyForces(level, pos, entity);
            }
            if(entity.lifetime < 20) {
                //applyForces(level, pos, entity);
                //System.out.println("applied");
            }

            entity.lifetime++;
        }
    }

    public static void applyForces(Level level, BlockPos pos, TurbojetTestBlockEntity be) {
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
