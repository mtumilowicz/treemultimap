package publisher;

import com.google.common.eventbus.EventBus;
import listener.CustomerEventListener;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import model.CustomerEvent;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class CustomerEventPublisher {
    static EventBus bus = new EventBus();

    static {
        bus.register(new CustomerEventListener());
    }

    public void publish(@NonNull CustomerEvent event) {
        bus.post(event);
    }
}
