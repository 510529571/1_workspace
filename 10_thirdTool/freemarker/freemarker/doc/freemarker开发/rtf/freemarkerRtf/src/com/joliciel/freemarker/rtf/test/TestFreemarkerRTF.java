 /*
  * Written by Assaf Urieli, Joliciel Informatique (http://www.joli-ciel.com), assaf_at_joli-ciel_dot_com
  * Contributed to the Public Domain.
  */
package com.joliciel.freemarker.rtf.test;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.*;
import java.util.*;
import java.io.*;

import com.joliciel.freemarker.rtf.RtfConverter;

/**
 * A tester for the RTF producing template.
 * @author Assaf Urieli
 */
public class TestFreemarkerRTF {
    
    public static void main(String[] args) throws Exception {
        /* Create and adjust the configuration */
        Configuration cfg = new Configuration();
        FileTemplateLoader ftl = new FileTemplateLoader(new File("templates"));
        ClassTemplateLoader ctl = new ClassTemplateLoader(RtfConverter.class, ""); // don't forget this to access rtf.ftl
        TemplateLoader[] loaders = new TemplateLoader[] { ftl, ctl };
        MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
        cfg.setTemplateLoader(mtl);
        
        /* Get or create a template */
        Template temp = cfg.getTemplate("rtfTest.ftl", Locale.FRENCH);

        /* Create a data model */
        Map model = new HashMap();
        // make sure you add the RtfConverter, since this will be needed to escape interpolations as Rtf
        model.put("RtfConverter", new RtfConverter());
        model.put("greeting", "RTF test.\n װאָס מאַכט אַ יִיד?");
        model.put("name", "éh bien, אסף уриели");
        model.put("reply", "ça va, ça va. تالبيانات المطلوبة. Un peu fatigué.\nA few funny letters: æ,Œ,œ,Š,š,Ž,ž\nNow some Greek: Πώς λέγεστε?");
        model.put("rtfEscapes", "a backslash: \\, a curley bracket {, and a close curley bracker }");
        model.put("russian", "русский язык");
        
        // what we want next is a date that has an accent in french - how about février
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 12);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2005);
        Date februaryDate = cal.getTime();
        model.put("testDate", februaryDate);

        /* Merge data model with template */
        Writer out = new FileWriter("test.rtf");
        temp.process(model, out);
        out.flush();
        out.close();
    }
}
