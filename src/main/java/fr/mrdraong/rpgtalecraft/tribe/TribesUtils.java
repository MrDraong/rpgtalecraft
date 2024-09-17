package fr.mrdraong.rpgtalecraft.tribe;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import fr.mrdraong.rpgtalecraft.RPGTaleCraftPlugin;
import fr.mrdraong.rpgtalecraft.item.ItemsPersistentDataUtils;

public final class TribesUtils {

	public static enum TribeEnum {
		WARRIOR("Warrior"), ARCHER("Archer"), WIZARD("Wizard"), NONE("None");

		private String name;

		private TribeEnum(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public static TribeEnum fromString(String tribeName) {
			return Arrays.stream(TribeEnum.values()).filter(tribe -> tribe.name.equalsIgnoreCase(tribeName)).findFirst()
					.orElseGet(() -> TribeEnum.NONE);
		}
	}

	public static void choseWarriorTribe(Player player) {
		removePlayerTribe(player);
		storePlayerTribeData(player, TribeEnum.WARRIOR);

		ItemStack swordItem = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta swordMeta = swordItem.getItemMeta();

		ArrayList<String> swordLore = new ArrayList<>();
		swordLore.add("Remember that a swordsman is not made by his weapon...");
		swordLore.add("All swordsmen have a weapon");

		swordMeta.setItemName("Beginner's Sword");
		swordMeta.setLore(swordLore);
		swordMeta.setRarity(ItemRarity.EPIC);
		swordMeta.setUnbreakable(true);
		swordMeta.addEnchant(Enchantment.SHARPNESS, 3, false);
		swordMeta.addEnchant(Enchantment.BINDING_CURSE, 1, false);

		ItemsPersistentDataUtils.itemAddStringTag(swordMeta, "rpgtalecraft", "rpgtalecraft");
		ItemsPersistentDataUtils.itemAddStringTag(swordMeta, "weapon", "sword");

		swordItem.setItemMeta(swordMeta);

		player.getInventory().addItem(swordItem);
		player.sendMessage(ChatColor.GREEN + "You are now a Warrior !");
	}

	public static void choseArcherTribe(Player player) {
		removePlayerTribe(player);
		storePlayerTribeData(player, TribeEnum.ARCHER);

		ItemStack bowItem = new ItemStack(Material.BOW);
		ItemMeta bowMeta = bowItem.getItemMeta();

		ArrayList<String> bowLore = new ArrayList<>();
		bowLore.add("Keep training to become stronger and stronger !");
		bowLore.add("Remeber there is a lot of things to train...");

		bowMeta.setItemName("Training Bow");
		bowMeta.setLore(bowLore);
		bowMeta.addEnchant(Enchantment.INFINITY, 1, false);
		bowMeta.setRarity(ItemRarity.EPIC);
		bowMeta.setUnbreakable(true);
		bowMeta.addEnchant(Enchantment.BINDING_CURSE, 1, false);

		ItemsPersistentDataUtils.itemAddStringTag(bowMeta, "rpgtalecraft", "rpgtalecraft");

		bowItem.setItemMeta(bowMeta);

		ItemStack arrowItem = new ItemStack(Material.ARROW);
		ItemMeta arrowMeta = arrowItem.getItemMeta();
		ArrayList<String> arrowLore = new ArrayList<>();
		arrowLore.add("Unleash a powerful arrow. With a " + ChatColor.GREEN + "right-click");
		arrowLore.add(ChatColor.RESET + "toggle between Fire and Lightning effects to master your bow.");
		arrowMeta.setItemName(ChatColor.BLUE + "Lightning Arrow");
		arrowMeta.setLore(arrowLore);
		arrowMeta.addEnchant(Enchantment.BINDING_CURSE, 1, false);
		ItemsPersistentDataUtils.itemAddStringTag(arrowMeta, "rpgtalecraft", "rpgtalecraft");
		ItemsPersistentDataUtils.itemAddStringTag(arrowMeta, "arrow", "lightning");
		arrowItem.setItemMeta(arrowMeta);

		player.getInventory().addItem(bowItem, arrowItem);
		player.sendMessage(ChatColor.GREEN + "You are now an Archer !");

	}

	public static void choseWizardTribe(Player player) {
		removePlayerTribe(player);
		storePlayerTribeData(player, TribeEnum.WIZARD);

		ItemStack wandItem = new ItemStack(Material.STICK);
		ItemMeta wandMeta = wandItem.getItemMeta();

		ArrayList<String> wandLore = new ArrayList<>();
		wandLore.add("Harry... You are not Harry !");
		wandLore.add("Who give you this power !?");
		wandLore.add("");
		wandLore.add("Wield the power of magic with a " + ChatColor.BLUE + "right-click");
		wandLore.add(ChatColor.RESET + "Switch between Fireball and Ice spells to control the battlefield.");
		wandMeta.setItemName("Fire Wand");
		wandMeta.setLore(wandLore);
		wandMeta.addEnchant(Enchantment.BINDING_CURSE, 1, false);

		ItemsPersistentDataUtils.itemAddStringTag(wandMeta, "rpgtalecraft", "rpgtalecraft");
		ItemsPersistentDataUtils.itemAddStringTag(wandMeta, "weapon", "wand");
		ItemsPersistentDataUtils.itemAddStringTag(wandMeta, "magic", "fire");
		wandItem.setItemMeta(wandMeta);

		player.getInventory().addItem(wandItem);
		player.sendMessage(ChatColor.GREEN + "You are now a Wizard !");

	}

	private static void storePlayerTribeData(Player player, TribeEnum tribe) {
		NamespacedKey playerTribe = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), "player-tribe");
		player.getPersistentDataContainer().set(playerTribe, PersistentDataType.STRING, tribe.getName());
	}

	public static TribeEnum getPlayerTribe(Player player) {
		PersistentDataContainer container = player.getPersistentDataContainer();
		NamespacedKey playerTribeNamespacedKey = new NamespacedKey(RPGTaleCraftPlugin.getInstance(), "player-tribe");
		String playerTribeName = TribeEnum.NONE.getName();

		if (container.has(playerTribeNamespacedKey, PersistentDataType.STRING)) {
			playerTribeName = container.get(playerTribeNamespacedKey, PersistentDataType.STRING);
		}

		TribeEnum playerTribeEnum = switch (TribeEnum.fromString(playerTribeName)) {
		case TribeEnum.WARRIOR -> TribeEnum.WARRIOR;
		case TribeEnum.ARCHER -> TribeEnum.ARCHER;
		case TribeEnum.WIZARD -> TribeEnum.WIZARD;
		default -> TribeEnum.NONE;
		};

		return playerTribeEnum;
	}

	public static void removePlayerTribe(Player player) {
		TribeEnum playerTribe = getPlayerTribe(player);
		if (playerTribe != TribeEnum.NONE) {
			removeTribeItems(player);
			player.sendMessage(ChatColor.BLUE + "You have left your tribe.");
		} else {
			player.sendMessage(ChatColor.RED + "You don't have a tribe yet.");
		}
	}

	private static void removeTribeItems(Player player) {
		if (player.getInventory() != null) {
			for (ItemStack item : player.getInventory().getContents()) {
				if (item != null && item.hasItemMeta()) {
					if (ItemsPersistentDataUtils.itemHasStringTag(item.getItemMeta(), "rpgtalecraft", "rpgtalecraft")) {
						player.getInventory().remove(item);
					}
				}
			}
			player.updateInventory();
		}

	}
}
