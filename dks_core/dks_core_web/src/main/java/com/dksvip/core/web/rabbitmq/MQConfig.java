package com.dksvip.core.web.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SeckillProject
 * @description: 消息队列配置类
 **/
@Configuration
public class MQConfig {
    public static final String QUEUE = "queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String HEADER_QUEUE = "header.queue";
    public static final String TOPIC_EXCHANGE = "topicExchage";
    public static final String FANOUT_EXCHANGE = "fanoutxchage";
    public static final String HEADERS_EXCHANGE = "headersExchage";

    //延迟队列
    private static final String delayed_exchange = "delayed.exchange";
    private static final String delayed_queue = "delayed.queue";
    private static final String delayed_routing_key = "delayed.routingKey";


    //声明目标队列
    @Bean(delayed_queue)
    public Queue delayedQueue(){
        return new Queue(delayed_queue);
    }

    //声明基于插件实现的交换机
    /*@Bean(delayed_exchange)
    public DirectExchange delayedExchange(){
        return new DirectExchange(delayed_exchange);
    }*/
    //基于插件实现的交换机，必须是CustomExchange类型，标识这是一个延时类型的交换机
    @Bean(delayed_exchange)
    public CustomExchange delayedExchange(){
        Map<String,Object> params = new HashMap<>();
        params.put("x-delayed-type","direct");
        /*
        参数1：交换机名字，参数2：交换机的类型，参数3：是否持久化，参数4：是否自动删除队列，参数5：交换机的额外参数设置
         */
        return new CustomExchange(delayed_exchange,"x-delayed-message",true,false,params);
    }

    //绑定延时交换机和目标队列
    @Bean
    public Binding delayedQueueBindingDelayedExchange(
            @Qualifier(delayed_queue) Queue queue,
            @Qualifier(delayed_exchange) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(delayed_routing_key).noargs();
    }


    /**
     * Direct模式 交换机Exchange
     * */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE, true);
    }

    /**
     * Topic模式 交换机Exchange
     * */
    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE1, true);
    }
    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE2, true);
    }
    @Bean
    public TopicExchange topicExchage(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchage()).with("topic.key1");
    }
    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchage()).with("topic.#");
    }

    /**
     * Fanout模式 交换机Exchange
     * */
    @Bean
    public FanoutExchange fanoutExchage(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Binding FanoutBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchage());
    }
    @Bean
    public Binding FanoutBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchage());
    }
    /**
     * Header模式 交换机Exchange
     * */
    @Bean
    public HeadersExchange headersExchage(){
        return new HeadersExchange(HEADERS_EXCHANGE);
    }
    @Bean
    public Queue headerQueue1() {
        return new Queue(HEADER_QUEUE, true);
    }
    @Bean
    public Binding headerBinding() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("header1", "value1");
        map.put("header2", "value2");
        return BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAll(map).match();
    }

}
