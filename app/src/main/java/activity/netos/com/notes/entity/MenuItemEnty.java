package activity.netos.com.notes.entity;

import android.graphics.drawable.Drawable;

/**
 * Created by yangcaihui on 15/3/30.
 */
public class MenuItemEnty {
    private Drawable drawable;
    private String itemName;

    public MenuItemEnty(Drawable drawable, String itemName) {
        this.drawable = drawable;
        this.itemName = itemName;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
