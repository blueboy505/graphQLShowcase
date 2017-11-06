package com.example.southpark.graphqlsouthparkdemo.graphql;

import com.oembedler.moon.graphql.boot.GraphQLJavaToolsAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore({GraphQLJavaToolsAutoConfiguration.class})
public class GraphQLDateTimeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DateTimeScalar graphQLDateTime() {
        return new DateTimeScalar();
    }
}
