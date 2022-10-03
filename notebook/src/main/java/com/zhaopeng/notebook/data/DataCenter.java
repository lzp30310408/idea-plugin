package com.zhaopeng.notebook.data;

import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 11:07
 * Description: 数据中心
 */
public class DataCenter {

    public static String SELECT_TEXT = null;

    public static String CUR_FILE_NAME = null;

    public static String CUR_FILE_TYPE = null;

    public static List<Notebook> NOTEBOOK_LIST = new LinkedList<>();

    public static String[] HEADERS = {"标题", "备注",  "代码段", "文件名"};

    public static DefaultTableModel TABLE_MODEL = new DefaultTableModel(null, HEADERS);

    public static void reset() {
        NOTEBOOK_LIST.clear();
        TABLE_MODEL.setDataVector(null, HEADERS);
    }
}
