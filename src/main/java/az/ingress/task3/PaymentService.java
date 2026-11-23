package az.ingress.task3;

import java.math.BigDecimal;
import java.util.List;

public class PaymentService {
//ADAPTER PATTERN
    interface PaymentGateway {
        boolean pay(String card, double amountAzn);
    }


    static class PayLinkSdk {

        int charge(String card, long amount) {
            System.out.println("   PayLink: " + amount + " qapik");
            return amount <= 100_000 ? 0 : 51;
        }
    }

    static class EasyPaySdk {

        void makePayment(BigDecimal amount, String currency, String card) {
            System.out.println("   EasyPay: " + amount + " " + currency);

            if (amount.compareTo(new BigDecimal("2000")) > 0) {
                throw new IllegalStateException("Limit exceeded");
            }
        }
    }

    static class GlobalPaySdk {

        String process(double amountUsd, String card) {
            System.out.println(
                    "   GlobalPay: " + String.format("%.2f", amountUsd) + " USD"
            );

            return amountUsd <= 1000 ? "OK" : "DECLINED";
        }
    }

    private final PaymentFactory factory = new PaymentFactory();

    public boolean pay(String provider, String card, double amountAzn) {

        PaymentGateway gateway = factory.getGateway(provider);

        return gateway.pay(card, amountAzn);
    }

    record Payment(String provider, String card, double amountAzn) {
    }

    public static void main(String[] args) {

        PaymentService service = new PaymentService();

        List<Payment> payments = List.of(
                new Payment("PAYLINK", "4169-1111", 150),
                new Payment("PAYLINK", "4169-1111", 1500),
                new Payment("EASYPAY", "5522-2222", 300),
                new Payment("EASYPAY", "5522-2222", 2500),

                new Payment("GLOBALPAY", "4098-3333", 340),
                new Payment("GLOBALPAY", "4098-3333", 1870)
        );

        for (Payment p : payments) {

            boolean ok = service.pay(
                    p.provider(),
                    p.card(),
                    p.amountAzn()
            );

            System.out.println(
                    p.provider() + " " +
                            p.amountAzn() + " AZN -> " +
                            (ok ? "SUCCESS" : "FAILED")
            );
        }
    }
}