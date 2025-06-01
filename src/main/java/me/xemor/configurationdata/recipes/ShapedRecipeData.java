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
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShapedRecipeData {

    @CompulsoryJsonProperty
    private List<String> recipe = new ArrayList<>();
    @CompulsoryJsonProperty
    private Map<Material, String> recipeKeys = new HashMap<>();
    @CompulsoryJsonProperty
    private ItemStack result;
    @CompulsoryJsonProperty
    @JsonAlias("unique_key")
    private String uniqueKey;
    @JsonIgnore
    private boolean hasRegisteredRecipe = false;

    public Recipe getRecipeAndLazyRegister() {
        ShapedRecipe shapedRecipe = new ShapedRecipe(new NamespacedKey(ConfigurationData.getPlugin(), uniqueKey), result);
        shapedRecipe.shape(recipe.toArray(new String[0]));
        for (Map.Entry<Material, String> entry : recipeKeys.entrySet()) {
            shapedRecipe.setIngredient(entry.getValue().charAt(0), entry.getKey());
        }
        if (!hasRegisteredRecipe) {
            hasRegisteredRecipe = true;
            Bukkit.addRecipe(shapedRecipe);
        }
        return shapedRecipe;
    }

}
