package com.wmy.landmine;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class LandmineBlocks {
    // 地雷方块：踩踏后爆炸，硬度低，可用探测铁靴安全通过（后续扩展）
    public static final Block LANDMINE = register("landmine",
            new LandmineBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.METAL)
                    .noCollision()      // 不阻挡玩家，踩上去才触发
                    .strength(0.2f)));

    public static Block register(String name, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK,
                Identifier.fromNamespaceAndPath(LandmineMod.MOD_ID, name), block);
    }

    public static void register() {
        // 静态字段在类加载时完成注册；此处保留显式调用入口以便将来加事件监听
    }
}
