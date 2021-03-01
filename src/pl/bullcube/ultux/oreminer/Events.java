package pl.bullcube.ultux.oreminer;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Orientable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class Events implements Listener {

    public static int pickNumber(int a, int b) {
        if (a == b) return a;
        return (int)(a + (Math.round(Math.random()*(b-a))));
    }
    private boolean chance(double a){
        double random = Math.random();
        return  random <= a;
    }

    private boolean isAnOre(Material material){
        return material.equals(Material.COAL_ORE) || material.equals(Material.DIAMOND_ORE) || material.equals(Material.REDSTONE_ORE) || material.equals(Material.LAPIS_ORE)
                || material.equals(Material.EMERALD_ORE) || material.equals(Material.GOLD_ORE) || material.equals(Material.IRON_ORE) || material.equals(Material.NETHER_QUARTZ_ORE);
    }


    private boolean hasPickaxe(Player player){
        return player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_PICKAXE) || player.getInventory().getItemInMainHand().getType().equals(Material.STONE_PICKAXE)
                || player.getInventory().getItemInMainHand().getType().equals(Material.IRON_PICKAXE) || player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE)
                || player.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_PICKAXE);
    }

    private boolean dropAndBreak(Block block, ItemStack tool){
        Material[] expMaterials = {Material.COAL_ORE, Material.EMERALD_ORE, Material.DIAMOND_ORE, Material.NETHER_QUARTZ_ORE, Material.LAPIS_ORE, Material.REDSTONE_ORE, Material.NETHER_GOLD_ORE};
        if (Arrays.stream(expMaterials).anyMatch(material -> material.equals(block.getType()))){
            ExperienceOrb exp = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            exp.setExperience(Chance.randBetween(0, 2));
        }
        block.breakNaturally(tool);
        return true;
    }

    private int lookForOre(Block block, ArrayList<Block> stack, Player player){
        Material oreType = block.getType();
        int i = 0;
        Damageable newItemMeta = ((Damageable) player.getInventory().getItemInMainHand().getItemMeta());
        while (stack.size() > i && newItemMeta.getDamage() <= player.getInventory().getItemInMainHand().getType().getMaxDurability()) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x != 0 || y != 0 || z != 0) {
                            Block currBlock = stack.get(i).getRelative(x,y,z);
                            if (currBlock.getType().equals(oreType)) {
                                if (!stack.contains(currBlock)) {
                                    stack.add(currBlock);
                                }
                            }
                        }
                    }
                }
            }
            if (dropAndBreak(stack.get(i), player.getInventory().getItemInMainHand())) {
                if (chance(((double) 1 / (1+player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY))))) {
                    newItemMeta.setDamage(((Damageable) player.getInventory().getItemInMainHand().getItemMeta()).getDamage() + 1);
                    player.getInventory().getItemInMainHand().setItemMeta((ItemMeta) newItemMeta);
                }
                else {
                    newItemMeta.setDamage(newItemMeta.getDamage()+1);
                }


            }

            player.getWorld().spawnParticle(Particle.BLOCK_CRACK, stack.get(i).getLocation(), 100, 0.25, 0.25, 0.25, 0.25, Bukkit.createBlockData("stone"));
            player.getWorld().playSound(stack.get(i).getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
            i++;
        }
        if (newItemMeta.getDamage() > player.getInventory().getItemInMainHand().getType().getMaxDurability()) player.getInventory().setItemInMainHand(null);
        else player.getInventory().getItemInMainHand().setItemMeta((ItemMeta) newItemMeta);
        player.updateInventory();
        return i;

    }


    @EventHandler(priority = EventPriority.MONITOR)
    public void BlockBreakEvent(BlockBreakEvent e){
        if (!e.isCancelled() && isAnOre(e.getBlock().getType())){
            if (hasPickaxe(e.getPlayer())){
                if (e.getPlayer().isSneaking()){
                    ArrayList<Block> visited = new ArrayList<>();
                    visited.add(e.getBlock());
                    int mined = lookForOre(e.getBlock(), visited, e.getPlayer());
                    String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
                    try {
                        Class packetClass = Class.forName("net.minecraft.server."+version+".PacketPlayOutTitle");
                        Class chatSerializer = Class.forName("net.minecraft.server."+version+".IChatBaseComponent$ChatSerializer");
                        Object textToSend = chatSerializer.getMethod("a", String.class).invoke( chatSerializer, "{\"text\":\"§2§lWYKOPANYCH BLOKÓW - §b§l"+mined+"\"}");
                        Class enumTitleAction = Class.forName("net.minecraft.server."+version+".PacketPlayOutTitle$EnumTitleAction");
                        Object packet = packetClass.getConstructor(enumTitleAction, Class.forName("net.minecraft.server."+version+".IChatBaseComponent"), int.class, int.class, int.class).newInstance(enumTitleAction.getField("ACTIONBAR").get(null), textToSend, 10, 100, 40);
                        Object playerHandle = e.getPlayer().getClass().getMethod("getHandle").invoke(e.getPlayer());
                        Object playerConnection = playerHandle.getClass().getField("playerConnection").get(playerHandle);
                        playerConnection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server."+version+".Packet")).invoke(playerConnection, packet);

                    }catch (NoSuchMethodException error) {
                        error.printStackTrace();
                    } catch (IllegalAccessException error) {
                        error.printStackTrace();
                    } catch (InvocationTargetException error) {
                        error.printStackTrace();
                    } catch (NoSuchFieldException error) {
                        error.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (InstantiationException e1) {
                        e1.printStackTrace();
                    }
                    e.setDropItems(false);
                }
            }
        }
    }
}
