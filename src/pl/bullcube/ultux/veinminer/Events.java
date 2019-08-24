package pl.bullcube.ultux.veinminer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Events implements Listener {

    private boolean isAnOre(Material material){
        if (material.equals(Material.COAL_ORE) || material.equals(Material.DIAMOND_ORE) || material.equals(Material.REDSTONE_ORE) ||material.equals(Material.LAPIS_ORE)
                || material.equals(Material.EMERALD_ORE) || material.equals(Material.GOLD_ORE) || material.equals(Material.IRON_ORE)) return true;
        return false;
    }

    private boolean isAnOre(Block block, Material oreType){
        if (block.getType().equals(oreType)) return true;
        return false;

    }

    private boolean hasPickaxe(Player player){
        if (player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_PICKAXE) || player.getInventory().getItemInMainHand().getType().equals(Material.STONE_PICKAXE)
                || player.getInventory().getItemInMainHand().getType().equals(Material.IRON_PICKAXE) || player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE)
                || player.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_PICKAXE)) return true;
        return false;
    }

    private void dropAndBreak(Block block){
        if (block.getType().equals(Material.IRON_ORE)) block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.IRON_ORE));
        if (block.getType().equals(Material.COAL_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.COAL));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
            }
        if (block.getType().equals(Material.EMERALD_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.EMERALD));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
        }
        if (block.getType().equals(Material.GOLD_ORE)) block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.GOLD_ORE));
        if (block.getType().equals(Material.DIAMOND_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.DIAMOND));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
        }
        if (block.getType().equals(Material.LAPIS_ORE)){
            block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.LAPIS_LAZULI));
            block.getWorld().spawn(block.getLocation(), ExperienceOrb.class);
        }
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
    private void lookForOre(Block block, ArrayList<Block> stack){
        int num = 0;
//        for (int x = -3; x <= 3; x++){
//            for (int y = -3; y <= 3; y++){
//                for (int z = -3; z <= 3; z++){
//                    Block possibleOre = block.getWorld().getBlockAt(block.getLocation().add(new Vector(x, y, z)));
//
//                }
//            }
//        }
        int i = 0;
        while (stack.size() > i){
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
            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.pluginInstance, new Runnable() {
                @Override
                public void run() {
                    stack.get(finalI).setType(Material.AIR);

                }
            }, 1);
            dropAndBreak(stack.get(i));
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
                    lookForOre(e.getBlock(), visited);

                }
            }
        }
    }
}
