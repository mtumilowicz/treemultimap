package model;

import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
@Value
@Builder
public class CustomerEvent {
    int customerId;
    CustomerEventDetails details;
}
