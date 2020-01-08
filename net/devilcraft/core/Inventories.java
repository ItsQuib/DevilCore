package net.devilcraft.core;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.ChatColor;

public class Inventories {
	
	
	Main plugin;	
	@SuppressWarnings("unused")
	private static Economy eco = null;
	
	public Inventories(Main plugin, Economy econ) {
		
		this.plugin = plugin;
		Inventories.eco = econ;
		
	}
	
	
	public ItemStack getItemStack(ItemStack current, int ammount) {
		ItemStack item = new ItemStack(current.getType(), ammount);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(current.getItemMeta().getDisplayName());
		item.setItemMeta(itemMeta);
		return item;
	}
	
	
	public ItemStack MakeItem(Material itemMaterial, String name, int itemSize) {
		
		ItemStack item = new ItemStack(itemMaterial, itemSize);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		return item;
	}
	
	public static ItemStack MakeItemL(Material itemMaterial, String name, int itemSize, String lore) {
		
		ItemStack item = new ItemStack(itemMaterial, itemSize);
		ItemMeta itemMeta = item.getItemMeta();
		ArrayList<String> itemLore = new ArrayList<String>();
		itemLore.add(ChatColor.GREEN + lore);
		itemMeta.setLore(itemLore);
		itemMeta.setDisplayName(name);
		item.setItemMeta(itemMeta);
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack MakeGapple(String name, int itemSize, String lore) {
		
		ItemStack item = new ItemStack(Material.GOLDEN_APPLE, itemSize, (short)1);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name);
		ArrayList<String> loreArray = new ArrayList<String>();
		loreArray.add(ChatColor.GREEN + lore);
		itemMeta.setLore(loreArray);
		item.setItemMeta(itemMeta);
		
		return item;
		
	}
	
	public ItemStack getItemDamaged(ItemStack item, int damage) {
		
		ItemStack i = item;
		Damageable itemDamageable = (Damageable) i.getItemMeta();
		itemDamageable.setDamage(damage);
		ItemMeta itemMeta = (ItemMeta) itemDamageable;
		i.setItemMeta(itemMeta);
		return i;
		
	}
	
	
	/*Inventory mainInv = Bukkit.getServer().createInventory(null, 27, "Main Menu");
	public void OpenCoreGui(Player p) {
		ItemStack glassPane = MakeItem(Material.GLASS, ChatColor.RED + "Core", 1);
		ItemStack dia = MakeItem(Material.DIAMOND, ChatColor.RED + "Profile", 1);
		
		mainInv.setItem(1, dia);
		mainInv.setItem(2, glassPane);
		
		p.openInventory(mainInv);
		
	}*/
	
