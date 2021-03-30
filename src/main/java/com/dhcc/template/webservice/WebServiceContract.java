package com.dhcc.template.webservice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhcc.template.entity.Contract;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WebServiceContract extends IService<Contract> {
}
