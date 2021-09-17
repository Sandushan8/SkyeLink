package com.example.skye.database;

import android.provider.BaseColumns;

public class ItemMaster {
    public ItemMaster() {
    }

    public static class  ItemsT implements BaseColumns {
        public static final String TABLE_NAME = "Items";
        public static final String COLUMN_ItemCode = "ItemCode";
        public static final String COLUMN_ItemName = "ItemName";
        public static final String COLUMN_ItemCategory = "ItemCategory";
        public static final String COLUMN_ItemSellPrice = "ItemSellPrice";
        public static final String COLUMN_ItemDescription = "ItemDescription";

    }
}
