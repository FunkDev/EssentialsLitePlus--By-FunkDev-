package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Invsee implements CommandExecutor {
	
	private Core core;
	public Invsee(Core core) {
		this.core = core;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("invsee")) {
			if(core.getConfig().getString("Commands.Invsee.Enabled").equalsIgnoreCase("true")) {
				if(player.hasPermission("el+.cmd.invsee")) {
					if(args.length == 0) {
						player.sendMessage(ChatColor.RED + "Please type in the player's name whom you wish to see.");
						player.sendMessage(ChatColor.RED + "Example: /invsee [playername]");
					}
					if(args.length == 1) {
						Player p = Bukkit.getServer().getPlayer(args[0]);
			            Inventory i = Bukkit.getServer().createInventory(null, p.getInventory().getSize(), "Invsee: " + p.getName());
			            
			            if(!p.isOnline()) p.sendMessage(ChatColor.RED + "The player " + args[0] + " is not online!");
			           
			            for (ItemStack item : p.getInventory()) {
			                    if (item != null) i.addItem(item);
			            }
			           
			            player.openInventory(i);
					}
				} else {
					player.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
				}
			} else {
			    player.sendMessage(ChatColor.RED + "This command is disabled!");
			}
		}
		
		return false;
	}

}
