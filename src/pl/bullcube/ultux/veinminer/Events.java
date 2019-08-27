package pl.bullcube.ultux.veinminer;

import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Events implements Listener {

    private int pickNumber(int a, int b) {
        if (a == b) return a;
        return (int)(a + (Math.round(Math.random()))*(b-a));
    }
    private boolean chance(double a){
        return Math.random() <= a;
    }
    private boolean isLog(Block block){
        return block.getType().equals(Material.ACACIA_LOG) || block.getType().equals(Material.BIRCH_LOG) || block.getType().equals(Material.DARK_OAK_LOG) ||
                block.getType().equals(Material.JUNGLE_LOG) || block.getType().equals(Material.SPRUCE_LOG) || block.getType().equals(Material.OAK_LOG);
    }
    private boolean isAxe(Material material){
        return material.equals(Material.DIAMOND_AXE) || material.equals(Material.GOLDEN_AXE) || material.equals(Material.IRON_AXE) ||
                material.equals(Material.STONE_AXE) || material.equals(Material.WOODEN_AXE);
    }

    private int howMuchToDrop(ItemStack item, Material material){
        if (material.equals(Material.REDSTONE_ORE)) {
            if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 0) {
                return pickNumber(4, 5);
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
                return 5;
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
                return pickNumber(5, 6);
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
                return 6;
            }
        }
        else if (material.equals(Material.DIAMOND_ORE) || material.equals(Material.COAL_ORE) || material.equals(Material.EMERALD_ORE)){
            if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 0) {
                return 1;
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
                return pickNumber(1, 2);
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
                return pickNumber(1, 2);
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
                return pickNumber(2, 3);
            }
        }
        else if (material.equals(Material.LAPIS_ORE)){
            if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 0) {
                return 6;
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
                return 8;
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
                return pickNumber(10, 11);
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
                return pickNumber(13, 14);
            }
        }
        return 0;


    }

    private boolean isAnOre(Material material){
        return material.equals(Material.COAL_ORE) || material.equals(Material.DIAMOND_ORE) || material.equals(Material.REDSTONE_ORE) || material.equals(Material.LAPIS_ORE)
                || material.equals(Material.EMERALD_ORE) || material.equals(Material.GOLD_ORE) || material.equals(Material.IRON_ORE);
    }

    private boolean isAnOre(Block block, Material oreType){
        return block.getType().equals(oreType);

    }

    private boolean hasPickaxe(Player player){
        return player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_PICKAXE) || player.getInventory().getItemInMainHand().getType().equals(Material.STONE_PICKAXE)
                || player.getInventory().getItemInMainHand().getType().equals(Material.IRON_PICKAXE) || player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE)
                || player.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_PICKAXE);
    }

    private boolean dropAndBreak(Block block, ItemStack tool){
        if (block.getType().equals(Material.IRON_ORE)) {
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_ORE));
            return true;
        }
        if (block.getType().equals(Material.COAL_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, howMuchToDrop(tool, block.getType())));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            return true;
        }
        if (block.getType().equals(Material.REDSTONE_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.REDSTONE, howMuchToDrop(tool, block.getType())));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            return true;
        }
        if (block.getType().equals(Material.EMERALD_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.EMERALD, howMuchToDrop(tool, block.getType())));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            return true;
        }
        if (block.getType().equals(Material.GOLD_ORE)){ block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_ORE)); return true;}
        if (block.getType().equals(Material.DIAMOND_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, howMuchToDrop(tool, block.getType())));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            return true;
        }
        if (block.getType().equals(Material.LAPIS_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LAPIS_LAZULI, howMuchToDrop(tool, block.getType())));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            return true;
        }
        return false;
    }

