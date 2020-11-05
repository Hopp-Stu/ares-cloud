package com.ares.system.utils;

import java.util.Collection;
import java.util.Map;

/**
 * @description:
 * @author: yy 2020/05/15
 **/
public class AresCommonUtils {

    public static boolean isNotEmpty(Object obj) {
        if (obj instanceof Map) {
            return null != obj && ((Map) obj).size() > 0;
        } else if (obj instanceof Collection) {
            return null != obj && ((Collection) obj).size() > 0;
        } else if (obj instanceof String) {
            return null != obj && !"".equals(((String) obj).trim());
        } else {
            return null != obj;
        }
    }

    public static boolean isEmpty(Object obj) {
        return !isNotEmpty(obj);
    }

}
