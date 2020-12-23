package cn.itcast.generator.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName FullUtil
 * @Description
 * @Created by MengYao
 * @Date 2020/12/16 17:35
 * @Version V1.0
 */
public class FullUtil {

    private static final String CHARSET_NAME = "UTF-8";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("conf", Locale.SIMPLIFIED_CHINESE);

    public static Connection getConnection() {
        return getConnection(RESOURCE_BUNDLE.getString("driver"), RESOURCE_BUNDLE.getString("url"), RESOURCE_BUNDLE.getString("user"), RESOURCE_BUNDLE.getString("password"));
    }

    public static Connection getConnection(String driver, String url, String user, String password) {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int execute(String sql, Function<String, Integer> func) {
        return func.apply(sql);
    }

    public static <T> void save(String sql, List<T> data, BiConsumer<String, List<T>> func) {
        func.accept(sql, data);
    }

    public static <SQL,DATA,RESULT> RESULT save(SQL sql, List<DATA> data, BiFunction<SQL,List<DATA>,RESULT> func) {
        return func.apply(sql, data);
    }

    public static <SQL,CONDITION,RESULT> RESULT query(SQL sql, CONDITION condition, BiFunction<SQL, CONDITION, RESULT> func) {
        return func.apply(sql, condition);
    }

    public static String loadFileAsText(String fileName) {
        try {
            return IOUtils.resourceToString("/"+fileName, Charset.forName(CHARSET_NAME));
        } catch (UnsupportedEncodingException| FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加载文本文件，返回List<String>
     * @param fileName  文本文件所在路径
     * @return          List<String>
     */
    public static List<String> loadFile(String fileName) {
        return loadJson(fileName, null);
    }

    /**
     * 加载json文件，返回List<String>
     * @param jsonFileName  json文件所在路径
     * @return          List<String>
     */
    public static List<String> loadJson(String jsonFileName) {
        return loadJson(jsonFileName, null);
    }

    /**
     * 加载json文件，返回List<T>
     * @param jsonFileName  json文件所在路径
     * @param caz       T
     * @param <T>       List<T>
     * @return
     */
    public static <T> List<T> loadJson(String jsonFileName, Class<T> caz) {
        try {
            // 从json文件路径中加载
            List<String> lines = IOUtils.readLines(getContext(jsonFileName), CHARSET_NAME);
            if (Objects.isNull(caz)) {
                return (List<T>) lines;
            }
            return lines.stream().map(jsonStr-> JSON.parseObject(jsonStr, caz)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream getContext(String fileName) {
        return FullUtil.class.getClass().getResourceAsStream("/"+fileName);
    }

    private static String getPath(String fileName) throws UnsupportedEncodingException {
        // 获取json文件路径
        String path = FullUtil.class.getClass().getResource("/"+fileName).getPath();
        // 防止path中因中文乱码或空格导致的FileNotFoundException
        if (Objects.nonNull(path)) {
            path = URLDecoder.decode(path, CHARSET_NAME);
        }
        return path;
    }

    public static void close(Connection connection) { closeAll(connection, null, null, null); }
    public static void close(Statement st) { closeAll(null, st, null, null); }
    public static void close(PreparedStatement ps) { closeAll(null, null, ps, null); }
    public static void close(ResultSet rs) { closeAll(null, null, null, rs); }
    public static void close(Statement st, ResultSet rs) { closeAll(null, st, null, rs); }
    public static void close(Connection connection, Statement st, ResultSet rs) { closeAll(connection, st, null, rs); }
    public static void close(Connection connection, Statement st) { closeAll(connection, st, null, null); }
    public static void close(PreparedStatement ps, ResultSet rs) { closeAll(null, null, ps, rs); }
    public static void close(Connection connection, PreparedStatement ps, ResultSet rs) { closeAll(connection, null, ps, rs); }
    public static void closeAll(Connection connection, Statement st, PreparedStatement ps, ResultSet rs) {
        try {
            if (null!=rs&&!rs.isClosed()) {
                rs.close();
            }
            if (null!=ps&&!ps.isClosed()) {
                ps.close();
            }
            if (null!=st&&!st.isClosed()) {
                st.close();
            }
            if (null!=connection&&!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String toDateStr(String pattern, long ts) {
        if ((ts+"").length()!=13){
            ts = ts*1000;
        }
        return new SimpleDateFormat(pattern).format(new Date(ts));
    }

}
