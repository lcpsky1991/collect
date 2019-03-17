package top.rish.collect.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
//扫描 Mapper 接口并容器管理
@MapperScan(basePackages = OracleDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleDataSourceConfig {
        // 精确到 oracle 目录，以便跟其他数据源隔离
        static final String PACKAGE = "top.rish.mappers.oracle";
        static final String MAPPER_LOCATION = "classpath:oraclemybatis/*.xml";
        @Value("${oracle.datasource.url}")
        private String url;

        @Value("${oracle.datasource.username}")
        private String user;

        @Value("${oracle.datasource.password}")
        private String password;

        @Value("${oracle.datasource.driverClassName}")
        private String driverClass;

        @Bean(name = "oracleDataSource")
        public DataSource oracleDataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setDriverClassName(driverClass);
            dataSource.setUrl(url);
            dataSource.setUsername(user);
            dataSource.setPassword(password);
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setValidationQueryTimeout(60000);
            return dataSource;
        }

        @Bean(name = "oracleTransactionManager")
        public DataSourceTransactionManager oracleTransactionManager() {
            return new DataSourceTransactionManager(oracleDataSource());
        }

        @Bean(name = "oracleSqlSessionFactory")
        public SqlSessionFactory oracleSqlSessionFactory(@Qualifier("oracleDataSource") DataSource oracleDataSource)
                throws Exception {
            final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(oracleDataSource);
            sessionFactory.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources(OracleDataSourceConfig.MAPPER_LOCATION));
            return sessionFactory.getObject();
        }
    }

