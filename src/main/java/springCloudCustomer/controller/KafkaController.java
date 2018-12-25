package springCloudCustomer.controller;

import java.text.MessageFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KafkaController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("sendMsg/{msg}")
    public String sendMsg(@PathVariable String msg){
        try {
            log.info("kafka's msg is -> {}.", msg);
            int sendCount = Integer.parseInt(msg);
            for(;sendCount>0;sendCount--){
                kafkaTemplate.send("test","sendCount",String.valueOf(sendCount));
            }
            String sendSucc = "kafka send msg success.";
            log.info(sendSucc);
            return sendSucc;
        }catch (NumberFormatException e){
            String sendFail = MessageFormat.format("msg must number, but is -> {0}.",msg);
            log.error(sendFail,e);
            return sendFail;
        }catch (Exception e) {
            String sendFail = "kafka send msg fail.";
            log.error(sendFail,e);
            return sendFail;
        }
    }

}
