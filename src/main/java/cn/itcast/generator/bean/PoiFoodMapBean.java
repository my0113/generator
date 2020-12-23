package cn.itcast.generator.bean;


import java.util.StringJoiner;

public class PoiFoodMapBean {

  private long id;
  private long poiId;
  private String foodId;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getPoiId() {
    return poiId;
  }

  public void setPoiId(long poiId) {
    this.poiId = poiId;
  }


  public String getFoodId() {
    return foodId;
  }

  public void setFoodId(String foodId) {
    this.foodId = foodId;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ")
            .add("" + id)
            .add("" + poiId)
            .add("" + foodId)
            .toString();
  }
}
