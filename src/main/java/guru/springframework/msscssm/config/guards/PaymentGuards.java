package guru.springframework.msscssm.config.guards;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import guru.springframework.msscssm.services.PaymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
public class PaymentGuards {
    @Bean
    public Guard<PaymentState, PaymentEvent> paymentIdGuard(){
        return context -> context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER) != null;
    }
}