	static Inventory weaponsInv = Bukkit.getServer().createInventory(null, 54, "Weapon Shop");
	public void OpenWeaponsGui(Player p, Boolean backButton) {
		
//		ItemStack balItem = MakeItemL(Material.GOLD_INGOT, ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"), 1, "Balance");
		ItemStack woodsword0 = MakeItemL(Material.WOODEN_SWORD, ChatColor.RED + "Broken Wooden Sword", 1, "40 Gold");
		woodsword0 = getItemDamaged(woodsword0, (2 * Math.round(woodsword0.getType().getMaxDurability() / 3)));
		ItemStack woodsword1 = MakeItemL(Material.WOODEN_SWORD, ChatColor.RED + "Broken Wooden Sword", 1, "55 Gold");
		woodsword1.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		woodsword1 = getItemDamaged(woodsword0, (2 * Math.round(woodsword0.getType().getMaxDurability() / 3)));
		ItemStack woodsword2 = MakeItemL(Material.WOODEN_SWORD, ChatColor.RED + "Broken Wooden Sword", 1, "65 Gold");
		woodsword2.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		woodsword2 = getItemDamaged(woodsword0, (2 * Math.round(woodsword0.getType().getMaxDurability() / 3)));
		ItemStack woodsword3 = MakeItemL(Material.WOODEN_SWORD, ChatColor.RED + "Broken Wooden Sword", 1, "90 Gold");
		woodsword3.addEnchantment(Enchantment.DAMAGE_ALL, 4);
		woodsword3 = getItemDamaged(woodsword0, (2 * Math.round(woodsword0.getType().getMaxDurability() / 3)));
		
		ItemStack stonesword0 = MakeItemL(Material.STONE_SWORD, ChatColor.RED + "Broken Stone Sword", 1, "110 Gold");
		stonesword0 = getItemDamaged(woodsword0, (2 * Math.round(stonesword0.getType().getMaxDurability() / 3)));
		ItemStack stonesword1 = MakeItemL(Material.STONE_SWORD, ChatColor.RED + "Broken Stone Sword", 1, "140 Gold");
		stonesword1.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		stonesword1 = getItemDamaged(woodsword0, (2 * Math.round(stonesword0.getType().getMaxDurability() / 3)));
		ItemStack stonesword2 = MakeItemL(Material.STONE_SWORD, ChatColor.RED + "Broken Stone Sword", 1, "165 Gold");
		stonesword2.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		stonesword2 = getItemDamaged(woodsword0, (2 * Math.round(stonesword0.getType().getMaxDurability() / 3)));
		ItemStack stonesword3 = MakeItemL(Material.STONE_SWORD, ChatColor.RED + "Broken Stone Sword", 1, "220 Gold");
		stonesword3.addEnchantment(Enchantment.DAMAGE_ALL, 4);
		stonesword2 = getItemDamaged(woodsword0, (2 * Math.round(stonesword0.getType().getMaxDurability() / 3)));
		
		ItemStack ironsword0 = MakeItemL(Material.IRON_SWORD, ChatColor.RED + "Broken Iron Sword", 1, "250 Gold");
		ironsword0 = getItemDamaged(ironsword0, (2 * Math.round(ironsword0.getType().getMaxDurability() / 3)));
		ItemStack ironsword1 = MakeItemL(Material.IRON_SWORD, ChatColor.RED + "Broken Iron Sword", 1, "300 Gold");
		ironsword1.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		ironsword1 = getItemDamaged(ironsword0, (2 * Math.round(ironsword0.getType().getMaxDurability() / 3)));
		ItemStack ironsword2 = MakeItemL(Material.IRON_SWORD, ChatColor.RED + "Broken Iron Sword", 1, "350 Gold");
		ironsword2.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		ironsword2 = getItemDamaged(ironsword0, (2 * Math.round(ironsword0.getType().getMaxDurability() / 3)));
		ItemStack ironsword3 = MakeItemL(Material.IRON_SWORD, ChatColor.RED + "Broken Iron Sword", 1, "460 Gold");
		ironsword3.addEnchantment(Enchantment.DAMAGE_ALL, 4);
		ironsword2 = getItemDamaged(ironsword0, (2 * Math.round(ironsword0.getType().getMaxDurability() / 3)));

		ItemStack diamondsword0 = MakeItemL(Material.DIAMOND_SWORD, ChatColor.RED + "Broken Diamond Sword", 1, "1,050 Gold");
		diamondsword0 = getItemDamaged(diamondsword0, (2 * Math.round(diamondsword0.getType().getMaxDurability() / 3)));
		ItemStack diamondsword1 = MakeItemL(Material.DIAMOND_SWORD, ChatColor.RED + "Broken Diamond Sword", 1, "1,250 Gold");
		diamondsword1.addEnchantment(Enchantment.DAMAGE_ALL, 1);
		diamondsword1 = getItemDamaged(diamondsword0, (2 * Math.round(diamondsword0.getType().getMaxDurability() / 3)));
		ItemStack diamondsword2 = MakeItemL(Material.DIAMOND_SWORD, ChatColor.RED + "Broken Diamond Sword", 1, "1,450 Gold");
		diamondsword2.addEnchantment(Enchantment.DAMAGE_ALL, 2);
		diamondsword2 = getItemDamaged(diamondsword0, (2 * Math.round(diamondsword0.getType().getMaxDurability() / 3)));
		ItemStack diamondsword3 = MakeItemL(Material.DIAMOND_SWORD, ChatColor.RED + "Broken Diamond Sword", 1, "1,850 Gold");
		diamondsword3.addEnchantment(Enchantment.DAMAGE_ALL, 4);
		diamondsword2 = getItemDamaged(diamondsword0, (2 * Math.round(diamondsword0.getType().getMaxDurability() / 3)));
		
		ItemStack back = MakeItemL(Material.BARRIER, ChatColor.RED + "Back", 1, "Go back to the Menu!");
		
//		ItemStack back = MakeItemL(Material.BARRIER, ChatColor.RED + "Back", 1, "Go back to the Blacksmith's Menu!");
		
		weaponsInv.setItem(10, woodsword0);
		weaponsInv.setItem(12, stonesword0);
		weaponsInv.setItem(14, ironsword0);
		weaponsInv.setItem(16, diamondsword0);

		weaponsInv.setItem(19, woodsword1);
		weaponsInv.setItem(21, stonesword1);
		weaponsInv.setItem(23, ironsword1);
		weaponsInv.setItem(25, diamondsword1);
		
		weaponsInv.setItem(28, woodsword2);
		weaponsInv.setItem(30, stonesword2);
		weaponsInv.setItem(32, ironsword2);
		weaponsInv.setItem(34, diamondsword2);

		weaponsInv.setItem(37, woodsword3);
		weaponsInv.setItem(39, stonesword3);
		weaponsInv.setItem(41, ironsword3);
		weaponsInv.setItem(43, diamondsword3);
		
//		weaponsInv.setItem(4, balItem);
		if(backButton) {
			weaponsInv.setItem(45, back);
			weaponsInv.setItem(53, back);
		}
		
		p.openInventory(weaponsInv);
		
	}
	
