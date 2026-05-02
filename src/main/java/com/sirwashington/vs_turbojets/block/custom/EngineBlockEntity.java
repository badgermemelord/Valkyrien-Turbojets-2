package com.sirwashington.vs_turbojets.block.custom;

import com.sirwashington.vs_turbojets.network.EngineNetwork;

public interface EngineBlockEntity {

    public EngineNetwork getNetwork();

    public void clearNetwork();

    public void setNetwork(EngineNetwork network);

}
