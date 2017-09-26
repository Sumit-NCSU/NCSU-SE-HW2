# HW2

CSC510 Software Engineering HW2


## OO Design Patterns

### Creational Patterns
  * `Singleton Pattern`: This design pattern is used when we need to make sure that a class has only one instance and all the other class instances are using the single instance of the singleton class (kind of like a global variable). In Java, this design pattern can be implemented by having a private constructor and a single private instance of the class defined within itself. The class instance can be accessed using a `public static getInstance()` method which initializes the private instance (if not already initialized) and returns it to the calling class.
  * `Factory Pattern`: In this design pattern, the base for creating an object is defined as an interface and the actual instantiation is done using subclasses. It uses the OO principle of inheritance to create the objects. In Java, this can be implemented by creating an interface and then creating classes which implement that interface. Now, we can have an object of the interface, but it will be instantiated as one of its implementing classes.

### Structural Patterns
  * `Adapter Pattern`: This design pattern is used when we have two classes that are incompatible, but they need to be used together. To implement this design patter, one of the classes is wrapped with an interface, which is in accordance with what is expected by the second class, thus allowing the second class to be able to interact with the first class which was previously incompatible.
  * `Decorator Pattern`: In this design pattern, additional functionality is added to an object dynamically after it has already been created. It is applicable when the functionality needs to be added dynamically to an object as opposed to adding it in a static way to the entire class (as in Inheritance). This can be achieved by creating multiple sub classes which extend and add features to the base class.

### Behavioral Patterns
  * `Observer Pattern`: This pattern is used when one (or more) objects need to subscribe to notifications for the changes in state of some object. Once the target object changes state, all the observing objects are notified of the change. To achieve this functionality, we can create an observer class which registers the classes which are interested in receiving updates for the changes of an object (all such classes must extend the observer class). The observer class has a method called `update()` which will be called for each of its registered classes whenever the state of the target object is changed, thus notifying the interested objects of the change)
  * `Memento Pattern`: This pattern is used when we need to return an object to a previous state (like the undo functionality in MS word). This is achieved by having a source object (originator) which can save its state, a caretaker object which tells when the state needs to be saved and a Memento which saves the state based on instruction from the caretaker. The memento can only be accessed by the originator.

## Free-style
  * `Lambda Design Pattern`: This design pattern is developed and used by Amazon AWS. It is a data processing design pattern (or design architecture), which handles processing of massive quantities of data for real time and batch processing. It is not related to any of the traditional "OO" design patterns
  Reference: [Lambda Architecture for Batch and Real-Time Processing on AWS](https://d0.awsstatic.com/whitepapers/lambda-architecure-on-for-batch-aws.pdf)
  * `Linux Kernel Design Patterns`: These are design patterns which were used while developing the Linux Kernel and are non-Object Oriented in nature (written in C - a non-object-oriented language). For example, toolbox - instead of providing a complete solution for a generic service, a set of tools is provided which can be used to build custom solutions.
  Reference: [Linux Kernel Design Patterns](https://lwn.net/Articles/336255/)
  * `Messaging Pattern`: This is a network oriented architectural design pattern which describes the process of connecting and communicating between different message passing systems. It has two kinds of message exchange patterns - request-response (as in HTML) or one-way (as in UDP).
  Reference: [Message Pattern](https://en.wikipedia.org/wiki/Messaging_pattern)

## Selenium Testing
The unit tests can be found [here](selenium/src/test/java/com/tests/WebTests.java).
