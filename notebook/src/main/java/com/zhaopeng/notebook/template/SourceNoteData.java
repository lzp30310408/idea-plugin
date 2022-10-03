package com.zhaopeng.notebook.template;

import com.zhaopeng.notebook.data.Notebook;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 14:14
 * Description: 数据源
 */
public class SourceNoteData {

    private String topic;

    private List<Notebook> notebookList = new LinkedList<>();

    private String fileName;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<Notebook> getNotebookList() {
        return notebookList;
    }

    public void setNotebookList(List<Notebook> notebookList) {
        this.notebookList = notebookList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
