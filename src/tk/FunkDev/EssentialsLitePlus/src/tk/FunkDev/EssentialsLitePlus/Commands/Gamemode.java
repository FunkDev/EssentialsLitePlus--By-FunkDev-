package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Gamemode implements CommandExecutor {

	private Core core;
	public Gamemode(Core core) {
		this.core = core;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		
		if(core.getConfig().getString("Commands.Gamemode.Enabled").equalsIgnoreCase("true")) {
			if(cmd.getName().equalsIgnoreCase("gamemode")) {
				if(args.length == 0) {
					if(p.hasPermission("el+.cmd.gamemode")) {
						p.sendMessage(ChatColor.DARK_RED + "Error:" + ChatColor.RESET + "Too little arguments.");
						p.sendMessage("Command Example: /[gamemode:gm] [0/s,1/c,2/a]");
					} else {
						p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
					}
				} else {
					if(args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("0")) {
						if(p.hasPermission("el+.cmd.gamemode.survival")) {
							p.setGameMode(GameMode.SURVIVAL);
							Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
								public void run() {
									p.sendMessage(core.getConfig().getString("Commands.Gamemode.Messages.SetGamemode").replaceAll("&", "§").replaceAll("%Gamemode%", p.getGameMode().name().toLowerCase()));
								}
							}, 2L);
						} else {
							p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
						}
					}
					if(args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase("1")) {
						if(p.hasPermission("el+.cmd.gamemode.creative")) {
							p.setGameMode(GameMode.CREATIVE);
							Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
								public void run() {
									p.sendMessage(core.getConfig().getString("Commands.Gamemode.Messages.SetGamemode").replaceAll("&", "§").replaceAll("%Gamemode%", p.getGameMode().name().toLowerCase()));
								}
							}, 2L);
						} else {
							p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
						}
					}
					if(args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("2")) {
						if(p.hasPermission("el+.cmd.gamemode.adventure")) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
								public void run() {
									p.sendMessage(core.getConfig().getString("Commands.Gamemode.Messages.SetGamemode").replaceAll("&", "§").replaceAll("%Gamemode%", p.getGameMode().name().toLowerCase()));
								}
							}, 2L);
							p.setGameMode(GameMode.ADVENTURE);
						} else {
							p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
						}
					}
				}
			}
			if(cmd.getName().equalsIgnoreCase("survival")) {
				if(p.hasPermission("el+.cmd.gamemode.survival")) {
					p.setGameMode(GameMode.SURVIVAL);
					Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						public void run() {
							p.sendMessage(core.getConfig().getString("Commands.Gamemode.Messages.SetGamemode").replaceAll("&", "§").replaceAll("%Gamemode%", p.getGameMode().name().toLowerCase()));
						}
					}, 2L);
				} else {
					p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
				}
			}
			if(cmd.getName().equalsIgnoreCase("creative")) {
				if(p.hasPermission("el+.cmd.gamemode.creative")) {
					p.setGameMode(GameMode.CREATIVE);
					Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						public void run() {
							p.sendMessage(core.getConfig().getString("Commands.Gamemode.Messages.SetGamemode").replaceAll("&", "§").replaceAll("%Gamemode%", p.getGameMode().name().toLowerCase()));
						}
					}, 2L);
				} else {
					p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
				}
			}
			if(cmd.getName().equalsIgnoreCase("adventure")) {
				if(p.hasPermission("el+.cmd.gamemode.adventure")) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(core, new Runnable() {
						public void run() {
							p.sendMessage(core.getConfig().getString("Commands.Gamemode.Messages.SetGamemode").replaceAll("&", "§").replaceAll("%Gamemode%", p.getGameMode().name().toLowerCase()));
						}
					}, 2L);
					p.setGameMode(GameMode.ADVENTURE);
				} else {
					p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
				}
			}
		}
		
		return false;
	}

}
