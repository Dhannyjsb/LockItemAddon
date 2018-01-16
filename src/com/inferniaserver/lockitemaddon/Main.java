package com.inferniaserver.lockitemaddon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener,CommandExecutor{
			
	    public void onEnable() {
	   	  getServer().getPluginManager().registerEvents(this, this);
	      this.saveDefaultConfig();
		  System.out.print("TEST ON");
	    }
	    
	    public void onDisable() {
	        this.saveConfig();
	        System.out.print("TEST OFF");
	    }
	    
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			 if (cmd.getName().equalsIgnoreCase("itemaddon")) {
		            if (args[0].equalsIgnoreCase("reload")) {
		                if (sender instanceof Player) {
		                	this.getConfig();
		                	this.reloadConfig();
		                        sender.sendMessage(ChatColor.RED + " plugin has been reloaded");
		                        return true;
		                } else if (!(sender instanceof Player)) {
		                	this.getConfig();
		                	this.reloadConfig();
		                    sender.sendMessage(ChatColor.RED + " plugin has been reloaded");
		                    return true;
		                }
		                return false;
		            }
		                if (args[0].equalsIgnoreCase("lock")) {
		                    if (sender instanceof Player) {
		            			Player p = (Player) sender;
		        	        ItemStack item = new ItemStack(Material.valueOf(getConfig().getString("stone-lock")));
		        	        ItemMeta im = item.getItemMeta();
		        	        im.setDisplayName("§6§l[Locked ITEM]");
		        	        im.addEnchant(Enchantment.BINDING_CURSE, 5, true);
		        	        item.setItemMeta(im);
		        	        p.getInventory().addItem(item);
		                            return true;
		                        }
		            return false;
		        }    
		                if (args[0].equalsIgnoreCase("unlock")) {
		                	
		                    if (sender instanceof Player) {
		            			Player p = (Player) sender;
		        	        ItemStack item = new ItemStack(Material.valueOf(getConfig().getString("stone-unlock")));
		        	        ItemMeta im = item.getItemMeta();
		        	        im.setDisplayName("§6§l[Unlock ITEM]");
		        	        im.addEnchant(Enchantment.BINDING_CURSE, 5, true);
		        	        item.setItemMeta(im);
		        	        p.getInventory().addItem(item);
		                            return true;
		                        }
		            return false;
		        }   
		}
				return false;
		}
	    @SuppressWarnings("deprecation")
		@EventHandler
	    public void OnKlickCuyAe(InventoryClickEvent e) {
	        List<String> listlock = getConfig().getStringList("item-list");
	        for(String nameblock : listlock)
	        if (e.getClick().isLeftClick()) {
	        	ItemStack item = new ItemStack(Material.valueOf(getConfig().getString("stone-lock")));
	            ItemMeta im = item.getItemMeta();
	            im.setDisplayName("§6§l[Locked ITEM]");
	            im.addEnchant(Enchantment.BINDING_CURSE, 5, true);
	            item.setItemMeta(im);
	            	if (e.getCursor().isSimilar(item) && e.getCurrentItem().getType() == Material.matchMaterial(nameblock) && e.getCurrentItem().getItemMeta().getLore() == null) {
		            	ItemStack item2 = new ItemStack(e.getCurrentItem());
		            	ItemMeta meta = item2.getItemMeta();
		            	 String name = e.getCurrentItem().getItemMeta().getDisplayName();
		            	ArrayList<String> lore = new ArrayList<String>();
		            	lore.add("§6§l[Locked]");
		            	if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
	                    	meta.setDisplayName(name);
	                    }
		            	else{
	                    	meta.setDisplayName("§2" + Material.matchMaterial(nameblock) + " §l+" );
	                    }
		            	meta.setLore(lore);
		            	item2.setItemMeta(meta);
		            	int stone = e.getCursor().getAmount();
		            	 if ( stone > 1) {
		            		 item.setAmount(stone - 1);
		            		 e.setCursor(item);
				             e.setCancelled(true);
		            	 }
		            	 if (stone == 1) {
		            		 e.setCursor(null);
		            		 e.setCancelled(true);
		            		 }
		            	 	e.getWhoClicked().sendMessage("§d(!) §8» §fItem §6[Locked]§f !");
		            	 	e.setCurrentItem(item2);
		                    }
	            	else if (e.getCursor().isSimilar(item) && e.getCurrentItem().getType() == Material.matchMaterial(nameblock) && e.getCurrentItem().getItemMeta().getLore().contains("§6§l[Locked]")) {
	            			e.getWhoClicked().sendMessage("§d(x) §8» §fItem Status sudah §6[Locked]§f !");
	            			e.setCancelled(true);
	             			}
	            	else if (e.getCursor().isSimilar(item) && e.getCurrentItem().getType() == Material.matchMaterial(nameblock) && e.getCurrentItem().getItemMeta().hasLore()) {
			            	ItemStack item2 = new ItemStack(e.getCurrentItem());
			            	ItemMeta meta = item2.getItemMeta();
		                    List<String> lore = new ArrayList<>();
		                    String name = e.getCurrentItem().getItemMeta().getDisplayName();
		                    for(String str : e.getCurrentItem().getItemMeta().getLore()){
		                    	lore.add(str);
		                    		}
		                    lore.add("§6§l[Locked]");
			            	if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
		                    	meta.setDisplayName(name);
		                    }
			            	else{
		                    	meta.setDisplayName("§2" + Material.matchMaterial(nameblock) + " §l+" );
		                    }
		                    meta.setLore(lore);
			                item2.setItemMeta(meta);   
			            	int stone = e.getCursor().getAmount();
			            	 if ( stone > 1) {
			            		 item.setAmount(stone - 1);
			            		 e.setCursor(item);
					             e.setCancelled(true);
			            	 }
			            	 if (stone == 1) {
			            		 e.setCursor(null);
			            		 e.setCancelled(true);
			            		 }
			                e.getWhoClicked().sendMessage("§d(!) §8» §fItem §6[Locked]§f !");
			                e.setCancelled(true);
			                e.setCurrentItem(item2);
			                    }
	              			}
	        for(String nameblock : listlock)
	        if (e.getClick().isLeftClick()) {
	        	ItemStack item = new ItemStack(Material.valueOf(getConfig().getString("stone-unlock")));
	            ItemMeta im = item.getItemMeta();
	            im.setDisplayName("§6§l[Unlock ITEM]");
	            im.addEnchant(Enchantment.BINDING_CURSE, 5, true);
	            item.setItemMeta(im);
	            	if (e.getCursor().isSimilar(item) && e.getCurrentItem().getType() == Material.matchMaterial(nameblock) && e.getCurrentItem().getItemMeta().getLore() == null) {
            			e.getWhoClicked().sendMessage("§d(x) §8» §fItem tidak §6[Locked]§f !");
            			e.setCancelled(true);
		                    }
	            	else if (e.getCursor().isSimilar(item) && e.getCurrentItem().getType() == Material.matchMaterial(nameblock) && e.getCurrentItem().getItemMeta().getLore().contains("§6§l[Locked]")) {
		            	ItemStack item2 = new ItemStack(e.getCurrentItem());
		            	ItemMeta meta = item2.getItemMeta();
	                    List<String> lore = new ArrayList<>();
	                    String name = e.getCurrentItem().getItemMeta().getDisplayName(); 
	                    for(String str : e.getCurrentItem().getItemMeta().getLore()){
	                    	lore.add(str);
	                    		}
	                    meta.setDisplayName(name);
	                    lore.remove("§6§l[Locked]");
	                    meta.setLore(lore);
		                item2.setItemMeta(meta);   
		            	int stone = e.getCursor().getAmount();
		            	 if ( stone > 1) {
		            		 item.setAmount(stone - 1);
		            		 e.setCursor(item);
				             e.setCancelled(true);
		            	 }
		            	 if (stone == 1) {
		            		 e.setCursor(null);
		            		 e.setCancelled(true);
		            		 }
		                e.getWhoClicked().sendMessage("§d(!) §8» §fItem §6[Unlocked]§f !");
		                e.setCancelled(true);
		                e.setCurrentItem(item2);
	             			}
	            	else if (e.getCursor().isSimilar(item) && e.getCurrentItem().getType() == Material.matchMaterial(nameblock) && e.getCurrentItem().getItemMeta().hasLore()) {
            			e.getWhoClicked().sendMessage("§d(x) §8» §fItem tidak §6[Locked]§f !");
            			e.setCancelled(true);
			                    }
	              			}
	                }
		}