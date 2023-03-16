package com.zp.monitor;

import cn.hutool.core.util.ReflectUtil;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Author: zhaopeng.liu
 * Date: 2023/3/15
 * Time: 1:19
 * Description: 监控方法
 */
public class MonitorMethod {

    @RuntimeType
    public static Object intercept(@This Object obj, @Origin Method method, @SuperCall Callable<?> callable,
                                   @AllArguments Object... args) throws Exception {
        try {
            return callable.call();
        } finally {
            String replaceSql = ReflectUtil.invoke(obj, "toString");
            if(replaceSql != null) {
                replaceSql = replaceSql.replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", "");
                if(System.getProperty("os.name").contains("Windows")) {
                    replaceSql = replaceSql.replace("\r\n", " ");
                }
                if(System.getProperty("os.name").contains("Unix") || System.getProperty("os.name").contains("Linux")) {
                    replaceSql = replaceSql.replace("\n", " ");
                }
                if(System.getProperty("os.name").contains("Mac")) {
                    replaceSql = replaceSql.replace("\r", " ");
                }
            }
            System.out.println("==============可执行SQL===============");
            System.out.println(replaceSql);
            System.out.println("==============可执行SQL===============");
        }
    }

}
