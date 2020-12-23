package cn.itcast.generator;

import cn.itcast.generator.bean.*;
import cn.itcast.generator.utils.FullUtil;

import java.io.File;
import java.sql.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 请使用命令：java -jar generator-1.0-SNAPSHOT.jar
 * @ClassName Generator
 * @Description
 * @Created by MengYao
 * @Date 2020/11/23 15:50
 * @Version V1.0
 */
public class Generator {


    private static final Random random = new Random();
    private static final List<DictBean> DICT_LIST = FullUtil.loadJson("tbl_dict.json", DictBean.class);
    private static final Map<Long, DiscountBean> DISCOUNT_LIST = FullUtil.loadJson("tbl_discount.json", DiscountBean.class).stream().collect(Collectors.toMap(DiscountBean::getId, v -> v, (DiscountBean o1, DiscountBean o2)-> o2, HashMap::new));
    private static final Map<String, FoodBean> FOOD_LIST = FullUtil.loadJson("tbl_food.json", FoodBean.class).stream().collect(Collectors.toMap(FoodBean::getApp_food_code, v -> v, (FoodBean o1, FoodBean o2)-> o2, HashMap::new));
    private static final List<OrderBean> ORDER_LIST = FullUtil.loadJson("tbl_order.json", OrderBean.class);
    private static final List<OrderDiscountMapBean> ORDER_DISCOUNT_LIST = FullUtil.loadJson("tbl_order_discount_map.json", OrderDiscountMapBean.class);
    private static final List<OrderFoodMapBean> ORDER_FOOD_LIST = FullUtil.loadJson("tbl_order_food_map.json", OrderFoodMapBean.class);
    private static final List<PoiBean> POI_LIST = FullUtil.loadJson("tbl_poi.json", PoiBean.class);
    private static final List<PoiDiscountMapBean> POI_DISCOUNT_LIST = FullUtil.loadJson("tbl_poi_discount_map.json", PoiDiscountMapBean.class);
    private static final List<PoiFoodMapBean> POI_FOOD_LIST = FullUtil.loadJson("tbl_poi_food_map.json", PoiFoodMapBean.class);

    static {
        // 确保订单时间按从小到大排序
        ORDER_LIST.sort(Comparator.comparing(OrderBean::getcTime));
    }

    /**
     * DICT_LIST.forEach(System.out::println);
     * DISCOUNT_LIST.values().forEach(System.out::println);
     * FOOD_LIST.values().forEach(System.out::println);
     * ORDER_LIST.forEach(System.out::println);
     * ORDER_DISCOUNT_LIST.forEach(System.out::println);
     * ORDER_FOOD_LIST.forEach(System.out::println);
     * POI_LIST.forEach(System.out::println);
     * POI_DISCOUNT_LIST.forEach(System.out::println);
     * POI_FOOD_LIST.forEach(System.out::println);
     * @param args
     */
    public static void main(String[] args) {
        // 初始化数据库表结构
        initBaseDB();
        // 初始化基础的维度数据
        initBaseDim();
        // 生成订单数据
        generator();

    }