//    private void lookForOre(Block block){
//        int num = 0;
//        for (int x = -3; x <= 3; x++){
//            for (int y = -3; y <= 3; y++){
//                for (int z = -3; z <= 3; z++){
//                    Block possibleOre = block.getWorld().getBlockAt(block.getLocation().add(new Vector(x, y, z)));
//
//                    if (possibleOre.getType().equals(block.getType())){
//                        dropAndBreak(possibleOre);
//                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pluginInstance, (Runnable) () -> possibleOre.setType(Material.AIR), 1);
//                    }
//                }
//            }
//        }
//    }
    private int lookForOre(Block block, ArrayList<Block> stack, Player player){
//        for (int x = -3; x <= 3; x++){
//            for (int y = -3; y <= 3; y++){
//                for (int z = -3; z <= 3; z++){
//                    Block possibleOre = block.getWorld().getBlockAt(block.getLocation().add(new Vector(x, y, z)));
//
//                }
//            }
//        }
        int i = 0;
        while (stack.size() > i) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x != 0 || y != 0 || z != 0) {
                            if (stack.get(i).getWorld().getBlockAt(stack.get(i).getLocation().add(new Vector(x, y, z))).getType().equals(block.getType())) {
                                if (!stack.contains(stack.get(i).getWorld().getBlockAt(stack.get(i).getLocation().add(new Vector(x, y, z))))) {
                                    stack.add(stack.get(i).getWorld().getBlockAt(stack.get(i).getLocation().add(new Vector(x, y, z))));
                                }
                            }
                        }
                    }
                }
            }
            int finalI = i;
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pluginInstance, () -> stack.get(finalI).setType(Material.AIR), 1);
            if (dropAndBreak(stack.get(i), player.getInventory().getItemInMainHand())) {
                Damageable newItemMeta = ((Damageable) player.getInventory().getItemInMainHand().getItemMeta());
                if (chance(1d / (1+player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY)))) {
                    newItemMeta.setDamage(((Damageable) player.getInventory().getItemInMainHand().getItemMeta()).getDamage() + 1);
                    player.getInventory().getItemInMainHand().setItemMeta((ItemMeta) newItemMeta);
                }
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, stack.get(i).getLocation(), 100, 0.25, 0.25, 0.25, 0.25, Bukkit.createBlockData("stone"));
                player.getWorld().playSound(stack.get(i).getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
            }

            i++;
        }
        return i;

    }

    private void chopTree(Block block, ArrayList<Block> stack, Player player){
        int i = 0;
        while (stack.size() > i) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x != 0 || y != 0 || z != 0) {
                            if (stack.get(i).getWorld().getBlockAt(stack.get(i).getLocation().add(new Vector(x, y, z))).getType().equals(block.getType())) {
                                if (!stack.contains(stack.get(i).getWorld().getBlockAt(stack.get(i).getLocation().add(new Vector(x, y, z))))) {
                                    stack.add(stack.get(i).getWorld().getBlockAt(stack.get(i).getLocation().add(new Vector(x, y, z))));
                                }
                            }
                        }
                    }
                }
            }
            int finalI = i;
            Block toBreak = stack.get(finalI);
            toBreak.getWorld().dropItem(toBreak.getLocation(), new ItemStack(toBreak.getType()));
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pluginInstance, () -> stack.get(finalI).setType(Material.AIR), 1);
            if (isLog(stack.get(i))) {
                int finalI1 = i;
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pluginInstance, () -> stack.get(finalI1).setType(Material.AIR), 1);
                Damageable newItemMeta = ((Damageable) player.getInventory().getItemInMainHand().getItemMeta());
                if (chance(1d / (1+player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY)))) {
                    newItemMeta.setDamage(((Damageable) player.getInventory().getItemInMainHand().getItemMeta()).getDamage() + 1);
                    player.getInventory().getItemInMainHand().setItemMeta((ItemMeta) newItemMeta);
                }
                player.getWorld().spawnParticle(Particle.BLOCK_CRACK, stack.get(i).getLocation(), 100, 0.25, 0.25, 0.25, 0.25, Bukkit.createBlockData("oak_log"));
                player.getWorld().playSound(stack.get(i).getLocation(), Sound.BLOCK_WOOD_BREAK, 1, 1);
            }

            i++;
        }

    }




    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent e){
        if (isAnOre(e.getBlock().getType())){
            if (hasPickaxe(e.getPlayer())){
                if (e.getPlayer().isSneaking()){
                    ArrayList<Block> visited = new ArrayList<>();
                    visited.add(e.getBlock());
                    int mined = lookForOre(e.getBlock(), visited, e.getPlayer());
                    PacketPlayOutTitle packet = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, IChatBaseComponent.ChatSerializer.a("{\"text\":\"§2§lWYKOPANYCH BLOKÓW - §b§l"+mined+"\"}"), 10, 100, 40);
                    ((CraftPlayer)e.getPlayer()).getHandle().playerConnection.sendPacket(packet);
                    e.setDropItems(false);
                }
            }
        }
        else if (isLog(e.getBlock())){
            if (isAxe(e.getPlayer().getInventory().getItemInMainHand().getType())) {
                ArrayList<Block> visited = new ArrayList<>();
                visited.add(e.getBlock());
                chopTree(e.getBlock(), visited, e.getPlayer());
                e.setDropItems(false);
            }

        }
    }
}
