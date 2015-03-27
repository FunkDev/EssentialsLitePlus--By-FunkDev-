package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Heal implements CommandExecutor {
	
	private Core core;
    public Heal(Core config) {
    	this.core = config;
    }
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("heal")) {
			if(core.getConfig().getString("Commands.Heal.Enabled").equalsIgnoreCase("true")) {
                if(args.length == 0) {
                	if(!(sender instanceof Player)) {
            			sender.sendMessage(ChatColor.RED + "Only players can heal themselves!");
            		}
                	if(p.hasPermission("el+.cmd.heal")) {
                		p.setHealth(20);
                    	p.sendMessage(core.getConfig().getString("Commands.Heal.Messages.PlayerHealed").replaceAll("&", "§"));
                	} else {
                		p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
                	}
                }
                if(args.length == 1) {
                	if(p.hasPermission("el+.cmd.heal.others")) {
                		Player target = Bukkit.getPlayer(args[0]);
                    	p.sendMessage(ChatColor.GREEN + "You have successfully healed " + args[0] + "!");
                    	target.setHealth(20);
                    	target.sendMessage(core.getConfig().getString("Commands.Heal.Messages.OtherPlayerHealed").replaceAll("&", "§").replaceAll("%PlayerName%", sender.getName()));
                    	
                    	if(!target.isOnline()) {
                    		p.sendMessage(ChatColor.RED + "Player " + args[0] + " is not online!");
                    	}
                	} else {
                		p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
                	}
                }
			} else {
				p.sendMessage(ChatColor.RED + "This command is disabled!");
			}
		}
		
		return false;
		
	}

}
