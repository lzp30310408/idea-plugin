package com.zp.agent;

import com.zp.monitor.MonitorMethod;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * Author: zhaopeng.liu
 * Date: 2023/3/14
 * Time: 0:25
 * Description: mybatis探针
 */
public class MybatisAgent {

    /**
     * Mybatis的[com.mysql.cj.jdbc.ClientPreparedStatement#executeInternal方法，执行时调用toString()]
     * @param agentArgs
     * @param inst
     */
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("----------mybatis-log-plugin premain执行。。。----------");
        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("com.mysql.cj.jdbc.ClientPreparedStatement"))
                .transform((builder, typeDescription, classLoader, javaModule) -> {
                    return builder
                            .method(ElementMatchers.named("executeInternal")) // 拦截任意方法
                            .intercept(MethodDelegation.to(MonitorMethod.class)); // 委托
                })
                .installOn(inst);
    }

}
