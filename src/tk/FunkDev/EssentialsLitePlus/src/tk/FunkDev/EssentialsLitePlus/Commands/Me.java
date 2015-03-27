package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Me implements CommandExecutor {

	private Core core;
	public Me(Core core) {
		this.core = core;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("me")) {
			
			if(core.getConfig().getString("Commands.Me.Enabled").equalsIgnoreCase("true")) {
				if(p.hasPermission("el+.cmd.me")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "You forgot to type a fake action! Ex: /me [message] ");
					}
					
					if(args.length > 0) {
						String me = "";
			            for (String arg : args) {
			                  me += arg + " ";
			            }
			             
			            me = me.substring(0, me.length() - 1);
			            
			            Bukkit.broadcastMessage(core.getConfig().getString("Commands.Me.Messages.MeMessage").replaceAll("%PlayerName%", p.getName()).replaceAll("%MeMessage%", me));
					}
				}  else {
					p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
				}
			} else {
				p.sendMessage(ChatColor.RED + "This command is disabled!");
			}
		
		}
		
		return false;
	}

}
