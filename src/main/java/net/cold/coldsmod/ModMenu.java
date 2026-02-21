package net.cold.coldsmod;

import net.cold.coldsmod.menu_blessing.BlessingMenu;
import net.cold.coldsmod.menu_stat.StatMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenu {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, "coldsmod");

    // This is the ID the OpenPacket needs to find
    public static final RegistryObject<MenuType<StatMenu>> STAT_MENU =
            MENUS.register("stat_menu", () -> IForgeMenuType.create(StatMenu::new));

    public static final RegistryObject<MenuType<BlessingMenu>> BLESSING_MENU =
            MENUS.register("blessing_menu", () -> IForgeMenuType.create(BlessingMenu::new));
}