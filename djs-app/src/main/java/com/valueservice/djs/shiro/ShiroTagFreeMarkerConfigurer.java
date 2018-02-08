package com.valueservice.djs.shiro;

import java.io.IOException;
import com.valueservice.djs.shiro.tags.ShiroTags;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import freemarker.template.TemplateException;

public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer {
 	@Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}
