package cn.yunlingfly.qiniuspringbootstarter.infra.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * 校验类
 *
 * @author yunlingfly
 * @create 2018-09-15
 */

public class QiNiuCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String ak = context.getEnvironment().getProperty("qiniu.access-key");
        String sk = context.getEnvironment().getProperty("qiniu.secret-key");
        String bucketName = context.getEnvironment().getProperty("qiniu.bucket-name");

        if (StringUtils.isEmpty(ak) || StringUtils.isEmpty(sk) || StringUtils.isEmpty(bucketName)) {
            throw new RuntimeException("缺少七牛云的配置");
        } else {
            return true;
        }
    }
}
