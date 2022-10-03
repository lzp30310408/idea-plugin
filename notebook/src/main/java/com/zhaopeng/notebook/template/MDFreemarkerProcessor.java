package com.zhaopeng.notebook.template;

import com.intellij.ide.fileTemplates.impl.UrlUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Author: zhaopeng.liu
 * Date: 2022/10/3
 * Time: 14:36
 * Description: md文件freemarker模板处理器
 */
public class MDFreemarkerProcessor extends AbstractFreemarkerProcessor{

    @Override
    protected Template getTemplate(SourceNoteData sourceNoteData) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        String temp = UrlUtil.loadText(Objects.requireNonNull(MDFreemarkerProcessor.class.getResource("/template/md.ftl")));
        StringTemplateLoader stl = new StringTemplateLoader();
        stl.putTemplate("mdTemplate", temp);
        configuration.setTemplateLoader(stl);
        return configuration.getTemplate("mdTemplate");
    }

    @Override
    protected Object getModel(SourceNoteData sourceNoteData) {
        Map<String, Object> model = new HashMap<>();
        model.put("topic", sourceNoteData.getTopic());
        model.put("notebookList", sourceNoteData.getNotebookList());
        return model;
    }

    @Override
    protected Writer getWriter(SourceNoteData sourceNoteData) throws IOException {
        File file = new File(sourceNoteData.getFileName());
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
    }

}
