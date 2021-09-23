package com.example.boiler.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfig {
  private final TypeResolver typeResolver;

  @Bean
  public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2)
        .securitySchemes(securitySchemes()).securityContexts(Collections.singletonList(securityContext()))
        .securitySchemes(Collections.singletonList(apiKey()))
        .alternateTypeRules(AlternateTypeRules
            .newRule(typeResolver.resolve(Pageable.class), typeResolver.resolve(Page.class)))
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.example.boiler"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    String description = "Boiler";
    return new ApiInfoBuilder()
        .title("Boiler TEST")
        .description(description)
        .version("1.0")
        .build();
  }

  private List<SecurityScheme> securitySchemes() {
    return Collections.singletonList(new BasicAuth("basicAuth"));
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", "Authorization", "header");
  }

  @Data
  @ApiModel
  static class Page {
    @ApiModelProperty(value = "페이지 번호(0..N)", example = "0")
    private Integer page;

    @ApiModelProperty(value = "페이지 크기", allowableValues="range[0, 100]")
    private Integer size;

    @ApiModelProperty(value = "정렬(사용법: 컬럼명,ASC|DESC)")
    private List<String> sort;
  }
}
