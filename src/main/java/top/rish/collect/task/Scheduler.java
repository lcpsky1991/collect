package top.rish.collect.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.rish.collect.common.utils.DateKit;
import top.rish.collect.entitys.mysql.TJkData;
import top.rish.collect.entitys.mysql.TTagInfoRtdb;
import top.rish.collect.mappers.mysql.TJkDataMapper;
import top.rish.collect.mappers.mysql.TTagInfoRtdbMapper;
import top.rish.collect.service.others.ISinopecService;

import java.util.Date;
import java.util.List;

@Component
public class Scheduler {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	TTagInfoRtdbMapper tTagInfoRtdbMapper;
	@Autowired
	TJkDataMapper tJkDataMapper;
	@Autowired
	ISinopecService sinopecService;

 	@Scheduled(cron="0 0/10 * * * ?") //每10分钟执行一次
	@Async
	public void sinopecScheduler() {
		long startTime=System.currentTimeMillis();
 		logger.info("{}初始化,准备请求sinopec.com服务...",
				DateKit.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
		List<TTagInfoRtdb> tTagInfoRtdbs = tTagInfoRtdbMapper.selectAll();
		List<TJkData> dataBySiteNo = sinopecService.getDataBySiteNo(tTagInfoRtdbs);
		logger.info("获得 t_tag_info_rtdb 表的数据共 {} 条, \r\n " +
				"请求 sinopec.com 服务后得到 {} 条数据, \r\n ",
				tTagInfoRtdbs,
				dataBySiteNo.size());
		logger.info("请求sinopec.com得到的数据是 {} ",dataBySiteNo);
		logger.info("开始批量操作 t_jk_data ... ");
		Date date = new Date();
		Integer count = 0;
		for (int i = 0; i < dataBySiteNo.size() ; i++) {

			TJkData tJkData = dataBySiteNo.get(i);
			if(null!=tJkData && tJkData.getTagCode()!=null){
					tJkData.setCreatTime(date);
					int insert = tJkDataMapper.insert(tJkData);
					if (insert==1){
						logger.info("{} 插入成功...",tJkData.getTagCode());
						count ++;
					}else{
						logger.info("{} 插入失败...",tJkData.getTagCode());
					}
				}
			}
		logger.info( "插入 t_jk_data 成功的数据共 {} 条", count);
		logger.error("插入 t_jk_data 失败的数据共 {} 条",dataBySiteNo.size()-count);
		long endTime=System.currentTimeMillis();
		logger.info(" 本次服务调用共耗时 {} ms. ",endTime-startTime);
	}

}