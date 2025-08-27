package com.sirwashington.vs_turbojets.item;

import com.sirwashington.vs_turbojets.VSTurbojetsMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class ModItems {


    //public static final Item TURBOJET_TEST_BLOCK = registerItem("turbojet_test_block", new Item(new FabricItemSettings()));

    public static void addItemsToCreativeTab(FabricItemGroupEntries entries) {
            //entries.prepend(TURBOJET_TEST_BLOCK);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(VSTurbojetsMod.MOD_ID, name), item);

    }

    public static void RegisterModItems() {
        //VSTurbojetsMod.LOGGER.info("Registering ModItems for " + WaterMod.MODID);
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(ModItems::addItemsToCreativeTab);
    }

}
