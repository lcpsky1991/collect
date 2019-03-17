package top.rish.collect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.rish.collect.mappers.mysql.TJkDataMapper;
import top.rish.collect.mappers.mysql.TTagInfoRtdbMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectApplicationTests {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TJkDataMapper tJkDataMapper;
    @Autowired
    TTagInfoRtdbMapper tTagInfoRtdbMapper;
    
    @Test
    public void test01(){

    }




}
