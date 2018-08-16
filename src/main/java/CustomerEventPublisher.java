import com.google.common.eventbus.EventBus;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
final class CustomerEventPublisher {
    static EventBus bus = new EventBus();

    static {
        bus.register(new CustomerEventListener());
    }

    void publish(@NonNull CustomerEvent event) {
        bus.post(event);
    }
}
