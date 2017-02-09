
package com.demo.springcloud.ribbon;

import java.net.URI;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.client.http.RestClient;

public class RibbonMain {
    public static void main( String[] args ) throws Exception {  
        ConfigurationManager.loadPropertiesFromResources("sample-client.properties");  
        System.out.println(ConfigurationManager.getConfigInstance().getProperty("sample-client.ribbon.listOfServers"));  
          
        RestClient client = (RestClient)ClientFactory.getNamedClient("sample-client");  
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build();  
          
        for(int i = 0; i < 4; i ++) {  
            HttpResponse response = client.executeWithLoadBalancer(request);  
            System.out.println("Status for URI:" + response.getRequestedURI() + " is :" + response.getStatus());  
        }  
          
        ZoneAwareLoadBalancer lb = (ZoneAwareLoadBalancer) client.getLoadBalancer();  
        System.out.println(lb.getLoadBalancerStats());  
          
        ConfigurationManager.getConfigInstance().setProperty("sample-client.ribbon.listOfServers", "ccblog.cn:80,www.linkedin.com:80");  
          
        System.out.println("changing servers ...");  
        Thread.sleep(3000);  
          
        for(int i = 0; i < 3; i ++) {  
            HttpResponse response = client.executeWithLoadBalancer(request);  
            System.out.println("Status for URI:" + response.getRequestedURI() + " is :" + response.getStatus());  
        }  
        System.out.println(lb.getLoadBalancerStats());  
    }  
}
