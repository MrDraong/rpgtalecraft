package fr.mrdraong.rpgtalecraft.tribe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TribeQuitCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player targetPlayer = null;

		if (sender instanceof Player) {
			targetPlayer = (Player) sender;
		} else {
			sender.sendMessage(ChatColor.RED + "This command can only be executed by a player");
			return false;
		}
		TribesUtils.removePlayerTribe(targetPlayer);

		return true;
	}

}
