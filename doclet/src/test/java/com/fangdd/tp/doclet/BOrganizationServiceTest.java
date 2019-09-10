package com.fangdd.tp.doclet;

import com.sun.tools.javadoc.Main;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xuwenzhen
 * @date 18/1/5
 */
public class BOrganizationServiceTest extends BaseTpDocTest {
    /**
     * 需要引入的包
     */
    private static final String[] JARS = new String[]{
            "org/springframework/boot/spring-boot-autoconfigure/2.1.3.RELEASE/spring-boot-autoconfigure-2.1.3.RELEASE.jar",
            "org/springframework/boot/spring-boot/2.1.3.RELEASE/spring-boot-2.1.3.RELEASE.jar",
            "org/springframework/spring-web/5.1.5.RELEASE/spring-web-5.1.5.RELEASE.jar",
            "com/alibaba/fastjson/1.2.7/fastjson-1.2.7.jar",
            "org/springframework/spring-context/5.1.5.RELEASE/spring-context-5.1.5.RELEASE.jar",
            "org/springframework/spring-core/5.1.5.RELEASE/spring-core-5.1.5.RELEASE.jar",
            "org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar",
            "org/springframework/spring-beans/5.1.5.RELEASE/spring-beans-5.1.5.RELEASE.jar",
            "com/google/guava/guava/26.0-jre/guava-26.0-jre.jar",
            "com/fangdd/graphql/graphql-provider-remote/1.0.0-SNAPSHOT/graphql-provider-remote-1.0.0-SNAPSHOT.jar",
            "com/fangdd/graphql/spring-boot-starter-fdd-graphql-provider-tpdoc/1.0.0-SNAPSHOT/spring-boot-starter-fdd-graphql-provider-tpdoc-1.0.0-SNAPSHOT.jar",
            "com/fasterxml/jackson/core/jackson-annotations/2.9.0/jackson-annotations-2.9.0.jar",
            "org/hibernate/validator/hibernate-validator/6.0.14.Final/hibernate-validator-6.0.14.Final.jar",
            "org/projectlombok/lombok/1.18.6/lombok-1.18.6.jar",
            "javax/validation/validation-api/2.0.1.Final/validation-api-2.0.1.Final.jar",
            "com/fangdd/fdd-common-caller/1.0.0-SNAPSHOT/fdd-common-caller-1.0.0-20190522.040232-28.jar",
            "com/baomidou/mybatis-plus-annotation/3.1.1/mybatis-plus-annotation-3.1.1.jar",
            "com/baomidou/mybatis-plus-core/3.1.1/mybatis-plus-core-3.1.1.jar",
            "com/fangdd/fdd-common-web/2.0.0-SNAPSHOT/fdd-common-web-2.0.0-20190805.121756-7.jar",
            "com/alibaba/dubbo/2.8.4/dubbo-2.8.4.jar",
            "com/fangdd/fdd-common-basic/2.0.0/fdd-common-basic-2.0.0.jar"
    };


    @Test
    public void test() throws IOException {
        System.setProperty("exporter", "console");
        String projectPath = getProjectPath();

        String projectSrcDirs = getProjectSrcDirs();
        System.out.println("扫描目录：" + projectSrcDirs);
        String[] docArgs = new String[]{
                "com.fangdd.nh.organization.server.service.impl",
//                "-subpackages",
//                projectPath + "/server/target/tp-server.jar",
                "-cp",
                getLibs(),
                "-sourcepath",
                projectSrcDirs
        };

        Main.execute("myJavadoc", TpDoclet.class.getName(), docArgs);
    }

    /**
     * 获取依赖包
     *
     * @return
     */
    @Override
    protected String[] getJars() {
        return JARS;
    }

    @Override
    protected String getProjectPath() {
        return "/Users/xuwenzhen/Projects/fdd/other/b-organization-service";
    }
}