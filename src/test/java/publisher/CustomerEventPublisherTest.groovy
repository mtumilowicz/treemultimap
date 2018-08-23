package publisher

import com.google.common.eventbus.EventBus
import model.CustomerEvent
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2018-08-23.
 */
class CustomerEventPublisherTest extends Specification {
    
    def eventBus = Mock(EventBus)
    def customerEventPublisher = new CustomerEventPublisher(eventBus)
    
    def "test publish"() {
        given:
        def build = CustomerEvent.builder().build()

        when:
        customerEventPublisher.publish(build)
        
        then:
        1 * eventBus.post(build)
    }
}
