package com.dhcc.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dhcc.template.entity.Contract;
import com.gitee.starblues.extension.mybatis.annotation.PluginMapper;


@PluginMapper
public interface ContractDao extends BaseMapper<Contract> {

    /**
     * 根据请求参数查询数据库是否已经存在该记录
     */
    Contract getByContractNo(String contractNo);
}
