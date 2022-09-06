package net.cryptic.eminus.item.custom;

import net.cryptic.eminus.item.creativetab.ModCreativeTab;
import net.minecraft.world.item.Item;

public class NeutronItem extends Item {

    public NeutronItem(Properties pProperties) {
        super(pProperties.tab(ModCreativeTab.EMINUS_TAB));
    }
}
