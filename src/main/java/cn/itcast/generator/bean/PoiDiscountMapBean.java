package cn.itcast.generator.bean;


import java.util.StringJoiner;

public class PoiDiscountMapBean {

  private long id;
  private long poiId;
  private long discountId;
  private double reduceFee;
  private double mtCharge;
  private double poiCharge;
  private java.sql.Timestamp ctime;
  private java.sql.Timestamp utime;
  private String remark;


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


  public long getDiscountId() {
    return discountId;
  }

  public void setDiscountId(long discountId) {
    this.discountId = discountId;
  }


  public double getReduceFee() {
    return reduceFee;
  }

  public void setReduceFee(double reduceFee) {
    this.reduceFee = reduceFee;
  }


  public double getMtCharge() {
    return mtCharge;
  }

  public void setMtCharge(double mtCharge) {
    this.mtCharge = mtCharge;
  }


  public double getPoiCharge() {
    return poiCharge;
  }

  public void setPoiCharge(double poiCharge) {
    this.poiCharge = poiCharge;
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
            .add("" + poiId)
            .add("" + discountId)
            .add("" + reduceFee)
            .add("" + mtCharge)
            .add("" + poiCharge)
            .add("" + ctime)
            .add("" + utime)
            .add("" + remark)
            .toString();
  }
}
