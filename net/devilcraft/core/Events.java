package net.devilcraft.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

public class Events implements Listener {
	
	Main plugin;
	private static Chat chat = null;
    private static Economy eco = null;

	Inventories icp = new Inventories(plugin, eco);
    ItemStorage is = new ItemStorage(plugin, eco);
	
	
	public void saveFile(FileConfiguration yamlConfig, File file) {
		try{
			yamlConfig.save(file);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	
	ArrayList<UUID> adminList = Main.adminList;
	
	public String chatPrefix = ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Devil" + ChatColor.WHITE + "Craft" + ChatColor.GRAY + "] ";
	
	public Events(Main p, Chat c, Economy e) {
		
		this.plugin = p;
		Events.chat = c;
		Events.eco = e;
		
	}
	
	Levels levelsClass = new Levels(plugin, chat);
	
	
	public void onEntityDeath(EntityDeathEvent e) {
		ArrayList<EntityType> passiveMobsArray = new ArrayList<EntityType>();
		passiveMobsArray.add(EntityType.BAT);passiveMobsArray.add(EntityType.CAT);passiveMobsArray.add(EntityType.CHICKEN);
		passiveMobsArray.add(EntityType.COW);passiveMobsArray.add(EntityType.DONKEY);passiveMobsArray.add(EntityType.FOX);
		passiveMobsArray.add(EntityType.HORSE);passiveMobsArray.add(EntityType.LLAMA);passiveMobsArray.add(EntityType.MULE);
		passiveMobsArray.add(EntityType.MUSHROOM_COW);passiveMobsArray.add(EntityType.OCELOT);passiveMobsArray.add(EntityType.PANDA);
		passiveMobsArray.add(EntityType.PIG);passiveMobsArray.add(EntityType.POLAR_BEAR);passiveMobsArray.add(EntityType.RABBIT);
		passiveMobsArray.add(EntityType.SHEEP);passiveMobsArray.add(EntityType.SQUID);passiveMobsArray.add(EntityType.WOLF);
		
		ArrayList<EntityType> aggressiveMobsArray = new ArrayList<EntityType>();
		aggressiveMobsArray.add(EntityType.BLAZE);aggressiveMobsArray.add(EntityType.CAVE_SPIDER);aggressiveMobsArray.add(EntityType.CREEPER);
		aggressiveMobsArray.add(EntityType.ELDER_GUARDIAN);aggressiveMobsArray.add(EntityType.ENDERMAN);aggressiveMobsArray.add(EntityType.GHAST);
		aggressiveMobsArray.add(EntityType.IRON_GOLEM);aggressiveMobsArray.add(EntityType.MAGMA_CUBE);aggressiveMobsArray.add(EntityType.PIG_ZOMBIE);
		aggressiveMobsArray.add(EntityType.SKELETON);aggressiveMobsArray.add(EntityType.SKELETON_HORSE);aggressiveMobsArray.add(EntityType.SPIDER);
		aggressiveMobsArray.add(EntityType.WITCH);aggressiveMobsArray.add(EntityType.WITHER_SKELETON);aggressiveMobsArray.add(EntityType.ZOMBIE);
		aggressiveMobsArray.add(EntityType.ZOMBIE_HORSE);aggressiveMobsArray.add(EntityType.ZOMBIE_VILLAGER);
		
		if(e.getEntity().getKiller() instanceof Player && (passiveMobsArray.contains(e.getEntityType()) || aggressiveMobsArray.contains(e.getEntityType()))) {
			
			Player p = e.getEntity().getKiller();
			
			File pFile = new File(plugin.getDataFolder() + "/Players/" + p.getUniqueId() + ".yml");
			FileConfiguration pConfig = YamlConfiguration.loadConfiguration(pFile);
			
			File cFile = new File(plugin.getDataFolder() + "/Config.yml");
			FileConfiguration cConfig = YamlConfiguration.loadConfiguration(cFile);
			
			if(passiveMobsArray.contains(e.getEntityType())) {
				
				int expToGive = cConfig.getInt("Leveling.xp.passivemobs");
				int currentPlayerExp = pConfig.getInt("level.exp");
				if(currentPlayerExp + expToGive > 100) {
					int leftoverExp = (currentPlayerExp + expToGive) - 100;
					levelsClass.levelUp(p, 1);
					pConfig.set("level.exp", leftoverExp);
					saveFile(pConfig, pFile);
				} else {
					int totalExp = currentPlayerExp + expToGive;
					pConfig.set("level.exp", totalExp);
					saveFile(pConfig, pFile);
				}
			}else if(aggressiveMobsArray.contains(e.getEntityType())) {
				
				int expToGive = cConfig.getInt("Leveling.xp.aggressivemobs");
				int currentPlayerExp = pConfig.getInt("level.exp");
				if(currentPlayerExp + expToGive > 100) {
					int leftoverExp = (currentPlayerExp + expToGive) - 100;
					levelsClass.levelUp(p, 1);
					pConfig.set("level.exp", leftoverExp);
					saveFile(pConfig, pFile);
				} else {
					int totalExp = currentPlayerExp + expToGive;
					pConfig.set("level.exp", totalExp);
					saveFile(pConfig, pFile);
				}
			}
		}
		
		
//		if(e.getEntityType() == )
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
//		Inventory pinv = p.getInventory();
		
		ItemStack coal = new ItemStack(Material.COAL);
		ItemMeta coalMeta = coal.getItemMeta();
		coalMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.DARK_GRAY + "Coal Piece");
		coal.setItemMeta(coalMeta);

		ItemStack iron = new ItemStack(Material.IRON_INGOT);
		ItemMeta ironMeta = coal.getItemMeta();
		ironMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.GRAY + "Iron Piece");
		iron.setItemMeta(ironMeta);

