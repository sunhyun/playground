package dev.playground.hyun.storage.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration(
    @Value("\${spring.jpa.database-platform}")
    private val hibernateDialect: String,
    @Value("\${spring.jpa.hibernate.ddl-auto}")
    private val hibernateDdlAuto: String,
    @Value("\${spring.jpa.properties.hibernate.format_sql}")
    private val hibernateFormatSql: String,
    @Value("\${spring.jpa.properties.hibernate.show_sql}")
    private val hibernateShowSql: String,
) {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "hyun.datasource.testdb")
    fun hikariConfig(): HikariConfig {
        return HikariConfig()
    }

    @Bean
    @Primary
    fun dataSource(hikariConfig: HikariConfig): DataSource {
        return HikariDataSource(hikariConfig)
    }

    @Bean
    @Primary
    fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val factory = LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSource
        factory.jpaVendorAdapter = HibernateJpaVendorAdapter()
        factory.setJpaPropertyMap(
            mapOf(
                "hibernate.dialect" to hibernateDialect,
                "hibernate.hbm2ddl.auto" to hibernateDdlAuto,
                "hibernate.format_sql" to hibernateFormatSql,
                "hibernate.show_sql" to hibernateShowSql
            )
        )
        factory.setPackagesToScan("dev.playground.hyun.storage")
        return factory
    }

    @Bean
    @Primary
    fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }
}
