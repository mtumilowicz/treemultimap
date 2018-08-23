import bus.BusFactory;
import infrastructure.CustomerEventsSortedMap;
import model.CustomerEvent;
import model.CustomerEventDetails;
import publisher.CustomerEventPublisher;

import java.time.LocalDateTime;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
public class Main {
    public static void main(String[] args) {
        CustomerEventsSortedMap customerEventsSortedMap = new CustomerEventsSortedMap();
        CustomerEventPublisher publisher = new CustomerEventPublisher(BusFactory.getBus(customerEventsSortedMap));

        publisher.publish(CustomerEvent.builder()
                .customerId(1)
                .details(CustomerEventDetails.builder()
                        .message("abc")
                        .dateTime(LocalDateTime.parse("2018-10-10T10:10"))
                        .build()).build());

        System.out.println(customerEventsSortedMap.getDetailsFor(1));
    }
}
