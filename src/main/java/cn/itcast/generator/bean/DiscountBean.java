package cn.itcast.generator.bean;

import java.util.StringJoiner;

/**
 * 优惠信息
 * @ClassName DiscountBean
 * @Description
 * @Created by MengYao
 * @Date 2020/11/23 15:50
 * @Version V1.0
 */
public class DiscountBean {

    private long id;
    /** 活动优惠金额，是在原价基础上减免的金额 **/
    private float reduce_fee;
    /** 该活动中美团承担的费用 **/
    private float mt_charge;
    /** 该活动中商家承担的费用 **/
    private float poi_charge;
    /** 优惠说明 **/
    private String remark;
    /**
     * 优惠活动类型:
     *      1-新用户立减；
     *      2-满减；
     *      3-抵价券；
     *      4-套餐赠送；
     *      5-满赠；
     *      6-超时赔付；
     *      7-特价菜；
     *      8-首单返优惠券；
     *      9-使用红包；
     *      11-提前下单减；
     *      12-满返优惠券；
     *      16-满免配送费；
     *      17-折扣商品；
     *      18-美团专送再减；
     *      19-点评券；
     *      20-第二份半价；
     *      21-会员免配送费；
     *      22-门店新客立减；
     *      23-买赠；
     *      24-平台新用户立减；
     *      25-满减配送费；
     *      27-指定商品满减；
     *      28-新客满减；
     *      30-阶梯满减配送费；
     *      36-平台新客减配送费；
     *      40-加价购；
     *      41-新客折扣菜；
     *      45-外卖拼团；
     *      46-外卖加价购；
     *      48-拼团减配送费；
     *      53-新客专享减包装费
     *      54-新客专享减配送费；
     *      100-满返商家代金券；
     *      101-使用商家代金券；
     *      103-进店领券；
     *      117-商品券；
     *      118-商品折扣券；
     *      305-津贴联盟；
     *      306-立减金；
     *      307-套餐推荐津贴）
     */
    private int type;

    public DiscountBean(){

    }

    public DiscountBean(float reduce_fee, float mt_charge, float poi_charge, int type, String remark) {
        this(0, reduce_fee, mt_charge, poi_charge, type, remark);
    }

    public DiscountBean(long id, float reduce_fee, float mt_charge, float poi_charge, int type, String remark) {
        this.id = id;
        this.reduce_fee = reduce_fee;
        this.mt_charge = mt_charge;
        this.poi_charge = poi_charge;
        this.type = type;
        this.remark = remark;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setMt_charge(float mt_charge) {
        this.mt_charge = mt_charge;
    }
    public float getMt_charge() {
        return mt_charge;
    }

    public void setPoi_charge(float poi_charge) {
        this.poi_charge = poi_charge;
    }
    public float getPoi_charge() {
        return poi_charge;
    }

    public void setReduce_fee(float reduce_fee) {
        this.reduce_fee = reduce_fee;
    }
    public float getReduce_fee() {
        return reduce_fee;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getRemark() {
        return remark;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add("" + id)
                .add("" + mt_charge)
                .add("" + poi_charge)
                .add("" + reduce_fee)
                .add("" + remark)
                .add("" + type)
                .toString();
    }

    public enum DiscountTypeBean {
        E1(1,"新用户立减"),
        E2(2,"满减"),
        E3(3,"抵价券"),
        E4(4,"套餐赠送"),
        E5(5,"满赠"),
        E6(6,"超时赔付"),
        E7(7,"特价菜"),
        E8(8,"首单返优惠券"),
        E9(9,"使用红包"),
        E11(11,"提前下单减"),
        E12(12,"满返优惠券"),
        E16(16,"满免配送费"),
        E17(17,"折扣商品"),
        E18(18,"美团专送再减"),
        E19(19,"点评券"),
        E20(20,"第二份半价"),
        E21(21,"会员免配送费"),
        E22(22,"门店新客立减"),
        E23(23,"买赠"),
        E24(24,"平台新用户立减"),
        E25(25,"满减配送费"),
        E27(27,"指定商品满减"),
        E28(28,"新客满减"),
        E30(30,"阶梯满减配送费"),
        E36(36,"平台新客减配送费"),
        E40(40,"加价购"),
        E41(41,"新客折扣菜"),
        E45(45,"外卖拼团"),
        E46(46,"外卖加价购"),
        E48(48,"拼团减配送费"),
        E53(53,"新客专享减包装费"),
        E54(54,"新客专享减配送费"),
        E100(100,"满返商家代金券"),
        E101(101,"使用商家代金券"),
        E103(103,"进店领券"),
        E117(117,"商品券"),
        E118(118,"商品折扣券"),
        E305(305,"津贴联盟"),
        E306(306,"立减金"),
        E307(307,"套餐推荐津贴");
        private int type;
        private String name;
        private DiscountTypeBean(int type, String name) {
            this.type = type;
            this.name = name;
        }
        public static String getName(int type) {
            for (DiscountTypeBean c : DiscountTypeBean.values()) {
                if (c.type == type) {
                    return c.name;
                }
            }
            return null;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}