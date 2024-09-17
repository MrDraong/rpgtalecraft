package fr.mrdraong.rpgtalecraft.player;

import java.util.ArrayList;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import fr.mrdraong.rpgtalecraft.item.ItemsPersistentDataUtils;

public class WarriorPlayerInteraction {

	private final static long COOLDOWN_TIME = 10000;

	public static void warriorPlayerInteract(PlayerInteractEvent playerInteractEvent) {
		Player player = playerInteractEvent.getPlayer();
		ItemStack itemStack = playerInteractEvent.getItem();

		if (itemStack != null
				&& ItemsPersistentDataUtils.itemHasStringTag(itemStack.getItemMeta(), "weapon", "sword")) {
			if (Action.RIGHT_CLICK_AIR.equals(playerInteractEvent.getAction())
					|| Action.RIGHT_CLICK_BLOCK.equals(playerInteractEvent.getAction())) {

				long currentTime = System.currentTimeMillis();
				long lastUsedTime = ItemsPersistentDataUtils.itemGetLongTagValue(itemStack.getItemMeta(), "cooldown");
				if (currentTime - lastUsedTime < COOLDOWN_TIME) {
					long timeLeft = (COOLDOWN_TIME - (currentTime - lastUsedTime)) / 1000;
					player.sendMessage("Cooldown time : " + timeLeft + " seconds left.");
					return;
				}

				ItemsPersistentDataUtils.itemAddLongTag(itemStack.getItemMeta(), "cooldown", currentTime);

				ArrayList<Entity> entitiesNearby = (ArrayList<Entity>) player.getNearbyEntities(10d, 10d, 10d);
				entitiesNearby.forEach(nearbyEntity -> {
					grabMonster(nearbyEntity, player);
				});
			}
		}
	}

	private static void grabMonster(Entity nearbyEntity, Player player) {
		if (nearbyEntity instanceof Monster) {
			Vector direction = player.getLocation().toVector().subtract(nearbyEntity.getLocation().toVector());

			double yBoost = 2;
			direction.setY(direction.getY() + yBoost);
			direction.normalize();
			direction.multiply(0.8);

			nearbyEntity.setVelocity(direction);

			player.getWorld().spawnParticle(Particle.FLAME, nearbyEntity.getLocation(), 20, 0.5, 0.5, 0.5, 0.05);
			player.getWorld().playSound(player.getLocation(), "minecraft:entity.ender_dragon.flap", 1, 1);
		}
	}

}
