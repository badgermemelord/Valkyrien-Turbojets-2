package com.sirwashington.vs_turbojets.network;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkManager {


    public static ArrayList<EngineNetwork> networkList = new ArrayList<>();

    public static HashMap<Long, EngineNetwork> networ = new HashMap<Long, EngineNetwork>();

    public static EngineNetwork getNetworkFromList() {
        networ.
        return networkList.
    }

    public static void addNetworkToList(EngineNetwork network) {
        networkList.add(network);
    }

    public static void removeNetworkFromList(EngineNetwork network) {
        networkList.remove(network);
    }

    public static void CleanupNetworkList() {
        for (EngineNetwork network : networkList) {
            //if network.
        }
    }

    public static void TickNetworks() {
        for (EngineNetwork network : networkList) {
            network.tick
        }
    }

}
