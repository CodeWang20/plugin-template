package com.dhcc.template.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhcc.template.common.Result;
import com.dhcc.template.entity.Contract;
import com.dhcc.template.entity.Option;
import com.dhcc.template.webservice.WebServiceContract;
import com.dhcc.template.webservice.WebServiceOption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author wangxiao
 */


@Slf4j
@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private WebServiceContract contractService;
    @Autowired
    private WebServiceOption optionService;

    /**
     * 添加合同及其意见
     * @param contract 包括合同主体信息和该合同的意见list(集合)
     */
    @PostMapping("/addContractAndOption")
    @Transactional
    public Result addContractAndOption(@RequestBody @Validated Contract contract){
        //插入前查询，防止添加重复记录
        QueryWrapper<Contract> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Contract::getContractNo, contract.getContractNo());
        Contract cont = contractService.getOne(wrapper);
        if (!ObjectUtils.isEmpty(cont)){
            return Result.fail("该合同信息已存在，请勿重复添加！");
        }
        try {
            //添加合同信息
            contractService.save(contract);
            if (contract.getOptionList().size() > 0 && contract.getOptionList() != null) {
                for (Option option : contract.getOptionList()){
                    //设置option的phyContId为合同编号
                    option.setPhyContId(contract.getContractNo());
                    //添加合同意见
                    optionService.save(option);
                }
            }
            return Result.succ("合同信息新增成功！", null);
        }catch (Exception e){
            log.info("合同及意见插入异常------------>", e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.fail("合同信息新增失败！");
        }
    }


    /**
     * 根据合同类型查询类似历史合同的审核意见
     * @param contractName 合同名称
     * @param contractType 合同类型
     * @param ourName 我方主体
     * @param otherCompany 对方公司
     * @param handleCompany 经办单位
     * @param contractAbstract 合同摘要
     * @return 与当前合同类型（type）类似的历史合同审核意见
     */
    @GetMapping("/historicalOpinions")
    public Result historicalOpinions(String contractName, String contractType, String ourName, String otherCompany, String handleCompany, String contractAbstract){
        //从合同表查出type相同的合同编号
        QueryWrapper<Contract> contractQueryWrapper = new QueryWrapper<>();
        contractQueryWrapper.lambda()
                .eq(Contract::getContractType, contractType)
                .eq(Contract::getOurName, ourName)
                .eq(Contract::getOtherCompany, otherCompany)
                .eq(Contract::getHandleCompany, handleCompany)
                .eq(Contract::getContractAbstract, contractAbstract)
                .like(Contract::getContractName, contractName)
                .select(Contract::getContractNo);
        List<Contract> contractList = contractService.list(contractQueryWrapper);
        ArrayList<Object> contractNo = new ArrayList<>();
        contractList.forEach(item -> contractNo.add(item.getContractNo()));

        //根据上面的合同编号集合，从意见表查出历史意见
        QueryWrapper<Option> optionQueryWrapper = new QueryWrapper<>();
        optionQueryWrapper.lambda()
                .in(Option::getPhyContId, contractNo);
        List<Option> optionList = optionService.list(optionQueryWrapper);
        return Result.succ("合同历史意见", optionList);
    }

    /**
     * TODO
     */

    @GetMapping("/delete")
    public Result deleteContractAndOption(@RequestBody Contract contract){
        QueryWrapper<Contract> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Contract::getContractNo, contract.getContractNo());
        contractService.remove(wrapper);
        return null;
    }

    @Autowired
    RabbitTemplate rabbitTemplate;


    /**
     * 消息推送
     */
    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "ok";
    }
}
