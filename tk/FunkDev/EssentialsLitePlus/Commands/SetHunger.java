package tk.FunkDev.EssentialsLitePlus.Commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class SetHunger implements CommandExecutor {
	
	 HashMap<String, Integer>test = new HashMap<String, Integer>();
	
	private Core core;
	public SetHunger(Core core) {
		this.core = core;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can set thier hunger levels!");
		}
		
		if(cmd.getName().equalsIgnoreCase("sethunger")) {
			Player p = (Player) sender;
			if(core.getConfig().getString("Commands.SetHunger.Enabled").equalsIgnoreCase("true")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "Too little arguments. Do /sethunger [0-20] [player]");
					} else {
						if(args.length == 1) {
							if(p.hasPermission("el+.cmd.sethunger")) {
								int fl = Integer.parseInt(args[0]);
								p.setFoodLevel(fl);
							} else {
								p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
							}
						} 
						if(args.length == 2) {
							if(p.hasPermission("el+.cmd.sethunger.others")) {
								Player target = Bukkit.getPlayer(args[0]);
								int fl = Integer.parseInt(args[0]);
								target.setFoodLevel(fl);
							} else {
								p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
							}
					    }
					}
			} else {
				p.sendMessage(ChatColor.RED + "This command is disabled!");
			}
		}
		
		return false;
	}
}
