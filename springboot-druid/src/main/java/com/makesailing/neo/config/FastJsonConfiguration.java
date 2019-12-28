package com.makesailing.neo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.util.List;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/23 23:15
 */
//@Configuration
public class FastJsonConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 修改自定义转换器
     *
     * @param converters 消息转换器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 调用父类的消息转换器
        super.configureMessageConverters(converters);
        // 创建fastJson消息转换器
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullStringAsEmpty
        );

        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        converters.add(fastJsonHttpMessageConverter);
    }
}
