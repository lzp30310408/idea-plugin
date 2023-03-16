package com.zp.logPlugin.run;

import cn.hutool.core.util.StrUtil;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.runners.JavaProgramPatcher;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.JavaSdkVersion;
import com.intellij.openapi.projectRoots.Sdk;
import com.zp.logPlugin.extension.MybatisLogExtension;
import com.zp.logPlugin.utils.PluginUtil;

import java.util.Objects;

/**
 * Author: zhaopeng.liu
 * Date: 2023/3/15
 * Time: 0:12
 * Description: 运行时监听每个启动的进程
 */
public class MybatisLogRun extends JavaProgramPatcher {


    @Override
    public void patchJavaParameters(Executor executor, RunProfile runProfile, JavaParameters javaParameters) {
        if (!(runProfile instanceof RunConfiguration)) {
            return;
        }
        Sdk jdk = javaParameters.getJdk();
        if (Objects.isNull(jdk)) {
            return;
        }
        JavaSdkVersion version = JavaSdk.getInstance().getVersion(jdk);
        if (Objects.isNull(version)) {
            return;
        }
        if (version.compareTo(JavaSdkVersion.JDK_1_8) < 0) {
            return;
        }
        String agentCoreJarPath = null;
        try {
            agentCoreJarPath = PluginUtil.getAgentCoreJarPath();
            if (StrUtil.isEmpty(agentCoreJarPath)) {
                System.err.println("Cannot find agent jar!");
                return;
            }
        } catch (Exception e) {
            System.err.println("Cannot find agent jar!");
            return;
        }
        if(MybatisLogExtension.openSqlLog) {
            ParametersList vmParametersList = javaParameters.getVMParametersList();
            vmParametersList.addParametersString("-javaagent:" + agentCoreJarPath);
            vmParametersList.addNotEmptyProperty("guide-idea-plugin-probe.projectId", ((RunConfiguration) runProfile).getProject().getLocationHash());
        }
    }
}
