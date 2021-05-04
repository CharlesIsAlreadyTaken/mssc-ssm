package guru.springframework.msscssm.config.actions;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import guru.springframework.msscssm.services.PaymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PaymentActions {
    @Bean
    public Action<PaymentState, PaymentEvent> preAuthAction() {
        return context -> {
            System.out.println("PreAuth was called!");

            if (new Random().nextInt(10) < 8) {
                System.out.println("Approved");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_APPROVED)
                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            } else {
                System.out.println("Declined, No Credit!");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.PRE_AUTH_DECLINED)
                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            }
        };
    }

    @Bean
    public Action<PaymentState, PaymentEvent> preAuthActionApproved() {
        return context -> {
            System.out.println("PreAuth was approved");
        };
    }

    @Bean
    public Action<PaymentState, PaymentEvent> preAuthActionDeclined() {
        return context -> {
            System.out.println("PreAuth was declined");
        };
    }


    @Bean
    public Action<PaymentState, PaymentEvent> authAction() {
        return context -> {
            System.out.println("AuthAuth was called!");

            if (new Random().nextInt(10) < 8) {
                System.out.println("Auth Approved");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.AUTH_APPROVED)
                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            } else {
                System.out.println("Auth declined, No Credit!");
                context.getStateMachine().sendEvent(MessageBuilder.withPayload(PaymentEvent.AUTH_DECLINED)
                        .setHeader(PaymentServiceImpl.PAYMENT_ID_HEADER, context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER))
                        .build());
            }
        };
    }

    @Bean
    public Action<PaymentState, PaymentEvent> authActionApproved() {
        return context -> {
            System.out.println("Auth was approved");
        };
    }

    @Bean
    public Action<PaymentState, PaymentEvent> authActionDeclined() {
        return context -> {
            System.out.println("Auth was declined");
        };
    }
}
