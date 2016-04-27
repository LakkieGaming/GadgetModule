package com.schiebros.gadgetcore.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.schiebros.gadgetcore.exceptions.NotEnabledException;
import com.schiebros.gadgetcore.exceptions.VariableNotChangableException;

import me.exerosis.jager.gameengine.core.component.Component;

public abstract class Gadget extends Component {

	protected List<GadgetUser> users = new ArrayList<GadgetUser>();
	private static List<Gadget> allGadgets = new ArrayList<Gadget>();
	protected boolean enabled = false;
	private int uniqueId;
	private boolean idChanged = false;
	private ItemStack labelItem;
	private String name;

	public Gadget(String name) {
		this.enable();
		this.setName(name);
		allGadgets.add(this);
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.getInventory().contains(getLabelItem());
			p.getInventory().remove(getLabelItem());
		}
	}

	public Gadget(int uniqueId, String name) {
		try {
			this.setUniqueId(uniqueId);
		} catch (VariableNotChangableException e) {
			e.printStackTrace();
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.getInventory().contains(getLabelItem());
			p.getInventory().remove(getLabelItem());
		}
		this.enable();
		this.setName(name);
		allGadgets.add(this);
	}

	public Gadget(int uniqueId, ItemStack labelItem, String name) {
		try {
			this.setUniqueId(uniqueId);
		} catch (VariableNotChangableException e) {
			e.printStackTrace();
		}
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.getInventory().contains(getLabelItem());
			p.getInventory().remove(getLabelItem());
		}
		this.enable();
		this.setLabelItem(labelItem);
		this.setName(name);
		allGadgets.add(this);
	}

	public static List<Gadget> getAllGadgets() {
		return allGadgets;
	}

	public static Gadget getGadgetById(int uniqueId) {
		for (Gadget g : Gadget.getAllGadgets()) {
			if (g.getUniqueId() == uniqueId) {
				return g;
			}
		}
		return null;
	}

	@Deprecated
	public static Gadget getGadgetByName(String name) {
		for (Gadget g : Gadget.getAllGadgets()) {
			if (g.getName().equals(name)) {
				return g;
			}
		}
		return null;
	}

	public void enable() {
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}

	public boolean isEnabled() {
		return enabled;
	}

	protected Gadget getGadget() {
		return this;
	}

	public void addUser(GadgetUser user) {
		this.users.add(user);
	}

	public void removeUser(GadgetUser user) {
		this.users.remove(user);
	}

	public List<GadgetUser> getAllUsers() {
		return this.users;
	}

	public void setAllUsers(List<GadgetUser> users) {
		this.users = users;
	}

	public int getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(int uniqueId) throws VariableNotChangableException {
		if (this.idChanged) {
			throw new VariableNotChangableException("Tried to change the ");
		}
		this.uniqueId = uniqueId;
		this.idChanged = true;
	}

	public abstract void use(GadgetUser user) throws NotEnabledException;

	public ItemStack getLabelItem() {
		return labelItem;
	}

	public void setLabelItem(ItemStack labelItem) {
		this.labelItem = labelItem;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDisplayName() {
		return this.getLabelItem().getItemMeta().getDisplayName();
	}

	public GadgetUser getNewUser(Player p) {
		GadgetUser user = new GadgetUser(p, name) {
			public void use(GadgetUser user) {
				this.use(user);
			}
		};
		return user;
	}

	public void registerNewUser(Player p) {
		GadgetUser user = new GadgetUser(p, name) {
			public void use(GadgetUser user) {
				this.use(user);
			}
		};
		this.addUser(user);
	}

}
