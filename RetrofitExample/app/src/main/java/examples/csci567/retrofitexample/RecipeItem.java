package examples.csci567.retrofitexample;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bryandixon on 6/9/15.
 */
public class RecipeItem implements Parcelable {
    private static final String TAG = RecipeItem.class.getSimpleName();


    @SerializedName("title")
    @Expose
    private String title;
    /*@SerializedName("created_at")
    @Expose
    private String createdAt;
    @Expose
    private Integer id;
    @Expose
    private String name;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("user_id")
    @Expose
    private Integer userId;*/

    public RecipeItem(String title){
        this.title = title;
    }
    /**
     *
     * @return
     * The recipe title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    public RecipeItem() {
    }

    private RecipeItem(Parcel in) {
        Log.d(TAG, in.toString());
        this.title = in.readString();
    }

    public static final Parcelable.Creator<RecipeItem> CREATOR = new Parcelable.Creator<RecipeItem>() {
        public RecipeItem createFromParcel(Parcel source) {
            return new RecipeItem(source);
        }

        public RecipeItem[] newArray(int size) {
            return new RecipeItem[size];
        }
    };
}
