package infrastructure;

import com.google.common.base.Preconditions;
import com.google.common.collect.TreeMultimap;
import com.google.common.eventbus.Subscribe;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import model.CustomerEvent;
import model.CustomerEventDetails;

import java.util.Comparator;
import java.util.TreeSet;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class CustomerEventsSortedMap {
    TreeMultimap<Integer, CustomerEventDetails> customerEvents =
            TreeMultimap.create(Comparator.naturalOrder(), Comparator.comparing(CustomerEventDetails::getDateTime));

    public TreeSet<CustomerEventDetails> getDetailsFor(int id) {
        return new TreeSet<>(customerEvents.get(id));
    }

    @Subscribe
    private void add(@NonNull CustomerEvent event) {
        Preconditions.checkArgument(nonNull(event.getDetails()));
        Preconditions.checkArgument(nonNull(event.getDetails().getDateTime()));

        customerEvents.put(event.getCustomerId(), event.getDetails());
    }
    
    public void clear() {
        customerEvents.clear();
    }
}
