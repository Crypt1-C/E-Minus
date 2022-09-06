package net.cryptic.eminus.item.custom;

import net.cryptic.eminus.item.creativetab.ModCreativeTab;
import net.minecraft.world.item.Item;

public class ProtonItem extends Item {

    public ProtonItem(Properties pProperties) {
        super(pProperties.tab(ModCreativeTab.EMINUS_TAB));
    }
}
