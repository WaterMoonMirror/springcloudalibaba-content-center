package com.wondersgroup.lz.contentcenter.configuration;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
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
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *同一集群优先调用
 */
@Slf4j
public class NacosSameClusterWeightedRule extends AbstractLoadBalancerRule {
    @Autowired
    NacosDiscoveryProperties nacosDiscoveryProperties;
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @SneakyThrows
    @Override
    public Server choose(Object o) {
        // 拿到配置文件中的集群名称 BJ
        var clusterName = nacosDiscoveryProperties.getClusterName();
        // 获取服务对象实例
        var loadBalancer =(BaseLoadBalancer) this.getLoadBalancer();
        var name = loadBalancer.getName();// 想要请求的微服务名称
        // 拿到服务发现的相关API
        var namingService = nacosDiscoveryProperties.namingServiceInstance();
        // 1. 找到指定服务的所有实例 A
        var instances = namingService.selectInstances(name, true);
        // 2. 过滤出相同集群下的所有实例 B
        var sameClusterInstances = instances.stream()
                .filter(instance -> Objects.equals(clusterName, instance.getClusterName()))
                .collect(Collectors.toList());
        // 3. 如果B是空，就用A
        List<Instance> instancesToBeChosen = new ArrayList<Instance>();
        if (CollectionUtils.isEmpty(sameClusterInstances)){
            instancesToBeChosen=instances;
            log.warn("发生跨集群的调用, name = {}, clusterName = {}, instances = {}",
                    name,
                    clusterName,
                    instances
            );
        }else {
            instancesToBeChosen=sameClusterInstances;
        }

        // 4. 基于权重的负载均衡算法，返回1个实例
        Instance instance = ExtendBalancer.getHostByRandomWeight2(instancesToBeChosen);
        log.info("选择的实例是 port = {}, instance = {}", instance.getPort(), instance);
        return new NacosServer(instance);
    }
}
class ExtendBalancer extends Balancer {
    public static Instance getHostByRandomWeight2(List<Instance> hosts) {
        return getHostByRandomWeight(hosts);
    }
}
