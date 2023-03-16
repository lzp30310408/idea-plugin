package com.zp.logPlugin.extension;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Author: zhaopeng.liu
 * Date: 2023/3/13
 * Time: 23:53
 * Description: mybatis-log setting页面配置
 */
public class MybatisLogExtension implements Configurable {


    public static boolean openSqlLog = false;

    private static JCheckBox jCheckBox;

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "mybatis-log";
    }

    @Override
    public @Nullable JComponent createComponent() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        jCheckBox = new JCheckBox("开启mybatis SQL日志打印");
        jCheckBox.setPreferredSize(new Dimension(100, 60));
        if(openSqlLog) {
            jCheckBox.setSelected(true);
        } else {
            jCheckBox.setSelected(false);
        }
        panel.add(jCheckBox);
        return panel;
    }

    @Override
    public boolean isModified() {
        return openSqlLog != jCheckBox.isSelected();
    }

    @Override
    public void apply() throws ConfigurationException {
        openSqlLog = jCheckBox.isSelected();
    }
}
