package com.errorstation.wallpaper.database;

import io.realm.RealmObject;

/**
 * Created by Rubayet on 30-Oct-16.
 */

public class TimeModel extends RealmObject {
    String categoryName,lastUpdateTime;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
