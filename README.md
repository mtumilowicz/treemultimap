[![Build Status](https://travis-ci.com/mtumilowicz/treemultimap.svg?token=PwyvjePQ7aiAX51hSYLE&branch=master)](https://travis-ci.com/mtumilowicz/treemultimap)

# treemultimap
Exploring basic features of guava's `TreeMultimap`.

# preface
`TreeMultimap` is an implementation of `Multimap` whose keys and values are ordered by their 
natural ordering or by supplied comparators.  
**Warning**: The comparators or comparables used must 
be consistent with equals as explained by the Comparable class specification. Otherwise, 
the resulting multiset will violate the general contract of SetMultimap, which it is 
specified in terms of Object.equals(java.lang.Object).

**SetMultimap** contract:
Since the value collections are sets, the behavior of a SetMultimap is not specified if 
key or value objects already present in the multimap change in a manner that affects 
equals comparisons. Use caution if mutable objects are used as keys or values in a SetMultimap.

# manual
* build.gradle
    ```
    compile group: 'com.google.guava', name: 'guava', version: '25.1-jre'
    ```

* TreeMultimap creation
    ```
    TreeMultimap<X, Y> customerEvents =
            TreeMultimap.create(keyComparator, valueComparator);
    ```

# project description
* `CustomerEvent` - events that are published:
    ```
    int customerId;
    CustomerEventDetails details;
    ```
    where `CustomerEventDetails` is:
    ```
    String message;
    LocalDateTime dateTime;
    ```
* `CustomerEventPublisher` publishes `CustomerEvent` using guava's EventBus
* `CustomerEventsSortedMap` listens to published `CustomerEvent`, and add them to TreeMultimap
   * defining multimap (sorted by dateTime from `CustomerEventDetails`):
        ```
        TreeMultimap<Integer, CustomerEventDetails> customerEvents =
            TreeMultimap.create(Comparator.naturalOrder(), Comparator.comparing(CustomerEventDetails::getDateTime));
        ```
    * listening:
        ```
        @Subscribe
        void add(@NonNull CustomerEvent event) {
            Preconditions.checkArgument(nonNull(event.getDetails()));
            Preconditions.checkArgument(nonNull(event.getDetails().getDateTime()));
         
            customerEvents.put(event.getCustomerId(), event.getDetails());
        }     
        ```
* binding publisher to listener:
    * producing `EventBus`:
        ```
        public static EventBus customerEventsSortedMapBus(CustomerEventsSortedMap map) {
             EventBus bus = new EventBus();
             bus.register(map);
             return bus;
        }    
        ```
    * binding to publisher:
        ```
        def customerEventsSortedMap = new CustomerEventsSortedMap()
        def bus = BusFactory.customerEventsSortedMapBus(customerEventsSortedMap)
        def customerEventPublisher = new CustomerEventPublisher(bus)        
        ```
# tests
**Coverage**: 90%


   
