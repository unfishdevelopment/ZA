package io.unfish.zombieapocalypse;

import com.destroystokyo.paper.event.entity.CreeperIgniteEvent;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main extends JavaPlugin implements Listener {
    private int taskid = -1;
    private Material block;
    private BossBar bar;
    private int id;
    private static int uid;
    private static BossBar wb;
    private static File wbyml;


    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Plugin ZombieApocalypse has been running");
        this.saveDefaultConfig();
        getLogger().info(format("&a-----      &c     /\\\\"));
        getLogger().info(format("&a    /      &c    /  \\\\"));
        getLogger().info(format("&a   /       &c   /____\\\\"));
        getLogger().info(format("&a  /        &c  / -----\\\\"));
        getLogger().info(format("&a /         &c /        \\\\"));
        getLogger().info(format("&a -----     &c/          \\\\"));
        FileConfiguration config = this.getConfig();
        File file = new File(getDataFolder(),"water_bar.yml");
        if(!file.exists()){
            saveResource("water_bar.yml",false);
        }
        wbyml = file;
        FileConfiguration wbconf = YamlConfiguration.loadConfiguration(file);
        File file_folder = new File(getDataFolder(),"expansions");
        File expansions = file_folder;
        try
        {if(!file_folder.exists()){
            if(file_folder.mkdir()){
                getLogger().info(format("&aSuccessfully created expansions folder"));
            }
            else{
            }
        if(config.getBoolean("expansions")==true){
            try{
                Bukkit.getPluginManager().loadPlugins(file_folder);
            }catch(Exception e){
                e.printStackTrace();;
                getLogger().info(format("&cGot some problem while was loading expansions in expansions folder"));
            }
        }
        }}catch(Exception e){
            getLogger().info(format("&eRan into some problem while was creating folder expansions"));
            e.printStackTrace();
        }
        taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                new Runnable() {
                    @Override
                    public void run() {
                        if (Bukkit.getOnlinePlayers().size() > 0)
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                for (Entity entity : player.getChunk().getEntities()) {
                                    if (entity.getType().equals(EntityType.ZOMBIE)) {
                                        Zombie zombie = (Zombie) entity;
                                        zombie.setTarget(player);
                                        World world = player.getWorld();
                                        for (Entity entity2 : world.getEntities()) {
                                            if (entity2.getType().equals(EntityType.ZOMBIE)) {
                                                Zombie zombies = (Zombie) entity2;
                                                zombies.setTarget(player);
                                                int playerY = player.getLocation().getBlockY();
                                                int playerX = player.getLocation().getBlockX();
                                                int playerz = player.getLocation().getBlockZ();
                                                int zombieY = zombie.getLocation().getBlockY();
                                                int zombieX = zombie.getLocation().getBlockX();
                                                int zombieZ = zombie.getLocation().getBlockZ();
                                                // playerY > zombieY
                                                    if (playerY - zombieY >= 1 && playerY - zombieY <= 22) {
                                                        if (playerX == zombieX && playerz == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }

                                                        }
                                                        if (playerX == zombieX && playerz - 1 == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                {if (!(loc12.getBlock().getType().equals(block)))
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                zombie.teleport(loc12);
                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                loc13.getBlock().setType(Material.COBBLESTONE);}
                                                            }
                                                        }
                                                        if (playerX == zombieX && playerz + 1 == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        if (playerX - 1 == zombieX && playerz == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        if (playerX + 1 == zombieX && playerz - 1 == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        if (playerX == zombieX && playerz - 2 == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        if (playerX == zombieX && playerz + 2 == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        if (playerX - 2 == zombieX && playerz == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        if (playerX + 2 == zombieX && playerz - 1 == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        //3
                                                        if (playerX == zombieX && playerz - 3 == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        if (playerX == zombieX && playerz + 3 == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        if (playerX - 3 == zombieX && playerz == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                        if (playerX + 3 == zombieX && playerz - 1 == zombieZ) {
                                                            long random = Math.round(Math.random() * 100 + 1);
                                                            if (random <= 40) {
                                                                Location loc12 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY + 1, zombieZ).getLocation();
                                                                if(getConfig().getBoolean("place_block"))
                                                                    if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                                            loc12.getBlock().setType(Material.AIR);
                                                                            zombie.teleport(loc12);
                                                                        }
                                                                    }
                                                                if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc12.getBlock().setType(Material.AIR);
                                                                        zombie.teleport(loc12);
                                                                    }
                                                                }
                                                                else{
                                                                    loc12.getBlock().setType(Material.AIR);
                                                                    zombie.teleport(loc12);
                                                                }

                                                                Location loc13 = zombie.getWorld().getBlockAt(zombieX,
                                                                        zombieY - 1, zombieZ).getLocation();
                                                                if(loc13.getBlock().getType().equals(Material.COBBLESTONE)){
                                                                    if(!(config.getBoolean("cobblestone")==true)){
                                                                        loc13.getBlock().setType(Material.DIRT);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.OBSIDIAN)){
                                                                    if(!(config.getBoolean("obsidian")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.GLASS)){
                                                                    if(!(config.getBoolean("glass")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.IRON_BARS)){
                                                                    if(!(config.getBoolean("iron_bars")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                                    if(!(config.getBoolean("stonebrick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                if(loc13.getBlock().getType().equals(Material.BRICK)){
                                                                    if(!(config.getBoolean("brick")==true)){
                                                                        loc13.getBlock().setType(Material.COBBLESTONE);
                                                                    }
                                                                }
                                                                else{
                                                                    loc13.getBlock().setType(Material.COBBLESTONE);
                                                                }
                                                            }
                                                        }
                                                    }
                                            // playerY < zombieY
                                                if(getConfig().getBoolean("break_block"))
                                                {if (zombieY - playerY >= 2 && zombieY - playerY <= 12) {
                                                    if (zombieX == playerX && zombieZ == playerz) {
                                                        Location loc12 = player.getWorld().getBlockAt(
                                                                playerX, zombieY - 1, playerz
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX - 1 == playerX && zombieZ == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX + 1 == playerX && zombieZ == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX == playerX || zombieZ + 1 == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX == playerX || zombieZ - 1 == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX - 2 == playerX && zombieZ == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX + 2 == playerX && zombieZ == playerz) {

                                                    }
                                                    if (zombieX == playerX || zombieZ + 2 == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX == playerX || zombieZ - 2 == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX - 3 == playerX && zombieZ == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX + 3 == playerX && zombieZ == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX,zombieY-1,zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("bricke")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }
                                                    }
                                                    if (zombieX == playerX || zombieZ + 3 == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                    if (zombieX == playerX || zombieZ - 3 == playerz) {
                                                        Location loc12 = zombie.getWorld().getBlockAt(
                                                                zombieX, zombieY - 1, zombieZ
                                                        ).getLocation();
                                                        if(loc12.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc12.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                loc12.getBlock().setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            loc12.getBlock().setType(Material.AIR);

                                                        }


                                                    }
                                                }}if(getConfig().getBoolean("place_block"))
                                                {if (zombieY - playerY == 1) {
                                                    if (zombieX - 1 == playerX && playerz == zombieZ) {
                                                        Location loc11 = player.getWorld().getBlockAt(
                                                                playerX, playerY + 1, playerz
                                                        ).getLocation();
                                                        if(loc11.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);
                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                        }
                                                    }
                                                    if (zombieX + 1 == playerX && zombieZ == playerz) {
                                                        Location loc11 = player.getWorld().getBlockAt(
                                                                playerX, playerY + 1, playerz
                                                        ).getLocation();
                                                        if(loc11.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);
                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                        }
                                                    }
                                                    if (zombieZ - 1 == playerz && zombieX == playerX) {
                                                        Location loc11 = player.getWorld().getBlockAt(
                                                                playerX, playerY + 1, playerz
                                                        ).getLocation();
                                                        if(loc11.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);
                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                        }
                                                    }
                                                    if (zombieZ + 1 == playerz && playerX == zombieX) {
                                                        Location loc11 = player.getWorld().getBlockAt(
                                                                playerX, playerY + 1, playerz
                                                        ).getLocation();
                                                        if(loc11.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);
                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        if(loc11.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc11).setType(Material.COBBLESTONE);

                                                        }
                                                    }
                                                }}
                                                if(getConfig().getBoolean("break_block"))
                                                {if (zombieY == playerY || zombieY - playerY == 1 ||
                                                        playerY - zombieY == 1) {
                                                    //zombieX - 2  = PlayerX 1 block thick wall
                                                    if (zombieX - 2 == playerX && zombieZ == playerz) {
                                                        Location loc2 = player.getWorld().getBlockAt(
                                                                playerX + 1, zombieY, playerz
                                                        ).getLocation();
                                                        Location loc3 = player.getWorld().getBlockAt(
                                                                playerX + 1, zombieY + 1, playerz
                                                        ).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
//
                                                        if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    // zombieZ - 2 = PlayerZ
                                                    if (zombieZ - 2 == playerz && zombieX == playerX) {
                                                        Location loc2 = player.getWorld().getBlockAt(
                                                                playerX, zombieY, playerz + 1
                                                        ).getLocation();
                                                        Location loc3 = player.getWorld().getBlockAt(
                                                                playerX, zombieY + 1, playerz + 1
                                                        ).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
//
                                                        if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if (zombieX + 2 == playerz && zombieZ == playerz) {
                                                        Location loc2 = player.getWorld().getBlockAt(
                                                                playerX - 1, zombieY, playerz
                                                        ).getLocation();
                                                        Location loc3 = player.getWorld().getBlockAt(
                                                                playerX - 1, zombieY + 1, playerz
                                                        ).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
//
                                                        if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if (zombieZ + 2 == playerz && zombieX == playerX) {
                                                        Location loc2 = player.getWorld().getBlockAt(
                                                                playerX, zombieY, playerz - 1
                                                        ).getLocation();
                                                        Location loc3 = player.getWorld().getBlockAt(
                                                                playerX, zombieY + 1, playerz - 1
                                                        ).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
//
                                                        if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if (zombieX - 3 == playerX && zombieZ == playerz) {
                                                        Location loc2 = zombie.getWorld().getBlockAt(zombieX - 1,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc3 = zombie.getWorld().getBlockAt(zombieX - 2,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc4 = zombie.getWorld().getBlockAt(zombieX - 1,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        Location loc5 = zombie.getWorld().getBlockAt(zombieX - 2,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }

                                                    if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                    }
//
                                                    if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                    }
//
                                                    if(loc5.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                    }



                                                }
                                                    if (zombieZ - 3 == playerz && zombieX == playerX) {
                                                        Location loc2 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ - 1).getLocation();
                                                        Location loc3 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ - 2).getLocation();
                                                        Location loc4= zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ - 1).getLocation();
                                                        Location loc5 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ - 2).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                    }
//
                                                    if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                    }
//
                                                    if(loc5.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                    }

                                                }
                                                    if (zombieX + 3 == playerz && zombieZ == playerz) {
                                                        Location loc2 = zombie.getWorld().getBlockAt(zombieX + 1,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc3 = zombie.getWorld().getBlockAt(zombieX + 2,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc4 = zombie.getWorld().getBlockAt(zombieX + 1,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        Location loc5 = zombie.getWorld().getBlockAt(zombieX + 2,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }

                                                    if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                    }
//
                                                    if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                    }
//
                                                    if(loc5.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                    }

                                                }
                                                    if (zombieZ + 3 == playerz && zombieX == playerX) {
                                                        Location loc2 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ + 1).getLocation();
                                                        Location loc3 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ + 2).getLocation();
                                                        Location loc4 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ + 1).getLocation();
                                                        Location loc5 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ + 2).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }

                                                    if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                    }
//
                                                    if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc4.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                    }
//
                                                    if(loc5.getBlock().getType().equals(Material.COBBLESTONE)){
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.OBSIDIAN)){
                                                        if(!(config.getBoolean("obsidian")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.GLASS)){
                                                        if(!(config.getBoolean("glass")==true)){

                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);
                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.IRON_BARS)){
                                                        if(!(config.getBoolean("iron_bars")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                        if(!(config.getBoolean("stonebrick")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    if(loc5.getBlock().getType().equals(Material.BRICK)){
                                                        if(!(config.getBoolean("brick")==true)){
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
                                                    }
                                                    else{
                                                        player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                    }

                                                }
                                                    if (zombieX - 4 == playerX && zombieZ == playerz) {
                                                        Location loc2 = zombie.getWorld().getBlockAt(zombieX - 1,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc3 = zombie.getWorld().getBlockAt(zombieX - 2,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc4 = zombie.getWorld().getBlockAt(zombieX - 1,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        Location loc5 = zombie.getWorld().getBlockAt(zombieX - 2,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        Location loc6 = zombie.getWorld().getBlockAt(zombieX - 3,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc7 = zombie.getWorld().getBlockAt(zombieX - 3,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }

                                                        if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
//
                                                        if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
//
                                                        if(loc5.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
//
                                                        if(loc6.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                        }

                                                        if(loc6.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                        }
//
                                                        if(loc7.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                        }

                                                    }
                                                    // zombieZ - 2 = PlayerZ
                                                    if (zombieZ - 4 == playerz && zombieX == playerX) {
                                                        Location loc2 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ - 1).getLocation();
                                                        Location loc3 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ - 2).getLocation();
                                                        Location loc4 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ - 2).getLocation();
                                                        Location loc5 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ - 1).getLocation();
                                                        Location loc6 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ - 3).getLocation();
                                                        Location loc7 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ - 3).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }

                                                        if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
//
                                                        if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
//
                                                        if(loc5.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
//
                                                        if(loc6.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                        }

                                                        if(loc6.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                        }
//
                                                        if(loc7.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                        }

                                                    }
                                                    if (zombieX + 4 == playerz && zombieZ == playerz) {
                                                        Location loc2 = zombie.getWorld().getBlockAt(zombieX + 1,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc3 = zombie.getWorld().getBlockAt(zombieX + 2,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc4 = zombie.getWorld().getBlockAt(zombieX + 1,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        Location loc5 = zombie.getWorld().getBlockAt(zombieX + 2,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        Location loc6 = zombie.getWorld().getBlockAt(zombieX + 3,
                                                                zombieY, zombieZ).getLocation();
                                                        Location loc7  = zombie.getWorld().getBlockAt(zombieX + 3,
                                                                zombieY + 1, zombieZ).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }

                                                        if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
//
                                                        if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
//
                                                        if(loc5.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
//
                                                        if(loc6.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                        }

                                                        if(loc6.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                        }
//
                                                        if(loc7.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                        }

                                                    }
                                                    if (zombieZ + 4 == playerz && zombieX == playerX) {
                                                        Location loc2 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ + 2).getLocation();
                                                        Location loc3 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ + 1).getLocation();
                                                        Location loc4 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ + 2).getLocation();
                                                        Location loc5 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ + 1).getLocation();
                                                        Location loc6 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY, zombieZ + 3).getLocation();
                                                        Location loc7 = zombie.getWorld().getBlockAt(zombieX,
                                                                zombieY + 1, zombieZ + 3).getLocation();
                                                        if(loc2.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }

                                                        if(loc2.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc2.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc2).setType(Material.AIR);

                                                        }
//
                                                        if(loc3.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc3).setType(Material.AIR);

                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc4.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc4).setType(Material.AIR);

                                                        }
//
                                                        if(loc5.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc5.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc5).setType(Material.AIR);

                                                        }
//
                                                        if(loc6.getBlock().getType().equals(Material.COBBLESTONE))
                                                        if(!(config.getBoolean("cobblestone")==true)){
                                                            player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                        }

                                                        if(loc6.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc6.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc6).setType(Material.AIR);

                                                        }
//
                                                        if(loc7.getBlock().getType().equals(Material.COBBLESTONE)){
                                                            if(!(config.getBoolean("cobblestone")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.OBSIDIAN)){
                                                            if(!(config.getBoolean("obsidian")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc3.getBlock().getType().equals(Material.GLASS)){
                                                            if(!(config.getBoolean("glass")==true)){

                                                                player.getWorld().getBlockAt(loc3).setType(Material.AIR);
                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.IRON_BARS)){
                                                            if(!(config.getBoolean("iron_bars")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.STONE_BRICKS)){
                                                            if(!(config.getBoolean("stonebrick")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        if(loc7.getBlock().getType().equals(Material.BRICK)){
                                                            if(!(config.getBoolean("brick")==true)){
                                                                player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                            }
                                                        }
                                                        else{
                                                            player.getWorld().getBlockAt(loc7).setType(Material.AIR);

                                                        }

                                                    }
                                                }}
                                            }
                                        }
                                    }
                                }
                            }
                    }
                }, 0, 60);

    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin turned off");
    }

    private static String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    @EventHandler
    public void ZombieCantBeBurntInDayAndMore(EntitySpawnEvent event) {
        if (event.getEntity().getType().equals(EntityType.ZOMBIE)) {
            Zombie zombie = (Zombie) event.getEntity();
            if(this.getConfig().getBoolean("not_burn_in_day")){
                zombie.setShouldBurnInDay(false);
            }
                if(this.getConfig().getBoolean("run_faster")){
                    zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                            27000, 1));
                }

            long random = Math.round(Math.random() * 100 + 1);
            if(this.getConfig().getBoolean("pickup_all_items")){
                zombie.setCanPickupItems(true);
            }
            if(this.getConfig().getBoolean("invisible")==true){
                long random2 = Math.round(Math.random() * 100 + 1);
                if(this.getConfig().getInt("percent")<= random2){
                    zombie.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,
                            27000,1));
                }
            }

        }
    }

    @EventHandler
    public void ZombieDamageIncrease(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType().equals(EntityType.PLAYER)) {
            Player player = (Player) event.getEntity();
            if(this.getConfig().getBoolean("attack_stronger"))
            {if (event.getDamager().getType().equals(EntityType.ZOMBIE)) {
                Zombie zombie = (Zombie) event.getDamager();
                player.setHealth(player.getHealth() - player.getHealth() / 5);
                long random = Math.round(Math.random() * 100 + 1);
                if (random >= 50) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.POISON,
                            15, 1));
                }
                if (random == 10) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,
                            10, 1));
                }
                if (random <= 30) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
                            120, 2));
                }
                if (random <= 20) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,
                            120, 2));
                }
            }}
        }
    }
    @EventHandler
    public void CreeperExplosion(CreeperIgniteEvent event){
       Creeper creeper =  event.getEntity();
       if(this.getConfig().getBoolean("creeper")==true){
           creeper.setExplosionRadius(this.getConfig().getInt("radius"));
       }
    }
    @EventHandler
    public void Explosion(ExplosionPrimeEvent event){
        Entity entity = event.getEntity();
        if(entity.getType().equals(EntityType.CREEPER)){
            Creeper creeper = (Creeper)entity;
            creeper.setExplosionRadius(this.getConfig().getInt("radius"));
            if(this.getConfig().getBoolean("explode_immediately")==true){
                creeper.explode();
            }
            if(this.getConfig().getBoolean("fire")){
                event.setFire(true);
            }
            if(this.getConfig().getBoolean("charge")){
                creeper.setPowered(true);
            }
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(label.equalsIgnoreCase("za")){
            if(args[0].equalsIgnoreCase("tps")){
                if(sender instanceof ConsoleCommandSender){
                    sender.sendMessage(format("&cThis command is can only be used by players!"));
                    return true;
                }
                Player player = (Player)sender;
                if(args[1].equalsIgnoreCase("on")){
                    createbar(player);

                }
                if(args[1].equalsIgnoreCase("off")){
                    bar.removePlayer(player);
                    Bukkit.getScheduler().cancelTask(id);
                }
            }
            if(args.length == 0||args[0].equalsIgnoreCase("help")){
                sender.sendMessage(format("&b----Commands----" +
                        "\n&e/za help: open this command list" +
                        "\n&e/za tps on/off: turn on/off tps bar\n" +
                        "&b"));
            }
        }
        return super.onCommand(sender, command, label, args);
    }
    public void createbar(Player player){
        bar = Bukkit.createBossBar(format("&eTPS"), BarColor.BLUE,
                BarStyle.SEGMENTED_20);

cast(player);
    }
    public void cast(Player player){
        id=Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                new Runnable() {
                    @Override
                    public void run() {
                        if(bar.getPlayers().contains(player)){
                            bar.removePlayer(player);
                        }
                       double[] tps = getServer().getTPS();
                       double tick = tps[0];
                       bar.setVisible(true);
                       if(tick >= 18.0){
                           bar.setColor(BarColor.BLUE);
                           bar.setTitle(format("&eTPS"+" &b"+tick));
                       }
                       if(tick <= 17.9){
                           if(tick >= 15.0){
                               bar.setColor(BarColor.GREEN);
                               bar.setTitle(format("&eTPS"+" &b"+tick));
                           }

                           if(tick <= 15.0){
                               if(tick > 13.0){
                                   bar.setColor(BarColor.YELLOW);
                                   bar.setTitle(format("&eTPS"+" &c"+tick));
                               }
                               if(tick <= 13.0){
                                   if(tick > 7.0){
                                       bar.setColor(BarColor.RED);
                                       bar.setTitle(format("&eTPS"+" &b"+tick));
                                   }

                                   if(tick < 7.0){
                                       bar.setColor(BarColor.WHITE);
                                       bar.setTitle(format("&eTPS"+" &4"+tick));
                                   }
                               }
                           }
                       }
                       bar.addPlayer(player);
                    }
                },0,100);
    }
    public void createwb(Player player){
        wb =  Bukkit.createBossBar(this.getConfig().getString("title"),BarColor.BLUE,
                BarStyle.SOLID);
        cast2(player);
    }
    public void cast2(Player player){
        uid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
                new Runnable() {
                    File file= new File(getDataFolder(),"water_bar.yml");
                    FileConfiguration wbconf = YamlConfiguration.loadConfiguration(new File(getDataFolder(),"water_bar.yml"));
                    double progress = wbconf.getDouble("progress."+player.getName());
                    double count = 1.0/20;
                    @Override
                    public void run() {
                        wb.setProgress(progress);
                        wb.addPlayer(player);
                        if(progress <= 0.5){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
                                    1800,1));
                        }
                        if(progress <= 0.45){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING,
                                    1620,1));
                        }
                        if(progress <= 0.4){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,
                                    1440,2));
                        }
                        if(progress<= 0.35){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,
                                    1260,1));
                        }
                        if(progress <= 0.3){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,
                                    1080,1));
                        }
                        if(progress <= 0.25){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.POISON,
                                    900,1));
                        }
                        if(progress <= 0.2){
                            getServer().dispatchCommand(getServer().getConsoleSender(), "minecraft:kill "+player.getName());
                            getServer().broadcastMessage(format("&e"+player.getName()+" đã chết khát"));
                        }
                        progress= progress - count;
                        wbconf.set("progress."+player.getName(),progress);
                        try{
                            wbconf.save(file);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                },0,this.getConfig().getInt("update")*20*60);
    }
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(this.getConfig().getBoolean("water_bar")==true){
            createwb(player);
        }
        FileConfiguration wbconf = YamlConfiguration.loadConfiguration(new File(getDataFolder(),"water_bar.yml"));
        if(!wbconf.getConfigurationSection("progress").getKeys(false).contains(player.getName())){
            File file = new File(getDataFolder(),"water_bar.yml");
            wbconf.createSection("progress."+player.getName());
            wbconf.set("progress."+player.getName(),1.0);
            try{
                wbconf.save(file);
            }catch(IOException e){
            }

        }

    }
    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
    }
    @EventHandler
    public void PlayerDrink(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(this.getConfig().getBoolean("water_bar")==true){
                if(player.getInventory().getItemInMainHand() == null){
                    Location loc = event.getClickedBlock().getLocation();
                    World world = player.getWorld();
                    if(world.getBlockAt(loc.getBlockX(),loc.getBlockY()+1,
                            loc.getBlockZ()).isLiquid()){
                        if(!(wb.getPlayers()==null)){
                            if(wb.getPlayers().contains(player)){
                                wb.removePlayer(player);
                                Bukkit.getScheduler().cancelTask(uid);
                                File file = new File(getDataFolder(),"water_bar.yml");
                                FileConfiguration wbconf = YamlConfiguration.loadConfiguration(file);
                                if(!(wbconf.getConfigurationSection("progress") == null)){
                                    if(wbconf.getConfigurationSection("progress").getKeys(false)
                                    .contains(player.getName())){
                                        try{
                                            wbconf.set("progress."+player.getName(),1.0);
                                            wbconf.save(file);
                                            player.playSound(player.getLocation(),Sound.ENTITY_WANDERING_TRADER_DRINK_MILK,2.0F,1.0F);
                                        }catch(Exception e){
                                        }

                                        int cid;
                                        cid = Bukkit.getScheduler().scheduleSyncDelayedTask(this,
                                                new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        createwb(player);
                                                    }
                                                },60);
                                    }
                                }
                            }
                        }
                    }
                }
                if(player.getInventory().getItemInMainHand().equals(Material.RABBIT_STEW)||player.getInventory()
                .getItemInMainHand().equals(Material.RABBIT_STEW)||player.getInventory().getItemInMainHand()
                        .equals(Material.BEETROOT_SOUP)){

                    File file = new File(getDataFolder(),"water_bar.yml");
                    FileConfiguration wbconf = YamlConfiguration.loadConfiguration(file);
                    if(!(wb.getPlayers() == null)){
                        if(wb.getPlayers().contains(player)){
                            Bukkit.getScheduler().cancelTask(uid);
                            player.getInventory().remove(player.getInventory().getItemInMainHand());
                            wb.removePlayer(player);
                            int cid;
                            if(!(wbconf.getConfigurationSection("progress").getKeys(false) == null)){
                                if(wbconf.getConfigurationSection("progress").getKeys(false).contains(player.getName())){
                                    try{
                                        wbconf.set("progress."+player.getName(),1.0);
                                        wbconf.save(file);
                                        player.playSound(player.getLocation(),Sound.ENTITY_WANDERING_TRADER_DRINK_MILK,2.0F,1.0F);
                                    }catch(Exception e){

                                    }
                                    cid = Bukkit.getScheduler().scheduleSyncDelayedTask(this,
                                            new Runnable() {
                                                @Override
                                                public void run() {
                                                    createwb(player);
                                                }
                                            },60);
                                }
                            }

                        }
                    }
                }
            }

        }
    }
    @EventHandler
    public void PlayerLeft(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(this.getConfig().getBoolean("water_bar")==true){
            wb.removePlayer(player);
            Bukkit.getScheduler().cancelTask(uid);
        }
    }

}


