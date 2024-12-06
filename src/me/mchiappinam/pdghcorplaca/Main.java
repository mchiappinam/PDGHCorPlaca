package me.mchiappinam.pdghcorplaca;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
		File file = new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				saveResource("config_template.yml",false);
				File file2 = new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			}
			catch(Exception e) {}
		}
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getConsoleSender().sendMessage("§3[PDGHCorPlaca] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHCorPlaca] §2Acesse: http://pdgh.net/");
    }
    @Override
    public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHCorPlaca] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHCorPlaca] §2Acesse: http://pdgh.net/");
    }

	@EventHandler(priority = EventPriority.HIGH)
	public void onSignChange(SignChangeEvent e) {
		if((e.getLine(0).toLowerCase().contains("pdgh"))||(e.getLine(0).toLowerCase().equalsIgnoreCase("pdgh")))
			if(!e.getPlayer().hasPermission("pdgh.op")) {
				e.getPlayer().sendMessage("§a[PDGH] §fImpossível criar shop!");
				e.getBlock().breakNaturally();
				return;
			}
		if (e.getPlayer().hasPermission("pdgh.vip")) {
			e.setLine(0, e.getLine(0).replaceAll("&([1-9A-Fa-fMmNnLlKkOo])", "\u00A7$1"));
			e.setLine(1, e.getLine(1).replaceAll("&([1-9A-Fa-fMmNnLlKkOo])", "\u00A7$1"));
			e.setLine(2, e.getLine(2).replaceAll("&([1-9A-Fa-fMmNnLlKkOo])", "\u00A7$1"));
			e.setLine(3, e.getLine(3).replaceAll("&([1-9A-Fa-fMmNnLlKkOo])", "\u00A7$1"));
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Mensagens.VIP")));
		}else{
			e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("Mensagens.NVIP")));
		}
	}
}