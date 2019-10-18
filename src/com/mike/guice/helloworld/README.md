-------------------
Conclusions:
-------------------

1. Guice does not need to explicitly include any dependency on any of its modules
2. If 1. happens, Guice will search for the dependency constructor and see if its has a dependency.
 If so, the constructor has to be marked with a **@inject**
3. @Provides can be used in the injection point and in the provider 

References
----------
1. Great tutorial: https://objectcomputing.com/resources/publications/sett/may-2007-dependency-injection-with-guice.
Provider as interface is shown, bind, Named.