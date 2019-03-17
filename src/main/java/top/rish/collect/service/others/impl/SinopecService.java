package top.rish.collect.service.others.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import top.rish.collect.common.utils.DateKit;
import top.rish.collect.common.utils.HttpClientUtil;
import top.rish.collect.entitys.mysql.TJkData;
import top.rish.collect.entitys.mysql.TTagInfoRtdb;
import top.rish.collect.mappers.mysql.TJkDataMapper;
import top.rish.collect.mappers.mysql.TTagInfoRtdbMapper;
import top.rish.collect.service.others.ISinopecService;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SinopecService implements ISinopecService {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TJkDataMapper tJkDataMapper;
    @Autowired
    TTagInfoRtdbMapper tTagInfoRtdbMapper;
//    http://rtdbtmp.emdm.ephm.mmsh.promace.sinopec.com/RTDB/DataItems?$tagCodeList=MMESP74.拼接一个或多个位号，多个位号需要用逗号隔开
    @Override
    public List<TJkData> getDataBySiteNo(List<TTagInfoRtdb> tTagInfoRtdbs) {
        List<TJkData> tJkDataList = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for(Iterator<TTagInfoRtdb> it = tTagInfoRtdbs.iterator(); it.hasNext();)
        {
            TTagInfoRtdb tTagInfoRtdb = it.next();
            if(tTagInfoRtdb!=null && !tTagInfoRtdb.getTagCodeNew().trim().equals("")){
                sb.append("MMESP74."+tTagInfoRtdb.getTagCodeNew());
                sb.append(",");
            }
        }
        String params = "";
        if(sb.length()>0){
            params = sb.toString().substring(0,sb.toString().length()-1);
        }else{
            params = sb.toString();
        }

//        HashMap<String, String> map = new HashMap<>();
//        map.put("?$tagCodeList",params);
        String s = HttpClientUtil.doGet(
                "http://rtdbtmp.emdm.ephm.mmsh.promace.sinopec.com/RTDB/DataItems?$tagCodeList="+params);
        JSONObject jsonObject = JSON.parseObject(s);

        if( jsonObject!=null && jsonObject.containsKey("collection")){
            JSONObject collection = jsonObject.getJSONObject("collection");
            if(collection.containsKey("items")){
                JSONArray items = collection.getJSONArray("items");
                for(Integer i = 0; i< items.size();i++){
                    JSONObject o = items.getJSONObject(i);
                    if(o.containsKey("data")){
                        JSONArray data = o.getJSONArray("data");
                        Map<String, String> map = new HashMap<>();
                        for(Integer j = 0 ; j<data.size();j++){
                            JSONObject jsonObject1 = data.getJSONObject(j);
                            if(jsonObject1.containsKey("name")){
                                String name = jsonObject1.getString("name");
                                switch (name){
                                    case "unTagCode":
                                        String unTagCode_value = jsonObject1.getString("value");
                                        map.put("unTagCode",unTagCode_value);
                                        break;
                                    case "value":
                                        String value_value = jsonObject1.getString("value");
                                        map.put("value",value_value);
                                        break;
                                    case "isTextValue":
                                        String isTextValue_value = jsonObject1.getString("value");
                                        map.put("isTextValue",isTextValue_value);
                                        break;
                                    case "dateTime":
                                        String dateTime_value = jsonObject1.getString("value");
                                        map.put("dateTime",dateTime_value);
                                        break;
                                    case "quanlity":
                                        String quanlity_value = jsonObject1.getString("value");
                                        map.put("quanlity",quanlity_value);
                                        break;
                                    case "errMessage":
                                        String errMessage_value = jsonObject1.getString("value");
                                        map.put("errMessage",errMessage_value);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        String errMessage = map.get("errMessage");
                        if(!(errMessage+"").contains("错误")){
                            String dateTime = map.get("dateTime");
                            String unTagCode = map.get("unTagCode");
                            String quanlity = map.get("quanlity");
                            String value = map.get("value");
                            TJkData tJkData = new TJkData();
                            //2019/03/07 16:22:54
                            try {
                                Date dtime = DateKit.stringToDate(dateTime, "yyyy/MM/dd HH:mm:ss");
                                tJkData.setResultTime(dtime);
                            } catch (Exception e) {
                                logger.info("调用sinopec.com服务时,{} ResultTime 数据格式错误！,转换失败！跳过采集！{}"
                                        ,map.get("unTagCode")
                                        ,e.getMessage());
                                continue;
                            }
                            try {
                                BigDecimal vvalue = new BigDecimal(value);
                                tJkData.setResultValue(vvalue);
                            }catch (Exception e){
                                logger.info("调用sinopec.com服务时, {} ResultValue 的 BigDecimal 数据格式错误,转换失败！跳过采集！{}"
                                        ,map.get("unTagCode")
                                        ,e.getMessage());
                                continue;
                            }
                            String replace = unTagCode.replace("MMESP74.", "");

                            Example example = new Example(TTagInfoRtdb.class);
                            Example.Criteria criteria = example.createCriteria();
                            criteria.andCondition("tag_code_new=",replace);
                            TTagInfoRtdb tTagInfoRtdbs1 = tTagInfoRtdbMapper.selectOneByExample(example);
                            if("OK".equals(quanlity)){
                                tJkData.setStatus(0);
                            }else{
                                tJkData.setStatus(-1);
                            }
                            if(tTagInfoRtdbs1 != null){
                                tJkData.setTagCode(tTagInfoRtdbs1.getTagCodeOld());
                                logger.info("{} 的状态是 {}",tJkData.getTagCode(),tJkData.getStatus());
                                tJkDataList.add(tJkData);
                                //logger.info("采集到：");
                            }

                        }else{
                            logger.error("调用sinopec.com服务时,data 数据格式错误！");
                            break;
                        }
                    }else{
                        logger.error("调用sinopec.com服务时,data 返回了空数据！");
                        break;
                    }
                }
            }else{
                logger.error("调用sinopec.com服务时,items 返回了空数据！");
            }
        }else{
            logger.error("调用sinopec.com服务时, collection 返回了空数据！");
        }
        logger.info(s);
        return tJkDataList;
    }
}
