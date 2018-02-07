///**
// * <p>
// * Description:<br />
// * </p>
// */
//package com.valueservice.djs.config;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
///**
// * <p>
// * Description:<br />
// * </p>
// * @author Bill Chan
// * @date 2017年7月30日 下午11:01:53
// */
//@Component
//@ConfigurationProperties(prefix = "spring.datasource")
//public class DruidConfigProperties {
//	private String masterDriverClassName;
//    private String masterUrl;
//    private String masterUsername;
//    private String masterPassword;
//
//    private String dmDriverClassName;
//    private String dmUrl;
//    private String dmUsername;
//    private String dmPassword;
//
//    private String odsDriverClassName;
//    private String odsUrl;
//    private String odsUsername;
//    private String odsPassword;
//
//    //connection pool
//    private Boolean testOnBorrow;
//    private String  type;
//    private Integer initialSize;
//    private Integer minIdle;
//    private Integer maxActive;
//    private Integer maxWait;
//    private Long timeBetweenEvictionRunsMillis;
//    private Long minEvictableIdleTimeMillis;
//    private String validationQuery;
//    private String validationQueryOracle;
//    private Boolean testWhileIdle;
//    private Boolean testOnReturn;
//    private Boolean poolPreparedStatements;
//    private Integer maxPoolPreparedStatementPerConnectionSize;
//    private String filters;
//    private String connectionProperties;
//
//	public String getMasterDriverClassName() {
//		return masterDriverClassName;
//	}
//	public void setMasterDriverClassName(String masterDriverClassName) {
//		this.masterDriverClassName = masterDriverClassName;
//	}
//	public String getMasterUrl() {
//		return masterUrl;
//	}
//	public void setMasterUrl(String masterUrl) {
//		this.masterUrl = masterUrl;
//	}
//	public String getMasterUsername() {
//		return masterUsername;
//	}
//	public void setMasterUsername(String masterUsername) {
//		this.masterUsername = masterUsername;
//	}
//	public String getMasterPassword() {
//		return masterPassword;
//	}
//	public void setMasterPassword(String masterPassword) {
//		this.masterPassword = masterPassword;
//	}
//	public String getDmDriverClassName() {
//		return dmDriverClassName;
//	}
//	public void setDmDriverClassName(String dmDriverClassName) {
//		this.dmDriverClassName = dmDriverClassName;
//	}
//	public String getDmUrl() {
//		return dmUrl;
//	}
//	public void setDmUrl(String dmUrl) {
//		this.dmUrl = dmUrl;
//	}
//	public String getDmUsername() {
//		return dmUsername;
//	}
//	public void setDmUsername(String dmUsername) {
//		this.dmUsername = dmUsername;
//	}
//	public String getDmPassword() {
//		return dmPassword;
//	}
//	public void setDmPassword(String dmPassword) {
//		this.dmPassword = dmPassword;
//	}
//	public String getOdsDriverClassName() {
//		return odsDriverClassName;
//	}
//	public void setOdsDriverClassName(String odsDriverClassName) {
//		this.odsDriverClassName = odsDriverClassName;
//	}
//	public String getOdsUrl() {
//		return odsUrl;
//	}
//	public void setOdsUrl(String odsUrl) {
//		this.odsUrl = odsUrl;
//	}
//	public String getOdsUsername() {
//		return odsUsername;
//	}
//	public void setOdsUsername(String odsUsername) {
//		this.odsUsername = odsUsername;
//	}
//	public String getOdsPassword() {
//		return odsPassword;
//	}
//	public void setOdsPassword(String odsPassword) {
//		this.odsPassword = odsPassword;
//	}
//	public Boolean getTestOnBorrow() {
//		return testOnBorrow;
//	}
//	public void setTestOnBorrow(Boolean testOnBorrow) {
//		this.testOnBorrow = testOnBorrow;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public Integer getInitialSize() {
//		return initialSize;
//	}
//	public void setInitialSize(Integer initialSize) {
//		this.initialSize = initialSize;
//	}
//	public Integer getMinIdle() {
//		return minIdle;
//	}
//	public void setMinIdle(Integer minIdle) {
//		this.minIdle = minIdle;
//	}
//	public Integer getMaxActive() {
//		return maxActive;
//	}
//	public void setMaxActive(Integer maxActive) {
//		this.maxActive = maxActive;
//	}
//	public Integer getMaxWait() {
//		return maxWait;
//	}
//	public void setMaxWait(Integer maxWait) {
//		this.maxWait = maxWait;
//	}
//	public Long getTimeBetweenEvictionRunsMillis() {
//		return timeBetweenEvictionRunsMillis;
//	}
//	public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
//		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
//	}
//	public Long getMinEvictableIdleTimeMillis() {
//		return minEvictableIdleTimeMillis;
//	}
//	public void setMinEvictableIdleTimeMillis(Long minEvictableIdleTimeMillis) {
//		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
//	}
//	public String getValidationQuery() {
//		return validationQuery;
//	}
//	public void setValidationQuery(String validationQuery) {
//		this.validationQuery = validationQuery;
//	}
//	public Boolean getTestWhileIdle() {
//		return testWhileIdle;
//	}
//	public void setTestWhileIdle(Boolean testWhileIdle) {
//		this.testWhileIdle = testWhileIdle;
//	}
//	public Boolean getTestOnReturn() {
//		return testOnReturn;
//	}
//	public void setTestOnReturn(Boolean testOnReturn) {
//		this.testOnReturn = testOnReturn;
//	}
//	public Boolean getPoolPreparedStatements() {
//		return poolPreparedStatements;
//	}
//	public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
//		this.poolPreparedStatements = poolPreparedStatements;
//	}
//	public Integer getMaxPoolPreparedStatementPerConnectionSize() {
//		return maxPoolPreparedStatementPerConnectionSize;
//	}
//	public void setMaxPoolPreparedStatementPerConnectionSize(
//			Integer maxPoolPreparedStatementPerConnectionSize) {
//		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
//	}
//	public String getFilters() {
//		return filters;
//	}
//	public void setFilters(String filters) {
//		this.filters = filters;
//	}
//	public String getConnectionProperties() {
//		return connectionProperties;
//	}
//	public void setConnectionProperties(String connectionProperties) {
//		this.connectionProperties = connectionProperties;
//	}
//
//	public String getValidationQueryOracle() {
//		return validationQueryOracle;
//	}
//
//	public void setValidationQueryOracle(String validationQueryOracle) {
//		this.validationQueryOracle = validationQueryOracle;
//	}
//}
