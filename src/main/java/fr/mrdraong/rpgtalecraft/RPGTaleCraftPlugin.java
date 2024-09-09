package fr.mrdraong.rpgtalecraft;

import org.bukkit.plugin.java.JavaPlugin;

import fr.mrdraong.rpgtalecraft.player.EntityListener;
import fr.mrdraong.rpgtalecraft.player.PlayerListener;
import fr.mrdraong.rpgtalecraft.player.TribeBookCommand;
import fr.mrdraong.rpgtalecraft.player.TribePageCommand;

public class RPGTaleCraftPlugin extends JavaPlugin {

	private static RPGTaleCraftPlugin instance;

	@Override
	public void onEnable() {
		instance = this;

		// Register event listeners
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new EntityListener(), this);

		// Register commands
		this.getCommand("givebook").setExecutor(new TribeBookCommand());
		this.getCommand("tribepage").setExecutor(new TribePageCommand());
	}

	@Override
	public void onDisable() {

	}

	public static RPGTaleCraftPlugin getInstance() {
		return instance;
	}
}