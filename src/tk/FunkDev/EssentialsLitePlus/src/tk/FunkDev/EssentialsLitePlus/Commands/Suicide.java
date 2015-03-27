package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Suicide implements CommandExecutor {

	private Core config;
    public Suicide(Core config) {
    	this.config = config;
    }
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can commit suicide!");
		}
		
		if(cmd.getName().equalsIgnoreCase("suicide")) {
			if(config.getConfig().getString("Commands.Suicide.Enabled").equalsIgnoreCase("true")) {
				if(p.hasPermission("el+.cmd.suicide")) {
					p.setHealth(0);
					p.sendMessage(config.getConfig().getString("Commands.Suicide.Messages.SenderMessage").replaceAll("&", "§"));
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
