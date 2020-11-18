package com.spf.test_mysql.controller;

import com.spf.test_mysql.pojo.PersonInfoEntity;
import com.spf.test_mysql.service.ITestService;
import com.spf.test_mysql.util.CommonUtils;
import com.spf.test_mysql.util.JsonBinder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    JsonBinder binder = JsonBinder.buildNormalBinder();

    Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    private ITestService testService;

    @RequestMapping("/queryAllPerson.do")
    public String queryAllPerson(final HttpServletRequest request) {
        Map<String, Object> paramsMap = CommonUtils.getDataFromRequest(request);
        List<PersonInfoEntity> personInfoEntities = testService.queryAllPerson(paramsMap);
        if (CollectionUtils.isNotEmpty(personInfoEntities)) {
            return binder.toJson(personInfoEntities);
        } else {
            return "no data find end!";
        }
    }


}
