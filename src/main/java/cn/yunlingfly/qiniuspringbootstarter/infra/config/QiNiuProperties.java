package cn.yunlingfly.qiniuspringbootstarter.infra.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 七牛云属性配置
 *
 * @author pjmike
 * @create 2018-08-20 11:52
 */
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiNiuProperties {
    /**
     * 七牛云的密钥
     */
    private String accessKey = "access_key";
    private String secretKey = "secret_key";
    /**
     * 存储空间名字
     */
    private String bucket = "bucket_name";
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
