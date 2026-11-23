package az.ingress.task3;

public interface PaymentGateway {

    boolean pay(String card, double amountAzn);
}