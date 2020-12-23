package cn.itcast.generator.bean;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @ClassName OrderMapBean
 * @Description
 * @Created by MengYao
 * @Date 2020/11/17 18:12
 * @Version V1.0
 */
public class OrderMapBean implements Serializable {

    private long orderId;
    private String foodId;
    private long discountId;
    private long cTime;
    private long uTime;

    public OrderMapBean() {
    }

    public OrderMapBean(long orderId, String foodId, long discountId, long cTime, long uTime) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.discountId = discountId;
        this.cTime = cTime;
        this.uTime = uTime;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public long getcTime() {
        return cTime;
    }

    public void setcTime(long cTime) {
        this.cTime = cTime;
    }

    public long getuTime() {
        return uTime;
    }

    public void setuTime(long uTime) {
        this.uTime = uTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("" + orderId)
                .add("" + foodId)
                .add("" + discountId)
                .add("" + cTime)
                .add("" + uTime)
                .toString();
    }

}
