package cn.pasteme.backend;

import cn.pasteme.common.annotation.NotComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author Ryan Lee
 * @version 1.0.0
 */

@Configuration
@ComponentScan(value = {"cn.pasteme.backend", "cn.pasteme.common"},
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                    classes = {
                            cn.pasteme.common.utils.exception.GlobalExceptionHandler.class
                    })
        })
@NotComponentScan
public class BackendAutoConfiguration {
}
