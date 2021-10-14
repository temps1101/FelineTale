package com.temps1101.felinetale.felinetale.listeners;

import com.temps1101.felinetale.felinetale.utils.InventoryUtils;
import com.temps1101.felinetale.felinetale.utils.MessageUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collection;
import java.util.logging.Logger;

public class InventoryClickListener implements Listener {
    private final Logger logger;
    public InventoryClickListener(Logger logger) {
        this.logger = logger;
    }
    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();


        switch (player.getOpenInventory().getTopInventory().getTitle()) {
            case "§3メニュー":
                switch (clickedItem.getType()) {
                    case WOOD_SWORD:
                        InventoryUtils.appendSeparator(player.getOpenInventory().getTopInventory(), 2);
                        if (player.getGameMode() != GameMode.SURVIVAL) {
                            player.setGameMode(GameMode.SURVIVAL);
                            InventoryUtils.changeGamemodeItem(player.getOpenInventory().getTopInventory(), Material.WOOD_SWORD);
                            player.sendMessage(MessageUtils.message("あなたのゲームモードをサバイバルにしました！", true));
                            logger.info(MessageUtils.message("Set {0}'s gamemode to survival", false, player.getDisplayName()));
                        }
                        InventoryUtils.setOptions(player.getOpenInventory().getTopInventory(), InventoryUtils.getGamemodeOptions(player.getGameMode()), 2);
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);

                        break;

                    case BRICK:
                        InventoryUtils.appendSeparator(player.getOpenInventory().getTopInventory(), 2);
                        if (player.getGameMode() != GameMode.CREATIVE) {
                            player.setGameMode(GameMode.CREATIVE);
                            InventoryUtils.changeGamemodeItem(player.getOpenInventory().getTopInventory(), Material.BRICK);
                            player.sendMessage(MessageUtils.message("あなたのゲームモードをクリエイティブにしました！", true));
                            logger.info(MessageUtils.message("Set {0}'s gamemode to creative", false, player.getDisplayName()));
                        }
                        InventoryUtils.setOptions(player.getOpenInventory().getTopInventory(), InventoryUtils.getGamemodeOptions(player.getGameMode()), 2);
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);

                        break;

                    case LEATHER_BOOTS:
                        InventoryUtils.appendSeparator(player.getOpenInventory().getTopInventory(), 2);
                        if (player.getGameMode() != GameMode.ADVENTURE) {
                            player.setGameMode(GameMode.ADVENTURE);
                            InventoryUtils.changeGamemodeItem(player.getOpenInventory().getTopInventory(), Material.LEATHER_BOOTS);
                            player.sendMessage(MessageUtils.message("あなたのゲームモードをアドベンチャーにしました！", true));
                            logger.info(MessageUtils.message("Set {0}'s gamemode to adventure", false, player.getDisplayName()));
                        }
                        InventoryUtils.setOptions(player.getOpenInventory().getTopInventory(), InventoryUtils.getGamemodeOptions(player.getGameMode()), 2);
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);

                        break;

                    case GLASS:
                        InventoryUtils.appendSeparator(player.getOpenInventory().getTopInventory(), 2);
                        if (player.getGameMode() != GameMode.SPECTATOR) {
                            player.setGameMode(GameMode.SPECTATOR);
                            InventoryUtils.changeGamemodeItem(player.getOpenInventory().getTopInventory(), Material.GLASS);
                            player.sendMessage(MessageUtils.message("あなたのゲームモードをスペクテイターにしました！", true));
                            logger.info(MessageUtils.message("Set {0}'s gamemode to spectator", false, player.getDisplayName()));
                        }
                        InventoryUtils.setOptions(player.getOpenInventory().getTopInventory(), InventoryUtils.getGamemodeOptions(player.getGameMode()), 2);
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);

                        break;

                    case GOLDEN_APPLE:
                        InventoryUtils.appendSeparator(player.getOpenInventory().getTopInventory(), 2);
                        player.setHealth(20.0);
                        player.setFoodLevel(20);

                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BREATH, 1, 1);
                        player.sendMessage(MessageUtils.message("体力＆満腹度を回復しました！", true));
                        logger.info(MessageUtils.message("Healed {0}'s health and hunger", false, player.getDisplayName()));
                        break;

                    case DIAMOND_SWORD:
                        InventoryUtils.appendSeparator(player.getOpenInventory().getTopInventory(), 2);
                        Collection<Entity> killList = player.getWorld().getNearbyEntities(player.getLocation(), 100, 100, 100);

                        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 1, 1);
                        killList.stream().filter(entity -> entity instanceof Monster).forEach(entity -> ((Monster) entity).damage(3.4 * Math.pow(10, 38)));

                        player.sendMessage(MessageUtils.message("{0}体の敵を倒しました！", true, killList.size()));
                        logger.info(MessageUtils.message("Killed {0} hostile mobs around {1} (position : {2})", false, killList.size(), player.getDisplayName(), player.getLocation().toString()));
                        break;

                    case ENCHANTMENT_TABLE:
                        InventoryUtils.setOptions(player.getOpenInventory().getTopInventory(), InventoryUtils.getEnchantmentCategoryOptions(), 2);
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case GOLD_CHESTPLATE:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_ARMORS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case GOLD_SWORD:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_WEAPONS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case GOLD_AXE:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_TOOLS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case BOW:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_BOWS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case FISHING_ROD:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_RODS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case BARRIER:
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    default:
                        break;

                }

                break;

            case "§3エンチャントのカテゴリーの選択":
                switch (clickedItem.getType()) {
                    case GOLD_CHESTPLATE:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_ARMORS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case GOLD_SWORD:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_WEAPONS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case GOLD_AXE:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_TOOLS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case BOW:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_BOWS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case FISHING_ROD:
                        player.openInventory(InventoryUtils.createEnchantmentMenu(player, InventoryUtils.ENCHANTMENT_RODS));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    case BARRIER:
                        player.closeInventory();
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);

                        break;

                    case DARK_OAK_DOOR_ITEM:
                        player.openInventory(InventoryUtils.createMainMenu(player));
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        break;

                    default:
                        break;
                }

                break;

            case "§3エンチャント一覧":
                switch (clickedItem.getType()) {
                    case ENCHANTED_BOOK:
                        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
                            player.sendMessage(MessageUtils.message("エンチャントするアイテムを持ってください！", true));
                        } else {
                            Enchantment enchantment = (Enchantment) ((EnchantmentStorageMeta) clickedItem.getItemMeta()).getStoredEnchants().keySet().toArray()[0];

                            ItemMeta mainHandMeta = player.getInventory().getItemInMainHand().getItemMeta();
                            mainHandMeta.addEnchant(enchantment, 1, true);
                            player.getInventory().getItemInMainHand().setItemMeta(mainHandMeta);

                            player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
                            player.sendMessage(MessageUtils.message("メインハンドのアイテムにエンチャントを付与しました！", true));
                            logger.info(MessageUtils.message("Added Enchantment ({0}) to {1}'s mainhand item)", false, enchantment.getName(), player.getDisplayName()));
                        }
                        break;

                    case DARK_OAK_DOOR_ITEM:
                        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
                        player.openInventory(InventoryUtils.createMainMenu(player));
                        InventoryUtils.setOptions(player.getOpenInventory().getTopInventory(), InventoryUtils.getEnchantmentCategoryOptions(), 2);
                        break;

                    case BARRIER:
                        player.closeInventory();
                        break;

                    default:
                        break;
                }

                break;

            default:
                break;
        }

        event.setCancelled(true);
    }
}
