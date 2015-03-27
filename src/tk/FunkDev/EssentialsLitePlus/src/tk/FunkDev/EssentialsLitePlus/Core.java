
package tk.FunkDev.EssentialsLitePlus;

import java.io.File;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import tk.FunkDev.EssentialsLitePlus.Commands.EnderChest;
import tk.FunkDev.EssentialsLitePlus.Commands.EssentialsLitePlus;
import tk.FunkDev.EssentialsLitePlus.Commands.Feed;
import tk.FunkDev.EssentialsLitePlus.Commands.Fly;
import tk.FunkDev.EssentialsLitePlus.Commands.Gamemode;
import tk.FunkDev.EssentialsLitePlus.Commands.Heal;
import tk.FunkDev.EssentialsLitePlus.Commands.Invsee;
import tk.FunkDev.EssentialsLitePlus.Commands.ItemInfo;
import tk.FunkDev.EssentialsLitePlus.Commands.Kill;
import tk.FunkDev.EssentialsLitePlus.Commands.Me;
import tk.FunkDev.EssentialsLitePlus.Commands.Nick;
import tk.FunkDev.EssentialsLitePlus.Commands.SetHealth;
import tk.FunkDev.EssentialsLitePlus.Commands.SetHunger;
import tk.FunkDev.EssentialsLitePlus.Commands.Speed;
import tk.FunkDev.EssentialsLitePlus.Commands.Suicide;
import tk.FunkDev.EssentialsLitePlus.Utils.Updater;
import tk.FunkDev.EssentialsLitePlus.Utils.Updater.UpdateResult;
import tk.FunkDev.EssentialsLitePlus.Utils.Updater.UpdateType;

public class Core extends JavaPlugin implements Listener {
	
	/*
	 * This plugin has been made by
	 * @author FunkDev.
	 * Leading Dev. funkemunky, Owner and Founder of the FunkDev team.
	 * @version 0.7.8
	 */
    
    
	@SuppressWarnings("rawtypes")
	public void onEnable() {
		
		File mainconfig = new File(getDataFolder() + File.separator + "config.yml");
		
		if(!mainconfig.exists()) {
			getLogger().info("Generating config.yml...");
			getLogger().info("Main Configuration file generated to disk!");
			
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
		
		Bukkit.getPluginManager().registerEvents(new InvseeListener(this), this);
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new Nick(this), this);
		Bukkit.getPluginManager().registerEvents(new OnDeathListener(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinFireworks(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerInfo(this), this);
		getCommand("essentialsliteplus").setExecutor(new EssentialsLitePlus(this));
		getCommand("feed").setExecutor(new Feed(this));
		getCommand("fly").setExecutor(new Fly(this));
		getCommand("heal").setExecutor(new Heal(this));
		getCommand("kill").setExecutor(new Kill(this));
		getCommand("speed").setExecutor(new Speed(this));
		getCommand("suicide").setExecutor(new Suicide(this));
		getCommand("sethunger").setExecutor(new SetHunger(this));
		getCommand("nick").setExecutor(new Nick(this));
		getCommand("iteminfo").setExecutor(new ItemInfo(this));
		getCommand("me").setExecutor(new Me(this));
		getCommand("sethealth").setExecutor(new SetHealth(this));
		getCommand("enderchest").setExecutor(new EnderChest(this));
		getCommand("gamemode").setExecutor(new Gamemode(this));
		getCommand("survival").setExecutor(new Gamemode(this));
		getCommand("creative").setExecutor(new Gamemode(this));
		getCommand("adventure").setExecutor(new Gamemode(this));
		getCommand("invsee").setExecutor(new Invsee(this));
	
	}
	
    private static ItemStack reloadConfig, update, checkupdate;
	
	public static Inventory GUI = Bukkit.createInventory(null, 9, "EssentialsLitePlus ControlPanel");
	
	static
	{
		reloadConfig = item1("§f§lReload");
		update = item2("§f§lUpdate");
		checkupdate = item3("§f§lCheck for updates");
		
		GUI.setItem(2, reloadConfig);
		GUI.setItem(4, update);
		GUI.setItem(6, checkupdate);
	}
	
	private static ItemStack item1(String name) {
		ItemStack i = new ItemStack(new ItemStack(Material.BLAZE_POWDER, 1));
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList("§aSave and Reload", "§aEssentialsLitePlus"));
		i.setItemMeta(im);
		return i;
	}

	private static ItemStack item2(String name) {
		ItemStack i = new ItemStack(new ItemStack(Material.BLAZE_POWDER, 1));
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList("§2Update EssentialsLite-Plus"));
		i.setItemMeta(im);
		return i;
	}
	
	private static ItemStack item3(String name) {
		ItemStack i = new ItemStack(new ItemStack(Material.BLAZE_POWDER, 1));
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList("§2Check if a new update is out!"));
		i.setItemMeta(im);
		return i;
	}
	
	@EventHandler
	public void onPlayerInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!e.getInventory().getName().equalsIgnoreCase(GUI.getName())) return;
		if (e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§f§lReload")){
			e.setCancelled(true);
			p.closeInventory();
			p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "EssentialsLitePlus" + ChatColor.GRAY + "] " + ChatColor.RED + "Reloading configuration file...");
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				public void run() {
					reloadConfig();
					p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "EssentialsLitePlus" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Configuration file reloaded!");
				}
			}, 10L);
		}
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§f§lUpdate")) {
			e.setCancelled(true);
			p.closeInventory();
			Updater updater = new Updater(this, 90094, this.getFile(), UpdateType.DEFAULT, true);
			p.sendMessage(ChatColor.GREEN + "Please check console to see the information on updates.");
			p.sendMessage(ChatColor.GREEN + "Update Result: " + updater.getResult().toString());
		}
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§f§lCheck for updates")) { 
			e.setCancelled(true);
			p.closeInventory();
			Updater updater = new Updater(this, 90094, this.getFile(), UpdateType.NO_DOWNLOAD, true);
			p.sendMessage("");
			p.sendMessage(ChatColor.GOLD + "Update Result: " + updater.getResult().toString());
			p.sendMessage("");
			p.sendMessage(ChatColor.GOLD + "Current EssentialsLitePlus Version: " + "EssentialsLitePlus v" + getDescription().getVersion());
			p.sendMessage(ChatColor.GOLD + "Latest EssentialsLitePlus Version: " + updater.getLatestName());
			p.sendMessage("");
			if(UpdateResult.UPDATE_AVAILABLE != null) p.sendMessage(ChatColor.GREEN + "Update is avalible so do /elp and click update to download!");
			p.sendMessage("");
		}
	}
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(getConfig().getString("RemoveNicknameOnLeave").equalsIgnoreCase("true")) {
			getConfig().set(p.getName(), p.getName());
			saveConfig();
		}
	}
}
