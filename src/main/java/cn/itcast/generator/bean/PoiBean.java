package cn.itcast.generator.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @ClassName PoiBean
 * @Description
 * @Created by MengYao
 * @Date 2020/11/18 15:34
 * @Version V1.0
 */
public class PoiBean implements Serializable {

    private long id;
    private String poiAddress;
    private String poiName;
    private String poiPhone;
    private Date ctime;
    private Date utime;
    private String remark;

    public PoiBean() {
    }

    public PoiBean(String poiAddress, String poiName, String poiPhone, Date ctime, Date utime, String remark) {
        this(0,poiAddress,poiName,poiPhone,ctime,utime,remark);
    }

    public PoiBean(long id, String poiAddress, String poiName, String poiPhone, Date ctime, Date utime, String remark) {
        this.id = id;
        this.poiAddress = poiAddress;
        this.poiName = poiName;
        this.poiPhone = poiPhone;
        this.ctime = ctime;
        this.utime = utime;
        this.remark = remark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoiAddress() {
        return poiAddress;
    }

    public void setPoiAddress(String poiAddress) {
        this.poiAddress = poiAddress;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiPhone() {
        return poiPhone;
    }

    public void setPoiPhone(String poiPhone) {
        this.poiPhone = poiPhone;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
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
                .add("" + poiAddress)
                .add("" + poiName)
                .add("" + poiPhone)
                .add("" + ctime)
                .add("" + utime)
                .add("" + remark)
                .toString();
    }
}
