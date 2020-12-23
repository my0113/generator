package cn.itcast.generator.bean;

import cn.itcast.generator.utils.FullUtil;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

/**
 * 外卖订单信息
 * @ClassName OrderBean
 * @Description
 * @Created by MengYao
 * @Date 2020/11/23 15:50
 * @Version V1.0
 */
public class OrderBean implements Serializable {

    /** 订单创建时间 **/
    private long cTime;
    /** 备注 **/
    private String caution;
    /** 城市Id **/
    private long cityId;
    /** 用户预计送达时间，“立即送达”时为0 **/
    private long deliveryTime;
    /** 订单菜品详情 **/
    private List<FoodBean> detail;
    /** 三方系统中的门店Id **/
    private String ePoiId;
    /** 订单优惠信息 **/
    private List<DiscountBean> extras;
    /** 是否需要发票:1-需要发票；0-不需要 **/
    private int hasInvoiced;
    /** 发票抬头 **/
    private String invoiceTitle;
    /** 发票税号 **/
    private String taxpayerId;
    /** 是否第三方配送 0：否；1：是   （目前基本上不支持第三方配送） **/
    private int isThirdShipping;
    /** 实际送餐地址纬度（高德系坐标） **/
    private double latitude;
    /** 实际送餐地址经度（高德系坐标） **/
    private double longitude;
    /** 取消配送时间 **/
    private long logisticsCancelTime;
    /** 配送类型码 **/
    private String logisticsCode;
    /** 配送完成时间 **/
    private long logisticsCompletedTime;
    /** 配送单确认时间，骑手接单时间 **/
    private long logisticsConfirmTime;
    /** 骑手电话 **/
    private String logisticsDispatcherMobile;
    /** 骑手姓名 **/
    private String logisticsDispatcherName;
    /** 骑手取单时间 **/
    private long logisticsFetchTime;
    /** 配送方ID **/
    private int logisticsId;
    /** 配送方名称 **/
    private String logisticsName;
    /** 配送单下单时间 **/
    private long logisticsSendTime;
    /** 配送订单状态 **/
    private int logisticsStatus;
    /** 订单完成时间 **/
    private long orderCompletedTime;
    /** 商家确认时间 **/
    private long orderConfirmTime;
    /** 订单取消时间 **/
    private long orderCancelTime;
    /** 订单Id **/
    private long orderId;
    /** 订单展示Id **/
    private long orderIdView;
    /** 用户下单时间 **/
    private long orderSendTime;
    /** 订单原价 **/
    private float originalPrice;
    /** 订单支付类型（1：货到付款；2：在线支付） **/
    private int payType;
    /** 门店地址 **/
    private String poiAddress;
    /** 门店Id **/
    private long poiId;
    /** 门店名称 **/
    private String poiName;
    /** 门店服务电话 **/
    private String poiPhone;
    /** 收货人地址 **/
    private String recipientAddress;
    /** 收货人名称 **/
    private String recipientName;
    /** 收货人电话 **/
    private String recipientPhone;
    /** 备份隐私号 **/
    private String backupRecipientPhone;
    /** 骑手电话 **/
    private String shippERPhone;
    /** 配送费用 **/
    private float shippingFee;
    /** 订单状态 **/
    private int status;
    /** 总价 **/
    private float total;
    /** 订单更新时间 **/
    private long uTime;
    /** 门店当天的订单流水号（每天流水号从1开始） **/
    private int daySeq;
    /** 就餐人数 **/
    private int dinnersNumber;
    /** 取餐类型 **/
    private int pickType;
    /** 用户是否收藏此门店 **/
    private boolean isFavorites;
    /** 用户是否第一次在此门店点餐 **/
    private boolean isPoiFirstOrder;
    /** 订单业务打标枚举，即业务身份（json字符串格式的数组，例："[16]"） **/
    private String orderTagList;

    public OrderBean() {
    }

