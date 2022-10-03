package com.zhaopeng.notebook.dialog;

import com.intellij.notification.*;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.EditorTextField;
import com.zhaopeng.notebook.data.DataCenter;
import com.zhaopeng.notebook.data.DataConvert;
import com.zhaopeng.notebook.data.Notebook;
import jnr.ffi.mapper.DataConverter;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 11:11
 * Description: 添加笔记弹出层
 */
public class NotebookDialog extends DialogWrapper {

    private static EditorTextField TITLE = null;

    private static EditorTextField CONTENT = null;

    private static NotificationGroup notificationGroup = NotificationGroup.create("测试通知",
            NotificationDisplayType.BALLOON, false, null, null, null,
            null);

    public NotebookDialog() {
        super(true);
        init();
        setTitle("添加笔记");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 150));
        TITLE = new EditorTextField();
        TITLE.setPlaceholder("请输入标题");
        panel.add(TITLE, BorderLayout.CENTER);
        CONTENT = new EditorTextField();
        CONTENT.setPlaceholder("请输入内容");
        CONTENT.setPreferredSize(new Dimension(390, 120));
        panel.add(CONTENT, BorderLayout.SOUTH);
        return panel;
    }


    @Override
    protected JComponent createSouthPanel() {
        JPanel panel = new JPanel();
        JButton save = new JButton("保存笔记到列表");
        save.addActionListener(e -> {
            String title = TITLE.getText();
            String content = CONTENT.getText();
            if (StringUtils.isEmpty(title)) {
                Notification notification = notificationGroup.createNotification("保存失败",
                        "标题不能为空。", NotificationType.ERROR);
                Notifications.Bus.notify(notification);
                TITLE.requestFocus();
                return;
            }
            if (StringUtils.isEmpty(content)) {
                Notification notification = notificationGroup.createNotification("保存失败",
                        "内容不能为空。", NotificationType.ERROR);
                Notifications.Bus.notify(notification);
                CONTENT.requestFocus();
                return;
            }
            Notebook notebook = new Notebook(title, content, DataCenter.CUR_FILE_NAME, DataCenter.CUR_FILE_TYPE,
                    DataCenter.SELECT_TEXT);
            DataCenter.NOTEBOOK_LIST.add(notebook);
            DataCenter.TABLE_MODEL.addRow(DataConvert.toStringify(notebook));
            Notification notification = notificationGroup.createNotification("保存成功",
                    "笔记已保存。", NotificationType.INFORMATION);
            Notifications.Bus.notify(notification);
            NotebookDialog.this.dispose();
        });
        JButton cancel = new JButton("取消");
        cancel.addActionListener(e -> NotebookDialog.this.dispose());
        panel.add(save);
        panel.add(cancel);
        return panel;
    }
}
