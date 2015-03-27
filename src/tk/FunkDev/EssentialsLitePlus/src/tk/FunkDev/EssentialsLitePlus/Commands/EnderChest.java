package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;
public class EnderChest implements CommandExecutor {
	
	private Core core;
	public EnderChest(Core core) {
		this.core = core;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("enderchest")) {
			if(core.getConfig().getString("Commands.EnderChest.Enabled").equalsIgnoreCase("true")) {
				if(p.hasPermission("el+.cmd.enderchest")) {
					if(args.length == 0) {
						p.openInventory(p.getEnderChest());
					}
					if(args.length == 1) {
						p.openInventory(Bukkit.getPlayer(args[0]).getEnderChest());
					}
				} else {
					p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
				}
			} else {
				p.sendMessage(ChatColor.RED + "This command is disabled!");
			}
		}
		
		return false;
	}

}