    public OrderBean(long cTime, String caution, long cityId, long deliveryTime, List<FoodBean> detail, String ePoiId, List<DiscountBean> extras, int hasInvoiced, String invoiceTitle, String taxpayerId, int isThirdShipping, double latitude, double longitude, long logisticsCancelTime, String logisticsCode, long logisticsCompletedTime, long logisticsConfirmTime, String logisticsDispatcherMobile, String logisticsDispatcherName, long logisticsFetchTime, int logisticsId, String logisticsName, long logisticsSendTime, int logisticsStatus, long orderCompletedTime, long orderConfirmTime, long orderCancelTime, long orderId, long orderIdView, long orderSendTime, float originalPrice, int payType, String poiAddress, long poiId, String poiName, String poiPhone, String recipientAddress, String recipientName, String recipientPhone, String backupRecipientPhone, String shippERPhone, float shippingFee, int status, float total, long uTime, int daySeq, int dinnersNumber, int pickType, boolean isFavorites, boolean isPoiFirstOrder, String orderTagList) {
        this.cTime = cTime;
        this.caution = caution;
        this.cityId = cityId;
        this.deliveryTime = deliveryTime;
        this.detail = detail;
        this.ePoiId = ePoiId;
        this.extras = extras;
        setHasInvoiced(hasInvoiced);
        this.invoiceTitle = invoiceTitle;
        this.taxpayerId = taxpayerId;
        this.isThirdShipping = isThirdShipping;
        this.latitude = latitude;
        this.longitude = longitude;
        this.logisticsCancelTime = logisticsCancelTime;
        this.logisticsCode = logisticsCode;
        this.logisticsCompletedTime = logisticsCompletedTime;
        this.logisticsConfirmTime = logisticsConfirmTime;
        this.logisticsDispatcherMobile = logisticsDispatcherMobile;
        this.logisticsDispatcherName = logisticsDispatcherName;
        this.logisticsFetchTime = logisticsFetchTime;
        this.logisticsId = logisticsId;
        this.logisticsName = logisticsName;
        this.logisticsSendTime = logisticsSendTime;
        this.logisticsStatus = logisticsStatus;
        this.orderCompletedTime = orderCompletedTime;
        this.orderConfirmTime = orderConfirmTime;
        this.orderCancelTime = orderCancelTime;
        this.orderId = orderId;
        this.orderIdView = orderIdView;
        this.orderSendTime = orderSendTime;
        this.originalPrice = originalPrice;
        setPayType(payType);
        this.poiAddress = poiAddress;
        this.poiId = poiId;
        this.poiName = poiName;
        this.poiPhone = poiPhone;
        this.recipientAddress = recipientAddress;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.backupRecipientPhone = backupRecipientPhone;
        this.shippERPhone = shippERPhone;
        this.shippingFee = shippingFee;
        this.status = status;
        this.total = total;
        this.uTime = uTime;
        this.daySeq = daySeq;
        this.dinnersNumber = dinnersNumber;
        this.pickType = pickType;
        this.isFavorites = isFavorites;
        this.isPoiFirstOrder = isPoiFirstOrder;
        this.orderTagList = orderTagList;
    }

    public long getcTime() {
        return cTime;
    }

    public void setcTime(long cTime) {
        this.cTime = cTime;
    }

    public String getCaution() {
        return caution;
    }

