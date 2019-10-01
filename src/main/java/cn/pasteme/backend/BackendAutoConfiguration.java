package cn.pasteme.backend;

import cn.pasteme.common.annotation.NotComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"cn.pasteme.backend", "cn.pasteme.common"})
@NotComponentScan
public class BackendAutoConfiguration {
}
