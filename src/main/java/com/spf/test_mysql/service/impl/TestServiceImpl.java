package com.spf.test_mysql.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.spf.test_mysql.mapper.SlovefPersonInfoMapper;
import com.spf.test_mysql.pojo.PersonInfoEntity;
import com.spf.test_mysql.service.ITestService;
import com.spf.test_mysql.util.JsonBinder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements ITestService {

    Logger logger = Logger.getLogger(TestServiceImpl.class);

    JsonBinder binder = JsonBinder.buildNormalBinder();

    @Autowired
    private SlovefPersonInfoMapper slovefPersonInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${redis.prefix}")
    private String prefix;

    @Override
    public List<PersonInfoEntity> queryAllPerson(Map<String, Object> paramsMap) {
        String json = (String) redisTemplate.boundValueOps(prefix + ":personInfoEntity").get();
        List<PersonInfoEntity> personInfoEntities = JSONObject.parseArray(json, PersonInfoEntity.class);
        if (CollectionUtils.isEmpty(personInfoEntities)) {
            logger.info("从mysql获取,然后保存到redis");
            personInfoEntities = slovefPersonInfoMapper.queryAllPerson(paramsMap);
            if (CollectionUtils.isEmpty(personInfoEntities)) {
                return personInfoEntities;
            } else {
                redisTemplate.boundValueOps(prefix + ":personInfoEntity").set(JSONObject.toJSONString(personInfoEntities));
            }
        } else {
            logger.info("直接从redis获取");
        }
        return personInfoEntities;
    }
}
