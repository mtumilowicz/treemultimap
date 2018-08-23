package publisher;

import com.google.common.eventbus.EventBus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import model.CustomerEvent;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
@SuppressWarnings("UnstableApiUsage")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public final class CustomerEventPublisher {
    EventBus bus;
    
    public void publish(@NonNull CustomerEvent event) {
        bus.post(event);
    }
}
