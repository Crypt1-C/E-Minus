package net.cryptic.eminus.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.SoulFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class RedFireBlock extends BaseFireBlock {
    public RedFireBlock(Properties pProperties, float pFireDamage) {
        super(pProperties
                .noOcclusion()
                .noCollission()
                .lightLevel((state)-> 15)
                , pFireDamage);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        pPlayer.displayClientMessage(new TextComponent("§6Burning Compound(s): §3Strontium [Nitrate/Carbonate/Oxalate/Sulfate/Chloride]"),true);
        return InteractionResult.SUCCESS;
    }



    @Override
    protected boolean canBurn(BlockState pState) {
        return true;
    }
}
