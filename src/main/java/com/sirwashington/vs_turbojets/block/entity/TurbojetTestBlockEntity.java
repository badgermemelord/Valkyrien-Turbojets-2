package com.sirwashington.vs_turbojets.block.entity;

import com.sirwashington.vs_turbojets.block.custom.TurbojetTestBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.valkyrienskies.core.api.ships.ServerShip;
import org.valkyrienskies.mod.common.VSGameUtilsKt;
import org.valkyrienskies.mod.common.ValkyrienSkiesMod;
import org.valkyrienskies.mod.common.util.GameToPhysicsAdapter;


public class TurbojetTestBlockEntity extends BlockEntity {

    public static int lifetime = 0;


    public TurbojetTestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.TURBOJET_TEST_BLOCK_ENTITY, blockPos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TurbojetTestBlockEntity entity) {
        //DO TICK STUFF
        if (level.getBlockState(pos).getBlock() != Blocks.VOID_AIR) {
            System.out.println("rot: " + level.getBlockState(pos).getValue(TurbojetTestBlock.FACING));
        }
        if(lifetime == 1)
            applyForces(level, pos, entity);
        lifetime++;
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
                Vector3dc forceToApply = new Vector3d(0,100000.0,0);
                //forces.applyInvariantForceToPos(ship.getChunkClaim().toLong(), forceToApply, enginePos);
                forces.applyInvariantForce(ship.getId(), forceToApply);

                System.out.println("applied forces to ship: " + ship.getSlug());
                
            }

        }
    }

}
