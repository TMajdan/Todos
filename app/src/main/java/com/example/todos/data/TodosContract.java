package com.example.todos.data;

import android.provider.BaseColumns;

/**
 * Created by wojciech.majdan on 2017-06-20.
 */
// CONTRACT
    // https://app.pluralsight.com/player?course=android-database-application-sqlite-building-your-first&author=simone-alessandria&name=android-database-application-sqlite-building-your-first-m3&clip=1&mode=live


public final class TodosContract {

    public static final class TodosEntry implements BaseColumns {

        // Table name
        public static final String TABLE_NAME = "todos";
        //column (field) names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TEXT = "text";
        public static final String COLUMN_CREATED = "created";
        public static final String COLUMN_EXPIRED = "expired";
        public static final String COLUMN_DONE = "done";
        public static final String COLUMN_CATEGORY = "category";
    }

    public static final class CategoriesEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "categories";
        //column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_DESCRIPTION = "description";
    }


}
