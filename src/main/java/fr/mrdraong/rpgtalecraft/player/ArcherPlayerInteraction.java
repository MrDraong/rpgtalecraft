package fr.mrdraong.rpgtalecraft.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.mrdraong.rpgtalecraft.item.ItemsPersistentDataUtils;

public class ArcherPlayerInteraction {
	public static void archerPlayerInteract(PlayerInteractEvent playerInteractEvent) {
		Player player = playerInteractEvent.getPlayer();
		ItemStack item = playerInteractEvent.getItem();
		if (item != null && item.getItemMeta() != null) {
			if (Action.RIGHT_CLICK_AIR.equals(playerInteractEvent.getAction())) {
				archerPlayerRightClick(player, item);
			}
		}

	}

	private static void archerPlayerRightClick(Player player, ItemStack item) {
		if (item.getItemMeta() != null) {
			ItemMeta itemMeta = item.getItemMeta();
			if (ItemsPersistentDataUtils.itemHasStringTag(itemMeta, "arrow", "fire")) {
				ItemsPersistentDataUtils.itemAddStringTag(itemMeta, "arrow", "lightning");
				itemMeta.setItemName(ChatColor.BLUE + "Lightning Arrow");
			} else if (ItemsPersistentDataUtils.itemHasStringTag(itemMeta, "arrow", "lightning")) {
				ItemsPersistentDataUtils.itemAddStringTag(itemMeta, "arrow", "fire");
				itemMeta.setItemName(ChatColor.RED + "Fire Arrow");
			}
			item.setItemMeta(itemMeta);
		}
	}
}