	Inventory blacksmithInv = Bukkit.getServer().createInventory(null, 27, "Smeltery Menu");
	public void OpenSmelterGui(Player p) {
		
		ItemStack diamondPick = MakeItemL(Material.DIAMOND_PICKAXE, ChatColor.RED + "Pickaxes", 1, "Go To The Pickaxe Menu!");
		ItemStack goldBar = MakeItemL(Material.GOLD_INGOT, ChatColor.GOLD + "Treasures", 1, "Go To The Treasures Menu!");
//		ItemStack diamondSword = MakeItemL(Material.DIAMOND_SWORD, ChatColor.RED + "Swords", 1, "Go To The Swords Menu!");
//		ItemStack diamondChestplate = MakeItemL(Material.DIAMOND_CHESTPLATE, ChatColor.RED + "Armour", 1, "Go To The Armour Menu!");
		
		blacksmithInv.setItem(12, diamondPick);
		
		blacksmithInv.setItem(14, goldBar);
		
		p.openInventory(blacksmithInv);
		
	}
	
	Inventory barInv = Bukkit.getServer().createInventory(null, 27, "Bartender Menu");
	public void OpenBarGui(Player p) {
		
		ItemStack drink = MakeItemL(Material.POTION, ChatColor.RED + "Potions and Drinks", 1, "Go To The Potion/Drinks Menu!");
		ItemStack steak = MakeItemL(Material.COOKED_BEEF, ChatColor.RED + "Food", 1, "Go To The Food Menu!");
		
		barInv.setItem(12, drink);
		barInv.setItem(14, steak);
		
		p.openInventory(barInv);
		
	}
	
