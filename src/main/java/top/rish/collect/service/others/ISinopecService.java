package top.rish.collect.service.others;

import org.springframework.stereotype.Service;
import top.rish.collect.entitys.mysql.TJkData;
import top.rish.collect.entitys.mysql.TTagInfoRtdb;

import java.util.List;

@Service
public interface ISinopecService {

    List<TJkData> getDataBySiteNo(List<TTagInfoRtdb> tTagInfoRtdbs);

}
