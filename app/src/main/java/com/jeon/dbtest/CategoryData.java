package com.jeon.dbtest;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class CategoryData {
    private String main_category;
    private String[] sub_category;
    private String[] Keyword;
    private String[] main_categories;

    public String[] getSub_category() {
        return sub_category;
    }

    public String[] getKeyword() {
        return Keyword;
    }

    private int main_server_id;

    public String getMain_category() {
        return main_category;
    }

    public void setMain_category(String main_category) {
        int main_server_id=1;
        this.main_server_id=main_server_id;
        this.main_category = main_category;

    }
}
