package fr.mrdraong.rpgtalecraft.entity;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.mrdraong.rpgtalecraft.RPGTaleCraftPlugin;

public class EntityPersistentDataUtils {
	public static boolean entityHasStringTag(Entity Entity, String storedName, String tag) {
		PersistentDataContainer container = Entity.getPersistentDataContainer();
		NamespacedKey storedNameSpacedKey = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), storedName);
		if (container.has(storedNameSpacedKey, PersistentDataType.STRING)) {
			String entityTagStored = container.get(storedNameSpacedKey, PersistentDataType.STRING);
			if (tag.equals(entityTagStored)) {
				return true;
			}
		}
		return false;
	}

	public static void entityAddStringTag(Entity entity, String storedName, String tag) {
		NamespacedKey storedNameSpacedKey = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), storedName);
		entity.getPersistentDataContainer().set(storedNameSpacedKey, PersistentDataType.STRING, tag);
	}
}
