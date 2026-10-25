package com.wmy.landmine;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * 地雷物品：继承 BlockItem，作为独立类便于后续扩展
 * （例如附魔、冷却、特殊提示等物品行为）。
 */
public class LandmineItem extends BlockItem {
    public LandmineItem(Block block, Item.Properties properties) {
        super(block, properties);
    }
}
