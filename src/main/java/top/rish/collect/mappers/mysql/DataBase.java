package top.rish.collect.mappers.mysql;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import top.rish.collect.entitys.ColumnInfo;
import top.rish.collect.entitys.TableInfo;

import java.util.List;
@Mapper
@Component
public interface DataBase {
    @Select("select DISTINCT * from information_schema.COLUMNS where table_name = #{tableName}")
    @Results( {
            @Result(property = "columnName", column = "column_name"),
            @Result(property = "columnType", column = "column_type"),
            @Result(property = "columnComment", column = "column_comment")
    })
    List<ColumnInfo> getColumnInfo(@Param("tableName") String tableName);
    @Select("SELECT " +
            "a.table_name, " +
            "a.table_comment " +
            "FROM information_schema.TABLES a    WHERE a.table_schema = 'fx_jqlh' and  " +
            "a.table_name = #{tableName} ")
    @Results( {
            @Result(property = "tableName", column = "TABLE_NAME"),
            @Result(property = "tableComment", column = "TABLE_COMMENT"),
    })
    TableInfo getTableInfo(@Param("tableName") String tableName);
}
