package com.cm.datalog.sender;

import com.cm.common.json.JacksonUtil;
import com.cm.common.spring.SpringUtil;
import com.cm.common.utils.DateTimeUtils;
import com.cm.datalog.BaseLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;

@Slf4j
public class MongoLogSender extends ObjectLogSender {

    private static MongoTemplate mongoTemplate;

    private static String COLLECTION_PRE = "log_";

    public MongoLogSender(boolean localLog) {
        super(localLog);
    }


    @Override
    protected boolean doSend(BaseLog baseLog) {
        getMongoTemplate().save(baseLog, getCollectionName());
        return true;
    }

    private static MongoTemplate getMongoTemplate(){
        if(null!=mongoTemplate){
            return mongoTemplate;
        }
        mongoTemplate = SpringUtil.getBean("mongoTemplate");
        return mongoTemplate;
    }

    private String getCollectionName(){
        return COLLECTION_PRE + DateTimeUtils.getCurrentDay();
    }
}
