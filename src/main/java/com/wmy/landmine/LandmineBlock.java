package com.wmy.landmine;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 地雷方块：任何实体踩上方块位置即触发爆炸（创造/旁观模式玩家除外）。
 */
public class LandmineBlock extends Block {
    public LandmineBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!level.isClientSide()) {
            if (entity instanceof Player player && (player.isCreative() || player.isSpectator())) {
                return;
            }
            explode(level, pos);
        }
    }

    private void explode(Level level, BlockPos pos) {
        level.removeBlock(pos, false);
        level.explode(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                3.5F, Level.ExplosionInteraction.BLOCK);
    }
}
