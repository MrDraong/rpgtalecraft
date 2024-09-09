package fr.mrdraong.rpgtalecraft.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.mrdraong.rpgtalecraft.RPGTaleCraftPlugin;

public class ItemsPersistentDataUtils {

	public static boolean itemHasTag(ItemMeta itemMeta, String storedName, String tag) {
		PersistentDataContainer container = itemMeta.getPersistentDataContainer();
		NamespacedKey storedNameSpacedKey = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), storedName);
		if (container.has(storedNameSpacedKey, PersistentDataType.STRING)) {
			String itemTagStored = container.get(storedNameSpacedKey, PersistentDataType.STRING);
			if (tag.equals(itemTagStored)) {
				return true;
			}
		}
		return false;
	}

	public static void itemAddTag(ItemMeta itemMeta, String storedName, String tag) {
		NamespacedKey storedNameSpacedKey = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), storedName);
		itemMeta.getPersistentDataContainer().set(storedNameSpacedKey, PersistentDataType.STRING, tag);
	}
}
