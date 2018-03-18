package com.valueservice.djs;

import com.valueservice.djs.util.OfficeConvert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableTransactionManagement
public class App extends SpringBootServletInitializer {

    @Value("${openoffice.workpath}")
    private String INITIAL_OPENOFFICE_HOME;

    @Value("${execute.openoffice.command}")
    private String INITIAL_OPENOFFICE_COMMAND;


    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(App.class).run(args);
    }

    @PostConstruct
    public void initial(){
        OfficeConvert.OPENOFFICE_HOME = INITIAL_OPENOFFICE_HOME;
        OfficeConvert.OPENOFFICE_COMMAND = INITIAL_OPENOFFICE_COMMAND;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
}