    private static void initBaseDB() {
        try {
            // 加载数据文件
            String contextSql = FullUtil.loadFileAsText("rtdw-struct.sql");
            if (Objects.isNull(contextSql)) return;
            // 初始化connection
            Connection connection = FullUtil.getConnection();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            // 按;切分contextSql中的SQL子串
            StringTokenizer semiColonTokenizer = new StringTokenizer(contextSql, ";");
            int counter = 0;
            while (semiColonTokenizer.hasMoreTokens()) {
                String nextToken = semiColonTokenizer.nextToken();
                if (nextToken.length() > 10) {
                    statement.addBatch(nextToken+";");
                    counter++;
                }
            }
            int[] result = statement.executeBatch();
            if (result.length==counter) {
                connection.commit();
                System.out.println("==== 初始化数据库表成功！ ====");
            }
            FullUtil.close(connection, statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化维度数据
     *      dict表（全局数据字典）
     *      food表（餐品）
     *      discount表（优惠券）
     *      poi表（商户）
     *      poi_food表（商户上架的商品）
     *      poi_discount表（商户提供的优惠券）
     */
    private static void initBaseDim() {
        long failCount = 0;
        try {
            // 初始化connection
            Connection connection = FullUtil.getConnection();
            connection.setAutoCommit(false);
            // 加载数据文件
            List<String> inserts = FullUtil.loadFile("rtdw-data.sql");
            if (Objects.nonNull(inserts)) {
                failCount = inserts.stream().map(sql-> FullUtil.execute(sql, insertSql-> {
                    try {
                        return connection.prepareStatement(insertSql).executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return 0;
                })).filter(state->state==0).count();
            }
            if (failCount == 0) {
                connection.commit();
                System.out.println("==== 初始化基础数据成功！ ====");
            } else {
                connection.rollback();
                System.err.println("==== 初始化基础数据失败！ ====");
            }
            FullUtil.close(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生产事实数据
     *      order表（订单）
     *      order_food表（订单中包含的餐品）
     *      order_discount表（订单中包含的优惠券）
     */
    private static void generator() {
        System.out.println("==== 开始生成订单！ ====");
        AtomicLong counter = new AtomicLong(0L);
        // 初始化connection
        Connection connection = FullUtil.getConnection();
        // 生成订单数据
        ORDER_LIST.forEach(bean -> {
            // 1、取出订单（订单）
            long orderId = bean.getOrderId();//订单ID
            float total = bean.getTotal();//总金额
            float shippingFee = bean.getShippingFee();//运费
            long poiId = bean.getPoiId();//店铺ID
            // 2、取出订单对应的商品（订单商品）
            List<OrderFoodMapBean> orderFoodMapBeans = ORDER_FOOD_LIST.stream().filter(orderFood -> orderFood.getOrderId() == orderId).collect(Collectors.toList());
            // 3、取出订单对应的优惠券（订单优惠券）
            List<OrderDiscountMapBean> orderDiscountMapBeans = ORDER_DISCOUNT_LIST.stream().filter(orderDiscount -> orderDiscount.getOrderId() == orderId).collect(Collectors.toList());
            // 4、入库（将订单、订单商品、订单优惠券数据写入MySQL）
            String fullFoods = null;
            String fullDiscount = null;
            try {
                if (saveOrder(connection, Arrays.asList(bean)) &&
                        saveOrderFood(connection, orderFoodMapBeans) &&
                        saveOrderDiscount(connection, orderDiscountMapBeans)) {
                    connection.commit();
                    if (Objects.nonNull(orderFoodMapBeans)) {
                        fullFoods = orderFoodMapBeans.stream().map(ofm -> FOOD_LIST.get(ofm.getFoodId())).filter(Objects::nonNull).map(food -> "---- "+food.getApp_food_code() + "\t" + food.getFood_name()+" ----").reduce((o1, o2) -> o1.concat(";\r\n").concat(o2)).orElseGet(()->"未购买餐品！");
                    }
                    if (Objects.nonNull(orderDiscountMapBeans)) {
                        fullDiscount = orderDiscountMapBeans.stream().map(odm -> DISCOUNT_LIST.get(odm.getId())).filter(Objects::nonNull).map(discount -> "---- "+discount.getId() + "\t" + discount.getRemark()+" ----").reduce((o1, o2) -> o1.concat(";\r\n").concat(o2)).orElseGet(()->"未使用红包！");
                    }
                    System.out.println("==== 第"+counter.incrementAndGet()+"条：订单["+orderId+"] ====\r\n---- 餐品清单 ----\r\n"+fullFoods+";\r\n---- 优惠券 ----\r\n"+fullDiscount);
                } else {
                    connection.rollback();
                    System.out.println("==== 生成订单("+orderId+")失败！");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 5、随机生成0毫秒~10秒之间的休眠时间
            try {
                Thread.sleep((int)(Math.random()*10001));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        FullUtil.close(connection);
    }

    // 订单入库
    private static boolean saveOrder(Connection connection, List<OrderBean> beans) {
        return FullUtil.save("INSERT INTO rtdw.tbl_order(cTime,caution,cityId,deliveryTime,ePoiId,hasInvoiced,invoiceTitle,taxpayerId,isThirdShipping,latitude,longitude,logisticsCancelTime,logisticsCode,logisticsCompletedTime,logisticsConfirmTime,logisticsDispatcherMobile,logisticsDispatcherName,logisticsFetchTime,logisticsId,logisticsName,logisticsSendTime,logisticsStatus,orderCompletedTime,orderConfirmTime,orderCancelTime,orderId,orderIdView,orderSendTime,originalPrice,payType,poiAddress,poiId,poiName,poiPhone,recipientAddress,recipientName,recipientPhone,backupRecipientPhone,shippERPhone,shippingFee,status,total,uTime,daySeq,dinnersNumber,pickType,isFavorites,isPoiFirstOrder,orderTagList)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", beans, (sql,data)->{
            int[] result = null;
            PreparedStatement ps = null;
            try {
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(sql);
                for (OrderBean bean : data) {
                    ps.setTimestamp(1,new Timestamp(bean.getcTime()));
                    ps.setString(2,bean.getCaution());
                    ps.setLong(3,bean.getCityId());
                    ps.setTimestamp(4,new Timestamp(bean.getDeliveryTime()));
                    ps.setString(5,bean.getePoiId());
                    ps.setInt(6,bean.getHasInvoiced());
                    ps.setString(7,bean.getInvoiceTitle());
                    ps.setString(8,bean.getTaxpayerId());
                    ps.setInt(9,bean.getIsThirdShipping());
                    ps.setDouble(10,bean.getLatitude());
                    ps.setDouble(11,bean.getLongitude());
                    ps.setTimestamp(12,new Timestamp(bean.getLogisticsCancelTime()==0?-28800000:bean.getLogisticsCancelTime()));
                    ps.setString(13,bean.getLogisticsCode());
                    ps.setTimestamp(14,new Timestamp(bean.getLogisticsCompletedTime()));
                    ps.setTimestamp(15,new Timestamp(bean.getLogisticsConfirmTime()));
                    ps.setString(16,bean.getLogisticsDispatcherMobile());
                    ps.setString(17,bean.getLogisticsDispatcherName());
                    ps.setTimestamp(18,new Timestamp(bean.getLogisticsFetchTime()));
                    ps.setInt(19,bean.getLogisticsId());
                    ps.setString(20,bean.getLogisticsName());
                    ps.setTimestamp(21,new Timestamp(bean.getLogisticsSendTime()));
                    ps.setInt(22,bean.getLogisticsStatus());
                    ps.setTimestamp(23,new Timestamp(bean.getOrderCompletedTime()));
                    ps.setTimestamp(24,new Timestamp(bean.getOrderConfirmTime()));
                    ps.setTimestamp(25,new Timestamp(bean.getOrderCancelTime()));
                    ps.setLong(26,bean.getOrderId());
                    ps.setLong(27,bean.getOrderIdView());
                    ps.setTimestamp(28,new Timestamp(bean.getOrderSendTime()));
                    ps.setFloat(29,bean.getOriginalPrice());
                    ps.setLong(30,bean.getPayType());
                    ps.setString(31,bean.getPoiAddress());
                    ps.setLong(32,bean.getPoiId());
                    ps.setString(33,bean.getPoiName());
                    ps.setString(34,bean.getPoiPhone());
                    ps.setString(35,bean.getRecipientAddress());
                    ps.setString(36,bean.getRecipientName());
                    ps.setString(37,bean.getRecipientPhone());
                    ps.setString(38,bean.getBackupRecipientPhone());
                    ps.setString(39,bean.getShippERPhone());
                    ps.setFloat(40,bean.getShippingFee());
                    ps.setInt(41,bean.getStatus());
                    ps.setFloat(42,bean.getTotal());
                    ps.setTimestamp(43,new Timestamp(bean.getuTime()));
                    ps.setInt(44,bean.getDaySeq());
                    ps.setInt(45,bean.getDinnersNumber());
                    ps.setInt(46,bean.getPickType());
                    ps.setInt(47,bean.isFavorites()?1:0);
                    ps.setInt(48,bean.isPoiFirstOrder()?1:0);
                    ps.setString(49,bean.getOrderTagList());
                    ps.addBatch();
                }
                result = ps.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                FullUtil.close(ps);
            }
            return result.length == data.size();
        });
    }

    // 订单商品入库
    private static boolean saveOrderFood(Connection connection, List<OrderFoodMapBean> beans) {
        return FullUtil.save("INSERT INTO rtdw.tbl_order_food_map(orderId,foodId,ctime,utime)VALUES(?,?,NOW(),NOW())", beans, (sql, data)->{
            int[] result = null;
            PreparedStatement ps = null;
            try {
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(sql);
                for (OrderFoodMapBean bean : data) {
                    ps.setLong(1, bean.getOrderId());
                    ps.setString(2, bean.getFoodId());
                    ps.addBatch();
                }
                result = ps.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                FullUtil.close(ps);
            }
            return result.length == data.size();
        });
    }

    // 订单优惠券入库
    private static boolean saveOrderDiscount(Connection connection, List<OrderDiscountMapBean> beans) {
        return FullUtil.save("INSERT INTO rtdw.tbl_order_discount_map(orderId,discountId,ctime,utime)VALUES(?,?,NOW(),NOW())", beans, (sql, data)->{
            int[] result = null;
            PreparedStatement ps = null;
            try {
                connection.setAutoCommit(false);
                ps = connection.prepareStatement(sql);
                for (OrderDiscountMapBean bean : data) {
                    ps.setLong(1, bean.getOrderId());
                    ps.setLong(2, bean.getDiscountId());
                    ps.addBatch();
                }
                result = ps.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                FullUtil.close(ps);
            }
            return result.length == data.size();
        });
    }

}
