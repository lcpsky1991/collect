package top.rish.collect.service.mysql;
import top.rish.collect.entitys.mysql.TJKData;
import java.util.List;
@Serice
public interface ITJKDataService {

    List<TJKData> selectPageList();
}
