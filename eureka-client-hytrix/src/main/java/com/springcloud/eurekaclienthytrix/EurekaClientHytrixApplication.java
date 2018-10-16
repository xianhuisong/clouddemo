package com.springcloud.eurekaclienthytrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
@RestController
public class EurekaClientHytrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientHytrixApplication.class, args);
    }
    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
        return "hi " + name + " ,i am from port:" + port;
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
}


//@RestController
//class ServiceInstanceRestController {
//
//    private static final Logger LOG = Logger.getLogger(ServiceInstanceRestController.class.getName());
//
//    @Value("${server.port}")
//    String port;
//
////    @RequestMapping("/hiOne")
////    public String home(@RequestParam(value = "name", defaultValue = "spring") String name) {
////        return "hi " + name + " ,i am from port:" + port;
////    }
////
////    @Autowired
////    private RestTemplate restTemplate;
////
////    @Bean
////    public RestTemplate getRestTemplate() {
////        return new RestTemplate();
////    }
////
////    @RequestMapping("/hi")
////    public String callHome() {
////        LOG.log(Level.INFO, "calling trace service-hi  ");
////        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
////    }
////
////    @RequestMapping("/info")
////    public String info() {
////        LOG.log(Level.INFO, "calling trace service-hi ");
////
////        return "i'm service-hi";
////
////    }
//
//    @RequestMapping("/hiTwo")
//    @HystrixCommand(fallbackMethod = "hiError")
//    public String home2(@RequestParam(value = "name", defaultValue = "spring") String name) {
//        return "hi " + name + " ,i am from port:" + port;
//    }
//
//    public String hiError(String name) {
//        return "hi," + name + ",sorry,error!";
//    }
//
////    @Bean
////    public Sampler defaultSampler() {
////        return Sampler.ALWAYS_SAMPLE;
////    }


