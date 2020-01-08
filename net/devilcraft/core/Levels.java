package net.devilcraft.core;

import java.io.File;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import net.milkbowl.vault.chat.Chat;

public class Levels implements Listener {
	
	Main plugin;
	private static Chat chat = null;
	
	public String chatPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Devil" + ChatColor.WHITE + "Craft" + ChatColor.GRAY + "] ";
	public String debugPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Devil" + ChatColor.WHITE + "Craft" + ChatColor.DARK_RED + " Debugging" + ChatColor.GRAY + "] ";
	
	public Levels(Main p, Chat c) {
		
		this.plugin = p;
		Levels.chat = c;
		
	}
	
	public void saveFile(FileConfiguration yamlConfig, File file) {
		try{
			yamlConfig.save(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void onEnable() {
		
		
		
	}
	
	
	
	public String getPrefix(int level) {
		String prefix;
		String lvlString = Integer.toString(level);
		if(level > 0 && level < 20) {
			prefix = ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + level + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED;
			return prefix;
		}else if (level > 19 && level < 40) {
			prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_GREEN + lvlString.charAt(0) + ChatColor.GREEN + lvlString.charAt(1) + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED;
			return prefix;
		}else if (level > 39 && level < 60) {
			prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + lvlString.charAt(0) + ChatColor.YELLOW + lvlString.charAt(1) + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED;
			return prefix;
		}else if (level > 59 && level < 80) {
			prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_PURPLE + lvlString.charAt(0) + ChatColor.LIGHT_PURPLE + lvlString.charAt(1) + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED;
			return prefix;
		}else if (level > 79 && level < 100) {
			prefix = ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + lvlString.charAt(0) + ChatColor.RED + lvlString.charAt(1) + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED;
			return prefix;
		}else if (level == 100) {
			prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + ChatColor.MAGIC + ":" + ChatColor.GRAY + level + ChatColor.GOLD + ChatColor.MAGIC + ":" + ChatColor.DARK_GRAY + "]" + ChatColor.DARK_RED;
			return prefix;
		}else {
			return null;
		}
	}
	
	//TestWhenOnline
	public void addExp(Player p, int expToAdd) {
		File pFile = new File(plugin.getDataFolder() + "/Players/" + p.getUniqueId() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		int currentPlayerExp = pConfig.getInt("level.exp");
		int totalExp = currentPlayerExp + expToAdd;
		int leftoverExp;
		if(totalExp > 100) {
			while (true) {
				leftoverExp = totalExp - 100;
				levelUp(p, 1);
				p.sendMessage(debugPrefix + ChatColor.RED + "Leveled Up");
				if(leftoverExp > 100) {
					totalExp = leftoverExp;
					p.sendMessage(debugPrefix + ChatColor.RED + "Leftover Exp over 100, restarting loop");
				} else {
					p.sendMessage(debugPrefix + ChatColor.RED + "breaking from while loop");
					break;
				}
			}
			pConfig.set("level.exp", leftoverExp);
		}else if (totalExp == 100) {
			levelUp(p, 1);
			pConfig.set("level.exp", 0);
		}else {
			pConfig.set("level.exp", totalExp);
			p.sendMessage(debugPrefix + ChatColor.RED + "Added exp to config");
		}
		saveFile(pConfig, pFile);
	}
	
	
	public void levelUp(Player p, int numberToLvlUp) {
		
		File pFile = new File(plugin.getDataFolder() + "/Players/" + p.getUniqueId() + ".yml");
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
		
		int playerLevel = pConfig.getInt("level.lvl");
		int origPlayerLevel = playerLevel;
		if(playerLevel == 100) {
			p.sendMessage(chatPrefix + ChatColor.RED + "You are already level 100! Prestige to level up more!");
			return;
		}else if (playerLevel + numberToLvlUp >= 100) {
			playerLevel = 100;
			p.sendMessage(chatPrefix + ChatColor.RED + "You have hit level 100! Prestige using [][][] to do sum shit");
			pConfig.set("level.lvl", playerLevel);
			saveFile(pConfig, pFile);
			chat.setPlayerPrefix(null, p, getPrefix(playerLevel));
			return;
		} else {
			playerLevel = playerLevel + numberToLvlUp;
			p.sendMessage(chatPrefix + ChatColor.RED + "You leveled up from level " + getPrefix(origPlayerLevel) + ChatColor.RED + " to level " + getPrefix(playerLevel) + ChatColor.RED + "!!!");
			pConfig.set("level.lvl", playerLevel);
			saveFile(pConfig, pFile);
//			try {
			chat.setPlayerPrefix(null, p, getPrefix(playerLevel));
//			} catch (NullPointerException e) {
//				p.sendMessage(debugPrefix + ChatColor.RED + "Something has gone wrong! Please message an admin and give them these two numbers: 'L-001', '" + playerLevel + "'"); //L-001
//			}
			return;
		}
	}
	
	public void checkLevel() {
		
		
		
	}
	
	
	
}