package com.mannanlive.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private static final String BASIC_AUTH = "Basic Auth";

    @Value("#{environment['HEROKU_RELEASE_VERSION']}")
    private String version;

    @Value("#{environment['HEROKU_SLUG_COMMIT']}")
    private String commit;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mannanlive.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(singletonList(new BasicAuth(BASIC_AUTH)))
                .securityContexts(singletonList(securityContext()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Corporate Game Share",
                "App to register console games you own and share them with your co-workers.",
                String.format("%s-%s", version, commit),
                "Terms of service",
                new Contact("MannanM", "https://github.com/MannanM/corporate-game-share", "whatabout@gmail.com"),
                "GPL-3.0",
                "https://github.com/MannanM/corporate-game-share/blob/master/LICENSE");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(input -> input != null && (input.contains("/organisations/") || input.contains("/users")))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        return singletonList(SecurityReference.builder()
                .scopes(new AuthorizationScope[0])
                .reference(BASIC_AUTH)
                .build());
    }
}