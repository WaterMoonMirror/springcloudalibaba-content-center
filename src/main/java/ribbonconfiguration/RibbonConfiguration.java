package ribbonconfiguration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.wondersgroup.lz.contentcenter.configuration.NacosWeightedRule;
import com.wondersgroup.lz.contentcenter.configuration.UserCenterRibbonConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 配置Ribbon 负载均衡规则
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule rubbonRule(){
        return new NacosWeightedRule();
    }
}
