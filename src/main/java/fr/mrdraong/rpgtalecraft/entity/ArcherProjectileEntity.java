package fr.mrdraong.rpgtalecraft.entity;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.mrdraong.rpgtalecraft.RPGTaleCraftPlugin;
import fr.mrdraong.rpgtalecraft.item.ItemsPersistentDataUtils;

public class ArcherProjectileEntity implements Listener {

	public static void onArcherArrowProjectile(Arrow arrow) {

		ItemStack arrowItem = arrow.getItem();

		if (arrowItem != null) {
			Location hitLocation = arrow.getLocation();
			if (ItemsPersistentDataUtils.itemHasStringTag(arrowItem.getItemMeta(), "arrow", "fire")) {

				arrow.getWorld().createExplosion(hitLocation, 2F, false, false);
				arrow.remove();

			} else if (ItemsPersistentDataUtils.itemHasStringTag(arrowItem.getItemMeta(), "arrow", "lightning")) {

				arrow.getWorld().strikeLightning(hitLocation);

				Bukkit.getScheduler().runTaskLater(RPGTaleCraftPlugin.getInstance(), () -> {
					for (Block block : getNearbyBlocks(hitLocation, 2)) {
						if (block.getType() == Material.FIRE) {
							block.setType(Material.AIR);
						}
					}
				}, 20L);
				arrow.remove();
			}
		}

	}

	public static void onArcherShootBow(Arrow arrowEntity, ItemStack shootItem) {
		if (shootItem != null) {
			if (ItemsPersistentDataUtils.itemHasStringTag(shootItem.getItemMeta(), "arrow", "fire")) {
				addFlameTrail(arrowEntity);
			} else if (ItemsPersistentDataUtils.itemHasStringTag(shootItem.getItemMeta(), "arrow", "lightning")) {
				addLightningTrail(arrowEntity);
			}

		}
	}

	private static void addFlameTrail(Arrow arrow) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!arrow.isDead() && !arrow.isOnGround() && arrow.isValid()) {
					arrow.getWorld().spawnParticle(Particle.FLAME, arrow.getLocation(), 5, 0.1, 0.1, 0.1, 0.01);
				} else {
					cancel();
				}
			}
		}.runTaskTimer(RPGTaleCraftPlugin.getInstance(), 0L, 3L); // Execute every 2 ticks
	}

	private static void addLightningTrail(Arrow arrow) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if (!arrow.isDead() && !arrow.isOnGround() && arrow.isValid()) {
					arrow.getWorld().spawnParticle(Particle.ANGRY_VILLAGER, arrow.getLocation(), 5, 0.1, 0.1, 0.1,
							0.01);
				} else {
					cancel();
				}
			}
		}.runTaskTimer(RPGTaleCraftPlugin.getInstance(), 0L, 3L); // Execute every 2 ticks
	}

	private static List<Block> getNearbyBlocks(Location location, int radius) {
		List<Block> blocks = new ArrayList<>();
		for (int x = -radius; x <= radius; x++) {
			for (int y = -radius; y <= radius; y++) {
				for (int z = -radius; z <= radius; z++) {
					blocks.add(location.getBlock().getRelative(x, y, z));
				}
			}
		}
		return blocks;
	}
}
