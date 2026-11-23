package az.ingress.task3;

public class PayLinkAdapter implements PaymentService.PaymentGateway {

    private final PaymentService.PayLinkSdk payLink;

    public PayLinkAdapter(PaymentService.PayLinkSdk payLink) {
        this.payLink = payLink;
    }

    @Override
    public boolean pay(String card, double amountAzn) {
        long qapik = Math.round(amountAzn * 100);

        int code = payLink.charge(card, qapik);

        return code == 0;
    }
}