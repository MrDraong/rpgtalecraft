package fr.mrdraong.rpgtalecraft.entity;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Snowball;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WizardProjectileEntity {

	public static void onWizardSnowballProjectile(Snowball snowball) {

		Location hitLocation = snowball.getLocation();

		snowball.getWorld().spawnParticle(Particle.CLOUD, hitLocation, 30, 0.5, 0.5, 0.5, 0.1);
		snowball.getWorld().spawnParticle(Particle.BLOCK, hitLocation, 30, 0.5, 0.5, 0.5, 0.1,
				Material.ICE.createBlockData());

		double radius = 2.0;
		List<Entity> nearbyEntities = (List<Entity>) snowball.getNearbyEntities(radius, radius, radius);

		for (Entity entity : nearbyEntities) {

			if (entity instanceof Monster) {

				((LivingEntity) entity).damage(2.0); // 1 heart
				((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 1));
			}
		}
	}

}
