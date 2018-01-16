package com.jmper.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 郑和明
 * @version 1.0 (createTime:2018-01-12 16:52:51)
 */
public class FilterChainDefinitionMap {


    public static Map<String, String> linkedHashMap() {
        Map<String, String> map = new LinkedHashMap<>();

        map.put("/login.jsp", "anon");
        map.put("/shiro/login.do", "anon");
        map.put("/shiro/logout.do", "logout");
        map.put("/rest/**", "anon");
        map.put("/** ", "authc");

        return map;
    }

}
