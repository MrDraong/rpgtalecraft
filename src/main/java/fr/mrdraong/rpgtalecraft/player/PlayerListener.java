package fr.mrdraong.rpgtalecraft.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.mrdraong.rpgtalecraft.player.TribesUtils.TribeEnum;

public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
		Player player = playerJoinEvent.getPlayer();
		player.sendTitle(ChatColor.GOLD + "Welcome Back ", ChatColor.AQUA + player.getName(), 10, 70, 20);

		String tribe = TribesUtils.getPlayerTribe(player).getName();
		player.sendMessage(ChatColor.DARK_GREEN + "Your tribe is : " + ChatColor.GREEN + tribe);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent playerInteractEvent) {
		TribeEnum playerTribe = TribesUtils.getPlayerTribe(playerInteractEvent.getPlayer());
		switch (playerTribe) {
		case TribeEnum.WIZARD -> WizardPlayerInteraction.wizardPlayerInteract(playerInteractEvent);
		}
	}
}
