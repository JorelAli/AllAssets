package io.github.skepter.allassets.libs;

import io.github.skepter.allassets.libs.ReflectionUtilsDarkBlade2.PackageType;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/** @author fillpant, Blame him! */
public class DisguiseLib {

	private static final String bukkitversion = Bukkit.getServer().getClass().getPackage().getName().substring(23);
	private String customName;
	private EntityDisguise type;
	private final Player disguised;
	private ItemStack hand, helm, chst, leg, boot;

	public DisguiseLib(final Player p) {
		this(p, null);
	}

	/** @param p player to disguise
	 * @param type Entity type of disguise */
	public DisguiseLib(final Player p, final EntityDisguise type) {
		this(p, type, null);
	}

	/** @param p player to disguise
	 * @param type Entity type of disguise
	 * @param name the display name of the disguised player (chat color is
	 * supported) */
	public DisguiseLib(final Player p, final EntityDisguise type, final String name) {
		this(p, type, name, null, null, null, null, null);
	}

	/** @param p player to disguise
	 * @param type Entity type of disguise
	 * @param name the display name of the disguised player (chat color is
	 * supported)
	 * @param inhand Item in hand
	 * @param helmet helmet item
	 * @param chestplate chestplate armor item
	 * @param leggings leggings armor item
	 * @param boots boots armor item <b>If You dont want a armor item like boots
	 * or something, provide 'null'</b> */
	public DisguiseLib(final Player p, final EntityDisguise type, final String name, final ItemStack inhand, final ItemStack helmet, final ItemStack chestplate, final ItemStack leggings, final ItemStack boots) {
		this.customName = name;
		this.type = type;
		this.disguised = p;
		this.hand = inhand;
		this.helm = helmet;
		this.chst = chestplate;
		this.leg = leggings;
		this.boot = boots;
	}

	/** @param to Player that will see the disguise (where the packets will be
	 * sent to.)
	 * @throws Exception Many exceptions can occur due to reflection used. */
	public void sendDisguise(final Player to) throws Exception {
		if (to.equals(disguised))
			throw new IllegalArgumentException("Target Player cannot be the same as the disguised player");
		final Object packetplayoutentitydestroy = ReflectionUtilsDarkBlade2.instantiateObject("PacketPlayOutEntityDestroy", PackageType.MINECRAFT_SERVER, new int[] { disguised.getEntityId() });
		final Object world = ReflectionUtilsDarkBlade2.invokeMethod(disguised.getWorld(), "getHandle", (Object[]) null);
		final Class<?> entity = Class.forName(type.getClassName());
		final Object ent = ReflectionUtilsDarkBlade2.instantiateObject(entity, world);
		ReflectionUtilsDarkBlade2.invokeMethod(ent, "setPosition", disguised.getLocation().getX(), disguised.getLocation().getY(), disguised.getLocation().getZ());
		ReflectionUtilsDarkBlade2.getMethod(entity, "d", int.class).invoke(ent, disguised.getEntityId());
		if (customName != null) {
			ReflectionUtilsDarkBlade2.getMethod(entity, "setCustomName", String.class).invoke(ent, customName);
			ReflectionUtilsDarkBlade2.getMethod(entity, "setCustomNameVisible", boolean.class).invoke(ent, true);
		}
		handleSpecialTypes(type, ent);
		final Object packetplayoutspawnentityliving = ReflectionUtilsDarkBlade2.instantiateObject("PacketPlayOutSpawnEntityLiving", PackageType.MINECRAFT_SERVER, ent);

		sendPacket(to, packetplayoutentitydestroy);
		sendPacket(to, packetplayoutspawnentityliving);
		if (hand != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 0, hand);
		if (helm != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 1, helm);
		if (chst != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 2, chst);
		if (leg != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 3, leg);
		if (boot != null)
			sendArmorContentPackets(to, disguised.getEntityId(), 4, boot);

	}

