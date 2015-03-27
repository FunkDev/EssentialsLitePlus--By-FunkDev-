package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Speed implements CommandExecutor {

	private Core config;
    public Speed(Core config) {
    	this.config = config;
    }
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can set thier speed!");
		}
		if(cmd.getName().equalsIgnoreCase("speed")) {
			if(config.getConfig().getString("Commands.Speed.Enabled").equalsIgnoreCase("true")) {
				if(p.hasPermission("el+.cmd.speed")) {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "Please add an integer of speed you wish to have.");
					} else {
						if(args[0].equalsIgnoreCase("0")) {
							float speed = 0f;
							if(p.isFlying()) {
								p.setFlySpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.FlySpeed").replaceAll("&", "§").replaceAll("%FlySpeed%", args[0]));
							}
							if(!p.isFlying()) {
								p.setWalkSpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.WalkSpeed").replaceAll("&", "§").replaceAll("%WalkSpeed%", args[0]));
							}
						}
						if(args[0].equalsIgnoreCase("1")) {
							float speed = 0.2f;
							if(p.isFlying()) {
								p.setFlySpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.FlySpeed").replaceAll("&", "§").replaceAll("%FlySpeed%", args[0]));
							}
							if(!p.isFlying()) {
								p.setWalkSpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.WalkSpeed").replaceAll("&", "§").replaceAll("%WalkSpeed%", args[0]));
							}
						}
						if(args[0].equalsIgnoreCase("2")) {
							float speed = 0.4f;
							if(p.isFlying()) {
								p.setFlySpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.FlySpeed").replaceAll("&", "§").replaceAll("%FlySpeed%", args[0]));
							}
							if(!p.isFlying()) {
								p.setWalkSpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.WalkSpeed").replaceAll("&", "§").replaceAll("%WalkSpeed%", args[0]));
							}
						}
						if(args[0].equalsIgnoreCase("3")) {
							float speed = 0.6f;
							if(p.isFlying()) {
								p.setFlySpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.FlySpeed").replaceAll("&", "§").replaceAll("%FlySpeed%", args[0]));
							}
							if(!p.isFlying()) {
								p.setWalkSpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.WalkSpeed").replaceAll("&", "§").replaceAll("%WalkSpeed%", args[0]));
							}
						}
						if(args[0].equalsIgnoreCase("4")) {
							float speed = 0.8f;
							if(p.isFlying()) {
								p.setFlySpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.FlySpeed").replaceAll("&", "§").replaceAll("%FlySpeed%", args[0]));
							}
							if(!p.isFlying()) {
								p.setWalkSpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.WalkSpeed").replaceAll("&", "§").replaceAll("%WalkSpeed%", args[0]));
							}
						}
						if(args[0].equalsIgnoreCase("5")) {
							float speed = 1f;
							if(p.isFlying()) {
								p.setFlySpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.FlySpeed").replaceAll("&", "§").replaceAll("%FlySpeed%", args[0]));
							}
							if(!p.isFlying()) {
								p.setWalkSpeed(speed);
								p.sendMessage(config.getConfig().getString("Commands.Speed.Messages.WalkSpeed").replaceAll("&", "§").replaceAll("%WalkSpeed%", args[0]));
							}
						}
					}
					} else {
						p.sendMessage(config.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
					}
				} else {
				p.sendMessage(ChatColor.RED + "Command Disabled!");
			}
		}
		
		return false;
		
	}

}
