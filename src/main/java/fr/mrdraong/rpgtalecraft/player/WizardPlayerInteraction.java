package fr.mrdraong.rpgtalecraft.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import fr.mrdraong.rpgtalecraft.RPGTaleCraftPlugin;
import fr.mrdraong.rpgtalecraft.entity.EntityPersistentDataUtils;
import fr.mrdraong.rpgtalecraft.item.ItemsPersistentDataUtils;

public class WizardPlayerInteraction {

	public static void wizardPlayerInteract(PlayerInteractEvent playerInteractEvent) {
		Player player = playerInteractEvent.getPlayer();
		ItemStack item = playerInteractEvent.getItem();
		if (item != null && ItemsPersistentDataUtils.itemHasStringTag(item.getItemMeta(), "weapon", "wand")) {
			if (Action.LEFT_CLICK_AIR.equals(playerInteractEvent.getAction())) {
				wizardPlayerLeftClick(player, item);
			} else if (Action.RIGHT_CLICK_AIR.equals(playerInteractEvent.getAction())) {
				wizardPlayerRightClick(player, item);
			}
		}

	}

	private static void wizardPlayerRightClick(Player player, ItemStack item) {
		if (item.getItemMeta() != null) {
			ItemMeta itemMeta = item.getItemMeta();
			if (ItemsPersistentDataUtils.itemHasStringTag(itemMeta, "magic", "fire")) {
				ItemsPersistentDataUtils.itemAddStringTag(itemMeta, "magic", "ice");
				itemMeta.setItemName(ChatColor.AQUA + "Frozen Wand");
			} else if (ItemsPersistentDataUtils.itemHasStringTag(itemMeta, "magic", "ice")) {
				ItemsPersistentDataUtils.itemAddStringTag(itemMeta, "magic", "fire");
				itemMeta.setItemName(ChatColor.RED + "Fire Wand");
			} else {
				ItemsPersistentDataUtils.itemAddStringTag(itemMeta, "magic", "fire");
				itemMeta.setItemName(ChatColor.RED + "Fire Wand");
			}
			item.setItemMeta(itemMeta);
		}
	}

	private static void wizardPlayerLeftClick(Player player, ItemStack item) {
		if (ItemsPersistentDataUtils.itemHasStringTag(item.getItemMeta(), "magic", "fire")) {
			castFireSpell(player);
		} else if (ItemsPersistentDataUtils.itemHasStringTag(item.getItemMeta(), "magic", "ice")) {
			castIceSpell(player);
		}
	}

	private static void castFireSpell(Player player) {
		Location playerEyeLocation = player.getEyeLocation();
		Vector fireballVelocity = playerEyeLocation.getDirection();

		Fireball fireballEntity = (Fireball) player.getWorld().spawnEntity(playerEyeLocation, EntityType.FIREBALL);
		fireballEntity.setVelocity(fireballVelocity);
		fireballEntity.setYield(0);
		fireballEntity.setIsIncendiary(false);

		Bukkit.getScheduler().runTaskLater(RPGTaleCraftPlugin.getInstance(), () -> {
			fireballEntity.remove();
		}, 80L);
	}

	private static void castIceSpell(Player player) {
		Location playerEyeLocation = player.getEyeLocation();
		Vector snowballVelocity = playerEyeLocation.getDirection();

		Snowball snowball = (Snowball) player.getWorld().spawnEntity(playerEyeLocation, EntityType.SNOWBALL);

		EntityPersistentDataUtils.entityAddStringTag(snowball, "spell", "ice");

		snowball.setVelocity(snowballVelocity);
	}

}
