package com.sirwashington.vs_turbojets.network;

import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkManager {



    public static HashMap<Long, EngineNetwork> networkList = new HashMap<Long, EngineNetwork>();

    public static EngineNetwork getNetworkFromList(Long key) {
        return networkList.get(key);
    }

    public static void addNetworkToList(EngineNetwork network, Long key) {
        networkList.put(key, network);
    }

    public static void removeNetworkFromList(Long key) {
        networkList.remove(key);
    }

    public static void CleanupNetworkList() {

    }

    public static void TickNetworks(Level level) {
/*        for (EngineNetwork network : networkList.values()) {
            network.ti
        }*/
        for (Long key : networkList.keySet()) {
            EngineNetwork network = networkList.get(key);
            network.tick(level, key);
        }
    }

}
