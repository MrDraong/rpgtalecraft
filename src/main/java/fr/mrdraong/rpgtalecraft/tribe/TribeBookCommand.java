package fr.mrdraong.rpgtalecraft.tribe;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class TribeBookCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			UUID playerUUID = player.getUniqueId();
			ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta bookMeta = (BookMeta) book.getItemMeta();

			bookMeta.setTitle("Tribes Book");
			bookMeta.setAuthor("Server");

			bookMeta.spigot().addPage(createWarriorLorePage(playerUUID), createArcherLorePage(playerUUID),
					createWizardLorePage(playerUUID));

			book.setItemMeta(bookMeta);

			player.getInventory().addItem(book);
			player.sendMessage(ChatColor.GREEN + "You have received the Tribes book!");

			return true;
		}

		return false;
	}

	private BaseComponent[] createWarriorLorePage(UUID playerUUID) {
		String warriorPageText = ChatColor.RED + "Strength surges through your veins.\n\n" + ChatColor.GOLD
				+ "[Join the Warrior now !]" + ChatColor.RED + " to embrace the path and become a true Hero\n\n"
				+ ChatColor.BLUE + "Taunt: Draw enemies close, forcing them to fight you.\n" + ChatColor.BLUE
				+ "Power Strike: Devastating blows with enhanced sword strength.";

		return new ComponentBuilder().append(warriorPageText)
				.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tribepage warrior " + playerUUID)).create();
	}

	private BaseComponent[] createArcherLorePage(UUID playerUUID) {
		String archerPageText = ChatColor.GREEN + "Your keen eyes never miss their mark.\n" + ChatColor.GOLD
				+ "[Sharpen your Arrows !]" + ChatColor.GREEN + " to wield the bow of the Archer.\n\n" + ChatColor.BLUE
				+ "Flame Arrow: Shoot flaming arrows that ignite your enemies.\n" + ChatColor.BLUE
				+ "Lightning Arrow: Unleash arrows charged with lightning.";

		return new ComponentBuilder().append(archerPageText)
				.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tribepage archer " + playerUUID)).create();
	}

	private BaseComponent[] createWizardLorePage(UUID playerUUID) {
		String wizardPageText = ChatColor.DARK_PURPLE + "The arcane secrets call to you.\n" + ChatColor.GOLD
				+ "[Discover the Power of Wizard !]" + ChatColor.DARK_PURPLE + " and master the Wizard's spells.\n\n"
				+ ChatColor.BLUE + "Fireball: Launch fiery projectiles that scorch your enemies.\n" + ChatColor.BLUE
				+ "Frostball: Hurl freezing spheres that slow and damage foes.";

		return new ComponentBuilder().append(wizardPageText)
				.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tribepage wizard " + playerUUID)).create();
	}
}
