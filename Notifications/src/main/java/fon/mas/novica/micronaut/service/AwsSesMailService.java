package fon.mas.novica.micronaut.service;

import fon.mas.novica.micronaut.config.AwsResourceAccessCondition;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@Singleton
@Requires(condition = AwsResourceAccessCondition.class)
public class AwsSesMailService implements EmailService {
    private static final Logger LOG = LoggerFactory.getLogger(AwsSesMailService.class);
    protected final String sourceEmail;
    protected final SesClient ses;

    public AwsSesMailService(@Value("${aws.region}") String awsRegionProp,
                             @Value("${aws.sourceemail}") String sourceEmailProp) {

        this.sourceEmail = sourceEmailProp;
        String awsRegion = awsRegionProp;
        this.ses = SesClient.builder().region(Region.of(awsRegion)).build();
    }

    @Override
    public void send(MyEmail email) {
        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .destination(Destination.builder().toAddresses(email.getRecipient()).build())
                .source(sourceEmail)
                .message(Message.builder().subject(Content.builder().data(email.getSubject()).build())
                        .body(Body.builder().text(Content.builder().data(email.getTextBody()).build()).build()).build())
                .build();
        SendEmailResponse response =ses.sendEmail(sendEmailRequest);
        LOG.info("Sent email with id: {}", response.messageId());
    }

}
