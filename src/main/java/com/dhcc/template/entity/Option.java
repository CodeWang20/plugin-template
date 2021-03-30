package com.dhcc.template.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
import java.util.Objects;

@Data
@TableName("`option`")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Option {

    @TableId(value = "PHY_ID", type = IdType.ASSIGN_ID)
    private String phyId;  //合同表主键

    @NotBlank
    @TableField(value = "PHY_CONT_ID")
    private String phyContId;  //合同编号

    @NotBlank
    @TableField(value = "法律审核人员")
    private String auditPerson;  //法律审核人员

    @NotBlank
    @TableField(value = "法律审核意见")
    private String auditOption;  //法律审核意见

    @NotNull
    @Past
    @TableField(value = "接收日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date acceptDate;  //接收日期

    @NotNull
    @Past
    @TableField(value = "审核日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date auditDate;  //审核日期

    //以下是备用字段
    @TableField(value = "备用字段1")
    private String backupOne;
    @TableField(value = "备用字段2")
    private String backupTwo;
    @TableField(value = "备用字段3")
    private String backupThree;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Option option = (Option) o;
        return Objects.equals(phyId, option.phyId) &&
                Objects.equals(phyContId, option.phyContId) &&
                Objects.equals(auditPerson, option.auditPerson) &&
                Objects.equals(auditOption, option.auditOption) &&
                Objects.equals(acceptDate, option.acceptDate) &&
                Objects.equals(auditDate, option.auditDate) &&
                Objects.equals(backupOne, option.backupOne) &&
                Objects.equals(backupTwo, option.backupTwo) &&
                Objects.equals(backupThree, option.backupThree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phyId, phyContId, auditPerson, auditOption, acceptDate, auditDate, backupOne, backupTwo, backupThree);
    }
}
