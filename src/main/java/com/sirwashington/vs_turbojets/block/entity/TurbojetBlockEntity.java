package com.sirwashington.vs_turbojets.block.entity;

import com.sirwashington.vs_turbojets.network.EngineNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TurbojetBlockEntity extends BlockEntity {

    private int lifetime = 0;

    private EngineNetwork network = new EngineNetwork();

    public TurbojetBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.TURBOJET_BLOCK_ENTITY, blockPos, blockState);
    }


    public int getLifeTime() {
        return lifetime;
    }

    public EngineNetwork getNetwork() {
        return network;
    }

    public void createNewNetwork() {
        network = new EngineNetwork();
    }

    public void clearNetwork() {
        network = null;
    }

    public void setNetwork(EngineNetwork newNetwork) {
        network = newNetwork;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TurbojetBlockEntity entity) {

        if (!level.isClientSide) {
            if (entity.lifetime == 0) {
                entity.createNewNetwork();
                entity.network.attemptNetworkCreation(pos, level, entity);
            }

            entity.network.tick(level);

            entity.lifetime++;
        }
    }
}
