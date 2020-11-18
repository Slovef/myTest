package com.spf.test_mysql.service;

import com.spf.test_mysql.pojo.PersonInfoEntity;

import java.util.List;
import java.util.Map;

public interface ITestService {
    List<PersonInfoEntity> queryAllPerson(Map<String, Object> paramsMap);
}
