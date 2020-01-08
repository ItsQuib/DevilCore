package net.devilcraft.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin implements Listener {
		
	Inventories inventoryClass = new Inventories(this, econ);
	Events eventsClass = new Events(this, chat, econ);
	ItemStorage itemStorageClass = new ItemStorage(this, econ);
	Battle battleClass = new Battle();
	Levels levelsClass = new Levels(this, chat);
	
	public String chatPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Devil" + ChatColor.WHITE + "Craft" + ChatColor.GRAY + "] ";
	
	public static ArrayList<UUID> adminList = new ArrayList<UUID>();
	public File playerFile = new File(this.getDataFolder() + "/players.yml");
	public FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);

	
/*	RegionContainer rCont = WorldGuard.getInstance().getPlatform().getRegionContainer();
	
	RegionManager regions = rCont.get(BukkitAdapter.adapt(Bukkit.getWorld("Battle")));
	
	ProtectedRegion reg = regions.getRegion("Outpost 1");
	
	ApplicableRegionSet set = regions.getApplicableRegions()*/
	
	
    private static Economy econ = null;
	public static Chat chat = null;;
	
    private boolean setupEcon() {
        RegisteredServiceProvider<Economy> econProvider = getServer().getServicesManager().getRegistration(Economy.class);
        if (econProvider != null) {
        	econ = econProvider.getProvider();            
        }
        
        return econ != null;
    }
	
	private boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
        
        return (chat != null);
    }
	
	@Override
	public void onEnable() {
		
		getLogger().info("RPG Core has been loaded!");
		
		if(setupEcon()) {
			getLogger().info("[RPGCore] Econ-setup successful");
		}else {
			getLogger().info("[RPGCore] Econ-setup failed");
		}
		if(setupChat()) {
			getLogger().info("[RPGCore] Chat-setup successful");
		}else {
			getLogger().info("[RPGCore] Chat-setup failed");
		}
		
		battleClass.initEvents(this);
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new Events(this, chat, econ), this);
		getServer().getPluginManager().registerEvents(new Battle(), this);
		getServer().getPluginManager().registerEvents(new Levels(this, chat), this);
		
		File outpostsFile = new File(this.getDataFolder() + "/Outposts.yml");
		if(!(outpostsFile.exists())) {
			FileConfiguration outpostsConfig = YamlConfiguration.loadConfiguration(outpostsFile);
			
			saveFile(outpostsConfig, outpostsFile);
			
		}
	}
	
	
	public void saveFile(FileConfiguration yamlConfig, File file) {
		try{
			yamlConfig.save(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
//		inventoryClass.OpenWeaponsGui(e.getPlayer(), false);
		File pFile = new File(this.getDataFolder() + "/Players/" + e.getPlayer().getUniqueId() + ".yml");
		if(!(pFile.exists())) {
			
			FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
			
			pConfig.set("player.name", e.getPlayer().getName());
			
			pConfig.set("battle.killcount", 0);
			pConfig.set("battle.deathcount", 0);
			pConfig.set("battle.team", null);
			
			pConfig.set("level.exp", 0);
			pConfig.set("level.lvl", 1);
			pConfig.set("level.prestige", 0);
			
			saveFile(pConfig, pFile);
			
			chat.setPlayerPrefix(null, e.getPlayer(), levelsClass.getPrefix(1));				
			
		} else {
			FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);

			chat.setPlayerPrefix(null, e.getPlayer(), levelsClass.getPrefix(pConfig.getInt("level.lvl")));
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(sender instanceof Player) {
			//TestWhenOnline
			if(cmd.getName().equalsIgnoreCase("shop")) {
				
				inventoryClass.OpenShopGui(p);
				
			}
			if(cmd.getName().equalsIgnoreCase("coreadmin")) {
				if(adminList.contains(p.getUniqueId())) {
					adminList.remove(p.getUniqueId());
					p.sendMessage(chatPrefix + ChatColor.RED + "You have left admin mode!!!");
//					ecp.updateArray(adminList);
					return true;
				}else {
					adminList.add(p.getUniqueId());
					p.sendMessage(chatPrefix + ChatColor.RED + "You have entered admin mode!!!");
//					ecp.updateArray(adminList);
					return true;
				}
			}else if(cmd.getName().equalsIgnoreCase("level")) {
				if(!(p.isOp())) {
					p.sendMessage(chatPrefix + ChatColor.RED + "You have to be an owner to do this command!!!");
					return true;
				}
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("lvlup")) {
						try {
							if(Integer.parseInt(args[1]) == (int)Integer.parseInt(args[1])) {
								levelsClass.levelUp(p, Integer.parseInt(args[1]));
								return true;
							}
						} catch (NumberFormatException e) {
							p.sendMessage(chatPrefix + ChatColor.RED + "You must enter a number! Usage:");
							p.sendMessage(chatPrefix + ChatColor.RED + "/level lvlup [number]");
						}
					}else if(args[0].equalsIgnoreCase("addexp")) {
						try {
							if(Integer.parseInt(args[1]) == (int)Integer.parseInt(args[1])) {
//								p.sendMessage(chatPrefix + ChatColor.RED + "Your current EXP: " + ChatColor.GOLD + currentExp);
//								p.sendMessage(chatPrefix + ChatColor.RED + "EXP To Add: " + ChatColor.GOLD + args[1]);
								levelsClass.addExp(p, Integer.parseInt(args[1]));
//								p.sendMessage(chatPrefix + ChatColor.RED + "New current EXP: " + ChatColor.GOLD + currentExp);

								return true;
							}
						} catch (NumberFormatException e) {
							p.sendMessage(chatPrefix + ChatColor.RED + "You must enter a number! Usage:");
							p.sendMessage(chatPrefix + ChatColor.RED + "/level addexp [number]");
						}
					}
				}else {
					if(args.length == 1) {
						if(args[0].equalsIgnoreCase("checklevel")) {
							
							
							
						}
					} else {
						
						p.sendMessage("");
						
					}
				}
			}else if(cmd.getName().equalsIgnoreCase("outpost")) {
				if(args.length == 3) {
					if(args[0].equalsIgnoreCase("banner")) {
						File outpostsFile = new File(this.getDataFolder() + "/Outposts.yml");
						FileConfiguration outpostsConfig = YamlConfiguration.loadConfiguration(outpostsFile);
						
						if(outpostsConfig.get("Outposts." + args[1] + ".banners." + args[2]) == null) {
							p.sendMessage("Debug 5");
							Location loc = p.getLocation();
							
							outpostsConfig.set("Outposts." + args[1] + ".banners." + args[2] + ".World", loc.getWorld().getName());
							outpostsConfig.set("Outposts." + args[1] + ".banners." + args[2] + ".X", loc.getBlockX());
							outpostsConfig.set("Outposts." + args[1] + ".banners." + args[2] + ".Y", loc.getBlockY());
							outpostsConfig.set("Outposts." + args[1] + ".banners." + args[2] + ".Z", loc.getBlockZ());
							saveFile(outpostsConfig, outpostsFile);
							p.sendMessage("Banner saved!");
							return true;
							
						} else {
							
							p.sendMessage(chatPrefix + ChatColor.RED + "This banner already exists! Please pick a new name");
							
						}
					}
				}else {
					
					if(args[0].equalsIgnoreCase("set")) {
						
						if(args[1] != null) {
							
							File outpostsFile = new File(this.getDataFolder() + "/Outposts.yml");
							FileConfiguration outpostsConfig = YamlConfiguration.loadConfiguration(outpostsFile);
							
							if(outpostsConfig.get("Outposts." + args[1]) != null) {
								
								p.sendMessage(chatPrefix + ChatColor.RED + "This outpost already exists!");
								
							} else {
								
								RegionContainer rCont = WorldGuard.getInstance().getPlatform().getRegionContainer();
								RegionManager regions = rCont.get(BukkitAdapter.adapt(Bukkit.getWorld("Battle")));
								
								if(regions.getRegion(args[1]) != null) {
									outpostsConfig.set("Outposts." + args[1] + ".occupation", "Neutral");
									saveFile(outpostsConfig, outpostsFile);
									p.sendMessage(chatPrefix + ChatColor.RED + "Outpost Set!");
								}
							}
							
						}else {
							
							p.sendMessage(chatPrefix + ChatColor.RED + "Please specify an outpost name! /outpost set [OutpostName]");
							
						}
						
					}
					
				}
			}
		}
		
		return true;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		
		
	}
	
}
