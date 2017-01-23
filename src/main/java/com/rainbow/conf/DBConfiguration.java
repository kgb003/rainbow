package com.rainbow.conf;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {
	
	@Autowired
	@Qualifier("rainbowDataSource")
	DataSource rainbowDataSource;
	
	@Primary
	@Configuration
	@ConfigurationProperties(prefix = "spring.datasource")
	static class RainbowDataSource extends HikariConfig {
		@Bean(name = "rainbowDataSource")
		DataSource rainbowDataSource() {			
			return new HikariDataSource(this);
		}
	}
	
	@Primary
	@Bean(name = "rainbowSqlSessionFactoryBean")
	public SqlSessionFactoryBean rainbowSqlSessionFactoryBean(ApplicationContext applicationContext) throws IOException {
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(rainbowDataSource);
		//factoryBean.setConfigLocation(applicationContext.getResource("classpath:META-INF/mybatis/configuration.xml"));
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:mybatis/rainbow/**.xml"));
		
		return factoryBean;
	}
	
	@Primary
	@Bean(name = "rainbowSqlSessionTemplate")
    public SqlSessionTemplate rainbowSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	
	
	
	
//	@Bean(name = "mailDataSource")
//	@ConfigurationProperties(prefix = "db.mail")
//	public DataSource mailDataSource() throws IOException {
//		return DataSourceBuilder.create().build();
//	}
// 
//	@Bean(name = "mysqlFactoryBean")
//	public LocalContainerEntityManagerFactoryBean getFactoryBean(EntityManagerFactoryBuilder builder) throws IOException
//	{
//		return builder.dataSource(getDataSource()).packages("com.web.domain.mysql").build();
//	}
// 
//	@Bean(name = "mysqlTransactionManager")
//	PlatformTransactionManager getTransactionManager(EntityManagerFactoryBuilder builder) throws IOException
//	{
//		return new JpaTransactionManager(getFactoryBean(builder).getObject());
//	}
}
