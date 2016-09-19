package com.jeon.dbtest;

import java.util.Map;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class CategoryData {
    private String main_category;
    private String[] sub_category;
    private String[] keyword;
    private Map<Integer, String> main_categories;


    private int main_server_id;

    public String[] getSub_categories(int main_category) {


        return sub_category;
    }

    public String[] getKeyword(int main_category) {
        return keyword;
    }

    public String getMain_category() {

        return main_category;
    }

    public Map<Integer, String> getMaincateogries() {
        main_categories = DBManager.getInstance().getMainCategories();


        return main_categories;
    }


    public void setMain_category(int main_server_id) {
        main_category = DBManager.getInstance().getMainCategory(main_server_id);
        this.main_server_id = main_server_id;

         sub_category = DBManager.getInstance().getSubCategories(main_server_id);

        keyword = DBManager.getInstance().getKeyword(main_server_id);


        //1. 메인카테고리에 따른 서브카테고리와, 키워드 목록을 가져온다.
        //2. 리턴
        //2. request를 날릴때 메인 카테고리와, 서브 카테고리, 키워드목록을 매개변수로 넣으면 맵으로 리턴, key;main,sub,keyword


//        DBManager.getInstance().getMainCategories();

    }
}
