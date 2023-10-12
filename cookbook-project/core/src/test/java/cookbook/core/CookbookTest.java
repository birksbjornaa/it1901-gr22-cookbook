package cookbook.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CookbookTest {
  
  private Cookbook cookbook;

  @BeforeEach
  public void setUp(){
    cookbook = new Cookbook(); //creating a new cookbook object 
  }

  @Test
  @DisplayName("Test if the addRecipe method works properly.")
  public void testAddRecipe(){
    Recipe recipe = new Recipe("Test recipe"); //creates recipe
    cookbook.addRecipe(recipe); //add recipe på tookbook
    assertTrue(cookbook.getRecipes().contains(recipe)); //asserts that the cookbook contains the recipe
    assertThrows(IllegalArgumentException.class, () -> cookbook.addRecipe(null)); //tests adding null
    assertThrows(IllegalArgumentException.class, () -> cookbook.addRecipe(recipe)); //test adding already existing recipe
    assertEquals(1, cookbook.getRecipes().size()); // tests cookbook size
  }

  @Test
  @DisplayName("Test if the removeRecipe method works properly.")
  public void testRemoveRecipe() {
    Recipe recipe = new Recipe("Test Recipe"); //creates recipe
    cookbook.addRecipe(recipe); //adds recipe
    cookbook.removeRecipe(recipe); //removes recipe
    assertFalse(cookbook.getRecipes().contains(recipe)); // tests that cookbook is empty
    assertThrows(IllegalArgumentException.class, () -> cookbook.removeRecipe(recipe)); //cannot remove 'nonexisting' recipe
  }

  @Test
  @DisplayName("Test if the filterRecipes method works properly.")
  public void testFilterRecipes() {
    Recipe testRecipe1 = new Recipe("testRecipe1");
    testRecipe1.addIngredient("ingredient1", "200.0");
    cookbook.addRecipe(testRecipe1);
        
    Recipe testRecipe2 = new Recipe("testRecipe2");
    testRecipe2.addIngredient("ingredient1", "100.0");
    cookbook.addRecipe(testRecipe2);
        
    Recipe testRecipe3 = new Recipe("testRecipe3");
    testRecipe3.addIngredient("ingredient2", "150.0");
    cookbook.addRecipe(testRecipe3);
        
    Predicate<Recipe> ingredientFilter = r -> r.getIngredients().containsKey("ingredient1");
    Collection<Recipe> ingredient1recipes = cookbook.filterRecipies(ingredientFilter);
    assertEquals(2, ingredient1recipes.size());
  }
}
