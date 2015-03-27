package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Fly implements CommandExecutor {
	
	private Core config;
    public Fly(Core config) {
    	this.config = config;
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can set thier allowance of flight!");
		}
		
		if(cmd.getName().equalsIgnoreCase("fly")) {
			if(config.getConfig().getString("Commands.Fly.Enabled").equalsIgnoreCase("true")) {
				if(p.hasPermission("el+.cmd.fly")) {
					if(p.getAllowFlight()) {
						p.setAllowFlight(false);
						p.setFlying(false);
						p.sendMessage(config.getConfig().getString("Commands.Fly.Messages.FlyOff").replaceAll("&", "§"));
					} else {
						p.setAllowFlight(true);
						p.setFlying(true);
						p.sendMessage(config.getConfig().getString("Commands.Fly.Messages.FlyOn").replaceAll("&", "§"));
					}
				} else {
					p.sendMessage(config.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
				}
 			} else {
 				p.sendMessage(ChatColor.RED + "Command disabled!");
 			}
		}
		
		return false;
	}

}
