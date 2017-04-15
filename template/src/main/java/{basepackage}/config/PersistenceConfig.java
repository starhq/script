package net.shinsoft.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:config.properties"})
@MapperScan("net.shinsoft.dao")
public class PersistenceConfig {

    @Autowired
    private Environment env;


    //    druid
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        //初始化时建立物理连接的个数
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("jdbc.initialSize")));
        //最小空闲数
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("jdbc.minIdle")));
        //最大活跃数
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("jdbc.maxActive")));
        //获取连接时最大等待时间
        dataSource.setMaxWait(Long.parseLong(env.getProperty("jdbc.maxWait")));
        //隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource
                .setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("jdbc" +
                        ".timeBetweenEvictionRunsMillis")));
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("jdbc.minEvictableIdleTimeMillis")));
        //用来检测连接是否有效的sql，要求是一个查询语句
        dataSource.setValidationQuery(env.getProperty("jdbc.validationQuery"));
        //申请连接的时候检测
        dataSource.setTestWhileIdle(Boolean.valueOf(env.getProperty("jdbc.testWhileIdle")));
        //申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnBorrow(Boolean.valueOf(env.getProperty("jdbc.testOnBorrow")));
        //归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnReturn(Boolean.valueOf(env.getProperty("jdbc.testOnReturn")));
        //要启用PSCache，必须配置大于0，当大于0时，
        //poolPreparedStatements自动触发修改为true。
        //在Druid中，不会存在Oracle下PSCache占用内存过多的问题，
        //可以把这个数值配置大一些，比如说100
        dataSource.setMaxOpenPreparedStatements(Integer.parseInt(env.getProperty("jdbc.maxOpenPreparedStatements")));
        //是否缓存preparedStatement，也就是PSCache。
        //PSCache对支持游标的数据库性能提升巨大，比如说oracle。
        //在mysql5.5以下的版本中没有PSCache功能，建议关闭掉。
        //作者在5.5版本中使用PSCache，通过监控界面发现PSCache有缓存命中率记录，
        //该应该是支持PSCache。
        dataSource.setPoolPreparedStatements(Boolean.valueOf(env.getProperty("jdbc.poolPreparedStatements")));
        //对于长时间不使用的连接强制关闭
        dataSource.setRemoveAbandoned(Boolean.valueOf(env.getProperty("jdbc.removeAbandoned")));
        //超过30分钟开始关闭空闲连接
        dataSource.setRemoveAbandonedTimeout(Integer.parseInt(env.getProperty("jdbc.removeAbandonedTimeout")));
        //将当前关闭动作记录到日志
        dataSource.setLogAbandoned(Boolean.valueOf(env.getProperty("jdbc.logAbandoned")));
        try {
            dataSource.setFilters(env.getProperty("jdbc.filters"));
        } catch (SQLException e) {
            throw new RuntimeException("datasource设置filter失败");
        }

        return dataSource;
    }

//  hikari
//    @Bean
//    public DataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(env.getProperty("sqlserver.driverClassName"));
//        dataSource.setJdbcUrl(env.getProperty("sqlserver.url"));
//        dataSource.setUsername(env.getProperty("sqlserver.username"));
//        dataSource.setPassword(env.getProperty("sqlserver.password"));
//
//        dataSource.setReadOnly(Boolean.valueOf(env.getProperty("jdbc.readOnly")));
//        dataSource.setConnectionTimeout(Long.valueOf(env.getProperty("jdbc.connectionTimeout")));
//        dataSource.setIdleTimeout(Long.valueOf(env.getProperty("jdbc.idleTimeout")));
//        dataSource.setMaxLifetime(Long.valueOf(env.getProperty("jdbc.maxLifetime")));
//        dataSource.setMaximumPoolSize(Integer.valueOf(env.getProperty("jdbc.maximumPoolSize")));
//
//        return dataSource;
//    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setConfigLocation(new ClassPathResource("sql-map-config.xml"));
        return sessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }


}
