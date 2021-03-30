package com.dhcc.template.webservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhcc.template.entity.Option;
import com.dhcc.template.mapper.OptionDao;
import com.dhcc.template.webservice.WebServiceOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;

@Service
@Transactional
@WebService(name = "webServiceOption", endpointInterface = "com.dhcc.template.webservice.WebServiceOption")
public class WebServiceWebImplOption extends ServiceImpl<OptionDao, Option> implements WebServiceOption {

    @Autowired
    private OptionDao optionDao;
}
