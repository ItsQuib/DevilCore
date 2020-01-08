package net.devilcraft.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import net.raidstone.wgevents.events.RegionLeftEvent;

public class Battle implements Listener {
	
	Plugin plugin;
	
//	public File playerFile = new File(this.getDataFolder() + "/players.yml");
//	public FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
	
	public String chatPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Devil" + ChatColor.WHITE + "Craft" + ChatColor.GRAY + "] ";
	
	ArrayList<String> neutralOutposts = new ArrayList<String>();
	ArrayList<String> redOutposts = new ArrayList<String>();
	ArrayList<String> blueOutposts = new ArrayList<String>();

		
	public void onEnable() {
		
		
		
	}
	
	public void initEvents(Plugin p) {
		
		this.plugin = p;
		
	}
	
	public void saveFile(FileConfiguration yamlConfig, File file) {
		try{
			yamlConfig.save(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean changeBanner(String outpostName, int color) {
		
		File outpostsFile = new File(plugin.getDataFolder() + "/Outposts.yml");
		FileConfiguration outpostsConfig = YamlConfiguration.loadConfiguration(outpostsFile);
		
		if(outpostsConfig.get("Outposts." + outpostName) == null) {
			return false;
		}
		
		for(String s : outpostsConfig.getConfigurationSection("Outposts." + outpostName + ".banners").getKeys(false)) {
				
		
			String bannerWorldName = outpostsConfig.getString("Outposts." + outpostName + ".banners." + s + ".World");
			int bannerX = outpostsConfig.getInt("Outposts." + outpostName + ".banners." + s + ".X");
			int bannerY = outpostsConfig.getInt("Outposts." + outpostName + ".banners." + s + ".Y");
			int bannerZ = outpostsConfig.getInt("Outposts." + outpostName + ".banners." + s + ".Z");
				
			Location bannerLoc = new Location(Bukkit.getWorld(bannerWorldName), bannerX, bannerY, bannerZ);
			Block b = Bukkit.getWorld(bannerWorldName).getBlockAt(bannerLoc);
			if(color == 1) {
				b.setType(Material.WHITE_BANNER);
			}else if (color == 2) {
				b.setType(Material.RED_BANNER);
			}else if (color == 3) {
				b.setType(Material.BLUE_BANNER);
			}else {
				return false;
			}
		}
		return true;
	}	
	
	
	@EventHandler
	public void onRegionEntered(RegionEnteredEvent e) {
		
		if(e.getRegionName().contains("utpost")) {
			
			e.getPlayer().sendMessage(chatPrefix + ChatColor.RED + "You have entered an Outpost!");
			
		}
		
	}
	
	@EventHandler
	public void onRegionLeft(RegionLeftEvent e) {
		
		if(e.getRegionName().contains("utpost")) {
			
			e.getPlayer().sendMessage(chatPrefix + ChatColor.RED + "You have left an Outpost!");
			
		}
		
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		if(e.getEntity().getWorld().getName().equalsIgnoreCase("battle")) {
			File deathFile = new File(plugin.getDataFolder() + "/Players/" + e.getEntity().getUniqueId() + ".yml");
			FileConfiguration deathConfig = YamlConfiguration.loadConfiguration(deathFile);
			int deathCount = deathConfig.getInt("battle.deathcount") + 1;
			deathConfig.set("battle.deathcount", deathCount);
			File killerFile = new File(plugin.getDataFolder() + "/Players/" + e.getEntity().getKiller().getUniqueId());
			FileConfiguration killerConfig = YamlConfiguration.loadConfiguration(killerFile);
			int killCount = killerConfig.getInt("battle.killcount") + 1;
			killerConfig.set("battle.killcount", killCount);
			saveFile(deathConfig, deathFile);
			saveFile(killerConfig, killerFile);
		}
	}	
}