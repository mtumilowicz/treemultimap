import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.TreeMultimap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.Comparator;

import static java.util.Objects.nonNull;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class CustomerEventsSortedMap {
    static TreeMultimap<Integer, CustomerEventDetails> customerEvents =
            TreeMultimap.create(Comparator.naturalOrder(), Comparator.comparing(CustomerEventDetails::getDateTime));


    static ImmutableCollection<Integer> getCustomerIds() {
        return ImmutableSet.copyOf(customerEvents.keySet());
    }

    static ImmutableSortedSet<CustomerEventDetails> getDetailsFor(int id) {
        return ImmutableSortedSet.copyOf(customerEvents.get(id));
    }

    static void add(@NonNull CustomerEvent event) {
        Preconditions.checkArgument(nonNull(event.getDetails()));

        customerEvents.put(event.getCustomerId(), event.getDetails());
    }
}
