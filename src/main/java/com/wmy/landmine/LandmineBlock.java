package com.wmy.landmine;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * 地雷方块：任何实体踩上方块位置即触发爆炸（创造/旁观模式玩家除外）。
 * 继承 BushBlock（与草/花同类），自带"必须种在地面上"的放置校验，
 * 并且碰撞箱为空——不会阻挡玩家，只有踩上才会引爆。
 */
public class LandmineBlock extends BushBlock {
    private static final VoxelShape SHAPE = Shapes.empty();

    public static final MapCodec<LandmineBlock> CODEC = simpleCodec(LandmineBlock::new);

    @Override
    public MapCodec<LandmineBlock> codec() {
        return CODEC;
    }

    public LandmineBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
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
