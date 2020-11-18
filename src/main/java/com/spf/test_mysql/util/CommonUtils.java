package com.spf.test_mysql.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    /**
     * 读取request中传过来的字符串
     * 有些调用方不知道用什么方式调用，可能是【application/x-www-form-urlencoded】、【text/plain】、【application/json】
     * 该方法都能处理，但是如果是按【application/x-www-form-urlencoded】
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static Map<String, Object> getDataFromRequest(HttpServletRequest request) {
        Gson gson = new Gson();
        String type = request.getContentType();
        Map<String, Object> receiveMap = new HashMap<String, Object>();
        if ("application/x-www-form-urlencoded".equals(type)) {
            Enumeration<String> enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String key = String.valueOf(enu.nextElement());
                String value = request.getParameter(key);
                receiveMap.put(key, value);
            }
        } else {    //else是text/plain、application/json这两种情况
            BufferedReader reader = null;
            StringBuilder sb = new StringBuilder();
            try {
                reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != reader) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            receiveMap = gson.fromJson(sb.toString(), new TypeToken<Map<String, String>>() {
            }.getType());//把JSON字符串转为对象
        }
        return receiveMap;
    }
}
