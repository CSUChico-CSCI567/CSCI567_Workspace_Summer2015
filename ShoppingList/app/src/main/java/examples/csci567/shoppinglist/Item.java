package examples.csci567.shoppinglist;

import com.orm.SugarRecord;

/**
 * Created by bryandixon on 6/3/15.
 */
public class Item extends SugarRecord<Item> {
    String name;
    public Item(){
    }

    public Item(String name){
        this.name = name;
    }



}
