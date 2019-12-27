package com.wondersgroup.lz.contentcenter.configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.alibaba.nacos.NacosDiscoveryProperties;
import org.springframework.cloud.alibaba.nacos.ribbon.NacosServer;

/**
 * 配置权重负责均衡
 */
@Slf4j
public class NacosWeightedRule extends AbstractLoadBalancerRule {
    // spring cloud commons --> 定义了标准
// spring cloud loadbalancer --> 没有权重
    @Autowired  // 可以获取所有Nacos相关的参数方法
            NacosDiscoveryProperties nacosDiscoveryProperties;


    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
// 读取配置文件，并初始化NacosWeightedRule
    }

    @SneakyThrows
    @Override
    public Server choose(Object o) {
        // 获取服务对象实例
        var loadBalancer =(BaseLoadBalancer) this.getLoadBalancer();
        var name = loadBalancer.getName();// 想要请求的微服务名称
        // 拿到服务发现的相关API
        var namingService = nacosDiscoveryProperties.namingServiceInstance();
        // nacos client自动通过基于权重的负载均衡算法，给我们选择一个实例。
        var instance = namingService.selectOneHealthyInstance(name);
        log.info("选择的实例是：port = {}, instance = {}", instance.getPort(), instance);
        return new NacosServer(instance);
    }
}