	Inventory toolsInv = Bukkit.getServer().createInventory(null, 54, "Tool Shop");
	public void OpenPickaxeGui(Player p, Boolean backButton) {
		
//		ItemStack balItem = MakeItemL(Material.GOLD_INGOT, ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"), 1, "Balance");
		
		ItemStack woodpick0 = MakeItemL(Material.WOODEN_PICKAXE, ChatColor.RED + "Broken Wooden Pick", 1, "20 Gold");
		woodpick0 = getItemDamaged(woodpick0, (2 * Math.round(woodpick0.getType().getMaxDurability() / 3)));
		ItemStack woodpick1 = MakeItemL(Material.WOODEN_PICKAXE, ChatColor.RED + "Broken Wooden Pick", 1, "30 Gold");
		woodpick1.addEnchantment(Enchantment.DIG_SPEED, 1);
		woodpick1 = getItemDamaged(woodpick1, (2 * Math.round(woodpick0.getType().getMaxDurability() / 3)));
		ItemStack woodpick2 = MakeItemL(Material.WOODEN_PICKAXE, ChatColor.RED + "Broken Wooden Pick", 1, "40 Gold");
		woodpick2.addEnchantment(Enchantment.DIG_SPEED, 2);
		woodpick2 = getItemDamaged(woodpick2, (2 * Math.round(woodpick0.getType().getMaxDurability() / 3)));
		ItemStack woodpick3 = MakeItemL(Material.WOODEN_PICKAXE, ChatColor.RED + "Broken Wooden Pick", 1, "60 Gold");
		woodpick3.addEnchantment(Enchantment.DIG_SPEED, 4);
		woodpick3 = getItemDamaged(woodpick3, (2 * Math.round(woodpick0.getType().getMaxDurability() / 3)));
		
		ItemStack stonepick0 = MakeItemL(Material.STONE_PICKAXE, ChatColor.RED + "Broken Stone Pick", 1, "90 Gold");
		stonepick0 = getItemDamaged(stonepick0, (2 * Math.round(stonepick0.getType().getMaxDurability() / 3)));
		ItemStack stonepick1 = MakeItemL(Material.STONE_PICKAXE, ChatColor.RED + "Broken Stone Pick", 1, "135 Gold");
		stonepick1.addEnchantment(Enchantment.DIG_SPEED, 1);
		stonepick1 = getItemDamaged(stonepick1, (2 * Math.round(stonepick0.getType().getMaxDurability() / 3)));
		ItemStack stonepick2 = MakeItemL(Material.STONE_PICKAXE, ChatColor.RED + "Broken Stone Pick", 1, "180 Gold");
		stonepick2.addEnchantment(Enchantment.DIG_SPEED, 2);
		stonepick2 = getItemDamaged(stonepick2, (2 * Math.round(stonepick0.getType().getMaxDurability() / 3)));
		ItemStack stonepick3 = MakeItemL(Material.STONE_PICKAXE, ChatColor.RED + "Broken Stone Pick", 1, "270 Gold");
		stonepick3.addEnchantment(Enchantment.DIG_SPEED, 4);
		stonepick3 = getItemDamaged(stonepick3, (2 * Math.round(stonepick0.getType().getMaxDurability() / 3)));
		
		ItemStack ironpick0 = MakeItemL(Material.IRON_PICKAXE, ChatColor.RED + "Iron Pick", 1, "250 Gold");
		ironpick0 = getItemDamaged(ironpick0, (2 * Math.round(ironpick0.getType().getMaxDurability() / 3)));
		ItemStack ironpick1 = MakeItemL(Material.IRON_PICKAXE, ChatColor.RED + "Broken Iron Pick", 1, "350 Gold");
		ironpick1.addEnchantment(Enchantment.DIG_SPEED, 1);
		ironpick1 = getItemDamaged(ironpick1, (2 * Math.round(ironpick0.getType().getMaxDurability() / 3)));
		ItemStack ironpick2 = MakeItemL(Material.IRON_PICKAXE, ChatColor.RED + "Broken Iron Pick", 1, "500 Gold");
		ironpick2.addEnchantment(Enchantment.DIG_SPEED, 2);
		ironpick2 = getItemDamaged(ironpick2, (2 * Math.round(ironpick0.getType().getMaxDurability() / 3)));
		ItemStack ironpick3 = MakeItemL(Material.IRON_PICKAXE, ChatColor.RED + "Broken Iron Pick", 1, "750 Gold");
		ironpick3.addEnchantment(Enchantment.DIG_SPEED, 4);
		ironpick3 = getItemDamaged(ironpick3, (2 * Math.round(ironpick0.getType().getMaxDurability() / 3)));
		
		ItemStack back = MakeItemL(Material.BARRIER, ChatColor.RED + "Back", 1, "Go back to the Menu!");

/*		ItemStack diamondpick0 = MakeItemL(Material.DIAMOND_PICKAXE, ChatColor.RED + "Broken Diamond Pick", 1, "20 Diamond Pieces");
		diamondpick0.setDurability((short) (4 * Math.round(diamondpick0.getType().getMaxDurability() / 5)));
		ItemStack diamondpick1 = MakeItemL(Material.DIAMOND_PICKAXE, ChatColor.RED + "Broken Diamond Pick", 1, "42 Diamond Pieces");
		diamondpick1.addEnchantment(Enchantment.DIG_SPEED, 1);
		diamondpick1.setDurability((short) (4 * Math.round(diamondpick0.getType().getMaxDurability() / 5)));
		ItemStack diamondpick2 = MakeItemL(Material.DIAMOND_PICKAXE, ChatColor.RED + "Broken Diamond Pick", 1, "96 Diamond Pieces (1.5 Stacks)");
		diamondpick2.addEnchantment(Enchantment.DIG_SPEED, 2);
		diamondpick2.setDurability((short) (4 * Math.round(diamondpick0.getType().getMaxDurability() / 5)));
		ItemStack diamondpick3 = MakeItemL(Material.DIAMOND_PICKAXE, ChatColor.RED + "Broken Diamond Pick", 1, "128 Diamond Pieces (2 Stacks)");
		diamondpick3.addEnchantment(Enchantment.DIG_SPEED, 4);
		diamondpick3.setDurability((short) (4 * Math.round(diamondpick0.getType().getMaxDurability() / 5)));*/

		
		
		toolsInv.setItem(11, woodpick0);
		toolsInv.setItem(13, stonepick0);
		toolsInv.setItem(15, ironpick0);
//		toolsInv.setItem(16, diamondpick0);

		toolsInv.setItem(20, woodpick1);
		toolsInv.setItem(22, stonepick1);
		toolsInv.setItem(24, ironpick1);
//		toolsInv.setItem(25, diamondpick1);
		
		toolsInv.setItem(29, woodpick2);
		toolsInv.setItem(31, stonepick2);
		toolsInv.setItem(33, ironpick2);
//		toolsInv.setItem(34, diamondpick2);

		toolsInv.setItem(38, woodpick3);
		toolsInv.setItem(40, stonepick3);
		toolsInv.setItem(42, ironpick3);
//		toolsInv.setItem(43, diamondpick3);
		
//		toolsInv.setItem(4, balItem);
		if(backButton) {
			toolsInv.setItem(45, back);
			toolsInv.setItem(53, back);
		}
		
		p.openInventory(toolsInv);
		
	}
	
