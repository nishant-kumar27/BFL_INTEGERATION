package com.bfl;

import java.io.IOException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"com.bfl"}, entityManagerFactoryRef = "appEntityManager", transactionManagerRef = "appTransactionManager")
public class AppDatabaseConfig {

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean appEntityManager() throws IOException {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(appDataSource());
		em.setPackagesToScan(new String[] { "com.bfl.model" ,"com.bfl.ui.auth.model"});

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", ConfigProperties.getInstance().getConfigValue("spring.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.dialect", ConfigProperties.getInstance().getConfigValue("spring.jpa.properties.hibernate.dialect"));
		em.setJpaPropertyMap(properties);
		
		return em;
	}

	@Primary
    @Bean
    @ConfigurationProperties(prefix="spring.app.datasource")
    public DataSource appDataSource() {
        return DataSourceBuilder.create().build();
    }

	@Primary
	@Bean
	public PlatformTransactionManager appTransactionManager() throws IOException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(appEntityManager().getObject());
		return transactionManager;
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(appDataSource());
	}
	
	@Bean(name = "appJdbcTemplate")
	public NamedParameterJdbcTemplate appJdbcTemplate(@Qualifier("appDataSource") DataSource ds) {
		return new NamedParameterJdbcTemplate(ds);
	}
}