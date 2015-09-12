package org.v1cow.antiinvis;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;


public class AntiInvis extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onDrink(PlayerInteractEvent event) {
        Action action = event.getAction();
        Player player = event.getPlayer();
        if ((action == Action.RIGHT_CLICK_AIR) || (action == Action.RIGHT_CLICK_BLOCK)) {
            ItemStack it = player.getItemInHand();
            if (it.getType() == Material.POTION && it.getDurability() != 0) {
                Potion potion = Potion.fromItemStack(it);
                PotionEffectType effecttype = potion.getType().getEffectType();
                if (effecttype == PotionEffectType.INVISIBILITY) {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + ChatColor.translateAlternateColorCodes('&', getConfig().getString("blockmsg")));
                }
            }
        }
    }

    @EventHandler
    public void onDispense(BlockDispenseEvent event) {
        ItemStack it = event.getItem();
        Material mat = it.getType();
        if (mat == Material.POTION) {
            Potion potion = Potion.fromItemStack(it);
            PotionEffectType effecttype = potion.getType().getEffectType();
            if (effecttype == PotionEffectType.INVISIBILITY) {
                event.setCancelled(true);
            }
        }
    }

    @Override
    public void onDisable() {
    }
}