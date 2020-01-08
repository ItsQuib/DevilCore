package net.devilcraft.core;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import net.milkbowl.vault.economy.Economy;

public class ItemStorage {
	
	Main plugin;
	private static Economy eco;
	
	public ItemStorage (Main p, Economy econ) {
		
		this.plugin = p;
		ItemStorage.eco = econ;
		
	}
	
	Inventories ivc = new Inventories(plugin, eco);
	
	
	@SuppressWarnings("deprecation")
	public ItemStack getItem(int itemId) {
		
		ItemStack item = null;
		
		switch(itemId) {
			case 1:
				item = ivc.MakeItem(Material.WOODEN_SWORD, ChatColor.DARK_PURPLE + "Wooden Sword", 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 2:
				item = ivc.MakeItem(Material.WOODEN_SWORD, ChatColor.DARK_PURPLE + "Wooden Sword", 1);
				item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 3:
				item = ivc.MakeItem(Material.WOODEN_SWORD, ChatColor.DARK_PURPLE + "Wooden Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));				
				break;
			case 4:
				item = ivc.MakeItem(Material.WOODEN_SWORD, ChatColor.DARK_PURPLE + "Wooden Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 4);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));				
				break;
			case 5:
				item = ivc.MakeItem(Material.STONE_SWORD, ChatColor.DARK_PURPLE + "Stone Sword", 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 6:
				item = ivc.MakeItem(Material.STONE_SWORD, ChatColor.DARK_PURPLE + "Stone Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));				
				break;
			case 7:
				item = ivc.MakeItem(Material.STONE_SWORD, ChatColor.DARK_PURPLE + "Stone Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));				
				break;
			case 8:
				item = ivc.MakeItem(Material.STONE_SWORD, ChatColor.DARK_PURPLE + "Stone Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 4);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));				
				break;
			case 9:
				item = ivc.MakeItem(Material.IRON_SWORD, ChatColor.DARK_PURPLE + "Iron Sword", 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 10:
				item = ivc.MakeItem(Material.IRON_SWORD, ChatColor.DARK_PURPLE + "Iron Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 11:
				item = ivc.MakeItem(Material.IRON_SWORD, ChatColor.DARK_PURPLE + "Iron Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 12:
				item = ivc.MakeItem(Material.IRON_SWORD, ChatColor.DARK_PURPLE + "Iron Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 4);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 13:
				item = ivc.MakeItem(Material.DIAMOND_SWORD, ChatColor.DARK_PURPLE + "Diamond Sword", 1);
				item.setDurability((short) (4 * Math.round(item.getType().getMaxDurability() / 5)));
				break;
			case 14:
				item = ivc.MakeItem(Material.DIAMOND_SWORD, ChatColor.DARK_PURPLE + "Diamond Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 1);
				item.setDurability((short) (4 * Math.round(item.getType().getMaxDurability() / 5)));
				break;
			case 15:
				item = ivc.MakeItem(Material.DIAMOND_SWORD, ChatColor.DARK_PURPLE + "Diamond Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 2);
				item.setDurability((short) (4 * Math.round(item.getType().getMaxDurability() / 5)));
				break;
			case 16:
				item = ivc.MakeItem(Material.DIAMOND_SWORD, ChatColor.DARK_PURPLE + "Diamond Sword", 1);		
				item.addEnchantment(Enchantment.DAMAGE_ALL, 4);
				item.setDurability((short) (4 * Math.round(item.getType().getMaxDurability() / 5)));
				break;
			case 17:
				item = ivc.MakeItem(Material.WOODEN_PICKAXE, ChatColor.DARK_PURPLE + "Wooden Pick", 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 18:
				item = ivc.MakeItem(Material.WOODEN_PICKAXE, ChatColor.DARK_PURPLE + "Wooden Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 19:
				item = ivc.MakeItem(Material.WOODEN_PICKAXE, ChatColor.DARK_PURPLE + "Wooden Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 2);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 20:
				item = ivc.MakeItem(Material.WOODEN_PICKAXE, ChatColor.DARK_PURPLE + "Wooden Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 4);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 21:
				item = ivc.MakeItem(Material.STONE_PICKAXE, ChatColor.DARK_PURPLE + "Stone Pick", 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 22:
				item = ivc.MakeItem(Material.STONE_PICKAXE, ChatColor.DARK_PURPLE + "Stone Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 23:
				item = ivc.MakeItem(Material.STONE_PICKAXE, ChatColor.DARK_PURPLE + "Stone Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 2);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 24:
				item = ivc.MakeItem(Material.STONE_PICKAXE, ChatColor.DARK_PURPLE + "Stone Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 4);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 25:
				item = ivc.MakeItem(Material.IRON_PICKAXE, ChatColor.DARK_PURPLE + "Iron Pick", 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 26:
				item = ivc.MakeItem(Material.IRON_PICKAXE, ChatColor.DARK_PURPLE + "Iron Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 1);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 27:
				item = ivc.MakeItem(Material.IRON_PICKAXE, ChatColor.DARK_PURPLE + "Iron Pick", 1);		
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				item.addEnchantment(Enchantment.DIG_SPEED, 2);
				break;
			case 28:
				item = ivc.MakeItem(Material.IRON_PICKAXE, ChatColor.DARK_PURPLE + "Iron Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 4);
				item.setDurability((short) (2 * Math.round(item.getType().getMaxDurability() / 3)));
				break;
			case 29:
				item = ivc.MakeItem(Material.DIAMOND_PICKAXE, ChatColor.DARK_PURPLE + "Diamond Pick", 1);
				item.setDurability((short) (4 * Math.round(item.getType().getMaxDurability() / 5)));
				break;
			case 30:
				item = ivc.MakeItem(Material.DIAMOND_PICKAXE, ChatColor.DARK_PURPLE + "Diamond Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 1);
				item.setDurability((short) (4 * Math.round(item.getType().getMaxDurability() / 5)));
				break;
			case 31:
				item = ivc.MakeItem(Material.DIAMOND_PICKAXE, ChatColor.DARK_PURPLE + "Diamond Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 2);
				item.setDurability((short) (4 * Math.round(item.getType().getMaxDurability() / 5)));
				break;
			case 32:
				item = ivc.MakeItem(Material.DIAMOND_PICKAXE, ChatColor.DARK_PURPLE + "Diamond Pick", 1);		
				item.addEnchantment(Enchantment.DIG_SPEED, 4);
				item.setDurability((short) (4 * Math.round(item.getType().getMaxDurability() / 5)));
				break;
			case 33:
				item = ivc.getItemDamaged(ivc.MakeItem(Material.LEATHER_HELMET, ChatColor.DARK_PURPLE + "Leather Helmet", 1), 2 * Math.round(Material.LEATHER_HELMET.getMaxDurability() / 3));
			case 1001:
				item = ivc.MakeItem(Material.COOKED_BEEF, ChatColor.DARK_PURPLE + "Steak", 1);		
				break;
			case 1002:
				item = ivc.MakeItem(Material.COOKED_BEEF, ChatColor.DARK_PURPLE + "Steak", 16);		
				break;
			case 1003:
				item = ivc.MakeItem(Material.COOKED_BEEF, ChatColor.DARK_PURPLE + "Steak", 32);
				break;
			case 1004:
				item = ivc.MakeItem(Material.COOKED_BEEF, ChatColor.DARK_PURPLE + "Steak", 64);
				break;
			case 1005:
				item = ivc.MakeItem(Material.POTATO, ChatColor.DARK_PURPLE + "Potato", 1);
				break;
			case 1006:
				item = ivc.MakeItem(Material.POTATO, ChatColor.DARK_PURPLE + "Potato", 16);		
				break;
			case 1007:
				item = ivc.MakeItem(Material.POTATO, ChatColor.DARK_PURPLE + "Potato", 32);		
				break;
			case 1008:
				item = ivc.MakeItem(Material.POTATO, ChatColor.DARK_PURPLE + "Potato", 64);		
				break;
			case 1009:
				item = ivc.MakeItem(Material.GOLDEN_APPLE, ChatColor.DARK_PURPLE + "Crapple", 1);
				break;
			case 1010:
				item = ivc.MakeItem(Material.GOLDEN_APPLE, ChatColor.DARK_PURPLE + "Crapple", 16);
				break;
			case 1011:
				item = ivc.MakeItem(Material.GOLDEN_APPLE, ChatColor.DARK_PURPLE + "Crapple", 32);
				break;
			case 1012:
				item = ivc.MakeItem(Material.GOLDEN_APPLE, ChatColor.DARK_PURPLE + "Crapple", 64);
				break;
			case 99999:
				item = ivc.MakeItem(Material.STONE, ChatColor.DARK_PURPLE + "Last Resort - If you get this item please give it to an Admin :)", 1);
				break;
		}
		
		return item;
		
	}
}