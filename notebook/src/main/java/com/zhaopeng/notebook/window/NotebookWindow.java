package com.zhaopeng.notebook.window;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageDialogBuilder;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.zhaopeng.notebook.data.DataCenter;
import com.zhaopeng.notebook.template.AbstractFreemarkerProcessor;
import com.zhaopeng.notebook.template.MDFreemarkerProcessor;
import com.zhaopeng.notebook.template.SourceNoteData;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 13:33
 * Description:
 */
public class NotebookWindow {
    private JTextField titleContent;
    private JTable notebookTable;
    private JButton createBtn;
    private JButton clearBtn;
    private JButton closeBtn;
    private JLabel title;
    private JPanel contentPanel;

    public NotebookWindow(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        init();
        createBtn.addActionListener(al -> {
            String text = titleContent.getText();
            if (StringUtils.isEmpty(text)) {
                MessageDialogBuilder.yesNo("错误", "请选择生成文件名!").yesText("确认").noText("取消").show();
                return;
            }
            // 用户选择文件位置后输出文件
            VirtualFile virtualFile = FileChooser.chooseFile(FileChooserDescriptorFactory.createSingleFileDescriptor(), project, project.getProjectFile());
            if (virtualFile != null) {
                String path = virtualFile.getPath();
                SourceNoteData sourceNoteData = new SourceNoteData();
                sourceNoteData.setNotebookList(DataCenter.NOTEBOOK_LIST);
                sourceNoteData.setTopic(text);
                sourceNoteData.setFileName(path + File.separator + text + ".md");
                AbstractFreemarkerProcessor processor = new MDFreemarkerProcessor();
                try {
                    processor.process(sourceNoteData);
                    MessageDialogBuilder.yesNo("完成", "文件生成完成!").yesText("确认").noText("取消").show();
                } catch (TemplateException | IOException e) {
                    MessageDialogBuilder.yesNo("失败", "文件生成异常!").yesText("确认").noText("取消").show();
                    throw new RuntimeException(e);
                }

            }
        });
        clearBtn.addActionListener(al -> {
            DataCenter.reset();
        });
        closeBtn.addActionListener(al -> {
            toolWindow.hide(null);
        });
    }


    public void init() {
        notebookTable.setModel(DataCenter.TABLE_MODEL);
        notebookTable.setEnabled(true);
    }

    public JComponent getJContent() {
        return contentPanel;
    }
}
