package top.rish.collect.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;
import tk.mybatis.mapper.common.special.InsertUseGeneratedKeysMapper;

// 继承的这2个里面就包含了crud、以及分页、条件查询的所有组件了
public interface MySqlBaseMapper<T>
        extends Mapper<T>,
        MySqlMapper<T>,
        InsertListMapper<T>,
        InsertUseGeneratedKeysMapper<T> {

}
