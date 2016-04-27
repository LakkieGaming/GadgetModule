package com.schiebros.gadgetcore.examples.firework;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.schiebros.gadgetcore.exceptions.NotEnabledException;
import com.schiebros.gadgetcore.handlers.Gadget;
import com.schiebros.gadgetcore.handlers.GadgetUser;

public class FireworkGadgetImplementation implements Listener {

	@EventHandler
	public void onItemInteract(PlayerInteractEvent e) {
		Gadget fireworkGadget = Gadget.getGadgetById(FireworkGadget.UNIQUE_ID);
		Player p = e.getPlayer();
		if (e.getPlayer().getItemInHand().getType().equals(fireworkGadget.getLabelItem().getType()) && e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(fireworkGadget.getLabelItem().getItemMeta().getDisplayName())) {
			GadgetUser user = fireworkGadget.getNewUser(p);
			fireworkGadget.enable();
			try {
				fireworkGadget.use(user);
			} catch (NotEnabledException e1) {
				p.sendMessage("§cYou do not have permission to use " + fireworkGadget.getDisplayName());
			}
			fireworkGadget.disable();
		}
	}

}
