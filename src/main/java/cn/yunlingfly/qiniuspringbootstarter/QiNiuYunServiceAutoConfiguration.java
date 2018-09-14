package cn.yunlingfly.qiniuspringbootstarter;

import cn.yunlingfly.qiniuspringbootstarter.api.service.IQiniuService;
import cn.yunlingfly.qiniuspringbootstarter.api.service.impl.QiniuServiceImpl;
import cn.yunlingfly.qiniuspringbootstarter.infra.condition.QiNiuCondition;
import cn.yunlingfly.qiniuspringbootstarter.infra.config.QiNiuProperties;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

// 装配配置属性
@Configuration
// 自动装配这个properties类，读取yaml自定义内容
@EnableConfigurationProperties(QiNiuProperties.class)
// service类
@ConditionalOnClass(IQiniuService.class)
// 校验类
@Conditional(QiNiuCondition.class)
// 校验前缀
@ConditionalOnProperty(prefix = "qiniu",value = "enabled",matchIfMissing = true)
public class QiNiuYunServiceAutoConfiguration {
    @Autowired
    private QiNiuProperties qiNiuYunProperties;

    /// 指定实例化接口的类
    @Bean
    @ConditionalOnMissingBean(QiniuServiceImpl.class)
    public IQiniuService qiNiuYunService() {
        return new QiniuServiceImpl();
    }

    // 构建一个七牛上传工具实例
    @Bean
    public UploadManager uploadManager() {
        return new UploadManager();
    }

    // 认证信息实例
    @Bean
    public Auth auth() {
        return Auth.create(qiNiuYunProperties.getAccessKey(), qiNiuYunProperties.getSecretKey());
    }

    // 构建七牛空间管理实例
    @Bean
    public BucketManager bucketManager() {
        return new BucketManager(auth());
    }
}
