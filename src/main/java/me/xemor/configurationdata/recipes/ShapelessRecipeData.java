package me.xemor.configurationdata.recipes;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import me.xemor.configurationdata.CompulsoryJsonProperty;
import me.xemor.configurationdata.ConfigurationData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.HashMap;
import java.util.Map;

public class ShapelessRecipeData {

    @CompulsoryJsonProperty
    private ItemStack result;
    @CompulsoryJsonProperty
    private Map<Material, Integer> ingredients = new HashMap<>();
    @CompulsoryJsonProperty
    @JsonAlias("unique_key")
    private String uniqueKey;
    @JsonIgnore
    public boolean hasRegisteredRecipe = false;

    public Recipe getRecipeAndLazyRegister() {
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(ConfigurationData.getPlugin(), uniqueKey), result);
        for (Map.Entry<Material, Integer> entry : ingredients.entrySet()) {
            recipe.addIngredient(entry.getValue(), entry.getKey());
        }
        if (!hasRegisteredRecipe) {
            hasRegisteredRecipe = true;
            Bukkit.addRecipe(recipe);
        }
        return recipe;
    }


}
