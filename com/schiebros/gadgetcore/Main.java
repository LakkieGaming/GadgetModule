package com.schiebros.gadgetcore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.schiebros.gadgetcore.examples.firework.FireworkGadget;
import com.schiebros.gadgetcore.examples.firework.FireworkGadgetImplementation;
import com.schiebros.gadgetcore.examples.sheep.SheepGadget;
import com.schiebros.gadgetcore.examples.sheep.SheepGadgetImplementation;
import com.schiebros.gadgetcore.handlers.Gadget;

public class Main extends JavaPlugin {

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(new FireworkGadgetImplementation(), this);
		Bukkit.getPluginManager().registerEvents(new SheepGadgetImplementation(), this);
		// This is important. This will not work if you don't do this.
		new FireworkGadget();
		SheepGadget.plugin = this;
		new SheepGadget();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (label.equalsIgnoreCase("firework")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cNope.");
				return false;
			}
			Player p = (Player) sender;
			if (args.length != 0) {
				p.sendMessage("§cInvalid arguments.");
				return false;
			} else {
				Gadget g = Gadget.getGadgetById(FireworkGadget.UNIQUE_ID);
				p.getInventory().addItem(g.getLabelItem());
				p.sendMessage("§aYou now have the " + g.getDisplayName() + "!");
				return true;
			}
		}
		
		if (label.equalsIgnoreCase("sheep")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§cNope.");
				return false;
			}
			Player p = (Player) sender;
			if (args.length != 0) {
				p.sendMessage("§cInvalid arguments.");
				return false;
			} else {
				Gadget g = Gadget.getGadgetById(SheepGadget.UNIQUE_ID);
				p.getInventory().addItem(g.getLabelItem());
				p.sendMessage("§aYou now have the " + g.getDisplayName() + "!");
				return true;
			}
		}
		
		return false;
	}
	
}