		ItemStack gold = new ItemStack(Material.GOLD_INGOT);
		ItemMeta goldMeta = gold.getItemMeta();
		goldMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.YELLOW + "Gold Piece");
		gold.setItemMeta(goldMeta);
		
		ItemStack dia = new ItemStack(Material.DIAMOND);
		ItemMeta diaMeta = dia.getItemMeta();
		diaMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.AQUA + "Diamond Piece");
		dia.setItemMeta(diaMeta);
		
		ItemStack emerald = new ItemStack(Material.EMERALD);
		ItemMeta emeraldMeta = emerald.getItemMeta();
		emeraldMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.GREEN + "Emerald Piece");
		emerald.setItemMeta(emeraldMeta);
		
		//BLACKSMITH
		if(e.getView().getTitle().contains("Smeltery Menu")) {
			if(!(e.getView().getTitle().contains("Smeltery Menu"))) {
				e.setCancelled(true);
			}
			String iname = e.getCurrentItem().getItemMeta().getDisplayName();			
			if(iname.contains("Pickaxes")) {
				
				icp.OpenPickaxeGui(p, true);
				e.setCancelled(true);
				
			}else if(iname.contains("Treasure")) {
				
				icp.OpenTreasureGui(p, true);
				e.setCancelled(true);
				
			} else {
				e.setCancelled(true);
			}
		//BARTENDER
		}else if(e.getView().getTitle().contains("Bartender Menu")) {
			if(!(eco.hasAccount(p))) {
				eco.createPlayerAccount(p);
				p.sendMessage(chatPrefix + ChatColor.RED + "An error has occured, please try again!");
				return;
			}
			String iname = e.getCurrentItem().getItemMeta().getDisplayName();
			if(iname.contains("Potions and Drinks")) {
				
				icp.OpenPotionGui(p);
				e.setCancelled(true);
				
			}else if (iname.contains("Food")) {
				
				icp.OpenFoodGui(p, true);
				e.setCancelled(true);
				
			}
		//WEAPON
		}else if(e.getView().getTitle().contains("Weapon Shop")) {
			if(!(eco.hasAccount(p))) {
				eco.createPlayerAccount(p);
				p.sendMessage(chatPrefix + ChatColor.RED + "An error has occured, please try again!");
				return;
			}
			if(!(e.getView().getTitle().contains("Weapon Shop"))) {
				e.setCancelled(true);
				return;
			}
			String iname = e.getCurrentItem().getItemMeta().getDisplayName();
			int iench = e.getCurrentItem().getEnchantmentLevel(Enchantment.DAMAGE_ALL);
						
			if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
				icp.OpenShopGui(p);
				e.setCancelled(true);
			}
			if(iname.contains("Wooden Sword")) {
				if(iench == 1) {
					if(eco.getBalance(p) >= 55) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(2));
						eco.withdrawPlayer(p, 55);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken wooden sword for 55 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 2) {
					if(eco.getBalance(p) >= 65) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(3));
						eco.withdrawPlayer(p, 65);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken wooden sword for 65 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 4) {
					if(eco.getBalance(p) >= 90) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(4));
						eco.withdrawPlayer(p, 90);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken wooden sword for 90 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else {
					if(eco.getBalance(p) >= 40) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(1));
						eco.withdrawPlayer(p, 40);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken wooden sword for 40 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}
			}else if (iname.contains("Stone Sword")) {
				if(iench == 1) {
					if(eco.getBalance(p) >= 140) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(6));
						eco.withdrawPlayer(p, 140);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken stone sword for 140 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 2) {
					if(eco.getBalance(p) >= 165) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(7));
						eco.withdrawPlayer(p, 165);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken stone sword for 165 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 4) {
					if(eco.getBalance(p) >= 220) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(8));
						eco.withdrawPlayer(p, 220);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken stone sword for 220 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else {
					if(eco.getBalance(p) >= 110) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(5));
						eco.withdrawPlayer(p, 110);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken stone sword for 110 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}
			}else if (iname.contains("Iron Sword")) {
				if(iench == 1) {
					if(eco.getBalance(p) >= 300) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(10));
						eco.withdrawPlayer(p, 300);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken iron sword for 300 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 2) {
					if(eco.getBalance(p) >= 350) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(11));
						eco.withdrawPlayer(p, 350);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken iron sword for 350 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 4) {
					if(eco.getBalance(p) >= 460) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(12));
						eco.withdrawPlayer(p, 460);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken iron sword for 460 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else {
					if(eco.getBalance(p) >= 250) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(9));
						eco.withdrawPlayer(p, 250);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken iron sword for 250 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}
			}else if (iname.contains("Diamond Sword")) {
				if(iench == 1) {
					if(eco.getBalance(p) >= 1250) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(14));
						eco.withdrawPlayer(p, 1250);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken diamond sword for 1,250 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 2) {
					if(eco.getBalance(p) >= 1450) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(15));
						eco.withdrawPlayer(p, 1450);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken diamond sword for 1,450 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 4) {
					if(eco.getBalance(p) >= 1850) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(16));
						eco.withdrawPlayer(p, 1850);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken diamond sword for 1,850 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else {
					if(eco.getBalance(p) >= 1050) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(13));
						eco.withdrawPlayer(p, 1050);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken wooden sword for 1,050 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}
			} else {
				e.setCancelled(true);
			}
		//Pickaxes
		}else if(e.getView().getTitle().contains("Armour Shop")) {
			if(!(eco.hasAccount(p))) {
				eco.createPlayerAccount(p);
				p.sendMessage(chatPrefix + ChatColor.RED + "An error has occured, please try again!");
				return;
			}
			
			String iname = e.getCurrentItem().getItemMeta().getDisplayName();

			if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
				icp.OpenShopGui(p);
				e.setCancelled(true);
			}
			
			if(iname.contains("Leather")) {
				
				
				
			}else if (iname.contains("Chainmail")) {
				
				
				
			}else if (iname.contains("Iron")) {
				
				
				
			}else if (iname.contains("Diamond")) {
				
				
				
			}
			
			
			
		}else if (e.getView().getTitle().contains("Tool Shop")) {
			if(!(eco.hasAccount(p))) {
				eco.createPlayerAccount(p);
				p.sendMessage(chatPrefix + ChatColor.RED + "An error has occured, please try again!");
				return;
			}
			if(!(e.getView().getTitle().contains("Tool Shop"))) {
				e.setCancelled(true);
				return;
			}
			String iname = e.getCurrentItem().getItemMeta().getDisplayName();
			
			int iench = e.getCurrentItem().getEnchantmentLevel(Enchantment.DIG_SPEED);

			if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
				icp.OpenShopGui(p);
				e.setCancelled(true);
			}
			if(iname.contains("Wooden Pick")) {
				if(iench == 1) {
					if(eco.getBalance(p) >= 30) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(18));
						eco.withdrawPlayer(p, 30);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken wooden pick for 30 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 2) {
					if(eco.getBalance(p) >= 40) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(19));
						eco.withdrawPlayer(p, 40);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken wooden pick for 40 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 4) {
					if(eco.getBalance(p) >= 60) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(20));
						eco.withdrawPlayer(p, 60);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken wooden pick for 60 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else {
					if(eco.getBalance(p) >= 20) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(17));
						eco.withdrawPlayer(p, 20);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken wooden pick for 20 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}
			}else if (iname.contains("Stone Pick")) {
				if(iench == 1) {
					if(eco.getBalance(p) >= 135) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(22));
						eco.withdrawPlayer(p, 135);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken stone pick for 135 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 2) {
					if(eco.getBalance(p) >= 180) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(23));
						eco.withdrawPlayer(p, 180);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken stone pick for 180 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 4) {
					if(eco.getBalance(p) >= 270) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(24));
						eco.withdrawPlayer(p, 270);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken stone pick for 270 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else {
					if(eco.getBalance(p) >= 90) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(21));
						eco.withdrawPlayer(p, 90);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken stone pick for 90 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}
			}else if (iname.contains("Iron Pick")) {
				if(iench == 1) {
					if(eco.getBalance(p) >= 350) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(26));
						eco.withdrawPlayer(p, 350);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken iron pick for 350 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 2) {
					if(eco.getBalance(p) >= 500) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(27));
						eco.withdrawPlayer(p, 500);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken iron pick for 500 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else if(iench == 4) {
					if(eco.getBalance(p) >= 750) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(28));
						eco.withdrawPlayer(p, 750);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken iron pick for 750 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}else {
					if(eco.getBalance(p) >= 250) {
						e.setCancelled(true);
						p.getInventory().addItem(is.getItem(25));
						eco.withdrawPlayer(p, 250);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "You just bought 1 broken iron pick for 250 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You cannot afford this item! Your balance:");
						p.sendMessage(ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"));
					}
				}
			} else {
				e.setCancelled(true);
			}
		//FOOD	
		}else if (e.getView().getTitle().contains("Food Shop")) {
			if(!(eco.hasAccount(p))) {
				eco.createPlayerAccount(p);
				p.sendMessage(chatPrefix + ChatColor.RED + "An error has occured, please try again!");
				return;
			}

			String iname = e.getCurrentItem().getItemMeta().getDisplayName();

			if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
				icp.OpenShopGui(p);
				e.setCancelled(true);
			}
			if(iname.contains("Steak")) {
				if(iname.contains("1")) {
					//4, 32, 64, 128					
					if(p.getInventory().containsAtLeast(coal, 4)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 4));
						p.getInventory().addItem(is.getItem(1001));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("16")) {
					if(p.getInventory().containsAtLeast(coal, 32)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 32));
						p.getInventory().addItem(is.getItem(1002));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("32")) {
					if(p.getInventory().containsAtLeast(coal, 64)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 64));
						p.getInventory().addItem(is.getItem(1003));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("64")) {
					if(p.getInventory().containsAtLeast(coal, 128)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 128));
						p.getInventory().addItem(is.getItem(1004));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}
			}else if (iname.contains("Potato")) {
				if(iname.contains("1")) {
					//1, 8, 16, 32					
					if(p.getInventory().containsAtLeast(coal, 1)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 1));
						p.getInventory().addItem(is.getItem(1005));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("16")) {
					if(p.getInventory().containsAtLeast(coal, 8)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 8));
						p.getInventory().addItem(is.getItem(1006));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("32")) {
					if(p.getInventory().containsAtLeast(coal, 16)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 16));
						p.getInventory().addItem(is.getItem(1007));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("64")) {
					if(p.getInventory().containsAtLeast(coal, 32)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 32));
						p.getInventory().addItem(is.getItem(1008));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}
			}else if (iname.contains("Crapple")) {
				if(iname.contains("1")) {
					//1, 8, 16, 32					
					if(p.getInventory().containsAtLeast(gold, 1)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(gold, 1));
						p.getInventory().addItem(is.getItem(1009));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("16")) {
					if(p.getInventory().containsAtLeast(gold, 8)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(gold, 8));
						p.getInventory().addItem(is.getItem(1010));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("32")) {
					if(p.getInventory().containsAtLeast(gold, 16)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(gold, 16));
						p.getInventory().addItem(is.getItem(1011));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("64")) {
					if(p.getInventory().containsAtLeast(gold, 32)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(gold, 32));
						p.getInventory().addItem(is.getItem(1012));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}
			}else if (iname.contains("Golden Apple")) {
				if(iname.contains("1")) {
					//10, 80, 160, 320					
					if(p.getInventory().containsAtLeast(emerald, 10)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(emerald, 1));
						p.getInventory().addItem(is.getItem(1013));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("16")) {
					if(p.getInventory().containsAtLeast(emerald, 8)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(emerald, 8));
						p.getInventory().addItem(is.getItem(1014));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("32")) {
					if(p.getInventory().containsAtLeast(emerald, 16)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(emerald, 16));
						p.getInventory().addItem(is.getItem(1015));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}else if (iname.contains("64")) {
					if(p.getInventory().containsAtLeast(emerald, 32)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(emerald, 32));
						p.getInventory().addItem(is.getItem(1016));
					}else {
						e.setCancelled(true);
						p.sendMessage(ChatColor.RED + "You cannot afford this item!");
					}
				}
			}
		//ARMOUR
		}else if (e.getView().getTitle().contains("Armour Shop")) {
			if(!(eco.hasAccount(p))) {
				eco.createPlayerAccount(p);
				p.sendMessage(chatPrefix + ChatColor.RED + "An error has occured, please try again!");
				return;
			}

			if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
				icp.OpenShopGui(p);
				e.setCancelled(true);
			}
		//MENU
		}else if(e.getView().getTitle().contains("Shop Menu")) {
			

			String iname = e.getCurrentItem().getItemMeta().getDisplayName();
			
			if(iname.contains("Weapons")) {
				
				icp.OpenWeaponsGui(p, true);
				e.setCancelled(true);
				
			}else if (iname.contains("Pickaxes")) {
				
				icp.OpenPickaxeGui(p, true);
				e.setCancelled(true);
				
			}else if (iname.contains("Armour")) {
				
				p.sendMessage("Debug");
				icp.OpenArmourGui(p, true);
				e.setCancelled(true);
				
			}else if (iname.contains("Potions")) {
				
//						icp.OpenPotionGui(p);
				p.sendMessage("Potion Shop Under Construction!");
				e.setCancelled(true);
				
			}else if (iname.contains("Food")) {
				
				icp.OpenFoodGui(p, true);
				e.setCancelled(true);
				
			}else if (iname.contains("Treasure")) {
				
				icp.OpenTreasureGui(p, true);
				e.setCancelled(true);
			}
		//TREASURE
		}else if(e.getView().getTitle().contains("Treasure")) {
			if(!(eco.hasAccount(p))) {
				eco.createPlayerAccount(p);
				p.sendMessage(chatPrefix + ChatColor.RED + "An error has occured, please try again!");
				return;
			}
			
			if(!(e.getView().getTitle().contains("Treasure"))) {
				e.setCancelled(true);
				return;
			}
			
			if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
				icp.OpenShopGui(p);
				e.setCancelled(true);
			}
			
			String iname = e.getCurrentItem().getItemMeta().getDisplayName();
			
			if(iname.contains("Coal")) {
				if(iname.contains("- 1 ")) {
					if(p.getInventory().containsAtLeast(coal, 1)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 1));
						eco.depositPlayer(p, 2);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 1 coal for 2 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 1 coal in your inventory to do this!");
					}
				}else if (iname.contains("- 16 ")) {
					if(p.getInventory().containsAtLeast(coal, 16)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 16));
						eco.depositPlayer(p, 32);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 16 coal for 32 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 16 coal in your inventory to do this!");
					}
				}else if (iname.contains("- 32 ")) {
					if(p.getInventory().containsAtLeast(coal, 32)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 32));
						eco.depositPlayer(p, 64);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 32 coal for 64 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 32 coal in your inventory to do this!");
					}
				}else if (iname.contains("- 64 ")) {
					if(p.getInventory().containsAtLeast(coal, 64)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(coal, 64));
						eco.depositPlayer(p, 128);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 64 coal for 128 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 64 coal in your inventory to do this!");
					}
				}else {
					p.sendMessage(iname);
					p.sendMessage("Test");
				}
			}else if (iname.contains("Iron")) {
				if(iname.contains("- 1 ")) {
					if(p.getInventory().containsAtLeast(iron, 1)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(iron, 1));
						eco.depositPlayer(p, 6);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 1 iron bar for 6 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 1 iron bar in your inventory to do this!");
					}
				}else if (iname.contains("- 16 ")) {
					if(p.getInventory().containsAtLeast(iron, 16)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(iron, 16));
						eco.depositPlayer(p, 96);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 16 iron bars for 96 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 16 iron in your inventory to do this!");
					}
				}else if (iname.contains("- 32 ")) {
					if(p.getInventory().containsAtLeast(iron, 32)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(iron, 32));
						eco.depositPlayer(p, 192);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 32 iron bars for 192 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 32 iron in your inventory to do this!");
					}
				}else if (iname.contains("- 64 ")) {
					if(p.getInventory().containsAtLeast(iron, 64)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(iron, 64));
						eco.depositPlayer(p, 384);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 64 iron bars for 384 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 64 iron in your inventory to do this!");
					}
				}
			}else if (iname.contains("Gold")) {
				if(iname.contains("- 1 ")) {
					if(p.getInventory().containsAtLeast(gold, 1)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(gold, 1));
						eco.depositPlayer(p, 18);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 1 gold bar for 18 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 1 gold bar in your inventory to do this!");
					}
				}else if (iname.contains("- 16 ")) {
					if(p.getInventory().containsAtLeast(gold, 16)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(gold, 16));
						eco.depositPlayer(p, 288);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 16 gold bars for 288 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 16 gold bars in your inventory to do this!");
					}
				}else if (iname.contains("- 32 ")) {
					if(p.getInventory().containsAtLeast(gold, 32)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(gold, 32));
						eco.depositPlayer(p, 576);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 32 gold bars for 576 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 32 gold bars in your inventory to do this!");
					}
				}else if (iname.contains("- 64 ")) {
					if(p.getInventory().containsAtLeast(gold, 64)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(gold, 64));
						eco.depositPlayer(p, 1152);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 64 gold bars for 1,152 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 64 gold bars in your inventory to do this!");
					}
				} else {
					e.setCancelled(true);
				}
			}else if (iname.contains("Diamond")) {
				if(iname.contains("- 1 ")) {
					if(p.getInventory().containsAtLeast(dia, 1)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(dia, 1));
						eco.depositPlayer(p, 54);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 1 diamond for 54 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 1 diamond in your inventory to do this!");
					}
				}else if (iname.contains("- 16 ")) {
					if(p.getInventory().containsAtLeast(dia, 16)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(dia, 16));
						eco.depositPlayer(p, 864);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 16 diamonds for 864 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 16 diamonds in your inventory to do this!");
					}
				}else if (iname.contains("- 32 ")) {
					if(p.getInventory().containsAtLeast(dia, 32)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(dia, 32));
						eco.depositPlayer(p, 1728);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 32 diamonds for 1,728 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 32 diamonds in your inventory to do this!");
					}
				}else if (iname.contains("- 64 ")) {
					if(p.getInventory().containsAtLeast(dia, 64)) {
						e.setCancelled(true);
						p.getInventory().removeItem(icp.getItemStack(dia, 64));
						eco.depositPlayer(p, 3456);
						p.sendMessage(chatPrefix + ChatColor.GRAY + "Sold 64 diamonds for 3,456 gold!");
					}else {
						e.setCancelled(true);
						p.sendMessage(chatPrefix + ChatColor.RED + "You need atleast 64 diamonds in your inventory to do this!");
					}
				}
			}else {
				e.setCancelled(true);
			}
		}
	}

	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
		Player p = (Player) e.getPlayer();
		Block b = (Block) e.getBlock();
		
		ArrayList<Material> allowedBlockList = new ArrayList<Material>();
		allowedBlockList.add(Material.COAL_ORE);
		allowedBlockList.add(Material.IRON_ORE);
		allowedBlockList.add(Material.GOLD_ORE);
		allowedBlockList.add(Material.DIAMOND_ORE);
		allowedBlockList.add(Material.EMERALD_ORE);

		ArrayList<Material> allowedToolList = new ArrayList<Material>();
		allowedToolList.add(Material.WOODEN_PICKAXE);
		allowedToolList.add(Material.STONE_PICKAXE);
		allowedToolList.add(Material.IRON_PICKAXE);
		allowedToolList.add(Material.DIAMOND_PICKAXE);
		allowedToolList.add(Material.GOLDEN_PICKAXE);

				
		ItemStack coal = new ItemStack(Material.COAL);
		ItemMeta coalMeta = coal.getItemMeta();
		coalMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.DARK_GRAY + "Coal Piece");
		coal.setItemMeta(coalMeta);

		ItemStack iron = new ItemStack(Material.IRON_INGOT);
		ItemMeta ironMeta = coal.getItemMeta();
		ironMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.GRAY + "Iron Piece");
		iron.setItemMeta(ironMeta);

		ItemStack gold = new ItemStack(Material.GOLD_INGOT);
		ItemMeta goldMeta = gold.getItemMeta();
		goldMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.YELLOW + "Gold Piece");
		gold.setItemMeta(goldMeta);
		
		ItemStack dia = new ItemStack(Material.DIAMOND);
		ItemMeta diaMeta = dia.getItemMeta();
		diaMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.AQUA + "Diamond Piece");
		dia.setItemMeta(diaMeta);
		
		ItemStack emerald = new ItemStack(Material.EMERALD);
		ItemMeta emeraldMeta = emerald.getItemMeta();
		emeraldMeta.setDisplayName(ChatColor.BOLD.toString() + ChatColor.GREEN + "Emerald Piece");
		emerald.setItemMeta(emeraldMeta);
		
		if(!(allowedBlockList.contains(b.getType()))) {
			return;
		}else if (!(allowedToolList.contains(p.getInventory().getItemInMainHand().getType()))) {
			
			p.sendMessage(chatPrefix + "You must break an ore with a pickaxe!!");
			e.setCancelled(true);
			return;
			
		}
		if(b.getType() == Material.COAL_ORE) {
   			
			e.setCancelled(true);
			b.setType(Material.AIR);
			p.getInventory().addItem(coal);
			if(e.getBlock().getWorld().getName().equals("Map1") && !(p.getGameMode().equals(GameMode.CREATIVE))) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
					
						b.setType(Material.COAL_ORE);
					
					}
				}, 500L);
			}
		}else if (b.getType() == Material.IRON_ORE) {
			
			e.setCancelled(true);
			b.setType(Material.AIR);
			p.getInventory().addItem(iron);
			if(e.getBlock().getWorld().getName().equals("Map1") && !(p.getGameMode().equals(GameMode.CREATIVE))) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
					
						b.setType(Material.IRON_ORE);
					
					}
				}, 750L);
			}
		}else if (b.getType() == Material.GOLD_ORE) {
			
			e.setCancelled(true);
			b.setType(Material.AIR);
			p.getInventory().addItem(gold);
			if(e.getBlock().getWorld().getName().equals("Map1") && !(p.getGameMode().equals(GameMode.CREATIVE))) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
					
						b.setType(Material.GOLD_ORE);
					
					}
				}, 900L);
			}
		}else if (b.getType() == Material.DIAMOND_ORE) {
			
			e.setCancelled(true);
			b.setType(Material.AIR);
			p.getInventory().addItem(dia);
			if(e.getBlock().getWorld().getName().equals("Map1") && !(p.getGameMode().equals(GameMode.CREATIVE))) {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
					
						b.setType(Material.DIAMOND_ORE);
					
					}
				}, 1000L);
			}
		}else if (b.getType() == Material.EMERALD_ORE) {
			
			e.setCancelled(true);
			return;
/*			b.setType(Material.AIR);
			if(p.getInventory().getItemInMainHand().equals(Material.DIAMOND_PICKAXE)) {
				List<String> itemLore = p.getInventory().getItemInMainHand().getItemMeta().getLore();
				if(itemLore.contains("Luck")) {
					for(int i = 0; i > itemLore.size(); i++) {
						if(itemLore.get(i).contains("Luck")) {
							String luckNum = itemLore.get(i);
							if(luckNum.contains(" I ")) {
								Random randomGen = new Random();
								int randomNum = randomGen.nextInt(10);
								if(randomNum < 3) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 1 emerald");
								}
							}else if(luckNum.contains(" II ")) {
								Random randomGen = new Random();
								int randomNum = randomGen.nextInt(10);
								if(randomNum == 2) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 1 emerald");
								} else if(randomNum == 1) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 2 emeralds");
								}
							}else if (luckNum.contains(" III ")) {
								Random randomGen = new Random();
								int randomNum = randomGen.nextInt(10);
								if(randomNum == 3 || randomNum == 2) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 1 emerald");
								} else if (randomNum == 1) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 2 emeralds");
								}
							}else if (luckNum.contains(" IV ")) {
								Random randomGen = new Random();
								int randomNum = randomGen.nextInt(10);
								if(randomNum == 4 || randomNum == 3) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 1 emerald");
								} else if (randomNum < 3) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 2 emeralds");
								}
							}else if (luckNum.contains(" V ")) {
								Random randomGen = new Random();
								int randomNum = randomGen.nextInt(10);
								if(randomNum == 4 || randomNum == 3) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 1 emerald");
								} else if (randomNum == 2) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 2 emeralds");
								} else if (randomNum == 1) {
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.getInventory().addItem(emerald);
									p.sendMessage(ChatColor.GREEN + "You hit a rich vain of ore! + 3 emeralds");
								}
							}
						}
					}
				} else {
					p.getInventory().addItem(emerald);
				}
			} else {
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
					public void run() {
						
						b.setType(Material.EMERALD_ORE);
						
					}
				}, 1500L);
			}*/
		}
	}
}