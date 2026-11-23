package az.ingress.task3;

import java.math.BigDecimal;

public class EasyPayAdapter implements PaymentService.PaymentGateway {

    private final PaymentService.EasyPaySdk easyPay;

    public EasyPayAdapter(PaymentService.EasyPaySdk easyPay) {
        this.easyPay = easyPay;
    }

    @Override
    public boolean pay(String card, double amountAzn) {
        try {
            easyPay.makePayment(
                    BigDecimal.valueOf(amountAzn),
                    "AZN",
                    card
            );
            return true;
        } catch (IllegalStateException e) {
            return false;
        }
    }
}