package com.schiebros.gadgetcore.examples.sheep;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.schiebros.gadgetcore.exceptions.NotEnabledException;
import com.schiebros.gadgetcore.handlers.Gadget;

public class SheepGadgetImplementation implements Listener {

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Gadget g = Gadget.getGadgetById(SheepGadget.UNIQUE_ID);
		if (!(e.getPlayer().getItemInHand().getType().equals(g.getLabelItem().getType()) && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(g.getLabelItem().getItemMeta().getDisplayName())))
			return;
		if (!(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
			return;
		e.setCancelled(true);
		SheepGadget gadget = (SheepGadget) g;
		gadget.enable();
		try {
			gadget.use(gadget.getNewUser(p));
		} catch (NotEnabledException e1) {
			p.sendMessage("§cERROR!");
		}
		p.sendMessage("§6You used " + gadget.getDisplayName());
		g.disable();
	}

}
