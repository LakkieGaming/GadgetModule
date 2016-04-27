package com.schiebros.gadgetcore.examples.sheep;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.schiebros.gadgetcore.Main;
import com.schiebros.gadgetcore.exceptions.NotEnabledException;
import com.schiebros.gadgetcore.exceptions.VariableNotChangableException;
import com.schiebros.gadgetcore.handlers.Gadget;
import com.schiebros.gadgetcore.handlers.GadgetUser;

public class SheepGadget extends Gadget {

	public static final int UNIQUE_ID = new Random().nextInt(1000000);

	public static Main plugin;
	
	public SheepGadget() {
		super("Sheep Gadget");
		@SuppressWarnings("deprecation")
		ItemStack label = new ItemStack(Material.getMaterial(383), 1);
		label.setDurability((short) 91);
		ItemMeta meta = label.getItemMeta();
		meta.setDisplayName("§6Sheep Gadget");
		label.setItemMeta(meta);
		setLabelItem(label);
		try {
			setUniqueId(UNIQUE_ID);
		} catch (VariableNotChangableException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public void use(GadgetUser user) throws NotEnabledException {
		if (!user.isEnabled()) {
			throw new NotEnabledException("Tried to use " + getName() + " but failed!");
		}
		Player p = user.getPlayer();
		Sheep s = (Sheep) p.getWorld().spawnEntity(p.getLocation(), EntityType.SHEEP);
		s.teleport(p.getLocation());
		s.setVelocity(p.getLocation().getDirection().multiply(4));
		s.setColor(DyeColor.PINK);
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new BukkitRunnable() {
			public void run() {
				s.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20, 1, true));
				s.setHealth(0);
			}
		}, 15l);
	}

}
