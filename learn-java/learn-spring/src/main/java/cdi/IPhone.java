package cdi;

import org.springframework.stereotype.Component;

/**
 * Created by mazhibin on 16/9/5
 */
@Component
public class IPhone implements Phone {
    @Override
    public void call() {
        System.out.println("IPhone call");
    }
}
