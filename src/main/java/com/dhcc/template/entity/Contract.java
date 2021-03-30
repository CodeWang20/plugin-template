package com.dhcc.template.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@TableName("contract")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Contract extends Model<Contract> {

    @TableId(value = "PHY_ID", type = IdType.ASSIGN_ID)
    private String phyId;  //合同表主键

    @NotBlank
    @TableField(value = "合同编号")
    private String contractNo;  //合同编号

    @NotBlank
    @TableField(value = "合同名称")
    private String contractName;  //合同名称

    @NotBlank
    @TableField(value = "合同类型")
    private String contractType;  //合同类型

    @NotBlank
    @TableField(value = "我方主体")
    private String ourName;  //我方主体

    @NotBlank
    @TableField(value = "对方单位")
    private String otherCompany;  //对方单位

    @TableField(value = "合同金额")
    private float money;  //合同金额

    @NotBlank
    @TableField(value = "经办单位")
    private String handleCompany;  //经办单位

    @NotBlank
    @TableField(value = "经办人")
    private String handlePerson;  //经办人

    @NotNull
    @Past
    @TableField(value = "申请日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date applyDate;  //申请日期

    @NotBlank
    @TableField(value = "审核状态")
    private String state;  //审核状态

    @NotNull
    @TableField(value = "合同摘要")
    private String contractAbstract;  //合同摘要


    //以下是备用字段
    @TableField(value = "备用字段1")
    private String backupOne;
    @TableField(value = "备用字段2")
    private String backupTwo;
    @TableField(value = "备用字段3")
    private String backupThree;

    @TableField(exist = false)
    //@TableField(typeHandler = FastjsonTypeHandler.class, exist = false)
    private List<Option> optionList;  //意见（多条）

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Float.compare(contract.money, money) == 0 &&
                Objects.equals(phyId, contract.phyId) &&
                Objects.equals(contractNo, contract.contractNo) &&
                Objects.equals(contractName, contract.contractName) &&
                Objects.equals(contractType, contract.contractType) &&
                Objects.equals(ourName, contract.ourName) &&
                Objects.equals(otherCompany, contract.otherCompany) &&
                Objects.equals(handleCompany, contract.handleCompany) &&
                Objects.equals(handlePerson, contract.handlePerson) &&
                Objects.equals(applyDate, contract.applyDate) &&
                Objects.equals(state, contract.state) &&
                Objects.equals(contractAbstract, contract.contractAbstract) &&
                Objects.equals(backupOne, contract.backupOne) &&
                Objects.equals(backupTwo, contract.backupTwo) &&
                Objects.equals(backupThree, contract.backupThree) &&
                Objects.equals(optionList, contract.optionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phyId, contractNo, contractName, contractType, ourName, otherCompany, money, handleCompany, handlePerson, applyDate, state, contractAbstract, backupOne, backupTwo, backupThree, optionList);
    }
}
