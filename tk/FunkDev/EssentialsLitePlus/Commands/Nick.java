package tk.FunkDev.EssentialsLitePlus.Commands;

import org.bukkit.command.CommandExecutor;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import tk.FunkDev.EssentialsLitePlus.Core;

public class Nick implements CommandExecutor, Listener {
	
	private Core core;
	public Nick(Core core) {
		this.core = core;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        
        if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can get nicknames!");
                return true;
        }
       
        Player p = (Player) sender;
       
        if (cmd.getName().equalsIgnoreCase("nick")) {
               
            if(core.getConfig().getString("Commands.Nick.Enabled").equalsIgnoreCase("true")) {
            	if(p.hasPermission("el+.cmd.nick")) {
                	if (args.length == 0) {
                        p.sendMessage(ChatColor.RED + "You no longer have a nickname!");
                        core.getConfig().set(p.getName(), p.getName());
                        core.saveConfig();
                        return true;
                }
               
                String nick = "";
                for (String arg : args) {
                        nick += arg + " ";
                }
               
                nick = nick.substring(0, nick.length() - 1);
               
                nick = nick.replaceAll("&", "§");
               
                p.sendMessage(ChatColor.GREEN + "You have changed your nickname to " + nick);
                core.getConfig().set(p.getName(), "*" + nick);
                core.saveConfig();
                
                } else {
                	p.sendMessage(core.getConfig().getString("NoPermMsg").replaceAll("&", "§"));
                }
            } else {
            	p.sendMessage(ChatColor.RED + "This command is disabled!");
            }
        	
        }
        return true;
      }

        @EventHandler
        public void onPlayerChat(AsyncPlayerChatEvent e) {
        	Player p = e.getPlayer();
               if (core.getConfig().getString(e.getPlayer().getName()) != null) {
            	   e.getPlayer().setDisplayName(core.getConfig().getString(p.getName()));
                       if(core.getConfig().getString(p.getName() + ".ChatColor") !=null) {
                    	   e.getPlayer().setDisplayName(core.getConfig().getString(p.getName()) + core.getConfig().getString(p.getName() + ".ChatColor"));
                       }
               }
        }

}