	/** @param players players that will see the disguise happening. The rest will
	 * see the disguised player as player... */
	public void sendDisguise(final Player... players) {
		for (final Player P : players) {
			if (P.equals(disguised))
				continue;
			try {
				sendDisguise(P);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** @param players players that will see the disguise happening. The rest will
	 * see the disguised player as player... */
	public void sendDisguise(final Collection<? extends Player> players) {
		for (final Player P : players) {
			if (P.equals(disguised))
				continue;
			try {
				sendDisguise(P);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	/** @param forwho who will see this update?
	 * @throws Exception Reflection exceptions */
	public void updateDisguise(final Player forwho) throws Exception {
		sendDisguise(forwho);
	}

	/** @param players a array of players that will see the update */
	public void updateDisguise(final Player... players) {
		sendDisguise(players);
	}

	/** @param type the new Disguise type
	 * @param sendto the player who will see the change
	 * @throws Exception Reflection exceptions */
	public void changePlayerDisguise(final EntityDisguise type, final Player sendto) throws Exception {
		this.type = type;
		sendDisguise(sendto);
	}

	/*
	 * @param type the new Disguise type
	 *
	 * @param sendto the player who will see the change
	 *
	 * @throws Exception Reflection exceptions
	 */
	public void changePlayerDisguise(final EntityDisguise type, final Player... sendto) throws Exception {
		this.type = type;
		sendDisguise(sendto);
	}

	//Dont mind this
	private void sendPacket(final Player p, final Object pack) throws ReflectiveOperationException {
		final Class<?> packet = Class.forName("net.minecraft.server." + bukkitversion + ".Packet");
		final Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + bukkitversion + ".entity.CraftPlayer");
		final Object handle = craftPlayer.getMethod("getHandle").invoke(p);
		final Object con = handle.getClass().getField("playerConnection").get(handle);
		con.getClass().getMethod("sendPacket", packet).invoke(con, pack);
	}

	//Dont mind this too.
	private void sendArmorContentPackets(final Player to, final int entityID, final int slot, final ItemStack item) throws ReflectiveOperationException {
		PackageType type;
		if (bukkitversion.startsWith("v1_7_"))
			type = PackageType.CRAFTBUKKIT;
		else
			type = PackageType.CRAFTBUKKIT_INVENTORY;
		final Object craftitmstk = ReflectionUtilsDarkBlade2.getMethod("CraftItemStack", type, "asNMSCopy", item.getClass()).invoke(null, item);
		final Object metadarapacket = ReflectionUtilsDarkBlade2.instantiateObject("PacketPlayOutEntityEquipment", PackageType.MINECRAFT_SERVER, entityID, slot, craftitmstk);
		sendPacket(to, metadarapacket);
	}

	//Forget this as well :3
	private Object handleSpecialTypes(final EntityDisguise type, final Object entity) throws ReflectiveOperationException {
		switch (type) {
			case WITHER_SKELETON:
				ReflectionUtilsDarkBlade2.invokeMethod(entity, "setSkeletonType", 1);
				break;
			default:
				break;
		}
		return entity;
	}

	public void removeDisguise() throws ReflectiveOperationException {
		final Object ppoed = ReflectionUtilsDarkBlade2.instantiateObject("PacketPlayOutEntityDestroy", PackageType.MINECRAFT_SERVER, new int[] { disguised.getEntityId() });
		final Object ppones = ReflectionUtilsDarkBlade2.instantiateObject("PacketPlayOutNamedEntitySpawn", PackageType.MINECRAFT_SERVER, ReflectionUtilsDarkBlade2.invokeMethod(disguised, "getHandle", (Object[]) null));
		for (final Player p : Bukkit.getOnlinePlayers()) {
			if (p.equals(disguised))
				continue;
			sendPacket(p, ppoed);
			sendPacket(p, ppones);
		}
	}

	/*To be documented,*/
	public ItemStack getItemInHand() {
		return hand;
	}

	public void setItemInHand(final ItemStack hand) {
		this.hand = hand;
	}

	public ItemStack getHelmet() {
		return helm;
	}

	public void setHelmet(final ItemStack helm) {
		this.helm = helm;
	}

	public ItemStack getChestplate() {
		return chst;
	}

	public void setChestplate(final ItemStack chst) {
		this.chst = chst;
	}

	public ItemStack getLeggings() {
		return leg;
	}

	public void setLeggings(final ItemStack leg) {
		this.leg = leg;
	}

	public ItemStack getBoots() {
		return boot;
	}

	public void setBoots(final ItemStack boot) {
		this.boot = boot;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(final String customName) {
		this.customName = customName;
	}

	public EntityDisguise getType() {
		return type;
	}

	public void setType(final EntityDisguise type) {
		this.type = type;
	}

	public Player getDisguised() {
		return disguised;
	}
}
