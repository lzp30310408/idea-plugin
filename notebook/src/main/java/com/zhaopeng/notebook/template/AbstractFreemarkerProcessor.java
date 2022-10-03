package com.zhaopeng.notebook.template;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.Writer;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 14:14
 * Description: freemarker抽象处理类
 */
public abstract class AbstractFreemarkerProcessor implements Processor{

    protected abstract Template getTemplate(SourceNoteData sourceNoteData) throws IOException;

    protected abstract Object getModel(SourceNoteData sourceNoteData);

    protected abstract Writer getWriter(SourceNoteData sourceNoteData) throws IOException;

    @Override
    public final void process(SourceNoteData sourceNoteData) throws TemplateException, IOException {
        Template template = getTemplate(sourceNoteData);
        Object model = getModel(sourceNoteData);
        Writer writer = getWriter(sourceNoteData);
        template.process(model, writer);
    }
}
