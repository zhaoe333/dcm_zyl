package com.cm.datalog.sender;

//import org.springframework.kafka.core.KafkaTemplate;

public class KafkaLogSender extends StringLogSender {

    private static final String topicName = "bmw-logs";

    public KafkaLogSender(boolean localLog) {
        super(localLog);
    }

    protected boolean doSend(String message) {
//            getKafkaTemplate().send(topicName, message);
        return true;
    }

//    private static KafkaTemplate<String,String> getKafkaTemplate(){
//        if(null!=kafkaTemplate){
//            return kafkaTemplate;
//        }
//        kafkaTemplate = SpringUtil.getBean("kafkaTemplate");
//        return kafkaTemplate;
//    }
}
