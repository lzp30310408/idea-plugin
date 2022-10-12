package com.zhaopeng.notebook.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.zhaopeng.notebook.data.DataCenter;
import com.zhaopeng.notebook.dialog.NotebookDialog;
import org.apache.commons.lang.StringUtils;

public class NotebookAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // 选中的文字
        DataCenter.SELECT_TEXT = e.getRequiredData(CommonDataKeys.EDITOR).getSelectionModel().getSelectedText();
        // 文件名
        DataCenter.CUR_FILE_NAME = e.getRequiredData(CommonDataKeys.PSI_FILE).getViewProvider().getVirtualFile()
                .getName();
        // 文件类型
        DataCenter.CUR_FILE_TYPE = DataCenter.CUR_FILE_NAME.substring(
                DataCenter.CUR_FILE_NAME.lastIndexOf(".") + 1);
        if(StringUtils.isEmpty(DataCenter.SELECT_TEXT)) {
            MessageDialogBuilder.yesNo("错误", "请选中要添加笔记的代码！").yesText("确定").noText("取消").show();
            return;
        }
        // 打开弹出层
        NotebookDialog notebookDialog = new NotebookDialog();
        notebookDialog.show();
    }
}
