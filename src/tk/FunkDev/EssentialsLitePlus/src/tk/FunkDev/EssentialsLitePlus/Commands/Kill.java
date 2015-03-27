package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Kill implements CommandExecutor {

	private Core config;
    public Kill(Core config) {
    	this.config = config;
    }
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("kill")) {
			if(config.getConfig().getString("Commands.Kill.Enabled").equalsIgnoreCase("true")) {
				if(p.hasPermission("el+.cmd.kill")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RESET + "Please specify a player.");
					} else {
						if(args.length == 1) {
						   if(!Bukkit.getServer().getPlayer(args[0]).isOnline()) {
							   p.sendMessage(ChatColor.DARK_RED + "Error: " + ChatColor.RESET + "Player " + args[0] + " is not online!");
						   } else {
							   Bukkit.getPlayer(args[0]).setHealth(0);
							   Bukkit.getPlayer(args[0]).sendMessage(config.getConfig().getString("Commands.Kill.Messages.PlayerNotificationMsg").replaceAll("&", "§").replaceAll("%PlayerSenderName%", p.getName()).replaceAll("%PlayerKilledName%", args[0]));
							   p.sendMessage(config.getConfig().getString("Commands.Kill.Messages.SenderMsg").replaceAll("&", "§").replaceAll("%PlayerSenderName%", p.getName()).replaceAll("%PlayerKilledName%", args[0]));
						   }
						}
					}
				} else {
					p.sendMessage(config.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
				}
			} else {
				p.sendMessage(ChatColor.RED + "This command is disabled!");
			}
		}
		return false;
		
	}
}
