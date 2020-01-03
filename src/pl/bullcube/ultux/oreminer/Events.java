package pl.bullcube.ultux.oreminer;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Orientable;
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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Events implements Listener {

    public static ArrayList<Integer> lapisFortune0 = new ArrayList<>();
    public static ArrayList<Integer> lapisFortune1 = new ArrayList<>();
    public static ArrayList<Integer> lapisFortune2 = new ArrayList<>();
    public static ArrayList<Integer> lapisFortune3 = new ArrayList<>();
    public static ArrayList<Integer> redstoneFortune0 = new ArrayList<>();
    public static ArrayList<Integer> redstoneFortune1 = new ArrayList<>();
    public static ArrayList<Integer> redstoneFortune2 = new ArrayList<>();
    public static ArrayList<Integer> redstoneFortune3 = new ArrayList<>();
    public static ArrayList<Integer> diamondFortune1 = new ArrayList<>();
    public static ArrayList<Integer> diamondFortune2 = new ArrayList<>();
    public static ArrayList<Integer> diamondFortune3 = new ArrayList<>();





    static {
        //lapis fortune 0 filling
        lapisFortune0.add(4);
        lapisFortune0.add(5);
        lapisFortune0.add(6);
        lapisFortune0.add(7);
        lapisFortune0.add(8);
        //lapis fortune 1 filling
        lapisFortune1.add(4);
        lapisFortune1.add(4);
        lapisFortune1.add(5);
        lapisFortune1.add(5);
        lapisFortune1.add(6);
        lapisFortune1.add(6);
        lapisFortune1.add(7);
        lapisFortune1.add(7);
        lapisFortune1.add(8);
        lapisFortune1.add(8);
        lapisFortune1.add(8);
        lapisFortune1.add(10);
        lapisFortune1.add(12);
        lapisFortune1.add(14);
        lapisFortune1.add(16);
        // lapis fortune 2 filling
        lapisFortune2.add(4);
        lapisFortune2.add(4);
        lapisFortune2.add(5);
        lapisFortune2.add(5);
        lapisFortune2.add(6);
        lapisFortune2.add(6);
        lapisFortune2.add(7);
        lapisFortune2.add(7);
        lapisFortune2.add(8);
        lapisFortune2.add(8);
        lapisFortune2.add(8);
        lapisFortune2.add(10);
        lapisFortune2.add(12);
        lapisFortune2.add(12);
        lapisFortune2.add(14);
        lapisFortune2.add(15);
        lapisFortune2.add(16);
        lapisFortune2.add(18);
        lapisFortune2.add(21);
        lapisFortune2.add(24);
        //lapis fortune 3 filling
        lapisFortune3.add(4);
        lapisFortune3.add(4);
        lapisFortune3.add(5);
        lapisFortune3.add(5);
        lapisFortune3.add(6);
        lapisFortune3.add(6);
        lapisFortune3.add(7);
        lapisFortune3.add(7);
        lapisFortune3.add(8);
        lapisFortune3.add(8);
        lapisFortune3.add(8);
        lapisFortune3.add(10);
        lapisFortune3.add(12);
        lapisFortune3.add(12);
        lapisFortune3.add(14);
        lapisFortune3.add(15);
        lapisFortune3.add(16);
        lapisFortune3.add(16);
        lapisFortune3.add(18);
        lapisFortune3.add(20);
        lapisFortune3.add(21);
        lapisFortune3.add(24);
        lapisFortune3.add(24);
        lapisFortune3.add(28);
        lapisFortune3.add(32);
        //Filing redstone fortune 0
        redstoneFortune0.add(4);
        redstoneFortune0.add(5);
        //Filing redstone fortune 1
        redstoneFortune1.add(4);
        redstoneFortune1.add(5);
        redstoneFortune1.add(5);
        redstoneFortune1.add(6);
        //Filing redstone fortune 2
        redstoneFortune2.add(4);
        redstoneFortune2.add(5);
        redstoneFortune2.add(5);
        redstoneFortune2.add(6);
        redstoneFortune2.add(6);
        redstoneFortune2.add(7);
        //Filing redstone fortune 3
        redstoneFortune3.add(4);
        redstoneFortune3.add(5);
        redstoneFortune3.add(5);
        redstoneFortune3.add(6);
        redstoneFortune3.add(6);
        redstoneFortune3.add(7);
        redstoneFortune3.add(7);
        redstoneFortune3.add(8);
        //Filing diamond fortune 1
        diamondFortune1.add(1);
        diamondFortune1.add(1);
        diamondFortune1.add(2);
        //Filing diamond fortune 2
        diamondFortune2.add(1);
        diamondFortune2.add(1);
        diamondFortune2.add(2);
        diamondFortune2.add(3);
        //Filing diamond fortune 3
        diamondFortune3.add(3);
        diamondFortune3.add(3);
        diamondFortune3.add(1);
        diamondFortune3.add(2);
        diamondFortune3.add(2);

    }


    public static int pickNumber(int a, int b) {
        if (a == b) return a;
        return (int)(a + (Math.round(Math.random()*(b-a))));
    }
    private boolean chance(double a){
        double random = Math.random();
        return  random <= a;
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
                return redstoneFortune0.get(pickNumber(0, redstoneFortune0.size()-1));
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
                return redstoneFortune1.get(pickNumber(0, redstoneFortune1.size()-1));
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
                return redstoneFortune2.get(pickNumber(0, redstoneFortune2.size()-1));
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
                return redstoneFortune3.get(pickNumber(0, redstoneFortune3.size()-1));
            }
        }
        else if (material.equals(Material.DIAMOND_ORE) || material.equals(Material.COAL_ORE) || material.equals(Material.EMERALD_ORE) || material.equals(Material.NETHER_QUARTZ_ORE)){
            if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 0) {
                return 1;
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
                return diamondFortune1.get(pickNumber(0, diamondFortune1.size()-1));
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
                return diamondFortune2.get(pickNumber(0, diamondFortune2.size()-1));
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
                return diamondFortune3.get(pickNumber(0, diamondFortune3.size()-1));
            }
        }
        else if (material.equals(Material.LAPIS_ORE)){
            if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 0) {
                return lapisFortune0.get(pickNumber(0, lapisFortune0.size()-1));
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 1) {
                return lapisFortune1.get(pickNumber(0, lapisFortune1.size()-1));
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 2) {
                return lapisFortune2.get(pickNumber(0, lapisFortune2.size()-1));
            } else if (item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) == 3) {
                return lapisFortune3.get(pickNumber(0, lapisFortune3.size()-1));
            }
        }
        return 0;


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
        if (block.getType().equals(Material.IRON_ORE) && (tool.getType().equals(Material.STONE_PICKAXE) || tool.getType().equals(Material.IRON_PICKAXE) || tool.getType().equals(Material.DIAMOND_PICKAXE) || tool.getType().equals(Material.GOLDEN_PICKAXE))) {
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_ORE));
            return true;
        }
        if (block.getType().equals(Material.COAL_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL, howMuchToDrop(tool, block.getType())));

            ExperienceOrb exp = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            exp.setExperience(Chance.randBetween(0, 2));
            return true;
        }
        if (block.getType().equals(Material.REDSTONE_ORE) && (tool.getType().equals(Material.IRON_PICKAXE) || tool.getType().equals(Material.DIAMOND_PICKAXE) || tool.getType().equals(Material.GOLDEN_PICKAXE))){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.REDSTONE, howMuchToDrop(tool, block.getType())));
            ExperienceOrb exp = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            exp.setExperience(Chance.randBetween(1, 5));
            return true;
        }
        if (block.getType().equals(Material.EMERALD_ORE) && (tool.getType().equals(Material.IRON_PICKAXE) || tool.getType().equals(Material.DIAMOND_PICKAXE))){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.EMERALD, howMuchToDrop(tool, block.getType())));
            ExperienceOrb exp = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            exp.setExperience(Chance.randBetween(3, 7));
            return true;
        }
        if (block.getType().equals(Material.GOLD_ORE) && (tool.getType().equals(Material.IRON_PICKAXE) || tool.getType().equals(Material.DIAMOND_PICKAXE))){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_ORE));
            return true;
        }
        if (block.getType().equals(Material.DIAMOND_ORE) && (tool.getType().equals(Material.IRON_PICKAXE) || tool.getType().equals(Material.DIAMOND_PICKAXE))){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND, howMuchToDrop(tool, block.getType())));
            ExperienceOrb exp = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            exp.setExperience(Chance.randBetween(3, 7));
            return true;
        }
        if (block.getType().equals(Material.LAPIS_ORE) && (tool.getType().equals(Material.IRON_PICKAXE) || tool.getType().equals(Material.DIAMOND_PICKAXE))){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LAPIS_LAZULI, howMuchToDrop(tool, block.getType())));
            ExperienceOrb exp = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            exp.setExperience(Chance.randBetween(2, 5));
            return true;
        }
        if (block.getType().equals(Material.NETHER_QUARTZ_ORE) && (tool.getType().equals(Material.IRON_PICKAXE) || tool.getType().equals(Material.DIAMOND_PICKAXE))){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.QUARTZ, howMuchToDrop(tool, block.getType())));
            ExperienceOrb exp = block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            exp.setExperience(Chance.randBetween(2, 5));
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
        Damageable newItemMeta = ((Damageable) player.getInventory().getItemInMainHand().getItemMeta());
        while (stack.size() > i && newItemMeta.getDamage() < player.getInventory().getItemInMainHand().getType().getMaxDurability()) {
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
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pluginInstance, () -> {
                 stack.get(finalI).setType(Material.AIR);

            }, 1);
            if (dropAndBreak(stack.get(i), player.getInventory().getItemInMainHand())) {
                if (chance(((double) 1 / (1+player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY))))) {
                    newItemMeta.setDamage(((Damageable) player.getInventory().getItemInMainHand().getItemMeta()).getDamage() + 1);
                    player.getInventory().getItemInMainHand().setItemMeta((ItemMeta) newItemMeta);
                }

            }

            player.getWorld().spawnParticle(Particle.BLOCK_CRACK, stack.get(finalI).getLocation(), 100, 0.25, 0.25, 0.25, 0.25, Bukkit.createBlockData("stone"));
            player.getWorld().playSound(stack.get(finalI).getLocation(), Sound.BLOCK_STONE_BREAK, 1, 1);
            i++;
        }
        return i;

    }

    private void chopTree(Block block, ArrayList<Block> stack, Player player){
        int i = 0;
        Damageable newItemMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
        while (stack.size() > i && newItemMeta.getDamage() < player.getInventory().getItemInMainHand().getType().getMaxDurability()) {
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x != 0 || y != 0 || z != 0) {
                            Block possibleBlock = stack.get(i).getWorld().getBlockAt(stack.get(i).getLocation().add(new Vector(x, y, z)));
                            if (possibleBlock.getType().equals(block.getType())) {
                                if (((Orientable) possibleBlock.getBlockData()).getAxis().equals(Axis.Y)) {
                                    if (!stack.contains(possibleBlock)) {
                                        stack.add(possibleBlock);
                                    }
                                }
                                else {
                                    boolean isTouchingLeaves = false;
                                    for (int xin = -1 ; xin <= 1; xin++){
                                        for (int yin = -1; yin <= 1; yin++){
                                            for (int zin = -1; zin <= 1; zin++){
                                                if (xin != 0 || yin != 0 || zin != 0){
                                                    Material touchingBlockType = possibleBlock.getWorld().getBlockAt(possibleBlock.getLocation().add(new Vector(xin, yin, zin))).getType();

                                                    if (touchingBlockType.equals(Material.ACACIA_LEAVES) || touchingBlockType.equals(Material.BIRCH_LEAVES) || touchingBlockType.equals(Material.DARK_OAK_LEAVES)
                                                        || touchingBlockType.equals(Material.JUNGLE_LEAVES) || touchingBlockType.equals(Material.OAK_LEAVES) || touchingBlockType.equals(Material.SPRUCE_LEAVES)) isTouchingLeaves = true;
                                                }
                                            }
                                        }
                                    }
                                    if (isTouchingLeaves) {
                                        if (!stack.contains(possibleBlock)) {
                                            stack.add(possibleBlock);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int finalI = i;
            Block toBreak = stack.get(finalI);
            toBreak.getWorld().dropItem(toBreak.getLocation(), new ItemStack(toBreak.getType()));

            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pluginInstance, new Runnable() {
                @Override
                public void run()
            {
                stack.get(finalI).setType(Material.AIR);
                if (isLog(stack.get(finalI))) {
                    int finalI1 = finalI;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pluginInstance, () -> stack.get(finalI1).setType(Material.AIR), 1);


                }
            }}, 1);
            if (isLog(stack.get(i))){
                if (chance(1d / (1+player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY)))) {
                    newItemMeta.setDamage(((Damageable) player.getInventory().getItemInMainHand().getItemMeta()).getDamage() + 1);
                    player.getInventory().getItemInMainHand().setItemMeta((ItemMeta) newItemMeta);
                }
            }
            player.getWorld().spawnParticle(Particle.BLOCK_CRACK, stack.get(finalI).getLocation(), 100, 0.25, 0.25, 0.25, 0.25, Bukkit.createBlockData("oak_log"));
            player.getWorld().playSound(stack.get(finalI).getLocation(), Sound.BLOCK_WOOD_BREAK, 1, 1);
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
        else if (isLog(e.getBlock())){
            if (((Orientable) e.getBlock().getBlockData()).getAxis().equals(Axis.Y)) {
                if (e.getPlayer().isSneaking()) {
                    if (isAxe(e.getPlayer().getInventory().getItemInMainHand().getType())) {
                        ArrayList<Block> visited = new ArrayList<>();
                        visited.add(e.getBlock());
                        chopTree(e.getBlock(), visited, e.getPlayer());
                        e.setDropItems(false);
                    }
                }
            }

        }
    }
}
