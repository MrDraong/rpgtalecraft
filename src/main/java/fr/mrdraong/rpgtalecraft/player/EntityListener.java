package fr.mrdraong.rpgtalecraft.player;

import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.mrdraong.rpgtalecraft.items.ItemsPersistentDataUtils;

public class EntityListener implements Listener {

	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent entityShootBowEvent) {
		ItemStack shootItem = entityShootBowEvent.getConsumable();
		if (shootItem != null) {
			if (ItemsPersistentDataUtils.itemHasTag(shootItem.getItemMeta(), "arrow", "fire")) {
				Arrow arrowEntity = (Arrow) entityShootBowEvent.getProjectile();
				PotionEffect potionEffectToApply = new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 1, 1, true);
				arrowEntity.addCustomEffect(potionEffectToApply, false);
			}
		}
	}
}
