package com.dhcc.template.webservice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhcc.template.entity.Contract;
import com.dhcc.template.mapper.ContractDao;
import com.dhcc.template.webservice.WebServiceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @author wangxiao
 */

@Service
@WebService(name = "webServiceIndicate", endpointInterface = "com.dhcc.template.webservice.WebServiceContract")
public class WebServiceWebImplContract extends ServiceImpl<ContractDao, Contract> implements WebServiceContract {

    @Autowired
    private ContractDao contractDao;
}
