package net.cryptic.eminus.item.custom;

import net.cryptic.eminus.item.creativetab.ModCreativeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ProtonItem extends Item {

    public ProtonItem(Properties pProperties) {
        super(pProperties.tab(ModCreativeTab.EMINUS_TAB));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(new TranslatableComponent("tooltip.item.eminus.proton"));
        pTooltipComponents.add(new TranslatableComponent("tooltip.charge.item.eminus.proton"));
    }
}
