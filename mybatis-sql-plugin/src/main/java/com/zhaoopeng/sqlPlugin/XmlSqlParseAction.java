package com.zhaoopeng.sqlPlugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;

public class XmlSqlParseAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String selectedText = e.getRequiredData(CommonDataKeys.EDITOR).getSelectionModel().getSelectedText();
        System.out.println("选中的sql内容为：" + selectedText);
        // TODO: 解析选中的xml中的sql
    }
}
