package fr.mrdraong.rpgtalecraft.player;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import fr.mrdraong.rpgtalecraft.item.ItemsPersistentDataUtils;
import fr.mrdraong.rpgtalecraft.tribe.TribesUtils;
import fr.mrdraong.rpgtalecraft.tribe.TribesUtils.TribeEnum;

public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
		Player player = playerJoinEvent.getPlayer();
		player.sendTitle(ChatColor.GOLD + "Welcome Back ", ChatColor.AQUA + player.getName(), 10, 70, 20);

		String tribe = TribesUtils.getPlayerTribe(player).getName();
		player.sendMessage(ChatColor.DARK_GREEN + "Your tribe is : " + ChatColor.GREEN + tribe);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent playerInteractEvent) {
		TribeEnum playerTribe = TribesUtils.getPlayerTribe(playerInteractEvent.getPlayer());

		if (playerTribe == TribeEnum.WIZARD) {
			WizardPlayerInteraction.wizardPlayerInteract(playerInteractEvent);
		} else if (playerTribe == TribeEnum.WARRIOR) {
			WarriorPlayerInteraction.warriorPlayerInteract(playerInteractEvent);
		} else if (playerTribe == TribeEnum.ARCHER) {
			ArcherPlayerInteraction.archerPlayerInteract(playerInteractEvent);
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent playerDeathEvent) {
		Player player = playerDeathEvent.getEntity();

		if (player.getWorld().getGameRuleValue(GameRule.KEEP_INVENTORY)) {
			return;
		}

		List<ItemStack> keepItems = new ArrayList<>();

		for (ItemStack item : playerDeathEvent.getDrops()) {
			if (item != null
					&& ItemsPersistentDataUtils.itemHasStringTag(item.getItemMeta(), "rpgtalecraft", "rpgtalecraft")) {
				keepItems.add(item);
			}
		}
		playerDeathEvent.getDrops().removeAll(keepItems);

		playerDeathEvent.setKeepInventory(true);
		player.getInventory().clear();
		keepItems.forEach(item -> player.getInventory().addItem(item));

	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent playerDropItemEvent) {
		ItemStack item = playerDropItemEvent.getItemDrop().getItemStack();
		if (ItemsPersistentDataUtils.itemHasStringTag(item.getItemMeta(), "rpgtalecraft", "rpgtalecraft")) {
			playerDropItemEvent.setCancelled(true);
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
		Inventory inventory = inventoryClickEvent.getInventory();
		boolean isNotPlayerInventory = !(inventory instanceof PlayerInventory
				|| inventory instanceof CraftingInventory);
		if (inventory != null && isNotPlayerInventory) {
			ItemStack item = inventoryClickEvent.getCurrentItem();
			if (item != null && item.hasItemMeta()
					&& ItemsPersistentDataUtils.itemHasStringTag(item.getItemMeta(), "rpgtalecraft", "rpgtalecraft")) {
				inventoryClickEvent.setCancelled(true);
			}
		}
	}
}
