package com.zhaopeng.notebook.data;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 13:54
 * Description: 转换数据
 */
public class DataConvert {

    public static Object[] toStringify(Notebook notebook) {
        Object[] objects = new Object[4];
        objects[0] = notebook.getTitle();
        objects[1] = notebook.getContent();
        objects[2] = notebook.getFileName();
        objects[3] = notebook.getCode();
        return objects;
    }

}
