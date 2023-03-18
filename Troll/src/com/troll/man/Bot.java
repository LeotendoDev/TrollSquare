package com.troll.man;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;

public class Bot {
	private String name;
	private ArrayList<String> conversation;
	private EntityPlayer npc;
	
	public static final int SHORTEST_MESSAGE_INTERVAL = 5000;
	public static final int LONGEST_MESSAGE_INTERVAL = 15000;
	
	public Bot(String name) {
		this.name = name;
		this.conversation = new ArrayList<String>();
	}
	
	public void spawnBot(Player p, String worldName) {
		MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer world = ((CraftWorld) Bukkit.getWorld(worldName)).getHandle();
		GameProfile gameProfile = new GameProfile(UUID.randomUUID(), this.name);
		this.npc = new EntityPlayer(server, world, gameProfile, new PlayerInteractManager(world));
		
		World bukkitWorld = Bukkit.getWorld(worldName);
		
		npc.setLocation(p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(),
				p.getLocation().getYaw(), p.getLocation().getPitch());
	
		sendPackets(npc);
	}
	
	public void sendPackets(EntityPlayer npc) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerConnection conn = ((CraftPlayer)p).getHandle().playerConnection;
			conn.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
			conn.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
			conn.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
		}
		
		join();
	}
	
	public void chat() {
		for (String msg : this.conversation) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				try {
					int restTime = (int) (Math.random() * (LONGEST_MESSAGE_INTERVAL - SHORTEST_MESSAGE_INTERVAL)) + SHORTEST_MESSAGE_INTERVAL;
					Thread.sleep(restTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				p.sendMessage("<" + this.name + "> " + msg);
			}
		}
	}
	
	public void join() {
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage("§e" + this.name + " joined the game");
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void left() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			p.sendMessage("§e" + this.name + " left the game");
		}
		
		Bukkit.getOnlinePlayers().forEach(p -> 
			((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc)));
		
		this.npc.setInvisible(true);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getConversation() {
		return this.conversation;
	}
	
	public void setConversation(ArrayList<String> conversation) {
		this.conversation = conversation;
	}
	
	public EntityPlayer getNPC() {
		return this.npc;
	}
	
	public void setNPC(EntityPlayer npc) {
		this.npc = npc;
	}
}
