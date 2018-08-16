import java.time.LocalDateTime;

/**
 * Created by mtumilowicz on 2018-08-16.
 */
public class Main {
    public static void main(String[] args) {
        CustomerEventPublisher publisher = new CustomerEventPublisher();

        publisher.publish(CustomerEvent.builder()
                .customerId(1)
                .details(CustomerEventDetails.builder()
                        .message("abc")
                        .dateTime(LocalDateTime.parse("2018-10-10T10:10"))
                        .build()).build());

        System.out.println(CustomerEventsSortedMap.getDetailsFor(1));
    }
}
