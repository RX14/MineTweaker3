/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stanhebben.minetweaker.mods.ic2.machines;

import ic2.api.recipe.Recipes;
import minetweaker.MineTweakerAPI;
import minetweaker.mc172.item.TweakerItemStack;
import minetweaker.minecraft.item.IIngredient;
import minetweaker.minecraft.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.minetweaker.mods.ic2.IC2RecipeInput;
import stanhebben.minetweaker.mods.ic2.MachineAddRecipeAction;
import stanhebben.zenscript.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

/**
 * Provides access to the IC2 Extractor recipes.
 * 
 * @author Stan Hebben
 */
@ZenClass("mods.ic2.Extractor")
public class Extractor {
	/**
	 * Adds a new recipe to the extractor.
	 * 
	 * The recipe input can be any pattern, as long as the stack size is determined.
	 * The output can be any item stack.
	 * 
	 * @param output recipe output
	 * @param ingredient recipe input
	 */
	@ZenMethod
	public static void addRecipe(
			@NotNull IItemStack output,
			@NotNull IIngredient ingredient) {
		if (ingredient.getAmount() < 0) {
			MineTweakerAPI.logger.logWarning("invalid ingredient: " + ingredient + " - stack size not known");
		} else {
			MineTweakerAPI.tweaker.apply(new MachineAddRecipeAction(
					"extractor",
					Recipes.extractor,
					new ItemStack[] {(ItemStack) output.getInternal()},
					null,
					new IC2RecipeInput(ingredient)));
		}
	}
	
	/**
	 * Determines the recipe output for the given input. Will return the output
	 * of a single item, even if the stack contains multiple items.
	 * 
	 * @param input recipe input
	 * @return recipe output
	 */
	@ZenMethod
	public static IItemStack getOutput(
			@NotNull IItemStack input) {
		return new TweakerItemStack(Recipes.extractor.getOutputFor((ItemStack) input.getInternal(), false).items.get(0));
	}
}