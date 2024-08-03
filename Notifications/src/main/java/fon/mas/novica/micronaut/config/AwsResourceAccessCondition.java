package fon.mas.novica.micronaut.config;

import io.micronaut.context.condition.Condition;
import io.micronaut.context.condition.ConditionContext;
import io.micronaut.context.env.Environment;
import io.micronaut.core.util.StringUtils;

public class AwsResourceAccessCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context) {

        if (StringUtils.isNotEmpty(System.getProperty("aws.accessKeyId")) && StringUtils.isNotEmpty(System.getProperty("aws.secretAccessKey"))) {
            System.err.println("OK");
            return true;
        } else {
            System.err.println("WTF?");
        }

        if (StringUtils.isNotEmpty(System.getenv("AWS_ACCESS_KEY_ID")) && StringUtils.isNotEmpty(System.getenv("AWS_SECRET_ACCESS_KEY"))) {
            return true;
        }

        if (StringUtils.isNotEmpty(System.getenv("AWS_CONTAINER_CREDENTIALS_RELATIVE_URI"))) {
            return true;
        }

        return context != null && context.getBean(Environment.class).getActiveNames().contains(Environment.AMAZON_EC2);
    }
}
