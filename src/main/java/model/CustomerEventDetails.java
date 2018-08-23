package model;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
@Value
@Builder
public class CustomerEventDetails {
    String message;
    LocalDateTime dateTime;
}
