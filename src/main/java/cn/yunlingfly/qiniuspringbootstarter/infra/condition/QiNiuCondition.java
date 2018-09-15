package cn.yunlingfly.qiniuspringbootstarter.infra.condition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(QiNiuCondition.class);
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String property = context.getEnvironment().getProperty("qiniu.access_key");
        if (StringUtils.isEmpty(property)) {
            throw new RuntimeException("没有七牛云的配置");
        } else {
            return true;
        }
    }
}
