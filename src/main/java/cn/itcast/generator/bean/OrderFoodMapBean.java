package cn.itcast.generator.bean;


import java.util.StringJoiner;

public class OrderFoodMapBean {

  private long id;
  private long orderId;
  private String foodId;
  private java.sql.Timestamp ctime;
  private java.sql.Timestamp utime;
  private String remark;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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


  public java.sql.Timestamp getCtime() {
    return ctime;
  }

  public void setCtime(java.sql.Timestamp ctime) {
    this.ctime = ctime;
  }


  public java.sql.Timestamp getUtime() {
    return utime;
  }

  public void setUtime(java.sql.Timestamp utime) {
    this.utime = utime;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ")
            .add("" + id)
            .add("" + orderId)
            .add("" + foodId)
            .add("" + ctime)
            .add("" + utime)
            .add("" + remark)
            .toString();
  }
}
