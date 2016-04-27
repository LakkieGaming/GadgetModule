package com.schiebros.gadgetcore.examples.firework;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import com.schiebros.gadgetcore.exceptions.NotEnabledException;
import com.schiebros.gadgetcore.exceptions.VariableNotChangableException;
import com.schiebros.gadgetcore.handlers.Gadget;
import com.schiebros.gadgetcore.handlers.GadgetUser;

// Start off creating a gadget by extending the gadget class
public class FireworkGadget extends Gadget {

	// All of this is used in the 'FireworkGadgetImplementation' class.
	
	// I put it in full capitals because it is a constant.
	public static final int UNIQUE_ID = new Random().nextInt(1000000);
	
	public FireworkGadget() {
		// When I refer to 'name', I mean this. Not the name of the label item's display name.
		super("Firework Gadget");
		// Here you can set the label item to an itemstack.
		ItemStack label = new ItemStack(Material.FIREWORK_CHARGE, 1);
		ItemMeta meta = label.getItemMeta();
		meta.setDisplayName("§6Firework Gadget");
		label.setItemMeta(meta);
		setLabelItem(label);
		// This is very nessecary. You could get a gadget by its name, but there might be other gadgets called that, so a random ID is the best way to go.
		// The try/catch is there because you cannot change the ID.
		try {
			setUniqueId(UNIQUE_ID);
		} catch (VariableNotChangableException e) {
			e.printStackTrace();
		}
	}
	
	// Override the use method, and this is what will be executed when the gadget is used.
	public void use(GadgetUser user) throws NotEnabledException {
		// Used to check if it is enabled on the server or not.
		// Do not add this if you do not want it to be disable-able.
		if (!isEnabled()) {
			throw new NotEnabledException("Tried to use " + getName() + " but failed!");
		} else {
			// This is how you get the player that is the user of the firework.
			// It is similar to an event.
			Player p = user.getPlayer();
			Random r = new Random();
			
			// This is all just code to give the firework random properties.
			Firework f = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
			FireworkMeta meta = f.getFireworkMeta();
			Builder b = FireworkEffect.builder();
			b.flicker(r.nextBoolean());
			b.withColor(Color.fromRGB(r.nextInt(255), r.nextInt(255), r.nextInt(255)), Color.fromRGB(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			b.withFade(Color.fromRGB(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			Type[] allTypes = {Type.BALL, Type.BALL_LARGE, Type.BURST, Type.CREEPER, Type.STAR};
			b.with(allTypes[r.nextInt(allTypes.length)]);
			if (r.nextBoolean()) {
				b.withFlicker();
			}
			meta.addEffect(b.build());
			f.setFireworkMeta(meta);
		}
	}

}
