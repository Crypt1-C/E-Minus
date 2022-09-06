package net.cryptic.eminus.item.custom;

import net.cryptic.eminus.item.creativetab.ModCreativeTab;
import net.minecraft.world.item.Item;

public class ElectronItem extends Item {

    public ElectronItem(Properties pProperties) {
        super(pProperties.tab(ModCreativeTab.EMINUS_TAB));
    }
}
