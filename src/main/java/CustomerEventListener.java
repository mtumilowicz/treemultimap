import com.google.common.eventbus.Subscribe;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * Created by mtumilowicz on 2018-05-22.
 */
@FieldDefaults(level = PRIVATE, makeFinal = true)
final class CustomerEventListener {
    
    @Subscribe
    private void onEvent(@NonNull CustomerEvent event) {
        CustomerEventsSortedMap.add(event);
    }
}
