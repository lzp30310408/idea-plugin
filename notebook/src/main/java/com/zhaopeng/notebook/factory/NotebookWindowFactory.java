package com.zhaopeng.notebook.factory;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.zhaopeng.notebook.window.NotebookWindow;
import org.jetbrains.annotations.NotNull;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 12:27
 * Description: 笔记工具窗口工厂类
 */
public class NotebookWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 创建窗口对象
        NotebookWindow notebookWindow = new NotebookWindow(project, toolWindow);
        // 获取内容工厂
        ContentFactory factory = ContentFactory.SERVICE.getInstance();
        // 创建显示内容
        Content content = factory.createContent(notebookWindow.getJContent(), "", false);
        // 加入内容
        toolWindow.getContentManager().addContent(content);
    }


}
