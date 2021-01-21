package me.cuchufleto.Gamble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class Main extends JavaPlugin {

    List<Inventory> invs = new ArrayList<Inventory>();
    public static ItemStack[] contents;
    private int itemIndex = 0;
    private int gambleFee = 32;

    @Override
    public void onEnable(){

    }

    @Override
    public void onDisable(){

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(label.equalsIgnoreCase("gamble"))
            if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        ItemStack fee = new ItemStack(Material.DIAMOND);
        fee.setAmount(gambleFee);
        if (player.getInventory().getItemInMainHand().isSimilar(fee) || player.getInventory().getItemInMainHand().equals(fee)){
            player.getInventory().removeItem(fee);
            //spin the wheel!
            spin(player);
            return true;
        }
        player.sendMessage(ChatColor.RED + "You need " + gambleFee + " diamonds to gamble!");
        return false;
    }

    public void shuffle(Inventory inv){
        if(contents == null) {
            ItemStack[] items = new ItemStack[10];
            items[0] = new ItemStack(Material.STONE, 64);

            items[1] = new ItemStack(Material.IRON_SWORD, 1);
            items[2] = new ItemStack(Material.IRON_CHESTPLATE, 1);
            items[3] = new ItemStack(Material.IRON_INGOT, 64);

            items[4] = new ItemStack(Material.DIAMOND_SWORD, 1);
            items[5] = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
            items[6] = new ItemStack(Material.DIAMOND, 32);

            items[7] = new ItemStack(Material.NETHERITE_SWORD, 1);
            items[8] = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
            items[9] = new ItemStack(Material.NETHERITE_INGOT, 16);
            contents = items;
        }

        int startingIndex = ThreadLocalRandom.current().nextInt(contents.length);
        for(int i = 0; i < startingIndex; i++){
            for(int items = 9; items < 18; items++){
                inv.setItem(items, contents[(items + itemIndex) % contents.length]);
            }
            itemIndex++;
        }

        //customize appearance
        ItemStack pane = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE); //panes as placeholders
        ItemMeta meta = pane.getItemMeta();
        meta.setDisplayName("");
        pane.setItemMeta(meta);
        for(int i = 0; i < 27; i++){
            inv.setItem(i, pane);
        }
        ItemStack hopper = new ItemStack(Material.HOPPER); //hopper as pointer
        meta = hopper.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_GRAY + "|");
        hopper.setItemMeta(meta);
        inv.setItem(4, hopper);
    }

    public void spin(final Player player){
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.AQUA + "" + ChatColor.BOLD + "Good Luck!");
        shuffle(inv);
        invs.add(inv);
        player.openInventory(inv);

        Random r = new Random();
        double duration = 10.0 + (15.0 - 10.0) * r.nextDouble(); //set the roll duration to random between 10-15

        new BukkitRunnable() {//roll runnable
            double delay = 0;
            int ticks = 0;
            boolean done = false;

            public void run(){
                if(done) return; //if we're done, stop
                ticks++; //run counter
                delay += 1 / (20*duration); //slow down progressively

                if(ticks > delay * 10){
                    ticks = 0;
                    for(int items = 9; items < 18; items++) //switch items around to make rolling effect
                        inv.setItem(items, contents[(items + itemIndex) % contents.length]);

                    itemIndex++;
                    if(delay >= 0.7){ //rolling has slowed sufficiently
                        done = true;
                        new BukkitRunnable(){ //get the winning item
                            public void run(){
                                ItemStack winningItem = inv.getItem(13); //item that landed in the center
                                if(player.getInventory().firstEmpty() != -1){
                                    player.getInventory().addItem(winningItem);
                                    player.updateInventory();
                                    player.closeInventory();
                                }
                                else{
                                    player.getWorld().dropItemNaturally(player.getLocation(), winningItem);
                                }
                                cancel();
                            }
                        }.runTaskLater(Main.getPlugin(Main.class), 50);
                        cancel();
                    }
                }
            }

        }.runTaskTimer(this, 0, 1);
    }
}
