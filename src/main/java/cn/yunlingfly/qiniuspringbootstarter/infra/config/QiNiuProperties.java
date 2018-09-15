package cn.yunlingfly.qiniuspringbootstarter.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 七牛云属性配置
 *
 * @author yunlingfly
 * @create 2018-09-15
 */

@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiNiuProperties {
    /**
     * 七牛云的密钥
     */
    private String accessKey = "access-key";
    private String secretKey = "secret-key";
    /**
     * 存储空间名字
     */
    private String bucket = "bucket-name";
    /**
     * 一般设置为cdn
     */
    private String cdnPrefix = "cdn";

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getCdnPrefix() {
        return cdnPrefix;
    }

    public void setCdnPrefix(String cdnPrefix) {
        this.cdnPrefix = cdnPrefix;
    }
}
