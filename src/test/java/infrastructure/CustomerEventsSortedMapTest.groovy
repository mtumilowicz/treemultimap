package infrastructure


import model.CustomerEvent
import model.CustomerEventDetails
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by mtumilowicz on 2018-08-23.
 */
class CustomerEventsSortedMapTest extends Specification {
    
    CustomerEventsSortedMap customerEventsSortedMap = new CustomerEventsSortedMap()
    
    def "test getDetailsFor, null set"() {
        expect:
        customerEventsSortedMap.getDetailsFor(1).isEmpty()
    }

    def "test getDetailsFor, CustomerEvent.details == null"() {
        when:
        customerEventsSortedMap.add(CustomerEvent.builder().customerId(1).build())

        then:
        thrown(IllegalArgumentException)
    }

    def "test getDetailsFor, CustomerEvent.details.dateTime == null"() {
        given:
        def eventDetails = CustomerEventDetails.builder()
                .message("message")
                .build()

        and:
        def event = CustomerEvent.builder()
                .customerId(1)
                .details(eventDetails)
                .build()

        when:
        customerEventsSortedMap.add(event)

        then:
        thrown(IllegalArgumentException)
    }

    def "test getDetailsFor, fully packed single data"() {
        given:
        def eventDetails = CustomerEventDetails.builder()
                .message("message")
                .dateTime(LocalDateTime.of(2010, 10, 10, 10, 10))
                .build()

        and:
        def event = CustomerEvent.builder()
                .customerId(1)
                .details(eventDetails)
                .build()

        when:
        customerEventsSortedMap.add(event)

        then:
        customerEventsSortedMap.getDetailsFor(1).size() == 1
        customerEventsSortedMap.getDetailsFor(1)[0] == eventDetails
    }

    def "test getDetailsFor, fully packed multiple data - order"() {
        given:
        def eventDetails1 = CustomerEventDetails.builder()
                .message("message")
                .dateTime(LocalDateTime.of(2010, 10, 10, 10, 10))
                .build()

        and:
        def event1 = CustomerEvent.builder()
                .customerId(1)
                .details(eventDetails1)
                .build()
        
        and:
        def eventDetails2 = CustomerEventDetails.builder()
                .message("message")
                .dateTime(LocalDateTime.of(2009, 10, 10, 10, 10))
                .build()

        and:
        def event2 = CustomerEvent.builder()
                .customerId(1)
                .details(eventDetails2)
                .build()

        and:
        def eventDetails3 = CustomerEventDetails.builder()
                .message("message")
                .dateTime(LocalDateTime.of(2012, 10, 10, 10, 10))
                .build()

        and:
        def event3 = CustomerEvent.builder()
                .customerId(1)
                .details(eventDetails3)
                .build()

        when:
        customerEventsSortedMap.add(event1)
        customerEventsSortedMap.add(event2)
        customerEventsSortedMap.add(event3)

        then:
        customerEventsSortedMap.getDetailsFor(1).size() == 3
        customerEventsSortedMap.getDetailsFor(1)[0] == eventDetails2
        customerEventsSortedMap.getDetailsFor(1)[1] == eventDetails1
        customerEventsSortedMap.getDetailsFor(1)[2] == eventDetails3
    }

    def "test getDetailsFor, fully packed multiple data, multiple customerId - order"() {
        given:
        def eventDetails1 = CustomerEventDetails.builder()
                .message("message")
                .dateTime(LocalDateTime.of(2010, 10, 10, 10, 10))
                .build()

        and:
        def event1 = CustomerEvent.builder()
                .customerId(1)
                .details(eventDetails1)
                .build()

        and:
        def eventDetails2 = CustomerEventDetails.builder()
                .message("message")
                .dateTime(LocalDateTime.of(2009, 10, 10, 10, 10))
                .build()

        and:
        def event2 = CustomerEvent.builder()
                .customerId(2)
                .details(eventDetails2)
                .build()

        and:
        def eventDetails3 = CustomerEventDetails.builder()
                .message("message")
                .dateTime(LocalDateTime.of(2012, 10, 10, 10, 10))
                .build()

        and:
        def event3 = CustomerEvent.builder()
                .customerId(1)
                .details(eventDetails3)
                .build()

        when:
        customerEventsSortedMap.add(event1)
        customerEventsSortedMap.add(event2)
        customerEventsSortedMap.add(event3)

        then:
        customerEventsSortedMap.getDetailsFor(1).size() == 2
        customerEventsSortedMap.getDetailsFor(1)[0] == eventDetails1
        customerEventsSortedMap.getDetailsFor(1)[1] == eventDetails3
        customerEventsSortedMap.getDetailsFor(2).size() == 1
        customerEventsSortedMap.getDetailsFor(2)[0] == eventDetails2
    }
}
