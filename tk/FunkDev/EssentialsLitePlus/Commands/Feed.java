package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Feed implements CommandExecutor {

	private Core config;
    public Feed(Core config) {
    	this.config = config;
    }
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("feed")) {
			if(config.getConfig().getString("Commands.Feed.Enabled").equalsIgnoreCase("true")) {
				if(args.length == 0) {
					if(!(sender instanceof Player)) {
						sender.sendMessage(ChatColor.RED + "Only players can feed themselves!");
					}
					if(p.hasPermission("el+.cmd.feed")) {
						p.setFoodLevel(20);
						p.sendMessage(config.getConfig().getString("Commands.Feed.Messages.PlayerFed").replaceAll("&", "§"));
					} else {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "EssentialsLite+" + ChatColor.GRAY + "] " + config.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
					}
				} else {
					if(p.hasPermission("el+.cmd.feed.others")) {
						Bukkit.getPlayer(args[0]).setFoodLevel(20);
						Bukkit.getPlayer(args[0]).sendMessage(config.getConfig().getString("Commands.Feed.Messages.PlayerFed").replaceAll("&", "§"));
						p.sendMessage(ChatColor.GREEN + "You have successfully fed " + Bukkit.getPlayer(args[0]).getName() + "!");
					} else {
						p.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + ChatColor.BOLD + "EssentialsLite+" + ChatColor.GRAY + "] " + config.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
					}
				}
			} else {
				p.sendMessage(ChatColor.RED + "Command Disabled!");
			}
		}
		
		return false;
		
	}

}
