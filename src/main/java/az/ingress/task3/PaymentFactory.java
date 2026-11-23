package az.ingress.task3;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactory {

    private final Map<String, PaymentService.PaymentGateway> gateways =
            new HashMap<>();

    public PaymentFactory() {
        gateways.put("PAYLINK",
                new PayLinkAdapter(new PaymentService.PayLinkSdk()));

        gateways.put("EASYPAY",
                new EasyPayAdapter(new PaymentService.EasyPaySdk()));

        gateways.put("GLOBALPAY",
                new GlobalPayAdapter(new PaymentService.GlobalPaySdk()));
    }

    public PaymentService.PaymentGateway getGateway(String provider) {

        PaymentService.PaymentGateway gateway = gateways.get(provider);

        if (gateway == null) {
            throw new IllegalArgumentException("Unknown provider: " + provider);
        }

        return gateway;
    }
}