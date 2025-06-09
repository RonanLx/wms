/**
 * @author Ronan_JoJo
 */
package com.itheima.wms.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 */
@Configuration
public class WmsWebConfiguration {

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = builder.createXmlMapper(false).featuresToDisable(new Object[]{SerializationFeature.WRITE_DATES_AS_TIMESTAMPS}).timeZone(TimeZone.getTimeZone("Asia/Shanghai")).build();
        objectMapper.setLocale(Locale.CHINA).configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true).configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true).configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true).setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.getDeserializationConfig().withoutFeatures(new DeserializationFeature[]{DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES});
        SimpleModule simpleModule = (new SimpleModule()).addSerializer(Long.class, ToStringSerializer.instance).addSerializer(Long.TYPE, ToStringSerializer.instance).addSerializer(BigInteger.class, ToStringSerializer.instance).addSerializer(BigDecimal.class, ToStringSerializer.instance);
        return objectMapper.registerModule(simpleModule);
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return (builder) -> {
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss"))).deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        };
    }

    // RFC 3986规范定义了Url中只允许包含英文字母（a-zA-Z）、数字（0-9）、-_.~4个特殊字符以及所有保留字符(RFC3986中指定了以下字符为保留字符：!* ’ ( ) ; : @ & = + $ , / ? # [ ])
    //调整TOMCAT配置，此处只是针对放行[]
    @Bean
    public TomcatServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((Connector connector) -> {
            connector.setProperty("relaxedPathChars", "\"<>[\\]^`{|}");
            connector.setProperty("relaxedQueryChars", "\"<>[\\]^`{|}");
        });
        return factory;
    }
}
