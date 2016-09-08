package cdi;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by mazhibin on 16/9/5
 */
@Service
public class User {

    @Inject
    Phone phone;

    void call(){
        phone.call();
    }
}
