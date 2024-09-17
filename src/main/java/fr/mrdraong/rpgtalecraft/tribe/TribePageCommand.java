package fr.mrdraong.rpgtalecraft.tribe;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TribePageCommand implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 1) {
			String pageIdentifier = args[0];
			String uuidString = args[1];

			// Conversion of the string into Java UUID
			try {
				UUID uuid = UUID.fromString(uuidString);
				Player player = Bukkit.getPlayer(uuid);
				if (player != null) {

					switch (pageIdentifier) {
					case "warrior" -> TribesUtils.choseWarriorTribe(player);
					case "archer" -> TribesUtils.choseArcherTribe(player);
					case "wizard" -> TribesUtils.choseWizardTribe(player);
					default -> player.sendMessage(ChatColor.RED + "Invalid tribe.");
					}

					return true;
				}
			} catch (IllegalArgumentException e) {
				// UUID invalid
				sender.sendMessage(ChatColor.RED + "You don't have permission !");
				return true;
			}
		}
		return false;
	}
}
