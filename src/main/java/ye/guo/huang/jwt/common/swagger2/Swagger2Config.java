package ye.guo.huang.jwt.common.swagger2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/20
 *  desc :http://localhost/swagger-ui.html#!/29992251433164929702/loginUsingPOST
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static final Logger LOGGER = LogManager.getLogger(Swagger2Config.class);

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("ye.guo.huang.jwt.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基础项目接口API-title")
                .description("基础项目接口API-desc")
                .version("1.0").build();
    }

}