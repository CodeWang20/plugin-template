package com.dhcc.template;

import com.gitee.starblues.extension.mybatis.configuration.SpringBootMybatisConfig;
import com.gitee.starblues.realize.BasePlugin;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginWrapper;

import java.util.Map;
import java.util.Set;


@Slf4j
public class AddContractAndOptionPlugin extends BasePlugin implements SpringBootMybatisConfig {

    public AddContractAndOptionPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }



    @Override
    protected void startEvent() {
        log.info("模板插件开始加载------------------");
        super.startEvent();
        log.info("模板插件加载成功------------------");
    }

    @Override
    protected void deleteEvent() {
        super.deleteEvent();
    }

    @Override
    protected void stopEvent() {
        super.stopEvent();
    }

    @Override
    public Set<String> mybatisMapperXmlLocationsMatch() {
        return null;
    }

    @Override
    public Map<String, Class> aliasMapping() {
        return null;
    }

    @Override
    public Set<String> typeAliasesPackage() {
        return null;
    }
}