	Inventory treasureInv = Bukkit.getServer().createInventory(null, 54, "Treasure Shop");
	public void OpenTreasureGui(Player p, Boolean backButton) {
		
//		ItemStack balItem = MakeItemL(Material.GOLD_INGOT, ChatColor.GOLD + (Math.round(eco.getBalance(p)) + " Gold"), 1, "Balance");
		
		ItemStack coal1 = MakeItemL(Material.COAL, ChatColor.RED + "Coal - 1 ", 1, "Sell For 2 Gold");
		ItemStack coal16 = MakeItemL(Material.COAL, ChatColor.RED + "Coal - 16 ", 16, "Sell For 32 Gold");
		ItemStack coal32 = MakeItemL(Material.COAL, ChatColor.RED + "Coal - 32 ", 32, "Sell For 64 Gold");
		ItemStack coal64 = MakeItemL(Material.COAL, ChatColor.RED + "Coal - 64 ", 64, "Sell For 128 Gold");
		
		ItemStack iron1 = MakeItemL(Material.IRON_INGOT, ChatColor.RED + "Iron - 1 ", 1, "Sell For 6 Gold");
		ItemStack iron16 = MakeItemL(Material.IRON_INGOT, ChatColor.RED + "Iron - 16 ", 16, "Sell For 96 Gold");
		ItemStack iron32 = MakeItemL(Material.IRON_INGOT, ChatColor.RED + "Iron - 32 ", 32, "Sell For 192 Gold");
		ItemStack iron64 = MakeItemL(Material.IRON_INGOT, ChatColor.RED + "Iron - 64 ", 64, "Sell For 384 Gold");
		
		ItemStack gold1 = MakeItemL(Material.GOLD_INGOT, ChatColor.RED + "Gold - 1 ", 1, "Sell For 18 Gold");
		ItemStack gold16 = MakeItemL(Material.GOLD_INGOT, ChatColor.RED + "Gold - 16 ", 16, "Sell For 288 Gold");
		ItemStack gold32 = MakeItemL(Material.GOLD_INGOT, ChatColor.RED + "Gold - 32 ", 32, "Sell For 576 Gold");
		ItemStack gold64 = MakeItemL(Material.GOLD_INGOT, ChatColor.RED + "Gold - 64 ", 64, "Sell For 1,152 Gold");

		ItemStack dia1 = MakeItemL(Material.DIAMOND, ChatColor.RED + "Diamond - 1 ", 1, "Sell For 54 Gold");
		ItemStack dia16 = MakeItemL(Material.DIAMOND, ChatColor.RED + "Diamond - 16 ", 16, "Sell For 864 Gold");
		ItemStack dia32 = MakeItemL(Material.DIAMOND, ChatColor.RED + "Diamond - 32 ", 32, "Sell For 1,728 Gold");
		ItemStack dia64 = MakeItemL(Material.DIAMOND, ChatColor.RED + "Diamond - 64 ", 64, "Sell For 3,456 Gold");
		
		ItemStack back = MakeItemL(Material.BARRIER, ChatColor.RED + "Back", 1, "Go back to the Menu!");

		
		treasureInv.setItem(10, coal1);
		treasureInv.setItem(12, iron1);
		treasureInv.setItem(14, gold1);
		treasureInv.setItem(16, dia1);

		treasureInv.setItem(19, coal16);
		treasureInv.setItem(21, iron16);
		treasureInv.setItem(23, gold16);
		treasureInv.setItem(25, dia16);
		
		treasureInv.setItem(28, coal32);
		treasureInv.setItem(30, iron32);
		treasureInv.setItem(32, gold32);
		treasureInv.setItem(34, dia32);

		treasureInv.setItem(37, coal64);
		treasureInv.setItem(39, iron64);
		treasureInv.setItem(41, gold64);
		treasureInv.setItem(43, dia64);
		
		if(backButton) {
			treasureInv.setItem(45, back);
			treasureInv.setItem(53, back);
		}
		
//		treasureInv.setItem(4, balItem);
		
		p.openInventory(treasureInv);
		
	}
	
