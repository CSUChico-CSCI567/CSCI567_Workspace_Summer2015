package examples.csci567.retrofitexample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bryandixon on 6/9/15.
 */
public class RecipeList {
    @SerializedName("count")
    @Expose
    public int count;

    @SerializedName("recipes")
    @Expose
    public List<RecipeItem> recipeItems;

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count=count;
    }

    public List<RecipeItem> getRecipeItems(){
        return recipeItems;
    }

    public void setRecipeItems(List<RecipeItem> recipeItems){
        this.recipeItems = recipeItems;
    }
}
