package infrastructure


import model.CustomerEvent
import model.CustomerEventDetails
import spock.lang.Specification

import java.time.LocalDateTime

/**
 * Created by mtumilowicz on 2018-08-23.
 */
class CustomerEventsSortedMapTest extends Specification {
    def "test getDetailsFor, null set"() {
        expect:
        CustomerEventsSortedMap.getDetailsFor(1).isEmpty()
    }

    def "test getDetailsFor, CustomerEvent.details == null"() {
        when:
        CustomerEventsSortedMap.add(CustomerEvent.builder().customerId(1).build())

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
        CustomerEventsSortedMap.add(event)

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
        CustomerEventsSortedMap.add(event)

        then:
        CustomerEventsSortedMap.getDetailsFor(1).size() == 1
        CustomerEventsSortedMap.getDetailsFor(1)[0] == eventDetails
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
        CustomerEventsSortedMap.add(event1)
        CustomerEventsSortedMap.add(event2)
        CustomerEventsSortedMap.add(event3)

        then:
        CustomerEventsSortedMap.getDetailsFor(1).size() == 3
        CustomerEventsSortedMap.getDetailsFor(1)[0] == eventDetails2
        CustomerEventsSortedMap.getDetailsFor(1)[1] == eventDetails1
        CustomerEventsSortedMap.getDetailsFor(1)[2] == eventDetails3
    }
}
