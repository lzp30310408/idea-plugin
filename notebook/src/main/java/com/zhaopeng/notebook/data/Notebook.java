package com.zhaopeng.notebook.data;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 11:07
 * Description: 笔记内容
 */
public class Notebook {
    /**
     * 笔记标题
     */
    private String title;
    /**
     * 笔记内容
     */
    private String content;
    /**
     * 笔记文件名
     */
    private String fileName;
    /**
     * 笔记文件类型
     */
    private String fileType;
    /**
     * 代码
     */
    private String code;

    public Notebook(String title, String content, String fileName, String fileType, String code) {
        this.title = title;
        this.content = content;
        this.fileName = fileName;
        this.fileType = fileType;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
