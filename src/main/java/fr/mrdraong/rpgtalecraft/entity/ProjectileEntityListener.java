package fr.mrdraong.rpgtalecraft.entity;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class ProjectileEntityListener implements Listener {

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent projectileHitEvent) {
		Entity projectileEntity = projectileHitEvent.getEntity();
		if (projectileEntity instanceof Snowball) {
			Snowball snowball = (Snowball) projectileHitEvent.getEntity();
			if (EntityPersistentDataUtils.entityHasStringTag(snowball, "spell", "ice")) {
				WizardProjectileEntity.onWizardSnowballProjectile(snowball);
			}
		} else if (projectileEntity instanceof Arrow) {
			Arrow arrow = (Arrow) projectileHitEvent.getEntity();
			ArcherProjectileEntity.onArcherArrowProjectile(arrow);
		}
	}

	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent entityShootBowEvent) {
		if (entityShootBowEvent.getEntityType() == EntityType.PLAYER) {
			ItemStack shootItem = entityShootBowEvent.getConsumable();
			Arrow arrowEntity = (Arrow) entityShootBowEvent.getProjectile();
			ArcherProjectileEntity.onArcherShootBow(arrowEntity, shootItem);
		}
	}
}
