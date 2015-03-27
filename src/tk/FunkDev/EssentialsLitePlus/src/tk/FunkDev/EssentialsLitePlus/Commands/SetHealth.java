package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class SetHealth implements CommandExecutor {

	private Core core;
	public SetHealth(Core core) {
		this.core = core;
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can set thier health levels!");
		}
		
		if(cmd.getName().equalsIgnoreCase("sethealth")) {
			Player p = (Player) sender;
			if(core.getConfig().getString("Commands.SetHealth.Enabled").equalsIgnoreCase("true")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "Too little arguments. Do /sethealth [0-20] [player]");
					} 
						if(p.hasPermission("el+.cmd.sethunger")) {
							int hl = Integer.parseInt(args[0]);
								p.setHealth(hl);
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
