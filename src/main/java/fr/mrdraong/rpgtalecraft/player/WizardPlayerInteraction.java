package fr.mrdraong.rpgtalecraft.player;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import fr.mrdraong.rpgtalecraft.items.ItemsPersistentDataUtils;

public class WizardPlayerInteraction implements Listener {

	public static void wizardPlayerInteract(PlayerInteractEvent playerInteractEvent) {
		Player player = playerInteractEvent.getPlayer();
		ItemStack item = playerInteractEvent.getItem();

		if (item != null && ItemsPersistentDataUtils.itemHasTag(item.getItemMeta(), "weapon", "wand")) {
			if (playerInteractEvent.getAction().equals(Action.LEFT_CLICK_AIR)) {

				Location playerEyeLocation = player.getEyeLocation();
				Vector fireballVelocity = playerEyeLocation.getDirection();

				Entity fireballEntity = player.getWorld().spawnEntity(playerEyeLocation, EntityType.FIREBALL);
				fireballEntity.setVelocity(fireballVelocity);
			}
		}
	}

}
