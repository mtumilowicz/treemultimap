package bus;

import com.google.common.eventbus.EventBus;
import infrastructure.CustomerEventsSortedMap;

/**
 * Created by mtumilowicz on 2018-08-23.
 */
public final class BusFactory {
    public static EventBus customerEventsSortedMapBus(CustomerEventsSortedMap map) {
        EventBus bus = new EventBus();
        bus.register(map);
        return bus;
    }
}
