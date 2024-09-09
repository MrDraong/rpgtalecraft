package fr.mrdraong.rpgtalecraft.player;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class TribeBookCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
			BookMeta bookMeta = (BookMeta) book.getItemMeta();

			bookMeta.setTitle("Tribes Book");
			bookMeta.setAuthor("Server");

			// Page for Warrior
			String warriorPageText = ChatColor.BLUE + "Click " + ChatColor.GOLD + "" + ChatColor.BOLD + "[here]"
					+ ChatColor.BLUE + " to get a special sword !";

			ComponentBuilder warriorPageComponentBuilder = new ComponentBuilder().append(warriorPageText)
					.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tribepage warrior " + player.getUniqueId()));

			// Page for Archer
			String archerPageText = ChatColor.BLUE + "Click " + ChatColor.GOLD + "" + ChatColor.BOLD + "[here]"
					+ ChatColor.BLUE + " to get a special shield !";

			ComponentBuilder archerPageComponentBuilder = new ComponentBuilder().append(archerPageText)
					.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tribepage archer " + player.getUniqueId()));

			// Page for Wizard
			String wizardPageText = ChatColor.BLUE + "Click " + ChatColor.GOLD + "" + ChatColor.BOLD + "[here]"
					+ ChatColor.BLUE + " to get a special shield !";

			ComponentBuilder wizardPageComponentBuilder = new ComponentBuilder().append(wizardPageText)
					.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tribepage wizard " + player.getUniqueId()));

			bookMeta.spigot().addPage(warriorPageComponentBuilder.create(), archerPageComponentBuilder.create(),
					wizardPageComponentBuilder.create());
			book.setItemMeta(bookMeta);

			player.getInventory().addItem(book);
			player.sendMessage(ChatColor.GREEN + "You have received the Tribes book !");

			return true;
		}

		return false;
	}
}