	Inventory armourInv = Bukkit.getServer().createInventory(null, 54, "Armour Shop");
	public void OpenArmourGui(Player p, Boolean backButton) {
		
		ItemStack leatherHead = getItemDamaged(MakeItemL(Material.LEATHER_HELMET, ChatColor.RED + "Broken Leather Helmet", 1, "20 Gold"), (2 * Math.round(Material.LEATHER_HELMET.getMaxDurability() / 3)));
		ItemStack leatherChest = getItemDamaged(MakeItemL(Material.LEATHER_CHESTPLATE, ChatColor.RED + "Broken Leather Chestplate", 1, "20 Gold"), (2 * Math.round(Material.LEATHER_CHESTPLATE.getMaxDurability() / 3)));
		ItemStack leatherLegs = getItemDamaged(MakeItemL(Material.LEATHER_LEGGINGS, ChatColor.RED + "Broken Leather Leggings", 1, "20 Gold"), (2 * Math.round(Material.LEATHER_LEGGINGS.getMaxDurability() / 3)));
		ItemStack leatherBoots = getItemDamaged(MakeItemL(Material.LEATHER_BOOTS, ChatColor.RED + "Broken Leather Boots", 1, "20 Gold"), (2 * Math.round(Material.LEATHER_BOOTS.getMaxDurability() / 3)));
		
		ItemStack chainHead = getItemDamaged(MakeItemL(Material.CHAINMAIL_HELMET, ChatColor.RED + "Broken Chainmail Helmet", 1, "20 Gold"), (2 * Math.round(Material.CHAINMAIL_HELMET.getMaxDurability() / 3)));
		ItemStack chainChest = getItemDamaged(MakeItemL(Material.CHAINMAIL_CHESTPLATE, ChatColor.RED + "Broken Chainmail Chestplate", 1, "20 Gold"), (2 * Math.round(Material.CHAINMAIL_CHESTPLATE.getMaxDurability() / 3)));
		ItemStack chainLegs = getItemDamaged(MakeItemL(Material.CHAINMAIL_LEGGINGS, ChatColor.RED + "Broken Chainmail Leggings", 1, "20 Gold"), (2 * Math.round(Material.CHAINMAIL_LEGGINGS.getMaxDurability() / 3)));
		ItemStack chainBoots = getItemDamaged(MakeItemL(Material.CHAINMAIL_BOOTS, ChatColor.RED + "Broken Chainmail Boots", 1, "20 Gold"), (2 * Math.round(Material.CHAINMAIL_BOOTS.getMaxDurability() / 3)));
		
		ItemStack ironHead = getItemDamaged(MakeItemL(Material.IRON_HELMET, ChatColor.RED + "Broken Iron Helmet", 1, "20 Gold"), (2 * Math.round(Material.IRON_HELMET.getMaxDurability() / 3)));
		ItemStack ironChest = getItemDamaged(MakeItemL(Material.IRON_CHESTPLATE, ChatColor.RED + "Broken Iron Chestplate", 1, "20 Gold"), (2 * Math.round(Material.IRON_CHESTPLATE.getMaxDurability() / 3)));
		ItemStack ironLegs = getItemDamaged(MakeItemL(Material.IRON_LEGGINGS, ChatColor.RED + "Broken Chainmail Leggings", 1, "20 Gold"), (2 * Math.round(Material.IRON_LEGGINGS.getMaxDurability() / 3)));
		ItemStack ironBoots = getItemDamaged(MakeItemL(Material.IRON_BOOTS, ChatColor.RED + "Broken Chainmail Boots", 1, "20 Gold"), (2 * Math.round(Material.IRON_BOOTS.getMaxDurability() / 3)));

		ItemStack diaHead = getItemDamaged(MakeItemL(Material.DIAMOND_HELMET, ChatColor.RED + "Broken Diamond Helmet", 1, "20 Gold"), (2 * Math.round(Material.DIAMOND_HELMET.getMaxDurability() / 3)));
		ItemStack diaChest = getItemDamaged(MakeItemL(Material.DIAMOND_CHESTPLATE, ChatColor.RED + "Broken Diamond Chestplate", 1, "20 Gold"), (2 * Math.round(Material.DIAMOND_CHESTPLATE.getMaxDurability() / 3)));
		ItemStack diaLegs = getItemDamaged(MakeItemL(Material.DIAMOND_LEGGINGS, ChatColor.RED + "Broken Diamond Leggings", 1, "20 Gold"), (2 * Math.round(Material.DIAMOND_LEGGINGS.getMaxDurability() / 3)));
		ItemStack diaBoots = getItemDamaged(MakeItemL(Material.DIAMOND_BOOTS, ChatColor.RED + "Broken Diamond Boots", 1, "20 Gold"), (2 * Math.round(Material.DIAMOND_BOOTS.getMaxDurability() / 3)));

		ItemStack back = MakeItemL(Material.BARRIER, ChatColor.RED + "Back", 1, "Go back to the Menu!");

		armourInv.setItem(10, leatherHead);
		armourInv.setItem(12, chainHead);
		armourInv.setItem(14, ironHead);
		armourInv.setItem(16, diaHead);

		armourInv.setItem(19, leatherChest);
		armourInv.setItem(21, chainChest);
		armourInv.setItem(23, ironChest);
		armourInv.setItem(25, diaChest);
		
		armourInv.setItem(28, leatherLegs);
		armourInv.setItem(30, chainLegs);
		armourInv.setItem(32, ironLegs);
		armourInv.setItem(34, diaLegs);

		armourInv.setItem(37, leatherBoots);
		armourInv.setItem(39, chainBoots);
		armourInv.setItem(41, ironBoots);
		armourInv.setItem(43, diaBoots);
		
		if(backButton) {
			armourInv.setItem(45, back);
			armourInv.setItem(53, back);
		}
		
		p.openInventory(armourInv);
		
	}
	
