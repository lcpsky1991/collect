package top.rish.collect.entitys.mysql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_jk_data")
public class TJkData implements Serializable {
    /**
     * 实时数据库位号编码
     */
    @Id
    @Column(name = "tag_code")
    private String tagCode;

    /**
     * 位号采集的时间点（对方系统时间点）
     */
    @Id
    @Column(name = "Result_time")
    private Date resultTime;

    /**
     * 按照预定规则生成的秒数，例如：10、20、30、40、50
     */
    private Integer sec;

    /**
     * 位号采集的数据
     */
    @Column(name = "Result_value")
    private BigDecimal resultValue;

    /**
     * 0：有效数据； 1：无效数据
     */
    private Integer status;

    /**
     * 本记录写入时间点
     */
    @Column(name = "creat_time")
    private Date creatTime;

    private static final long serialVersionUID = 1L;

    /**
     * 获取实时数据库位号编码
     *
     * @return tag_code - 实时数据库位号编码
     */
    public String getTagCode() {
        return tagCode;
    }

    /**
     * 设置实时数据库位号编码
     *
     * @param tagCode 实时数据库位号编码
     */
    public void setTagCode(String tagCode) {
        this.tagCode = tagCode == null ? null : tagCode.trim();
    }

    /**
     * 获取位号采集的时间点（对方系统时间点）
     *
     * @return Result_time - 位号采集的时间点（对方系统时间点）
     */
    public Date getResultTime() {
        return resultTime;
    }

    /**
     * 设置位号采集的时间点（对方系统时间点）
     *
     * @param resultTime 位号采集的时间点（对方系统时间点）
     */
    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    /**
     * 获取按照预定规则生成的秒数，例如：10、20、30、40、50
     *
     * @return sec - 按照预定规则生成的秒数，例如：10、20、30、40、50
     */
    public Integer getSec() {
        return sec;
    }

    /**
     * 设置按照预定规则生成的秒数，例如：10、20、30、40、50
     *
     * @param sec 按照预定规则生成的秒数，例如：10、20、30、40、50
     */
    public void setSec(Integer sec) {
        this.sec = sec;
    }

    /**
     * 获取位号采集的数据
     *
     * @return Result_value - 位号采集的数据
     */
    public BigDecimal getResultValue() {
        return resultValue;
    }

    /**
     * 设置位号采集的数据
     *
     * @param resultValue 位号采集的数据
     */
    public void setResultValue(BigDecimal resultValue) {
        this.resultValue = resultValue;
    }

    /**
     * 获取0：有效数据； 1：无效数据
     *
     * @return status - 0：有效数据； 1：无效数据
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：有效数据； 1：无效数据
     *
     * @param status 0：有效数据； 1：无效数据
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取本记录写入时间点
     *
     * @return creat_time - 本记录写入时间点
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置本记录写入时间点
     *
     * @param creatTime 本记录写入时间点
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagCode=").append(tagCode);
        sb.append(", resultTime=").append(resultTime);
        sb.append(", sec=").append(sec);
        sb.append(", resultValue=").append(resultValue);
        sb.append(", status=").append(status);
        sb.append(", creatTime=").append(creatTime);
        sb.append("]");
        return sb.toString();
    }
}