package com.wmy.landmine;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LandmineItems {
    public static final Item LANDMINE = register("landmine",
            new LandmineItem(LandmineBlocks.LANDMINE, new Item.Properties()));

    public static final CreativeModeTab LANDMINE_GROUP =
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
                    .title(net.minecraft.network.chat.Component.translatable("itemGroup.landmine"))
                    .icon(() -> new ItemStack(LANDMINE))
                    .displayItems((params, output) -> output.accept(LANDMINE))
                    .build();

    public static Item register(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM,
                Identifier.fromNamespaceAndPath(LandmineMod.MOD_ID, name), item);
    }

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(LandmineMod.MOD_ID, "landmine_group"),
                LANDMINE_GROUP);
    }
}
