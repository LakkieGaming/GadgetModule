package com.schiebros.gadgetcore.handlers;

import org.bukkit.entity.Player;

import com.schiebros.gadgetcore.exceptions.NotEnabledException;

import me.exerosis.jager.gameengine.core.component.Enableable;

public abstract class GadgetUser extends Gadget implements Enableable {

	private Player p;
	
	/**
	 * Do not instantiate this(create instance of).
	 */
	public GadgetUser(Player p, String gadgetName) {
		super(gadgetName);
		this.p = p;
	}
	
	public Gadget getGadget() {
		return super.getGadget();
	}

	public Player getPlayer() {
		return p;
	}

	public void setPlayer(Player p) {
		this.p = p;
	}

	public void use(GadgetUser user) throws NotEnabledException {
		this.use(user);
	}

}
