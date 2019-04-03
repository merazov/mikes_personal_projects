---------------------
Conclusions:
-------------------

1. Guice does not need to explicitly include any dependency on any of its modules
2. If 1. happens, Guice will search for the dependency constructor and see if its has a dependency. If so, the constructor has to be marked with a **@inject**