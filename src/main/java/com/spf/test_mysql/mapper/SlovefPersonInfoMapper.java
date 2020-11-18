package com.spf.test_mysql.mapper;

import com.spf.test_mysql.pojo.PersonInfoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface SlovefPersonInfoMapper {

    List<PersonInfoEntity> queryAllPerson(Map<String, Object> paramsMap);
}
