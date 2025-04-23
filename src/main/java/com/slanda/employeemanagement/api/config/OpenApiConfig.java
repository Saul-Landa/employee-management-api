package com.slanda.employeemanagement.api.config;

import static com.slanda.employeemanagement.api.util.constants.APIDefinitionConstants.API_TITLE;
import static com.slanda.employeemanagement.api.util.constants.APIDefinitionConstants.API_VERSION;
import static com.slanda.employeemanagement.api.util.constants.APIDefinitionConstants.API_DESCRIPTION;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Class for configuring API documentation
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = API_TITLE,
                version = API_VERSION,
                description = API_DESCRIPTION
        )
)
public class OpenApiConfig {
}
