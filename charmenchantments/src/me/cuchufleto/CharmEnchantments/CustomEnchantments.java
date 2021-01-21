package me.cuchufleto.CharmEnchantments;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchantments {
    public static final Enchantment EXCAVATION = new EnchantmentWrapper("excavation", "Excavation", 1);
    public static final Enchantment SUMMONING = new EnchantmentWrapper("summoning", "Summoning", 1);
    public static final Enchantment DECEPTION = new EnchantmentWrapper("deception", "Deception", 1);
    public static final Enchantment CREATION = new EnchantmentWrapper("creation", "Creation",  1);

    public static void register(Enchantment e){
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(e);

        if(!registered) registerEnchantment(e);
    }

    private static void registerEnchantment(Enchantment e) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(e);

        } catch(Exception exception) {
            registered = false;
            exception.printStackTrace();
        }
    }
}
