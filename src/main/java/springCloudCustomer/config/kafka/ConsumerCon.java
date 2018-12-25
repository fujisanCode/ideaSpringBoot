package springCloudCustomer.config.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class ConsumerCon {

    // 消费者配置
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> consumerConfigs = new HashMap<>();
        consumerConfigs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        consumerConfigs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        consumerConfigs.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        consumerConfigs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "6000");
        consumerConfigs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        consumerConfigs.put(
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            StringDeserializer.class);
        consumerConfigs.put(
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            StringDeserializer.class);
        return consumerConfigs;
    }

    // 消费者工厂
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    // 消费者监听
    @Bean
    public ConcurrentKafkaListenerContainerFactory consumerListener() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> consumerFactory =
            new ConcurrentKafkaListenerContainerFactory<>();
        consumerFactory.setConsumerFactory(consumerFactory());
        consumerFactory.setConcurrency(10);
        consumerFactory.getContainerProperties().setPollTimeout(1500);
        return consumerFactory;
    }

    //consumerListener配置到kafkaConfig中
    @Bean
    public SimpleConsumerListener simpleListener() {
        return new SimpleConsumerListener();
    }
}
