package az.ingress.task3;

public class GlobalPayAdapter implements PaymentService.PaymentGateway {

    private final PaymentService.GlobalPaySdk globalPay;

    public GlobalPayAdapter(PaymentService.GlobalPaySdk globalPay) {
        this.globalPay = globalPay;
    }

    @Override
    public boolean pay(String card, double amountAzn) {
        double amountUsd = amountAzn / 1.70;

        String result = globalPay.process(amountUsd, card);

        return result.equals("OK");
    }
}