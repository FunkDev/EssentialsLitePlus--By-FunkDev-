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

import tk.FunkDev.EssentialsLitePlus.Updater.UpdateResult;
import tk.FunkDev.EssentialsLitePlus.Updater.UpdateType;
import tk.FunkDev.EssentialsLitePlus.Commands.EssentialsLitePlus;
import tk.FunkDev.EssentialsLitePlus.Commands.Feed;
import tk.FunkDev.EssentialsLitePlus.Commands.Fly;
import tk.FunkDev.EssentialsLitePlus.Commands.Heal;
import tk.FunkDev.EssentialsLitePlus.Commands.Kill;
import tk.FunkDev.EssentialsLitePlus.Commands.Nick;
import tk.FunkDev.EssentialsLitePlus.Commands.SetHunger;
import tk.FunkDev.EssentialsLitePlus.Commands.Speed;
import tk.FunkDev.EssentialsLitePlus.Commands.Suicide;

public class Core extends JavaPlugin implements Listener {
	
	public void onEnable() {
		File file = new File(getDataFolder() + File.separator + "config.yml");
		
		Bukkit.getPluginManager().registerEvents(this, this);
		Bukkit.getPluginManager().registerEvents(new Nick(this), this);
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerChat(), this);
		getCommand("essentialsliteplus").setExecutor(new EssentialsLitePlus(this));
		getCommand("feed").setExecutor(new Feed(this));
		getCommand("fly").setExecutor(new Fly(this));
		getCommand("heal").setExecutor(new Heal(this));
		getCommand("kill").setExecutor(new Kill(this));
		getCommand("speed").setExecutor(new Speed(this));
		getCommand("suicide").setExecutor(new Suicide(this));
		getCommand("sethunger").setExecutor(new SetHunger(this));
		getCommand("nick").setExecutor(new Nick(this));
		
		if(!file.exists()) {
			getLogger().info("Configuration Status: " + ChatColor.RED + "NON-EXISTANT");
			getLogger().info("Generating config.yml...");
			getLogger().info("Configuration file has been generated!");
			
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}
	
private static ItemStack reloadConfig, update;
	
	public static Inventory GUI = Bukkit.createInventory(null, 9, "EssentialsLite+ Control Panel");

	
	static
	{
		reloadConfig = item1("§f§lReload");
		update = item2("§f§lUpdate");
		
		GUI.setItem(3, reloadConfig);
		GUI.setItem(5, update);
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
	
	@EventHandler
	public void onPlayerInventoryClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (!e.getInventory().getName().equalsIgnoreCase(GUI.getName())) return;
		if (e.getCurrentItem().getItemMeta() == null) return;
		if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§f§lReload")){
			e.setCancelled(true);
            reloadConfig();
            p.closeInventory();
            p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "EssentialsLite+" + ChatColor.GRAY + "] " + ChatColor.GREEN + "EssentialsLitePlus has been reloaded from disk!");
		}
		if(e.getCurrentItem().getItemMeta().getDisplayName().contains("§f§lUpdate")) {
			e.setCancelled(true);
			p.closeInventory();
			Updater updater = new Updater(this, 90094, this.getFile(), UpdateType.DEFAULT, true);
			p.sendMessage(ChatColor.GREEN + "Please check console to see the information on updates.");
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
