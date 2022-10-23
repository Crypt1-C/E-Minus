package net.cryptic.eminus.item.creativetab;

import net.cryptic.eminus.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTab {
    public static final CreativeModeTab EMINUS_TAB = new CreativeModeTab("eminus_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.BLAZE_POWDER);
        }
    };
}
