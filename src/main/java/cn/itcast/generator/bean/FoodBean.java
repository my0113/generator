package cn.itcast.generator.bean;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * 餐品信息
 * @ClassName FoodBean
 * @Description
 * @Created by MengYao
 * @Date 2020/11/23 15:50
 * @Version V1.0
 */
public class FoodBean implements Serializable {

    /** 菜品id **/
    private String app_food_code;
    /** 餐盒数量 **/
    private int box_num;
    /** 餐盒单价 **/
    private float box_price;
    /** 菜品名 **/
    private String food_name;
    /** 菜品价格 **/
    private float price;
    /** 菜品份数 **/
    private int quantity;
    /** 菜品sku **/
    private String sku_id;
    /** 单位(份) **/
    private String unit;

    public FoodBean() {
    }

    public FoodBean(String app_food_code, int box_num, float box_price, String food_name, float price, int quantity, String sku_id, String unit) {
        this.app_food_code = app_food_code;
        this.box_num = box_num;
        this.box_price = box_price;
        this.food_name = food_name;
        this.price = price;
        this.quantity = quantity;
        this.sku_id = sku_id;
        this.unit = unit;
    }

    public void setApp_food_code(String app_food_code) {
        this.app_food_code = app_food_code;
    }
    public String getApp_food_code() {
        return app_food_code;
    }

    public void setBox_num(int box_num) {
        this.box_num = box_num;
    }
    public int getBox_num() {
        return box_num;
    }

    public void setBox_price(float box_price) {
        this.box_price = box_price;
    }
    public float getBox_price() {
        return box_price;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }
    public String getFood_name() {
        return food_name;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public float getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }
    public String getSku_id() {
        return sku_id;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getUnit() {
        return unit;
    }

    public float getFoodTotalPrice() {
        // 餐盒单价*餐盒数量+餐品单价*餐品数量
        return box_price*box_num+price*quantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("" + app_food_code)
                .add("" + box_num)
                .add("" + box_price)
                .add("" + food_name)
                .add("" + price)
                .add("" + quantity)
                .add("" + sku_id)
                .add("" + unit)
                .toString();
    }
}