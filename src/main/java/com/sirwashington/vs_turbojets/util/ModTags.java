package com.sirwashington.vs_turbojets.util;

import com.sirwashington.vs_turbojets.VSTurbojetsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> TURBOJET_PART_BLOCKS =
                createTag("turbojet_part_blocks");

        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(VSTurbojetsMod.MOD_ID, name));
        }

    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, new ResourceLocation(VSTurbojetsMod.MOD_ID, name));
        }
    }
}
