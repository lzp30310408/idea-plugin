package com.zhaopeng.notebook.template;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 14:13
 * Description: 处理器
 */
public interface Processor {

    public void process(SourceNoteData sourceNoteData) throws TemplateException, IOException;
}
