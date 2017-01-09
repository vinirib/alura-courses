package br.com.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		
		DriverManagerDataSource source = new DriverManagerDataSource();
		
		source.setUsername("root");
		source.setPassword("root");
		source.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
		source.setDriverClassName("com.mysql.jdbc.Driver");
		
		factoryBean.setDataSource(source);
		
		Properties properties = new Properties();
		
        properties.setProperty("hibernate.dialect" , "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        
        factoryBean.setJpaProperties(properties);

        factoryBean.setPackagesToScan("br.com.casadocodigo.loja.models");

        return factoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory){
		return new JpaTransactionManager(factory);
	}
}
