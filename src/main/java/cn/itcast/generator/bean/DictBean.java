package cn.itcast.generator.bean;


import java.util.StringJoiner;

public class DictBean {

  private long id;
  private String dictCode;
  private String dictDesc;
  private String categoryCode;
  private String categoryDesc;
  private long sortNo;
  private String dataType;
  private String remark;
  private String locateCode;
  private long cid;
  private long uid;
  private java.sql.Timestamp ctime;
  private java.sql.Timestamp utime;
  private long version;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getDictCode() {
    return dictCode;
  }

  public void setDictCode(String dictCode) {
    this.dictCode = dictCode;
  }


  public String getDictDesc() {
    return dictDesc;
  }

  public void setDictDesc(String dictDesc) {
    this.dictDesc = dictDesc;
  }


  public String getCategoryCode() {
    return categoryCode;
  }

  public void setCategoryCode(String categoryCode) {
    this.categoryCode = categoryCode;
  }


  public String getCategoryDesc() {
    return categoryDesc;
  }

  public void setCategoryDesc(String categoryDesc) {
    this.categoryDesc = categoryDesc;
  }


  public long getSortNo() {
    return sortNo;
  }

  public void setSortNo(long sortNo) {
    this.sortNo = sortNo;
  }


  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getLocateCode() {
    return locateCode;
  }

  public void setLocateCode(String locateCode) {
    this.locateCode = locateCode;
  }


  public long getCid() {
    return cid;
  }

  public void setCid(long cid) {
    this.cid = cid;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
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


  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ")
            .add("" + id)
            .add("" + dictCode)
            .add("" + dictDesc)
            .add("" + categoryCode)
            .add("" + categoryDesc)
            .add("" + sortNo)
            .add("" + dataType)
            .add("" + remark)
            .add("" + locateCode)
            .add("" + cid)
            .add("" + uid)
            .add("" + ctime)
            .add("" + utime)
            .add("" + version)
            .toString();
  }
}
