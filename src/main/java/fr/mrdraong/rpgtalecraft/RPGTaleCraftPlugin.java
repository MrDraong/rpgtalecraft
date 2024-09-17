package fr.mrdraong.rpgtalecraft;

import org.bukkit.plugin.java.JavaPlugin;

import fr.mrdraong.rpgtalecraft.entity.ProjectileEntityListener;
import fr.mrdraong.rpgtalecraft.player.PlayerListener;
import fr.mrdraong.rpgtalecraft.tribe.TribeBookCommand;
import fr.mrdraong.rpgtalecraft.tribe.TribePageCommand;
import fr.mrdraong.rpgtalecraft.tribe.TribeQuitCommand;

public class RPGTaleCraftPlugin extends JavaPlugin {

	private static RPGTaleCraftPlugin instance;

	@Override
	public void onEnable() {
		instance = this;

		// Register event listeners
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		getServer().getPluginManager().registerEvents(new ProjectileEntityListener(), this);

		// Register commands
		this.getCommand("tribebook").setExecutor(new TribeBookCommand());
		this.getCommand("tribepage").setExecutor(new TribePageCommand());
		this.getCommand("tribequit").setExecutor(new TribeQuitCommand());
	}

	@Override
	public void onDisable() {

	}

	public static RPGTaleCraftPlugin getInstance() {
		return instance;
	}
}