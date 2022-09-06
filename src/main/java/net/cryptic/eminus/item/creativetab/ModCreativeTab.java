package net.cryptic.eminus.item.creativetab;

import net.cryptic.eminus.item.ModItems;
import net.cryptic.eminus.item.custom.ElectronItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModCreativeTab {
    public static final CreativeModeTab EMINUS_TAB = new CreativeModeTab("eminus_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.ELECTRON.get());
        }
    };
}
