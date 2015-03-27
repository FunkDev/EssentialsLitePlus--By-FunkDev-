package me.funkemunky.Database;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Teleportation implements CommandExecutor {
	
	private Core core;
	public Teleportation(Core core) {
		this.core = core;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        
        if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This plugin is for players only!");
                return true;
        }
       
        Player p = (Player) sender;
        
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
                if(config.getConfig().getString("Commands.Spawns.Enabled").equalsIgnoreCase("true")) {
                	if(p.hasPermission("el+.cmd.setspawn")) {
                		core.getData().set("spawn.world", p.getLocation().getWorld().getName());
                        core.getData().set("spawn.x", p.getLocation().getX());
                        core.getWarps().set("spawn.y", p.getLocation().getY());
                        core.getWarps().set("spawn.z", p.getLocation().getZ());
                        core.getWarps().set("spawn.pitch", p.getLocation().getPitch());
                        core.getWarps().set("spawn.yaw", p.getLocation().getYaw());
                        core.saveWarps();
                        p.sendMessage(ChatColor.GREEN + "Spawn set!");
                        return true;
                	} else {
                		p.sendMessage(config.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
                	}
                } else {
                	p.sendMessage(ChatColor.RED + "This command is disabled!");
                }
        }
       
        if (cmd.getName().equalsIgnoreCase("spawn")) {
                if(config.getConfig().getString("Commands.Spawns.Enabled").equalsIgnoreCase("true")) {
                    if(p.hasPermission("el+.cmd.spawn")) {
                    	if (core.getWarps().getConfigurationSection("spawn") == null) {
                            p.sendMessage(core.getConfig().getString("Commands.Spawns.Messages.SpawnNotSet").replaceAll("&", "§"));
                       }
                       World w = Bukkit.getServer().getWorld(core.getWarps().getString("spawn.world"));
                       double y = core.getWarps().getDouble("spawn.y");
                       double x = core.getWarps().getDouble("spawn.x");
                       double z = core.getWarps().getDouble("spawn.z");
                       float pitch = (float) core.getWarps().getInt("spawn.pitch");
                       float yaw = (float) core.getWarps().getInt("spawn.yaw");
                       p.teleport(new Location(w, x, y, z, pitch, yaw));
                       p.sendMessage(core.getConfig().getString("Commands.Spawns.Messages.WelcomeToSpawn").replaceAll("&", "§"));
                    } else {
                		p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
                	}
                } else {
                	p.sendMessage(ChatColor.RED + "This command is disabled!");
                }
        }
       
        if (cmd.getName().equalsIgnoreCase("setwarp")) {
                if(core.getConfig().getString("Commands.Warps.Enabled").equalsIgnoreCase("true")) {
                	if(p.hasPermission("el+.cmd.setwarp")) {
                		if (args.length == 0) {
                            p.sendMessage(ChatColor.RED + "Please specify a name!");
                            return true;
                        }
                        core.getWarps().set("warps." + args[0] + ".world", p.getLocation().getWorld().getName());
                        core.getWarps().set("warps." + args[0] + ".x", p.getLocation().getX());
                        core.getWarps().set("warps." + args[0] + ".y", p.getLocation().getY());
                        core.getWarps().set("warps." + args[0] + ".z", p.getLocation().getZ());
                        core.getWarps().set("warps." + args[0] + ".pitch", p.getLocation().getPitch());
                        core.getWarps().set("warps." + args[0] + ".yaw", p.getLocation().getYaw());
                        core.getWarps().getStringList("WarpNames").add(args[0]);
                        core.saveWarps();
                        p.sendMessage(ChatColor.GREEN + "Set warp " + args[0] + "!");
                	} else {
                		p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
                	}
                } else {
                	p.sendMessage(ChatColor.RED + "This command is disabled!");
                }
        }
       
        if (cmd.getName().equalsIgnoreCase("warp")) {
                if(config.getConfig().getString("Commands.Warps.Enabled").equalsIgnoreCase("true")) {
                	if(p.hasPermission("el+.cmd.warp") && p.hasPermission("el+.cmd.warp." + args[0])) {
                		if (args.length == 0) {
                            p.sendMessage(ChatColor.AQUA + "Warps:");
                            p.sendMessage(core.getWarps().getStringList("WarpNames").toString());
                            return true;
                        }
                        if (core.getWarps().getConfigurationSection("warps." + args[0]) == null) {
                                p.sendMessage(ChatColor.RED + "Warp " + args[0] + " does not exist!");
                                return true;
                        }
                        World w = Bukkit.getServer().getWorld(core.getWarps().getString("warps." + args[0] + ".world"));
                        double x = core.getWarps().getDouble("warps." + args[0] + ".x");
                        double y = core.getWarps().getDouble("warps." + args[0] + ".y");
                        double z = core.getWarps().getDouble("warps." + args[0] + ".z");
                        float pitch = (float) core.getWarps().getInt("warps." + args[0] + ".pitch");
                        float yaw = (float) core.getWarps().getInt("warps." + args[0] + ".yaw");
                        p.teleport(new Location(w, x, y, z, pitch, yaw));
                        p.sendMessage(ChatColor.GREEN + "Teleported to " + args[0] + "!");
                	} else {
                		if(!p.hasPermission("el+.cmd.warp")) {
                			p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
                		}
                		if(!p.hasPermission("el+.cmd.warp." + args[0])) {
                			if (core.getWarps().getConfigurationSection("warps." + args[0]) == null) p.sendMessage(ChatColor.RED + "Sorry you don't have permission for that warp!");
                			p.sendMessage(ChatColor.RED + "Sorry you don't have permission for warp " + args[0] + "!");
                		}
                	}
                } else {
                	p.sendMessage(ChatColor.RED + "This command is disabled!");
                }
        }
       
        if (cmd.getName().equalsIgnoreCase("delwarp")) {
               if(config.getConfig().getString("Commands.Warps.Enabled").equalsIgnoreCase("true")) {
            	   if(p.hasPermission("el+.cmd.delwarp")) {
            		   if (args.length == 0) {
                           p.sendMessage(ChatColor.RED + "Please specify a name!");
                           return true;
                     }
                     if (core.getWarps().getConfigurationSection("warps." + args[0]) == null) {
                           p.sendMessage(ChatColor.RED + "Warp " + args[0] + " does not exist!");
                           return true;
                     }
                     core.getWarps().set("warps." + args[0], null);
                     core.saveWarps();
                     core.getWarps().getStringList("WarpNames").remove(args[0]);
                     p.sendMessage(ChatColor.GREEN + "Removed warp " + args[0] + "!");
            	   }
               } else {
            	   p.sendMessage(ChatColor.RED + "This command is disabled!");
               }
        }
        return true;
}

}