	public void OpenPotionGui(Player p) {
		//ItemStack back = MakeItemL(Material.BARRIER, ChatColor.RED + "Back", 1, "Go back to the Bartender's Menu!");
		
	}
	
	Inventory foodInv = Bukkit.getServer().createInventory(null, 54, "Food Shop");
	public void OpenFoodGui(Player p, Boolean backButton) {
		
		ItemStack steak1 = MakeItemL(Material.COOKED_BEEF, ChatColor.RED + "Steak", 1, "4 Coal Pieces");
		ItemStack steak16 = MakeItemL(Material.COOKED_BEEF, ChatColor.RED + "Steak", 16, "32 Coal Pieces");
		ItemStack steak32 = MakeItemL(Material.COOKED_BEEF, ChatColor.RED + "Steak", 32, "64 Coal Pieces");
		ItemStack steak64 = MakeItemL(Material.COOKED_BEEF, ChatColor.RED + "Steak", 64, "128 Coal Pieces (2 Stacks)");
		
//		ItemStack pot1 = MakeItemL(Material.POTATO_ITEM, ChatColor.RED + "Potato", 1, "1 Coal Pieces");
//		ItemStack pot16 = MakeItemL(Material.POTATO_ITEM, ChatColor.RED + "Potato", 16, "8 Coal Pieces");
//		ItemStack pot32 = MakeItemL(Material.POTATO_ITEM, ChatColor.RED + "Potato", 32, "16 Coal Pieces");
//		ItemStack pot64 = MakeItemL(Material.POTATO_ITEM, ChatColor.RED + "Potato", 64, "32 Coal Pieces");

		ItemStack crap1 = MakeItemL(Material.GOLDEN_APPLE, ChatColor.RED + "Crapple", 1, "1 Gold Pieces");
		ItemStack crap16 = MakeItemL(Material.GOLDEN_APPLE, ChatColor.RED + "Crapple", 16, "8 Gold Pieces");
		ItemStack crap32 = MakeItemL(Material.GOLDEN_APPLE, ChatColor.RED + "Crapple", 32, "16 Gold Pieces");
		ItemStack crap64 = MakeItemL(Material.GOLDEN_APPLE, ChatColor.RED + "Crapple", 64, "32 Gold Pieces");
		
		ItemStack gap1 = MakeGapple(ChatColor.RED + "Golden Apple", 1, "10 Emerald Pieces");
		ItemStack gap16 = MakeGapple(ChatColor.RED + "Golden Apple", 16, "80 Emerald Pieces");
		ItemStack gap32 = MakeGapple(ChatColor.RED + "Golden Apple", 32, "160 Emerald Pieces (2.5 Stacks)");
		ItemStack gap64 = MakeGapple(ChatColor.RED + "Golden Apple", 64, "320 Emerald Pieces (5 Stacks)");
		
		ItemStack back = MakeItemL(Material.BARRIER, ChatColor.RED + "Back", 1, "Go back to the  Menu!");
		
		foodInv.setItem(10, steak1);
//		foodInv.setItem(12, pot1);
		foodInv.setItem(14, crap1);
		foodInv.setItem(16, gap1);

		foodInv.setItem(19, steak16);
//		foodInv.setItem(21, pot16);
		foodInv.setItem(23, crap16);
		foodInv.setItem(25, gap16);
		
		foodInv.setItem(28, steak32);
//		foodInv.setItem(30, pot32);
		foodInv.setItem(32, crap32);
		foodInv.setItem(34, gap32);

		foodInv.setItem(37, steak64);
//		foodInv.setItem(39, pot64);
		foodInv.setItem(41, crap64);
		foodInv.setItem(43, gap64);
		
		if(backButton) {
			foodInv.setItem(45, back);
			foodInv.setItem(53, back);
		}
		
		p.openInventory(foodInv);
		
	}
	
	Inventory shopInv = Bukkit.getServer().createInventory(null, 45, "Shop Menu");
	public void OpenShopGui(Player p) {
		
		ItemStack goldbar = MakeItem(Material.GOLD_INGOT, ChatColor.GOLD + "Treasures", 1);
		ItemStack diasword = MakeItem(Material.DIAMOND_SWORD, ChatColor.RED + "Weapons", 1);
		ItemStack diapick = MakeItem(Material.DIAMOND_PICKAXE, ChatColor.RED + "Pickaxes", 1);
		ItemStack diaarmour = MakeItem(Material.DIAMOND_CHESTPLATE, ChatColor.RED + "Armour", 1);
		ItemStack pot = MakeItem(Material.POTION, ChatColor.RED + "Potions", 1);
		ItemStack food = MakeItem(Material.COOKED_BEEF, ChatColor.RED + "Food", 1);
		
		shopInv.setItem(4, goldbar);
		shopInv.setItem(19, food);
		shopInv.setItem(21, diapick);
		shopInv.setItem(22, diasword);
		shopInv.setItem(23, diaarmour);
		shopInv.setItem(25, pot);
		
		p.openInventory(shopInv);
		
	}

	
	
}