    public void setCaution(String caution) {
        this.caution = caution;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public List<FoodBean> getDetail() {
        return detail;
    }

    public void setDetail(List<FoodBean> detail) {
        this.detail = detail;
    }

    public String getePoiId() {
        return ePoiId;
    }

    public void setePoiId(String ePoiId) {
        this.ePoiId = ePoiId;
    }

    public List<DiscountBean> getExtras() {
        return extras;
    }

    public void setExtras(List<DiscountBean> extras) {
        this.extras = extras;
    }

    public int getHasInvoiced() {
        return hasInvoiced;
    }

    public void setHasInvoiced(int hasInvoiced) {
        if (hasInvoiced>1||hasInvoiced<0) throw new IllegalArgumentException("参数错误，必须是：1-需要发票；0-不需要");
        this.hasInvoiced = hasInvoiced;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getTaxpayerId() {
        return taxpayerId;
    }

    public void setTaxpayerId(String taxpayerId) {
        this.taxpayerId = taxpayerId;
    }

    public int getIsThirdShipping() {
        return isThirdShipping;
    }

    public void setIsThirdShipping(int isThirdShipping) {
        this.isThirdShipping = isThirdShipping;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getLogisticsCancelTime() {
        return logisticsCancelTime;
    }

    public void setLogisticsCancelTime(long logisticsCancelTime) {
        this.logisticsCancelTime = logisticsCancelTime;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public long getLogisticsCompletedTime() {
        return logisticsCompletedTime;
    }

    public void setLogisticsCompletedTime(long logisticsCompletedTime) {
        this.logisticsCompletedTime = logisticsCompletedTime;
    }

    public long getLogisticsConfirmTime() {
        return logisticsConfirmTime;
    }

    public void setLogisticsConfirmTime(long logisticsConfirmTime) {
        this.logisticsConfirmTime = logisticsConfirmTime;
    }

    public String getLogisticsDispatcherMobile() {
        return logisticsDispatcherMobile;
    }

    public void setLogisticsDispatcherMobile(String logisticsDispatcherMobile) {
        this.logisticsDispatcherMobile = logisticsDispatcherMobile;
    }

    public String getLogisticsDispatcherName() {
        return logisticsDispatcherName;
    }

    public void setLogisticsDispatcherName(String logisticsDispatcherName) {
        this.logisticsDispatcherName = logisticsDispatcherName;
    }

    public long getLogisticsFetchTime() {
        return logisticsFetchTime;
    }

    public void setLogisticsFetchTime(long logisticsFetchTime) {
        this.logisticsFetchTime = logisticsFetchTime;
    }

    public int getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(int logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public long getLogisticsSendTime() {
        return logisticsSendTime;
    }

    public void setLogisticsSendTime(long logisticsSendTime) {
        this.logisticsSendTime = logisticsSendTime;
    }

    public int getLogisticsStatus() {
        return logisticsStatus;
    }

    public void setLogisticsStatus(int logisticsStatus) {
        this.logisticsStatus = logisticsStatus;
    }

    public long getOrderCompletedTime() {
        return orderCompletedTime;
    }

    public void setOrderCompletedTime(long orderCompletedTime) {
        this.orderCompletedTime = orderCompletedTime;
    }

    public long getOrderConfirmTime() {
        return orderConfirmTime;
    }

    public void setOrderConfirmTime(long orderConfirmTime) {
        this.orderConfirmTime = orderConfirmTime;
    }

    public long getOrderCancelTime() {
        return orderCancelTime;
    }

    public void setOrderCancelTime(long orderCancelTime) {
        this.orderCancelTime = orderCancelTime;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderIdView() {
        return orderIdView;
    }

    public void setOrderIdView(long orderIdView) {
        this.orderIdView = orderIdView;
    }

    public long getOrderSendTime() {
        return orderSendTime;
    }

    public void setOrderSendTime(long orderSendTime) {
        this.orderSendTime = orderSendTime;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        if (payType>2||payType<1) throw new IllegalArgumentException("参数错误，必须是：1-货到付款；2-在线支付");
        this.payType = payType;
    }

    public String getPoiAddress() {
        return poiAddress;
    }

    public void setPoiAddress(String poiAddress) {
        this.poiAddress = poiAddress;
    }

    public long getPoiId() {
        return poiId;
    }

    public void setPoiId(long poiId) {
        this.poiId = poiId;
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

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getBackupRecipientPhone() {
        return backupRecipientPhone;
    }

    public void setBackupRecipientPhone(String backupRecipientPhone) {
        this.backupRecipientPhone = backupRecipientPhone;
    }

    public String getShippERPhone() {
        return shippERPhone;
    }

    public void setShippERPhone(String shippERPhone) {
        this.shippERPhone = shippERPhone;
    }

    public float getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(float shippingFee) {
        this.shippingFee = shippingFee;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public long getuTime() {
        return uTime;
    }

    public void setuTime(long uTime) {
        this.uTime = uTime;
    }

    public int getDaySeq() {
        return daySeq;
    }

    public void setDaySeq(int daySeq) {
        this.daySeq = daySeq;
    }

    public int getDinnersNumber() {
        return dinnersNumber;
    }

    public void setDinnersNumber(int dinnersNumber) {
        this.dinnersNumber = dinnersNumber;
    }

    public int getPickType() {
        return pickType;
    }

    public void setPickType(int pickType) {
        this.pickType = pickType;
    }

    public boolean isFavorites() {
        return isFavorites;
    }

    public void setFavorites(boolean favorites) {
        isFavorites = favorites;
    }

    public boolean isPoiFirstOrder() {
        return isPoiFirstOrder;
    }

    public void setPoiFirstOrder(boolean poiFirstOrder) {
        isPoiFirstOrder = poiFirstOrder;
    }

    public String getOrderTagList() {
        return orderTagList;
    }

    public void setOrderTagList(String orderTagList) {
        this.orderTagList = orderTagList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", cTime))
                .add("" + caution)
                .add("" + cityId)
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", deliveryTime))
                .add("" + detail)
                .add("" + ePoiId)
                .add("" + extras)
                .add("" + hasInvoiced)
                .add("" + invoiceTitle)
                .add("" + taxpayerId)
                .add("" + isThirdShipping)
                .add("" + latitude)
                .add("" + longitude)
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", logisticsCancelTime))
                .add("" + logisticsCode)
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", logisticsCompletedTime))
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", logisticsConfirmTime))
                .add("" + logisticsDispatcherMobile)
                .add("" + logisticsDispatcherName)
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", logisticsFetchTime))
                .add("" + logisticsId)
                .add("" + logisticsName)
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", logisticsSendTime))
                .add("" + logisticsStatus)
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", orderCompletedTime))
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", orderConfirmTime))
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", orderCancelTime))
                .add("" + orderId)
                .add("" + orderIdView)
                .add("" + FullUtil.toDateStr("yyyy-MM-dd HH:mm:ss", orderSendTime))
                .add("" + originalPrice)
                .add("" + payType)
                .add("" + poiAddress)
                .add("" + poiId)
                .add("" + poiName)
                .add("" + poiPhone)
                .add("" + recipientAddress)
                .add("" + recipientName)
                .add("" + recipientPhone)
                .add("" + backupRecipientPhone)
                .add("" + shippERPhone)
                .add("" + shippingFee)
                .add("" + status)
                .add("" + total)
                .add("" + uTime)
                .add("" + daySeq)
                .add("" + dinnersNumber)
                .add("" + pickType)
                .add("" + isFavorites)
                .add("" + isPoiFirstOrder)
                .add("" + orderTagList)
                .toString();
    }
}