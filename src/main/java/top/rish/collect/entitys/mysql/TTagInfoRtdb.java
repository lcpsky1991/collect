package top.rish.collect.entitys.mysql;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "t_tag_info_rtdb")
public class TTagInfoRtdb implements Serializable {
    /**
     * 新数采位号
     */
    @Id
    @Column(name = "tag_code_new")
    private String tagCodeNew;

    /**
     * 旧数采位号
     */
    @Id
    @Column(name = "tag_code_old")
    private String tagCodeOld;

    /**
     * 位号描述
     */
    @Column(name = "tag_desc")
    private String tagDesc;

    /**
     * 下限值
     */
    @Column(name = "Lower_limit")
    private Integer lowerLimit;

    /**
     * 上限值
     */
    @Column(name = "upper_limit")
    private Integer upperLimit;

    /**
     * 单位
     */
    @Column(name = "tag_unit")
    private String tagUnit;

    private static final long serialVersionUID = 1L;

    /**
     * 获取新数采位号
     *
     * @return tag_code_new - 新数采位号
     */
    public String getTagCodeNew() {
        return tagCodeNew;
    }

    /**
     * 设置新数采位号
     *
     * @param tagCodeNew 新数采位号
     */
    public void setTagCodeNew(String tagCodeNew) {
        this.tagCodeNew = tagCodeNew == null ? null : tagCodeNew.trim();
    }

    /**
     * 获取旧数采位号
     *
     * @return tag_code_old - 旧数采位号
     */
    public String getTagCodeOld() {
        return tagCodeOld;
    }

    /**
     * 设置旧数采位号
     *
     * @param tagCodeOld 旧数采位号
     */
    public void setTagCodeOld(String tagCodeOld) {
        this.tagCodeOld = tagCodeOld == null ? null : tagCodeOld.trim();
    }

    /**
     * 获取位号描述
     *
     * @return tag_desc - 位号描述
     */
    public String getTagDesc() {
        return tagDesc;
    }

    /**
     * 设置位号描述
     *
     * @param tagDesc 位号描述
     */
    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc == null ? null : tagDesc.trim();
    }

    /**
     * 获取下限值
     *
     * @return Lower_limit - 下限值
     */
    public Integer getLowerLimit() {
        return lowerLimit;
    }

    /**
     * 设置下限值
     *
     * @param lowerLimit 下限值
     */
    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    /**
     * 获取上限值
     *
     * @return upper_limit - 上限值
     */
    public Integer getUpperLimit() {
        return upperLimit;
    }

    /**
     * 设置上限值
     *
     * @param upperLimit 上限值
     */
    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    /**
     * 获取单位
     *
     * @return tag_unit - 单位
     */
    public String getTagUnit() {
        return tagUnit;
    }

    /**
     * 设置单位
     *
     * @param tagUnit 单位
     */
    public void setTagUnit(String tagUnit) {
        this.tagUnit = tagUnit == null ? null : tagUnit.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", tagCodeNew=").append(tagCodeNew);
        sb.append(", tagCodeOld=").append(tagCodeOld);
        sb.append(", tagDesc=").append(tagDesc);
        sb.append(", lowerLimit=").append(lowerLimit);
        sb.append(", upperLimit=").append(upperLimit);
        sb.append(", tagUnit=").append(tagUnit);
        sb.append("]");
        return sb.toString();
    }
}