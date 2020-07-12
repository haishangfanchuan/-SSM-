package top.theonecyl.graphicSystem.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
    // 整型转换
    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    // 长整型转换
    public static long getLong(HttpServletRequest request, String key) {
        try {
            return Long.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1;
        }
    }

    // 浮点型转换
    public static Double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return -1d;
        }
    }

    // 布尔型转换
    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return false;
        }
    }

    // 字符串转换
    public static String getString(HttpServletRequest request, String key){
        try {
            String result=request.getParameter(key);
            if (result!=null){
                result=result.trim();
            }
            if ("".equals(result)){
                result=null;
            }
            return result;
        }catch (Exception e){
            return null;
        }
    }
}

