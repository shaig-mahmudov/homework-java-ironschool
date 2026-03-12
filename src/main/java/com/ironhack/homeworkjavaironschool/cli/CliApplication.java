package com.ironhack.homeworkjavaironschool.cli;

import com.ironhack.homeworkjavaironschool.HomeworkJavaIronSchoolApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class CliApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(HomeworkJavaIronSchoolApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        try {
            context.getBean(CliRunner.class).run();
        } finally {
            context.close();
        }
    }
}
