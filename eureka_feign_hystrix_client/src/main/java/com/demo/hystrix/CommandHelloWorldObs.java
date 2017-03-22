
package com.demo.hystrix;



import java.util.concurrent.TimeUnit;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class CommandHelloWorldObs extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorldObs(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ccblog"))  //监控使用
              .andCommandKey(HystrixCommandKey.Factory.asKey("testCommandKey")) //
              .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ThreadPoolTest"))
              .andCommandPropertiesDefaults(
                  HystrixCommandProperties.Setter()
                      .withExecutionTimeoutInMilliseconds(5000)
              )
              .andThreadPoolPropertiesDefaults(
                  HystrixThreadPoolProperties.Setter()
                      .withCoreSize(3)    // 配置线程池里的线程数
              )
      );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        throw new RuntimeException("this command always fails");
    }

    @Override
    protected String getFallback() {
        return "Hello Failure " + name + "!"+Thread.currentThread().getName();
    }
    
    public static void main(String[] args) throws Exception {
        CommandHelloWorldObs commandHelloWorld = new CommandHelloWorldObs("");
        String result = commandHelloWorld.execute();
        System.out.println("result=" + result);
        
    }
}