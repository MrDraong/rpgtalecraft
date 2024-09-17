package fr.mrdraong.rpgtalecraft.item;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.mrdraong.rpgtalecraft.RPGTaleCraftPlugin;

public class ItemsPersistentDataUtils {

	public static boolean itemHasStringTag(ItemMeta itemMeta, String storedName, String tag) {
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

	public static void itemAddStringTag(ItemMeta itemMeta, String storedName, String tag) {
		NamespacedKey storedNameSpacedKey = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), storedName);
		itemMeta.getPersistentDataContainer().set(storedNameSpacedKey, PersistentDataType.STRING, tag);
	}

	public static void itemAddLongTag(ItemMeta itemMeta, String storedName, Long tag) {
		NamespacedKey storedNameSpacedKey = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), storedName);
		itemMeta.getPersistentDataContainer().set(storedNameSpacedKey, PersistentDataType.LONG, tag);
	}

	public static Long itemGetLongTagValue(ItemMeta itemMeta, String storedName) {
		PersistentDataContainer container = itemMeta.getPersistentDataContainer();
		NamespacedKey storedNameSpacedKey = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), storedName);
		Long itemTagStored = 0L;
		if (container.has(storedNameSpacedKey, PersistentDataType.LONG)) {
			itemTagStored = container.get(storedNameSpacedKey, PersistentDataType.LONG);

		}
		return itemTagStored;
	}

	public static String itemGetStringTagValue(ItemMeta itemMeta, String storedName) {
		PersistentDataContainer container = itemMeta.getPersistentDataContainer();
		NamespacedKey storedNameSpacedKey = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), storedName);
		String itemTagStored = "";
		if (container.has(storedNameSpacedKey, PersistentDataType.STRING)) {
			itemTagStored = container.get(storedNameSpacedKey, PersistentDataType.STRING);

		}
		return itemTagStored;
	}
}
