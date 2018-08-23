package publisher

import bus.BusFactory
import infrastructure.CustomerEventsSortedMap
import model.CustomerEvent
import model.CustomerEventDetails
import publisher.CustomerEventPublisher
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by mtumilowicz on 2018-08-23.
 */
@SuppressWarnings("UnstableApiUsage")
class PublisherIntegrationTest extends Specification {
    def "integration"() {
        given:
        def customerEventsSortedMap = new CustomerEventsSortedMap()

        and:
        def bus = BusFactory.customerEventsSortedMapBus(customerEventsSortedMap)

        and:
        def customerEventPublisher = new CustomerEventPublisher(bus)

        and:
        def details = CustomerEventDetails.builder()
                .message("message")
                .dateTime(LocalDateTime.of(2010, 10, 10, 10, 10))
                .build()

        and:
        def event = CustomerEvent.builder()
                .customerId(1)
                .details(details)
                .build()

        when:
        customerEventPublisher.publish(event)
        
        then:
        customerEventsSortedMap.getDetailsFor(1)[0] == details
    }
}
