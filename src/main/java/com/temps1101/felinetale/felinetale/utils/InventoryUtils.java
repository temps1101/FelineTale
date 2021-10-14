package com.temps1101.felinetale.felinetale.utils;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;


public class InventoryUtils {
    public static final int ENCHANTMENT_ARMORS = 0;
    public static final int ENCHANTMENT_WEAPONS = 1;
    public static final int ENCHANTMENT_TOOLS = 2;
    public static final int ENCHANTMENT_BOWS = 3;
    public static final int ENCHANTMENT_RODS = 4;

    static final ItemStack exit = ItemUtils.createItem(Material.BARRIER, " ", null);
    static final ItemStack home = ItemUtils.createItem(Material.DARK_OAK_DOOR_ITEM, " ", null);
    static final ItemStack separator = ItemUtils.createItem(Material.STAINED_GLASS_PANE, " ", null);

    public static Inventory createMainMenu(Player p) {
        ItemStack gamemode = ItemUtils.createItem(getItemFromGamemode(p.getGameMode()), " ", null);
        ItemStack heal = ItemUtils.createItem(Material.GOLDEN_APPLE, " ", null);
        ItemStack killEnemies = ItemUtils.createItem(Material.DIAMOND_SWORD, " ", null);
        ItemStack enchant = ItemUtils.createItem(Material.ENCHANTMENT_TABLE, " ", null);
        ItemStack[] mainMenuItems = {gamemode, heal, killEnemies, enchant};

        Inventory mainMenu = Bukkit.createInventory(p, 45, MessageUtils.message("&3メニュー", false));
        mainMenu.setContents(mainMenuItems);
        appendSeparator(mainMenu, 1);
        appendSeparator(mainMenu, 2);
        appendSeparator(mainMenu, 3);
        mainMenu.setItem(36, exit);

        return mainMenu;
    }

    public static Inventory createEnchantmentCategoryMenu(Player p) {
        ItemStack armors = ItemUtils.createItem(Material.GOLD_CHESTPLATE, " ", null);
        ItemStack weapons = ItemUtils.createItem(Material.GOLD_SWORD, " ", null);
        ItemStack tools = ItemUtils.createItem(Material.GOLD_AXE, " ", null);
        ItemStack bows = ItemUtils.createItem(Material.BOW, " ", null);
        ItemStack rods = ItemUtils.createItem(Material.FISHING_ROD, " ", null);
        ItemStack[] enchantmentCategoryMenuItems = {armors, weapons, tools, bows, rods};

        Inventory enchantmentCategoryMenu = Bukkit.createInventory(p, 27, MessageUtils.message("&3エンチャントのカテゴリーの選択", false));
        enchantmentCategoryMenu.setContents(enchantmentCategoryMenuItems);
        appendSeparator(enchantmentCategoryMenu, 1);
        enchantmentCategoryMenu.setItem(18, exit);
        enchantmentCategoryMenu.setItem(26, home);

        return enchantmentCategoryMenu;
    }

    public static Inventory createEnchantmentMenu(Player p, int category) {
        int[] armorEnchantmentIds = {0, 1, 2, 3, 4, 5, 6, 7, 8, 34, 70};
        int[] weaponEnchantmentIds = {16, 17, 18, 19, 20, 21, 34, 70};
        int[] toolEnchantmentIds = {32, 33, 35, 34, 70};
        int[] bowEnchantmentIds = {48, 49, 50, 51, 34, 70};
        int[] rodEnchantmentIds = {61, 62, 34, 70};

        int[] enchantmentIds = new int[0];
        switch(category) {
            case ENCHANTMENT_ARMORS:
                enchantmentIds = armorEnchantmentIds;
                break;

            case ENCHANTMENT_WEAPONS:
                enchantmentIds = weaponEnchantmentIds;
                break;

            case ENCHANTMENT_TOOLS:
                enchantmentIds = toolEnchantmentIds;
                break;

            case ENCHANTMENT_BOWS:
                enchantmentIds = bowEnchantmentIds;
                break;

            case ENCHANTMENT_RODS:
                enchantmentIds = rodEnchantmentIds;
                break;
        }

        int enchantmentIdsLength = enchantmentIds.length;

        ItemStack[] enchantmentBooks = new ItemStack[enchantmentIds.length];
        int i = 0;
        for (int id : enchantmentIds) {
            ItemStack enchantmentBook = ItemUtils.createItem(Material.ENCHANTED_BOOK, null, new EnchantmentWrapper(id));
            enchantmentBooks[i] = enchantmentBook;
            i++;
        }

        Inventory enchantmentMenu = Bukkit.createInventory(p, ((enchantmentIdsLength / 9 + 1) * 9 + 18), MessageUtils.message("&3エンチャント一覧", false));
        enchantmentMenu.setContents(enchantmentBooks);
        appendSeparator(enchantmentMenu, (enchantmentIdsLength / 9 + 1));
        enchantmentMenu.setItem((enchantmentIdsLength / 9 + 1) * 9 + 9, exit);
        enchantmentMenu.setItem((enchantmentIdsLength / 9 + 1) * 9 + 17, home);

        return enchantmentMenu;
    }

    public static void changeGamemodeItem(Inventory mainMenu, Material gamemodeItemMaterial) {
        ItemStack gamemodeItem = ItemUtils.createItem(gamemodeItemMaterial, " ", null);
        mainMenu.setItem(0, gamemodeItem);
    }

    public static void appendSeparator(Inventory inventory, int index) {
        int startIndex = index*9;
        for (int i=startIndex ; i<startIndex+9 ; i++) {
            inventory.setItem(i, separator);
        }
    }

    public static Material getItemFromGamemode(GameMode currentGamemode) {
        switch (currentGamemode) {
            case SURVIVAL:
                return Material.WOOD_SWORD;

            case CREATIVE:
                return Material.BRICK;

            case ADVENTURE:
                return Material.LEATHER_BOOTS;

            case SPECTATOR:
                return Material.GLASS;

            default:
                return null;
        }
    }

    public static Inventory setOptions(Inventory inventory, ItemStack[] options, int line) {
        if (options.length > 9) {
            return inventory;
        }

        int startIndex = (5 - (options.length + 1) / 2) + line * 9;

        int optionCounter = 0;
        for (int i=startIndex ; i<startIndex+options.length ; i++) {
            inventory.setItem(i, options[optionCounter]);
            optionCounter++;
        }

        return inventory;
    }

    public static ItemStack[] getGamemodeOptions(GameMode currentGamemode) {
        ArrayList<Material> options_material = new ArrayList<Material>();
        options_material.add(Material.WOOD_SWORD);
        options_material.add(Material.BRICK);
        options_material.add(Material.LEATHER_BOOTS);
        options_material.add(Material.GLASS);

        options_material.remove(getItemFromGamemode(currentGamemode));

        ItemStack[] options = new ItemStack[3];
        for (int i=0 ; i<3 ; i++) {
            options[i] = ItemUtils.createItem(options_material.get(i), " ", null);
        }

        return options;
    }

    public static ItemStack[] getEnchantmentCategoryOptions() {
        return new ItemStack[]{
                ItemUtils.createItem(Material.GOLD_CHESTPLATE, " ", null),
                ItemUtils.createItem(Material.GOLD_SWORD, " ", null),
                ItemUtils.createItem(Material.GOLD_AXE, " ", null),
                ItemUtils.createItem(Material.BOW, " ", null),
                ItemUtils.createItem(Material.FISHING_ROD, " ", null)
        };
    }
}
