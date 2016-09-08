package com.jeon.dbtest;

import android.provider.BaseColumns;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class CategoryKeywordData {

    public interface MainCateory extends BaseColumns{
        public static final String TABLE = "maincategory";
        public static final String SERVER_ID="server_id";
        public static final String CULUMN_NAME ="name";
    }
    public interface SubCategory extends BaseColumns{
        public static final String TABLE="sub_category";
        public static final String SERVER_ID="sub_server_id";
        public static final String MAIN_SERVER_ID="server_id";
        public static final String CULUMN_NAME="name";
    }
    public interface Keyword extends BaseColumns{
        public static final String TABLE = "keyword";
        public static final String SERVER_ID="keyword_id";
        public static final String CULUMN_NAME="name";
    }

    public interface Category_Key extends BaseColumns{
        public static final String TABLE ="category_key";
        public static final String KEYWORD_ID="keyword_id";
        public static final String MAIN_SERVER_ID="server_id";
    }
}
