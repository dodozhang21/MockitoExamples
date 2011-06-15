If using eclipse, install m2eclipse plugin.

Import -> Maven -> Existing Maven Projects -> Select MockitoExamples folder
Add the following folders as source folders
main
test
main/properties
main/resources
(Optional: change the jre lib to whatever you wish to use)

When you run the entire test source folder as JUnit, the only test should fail is ExampleServiceTest.testDoSomething() due to the bug http://code.google.com/p/mockito/issues/detail?id=262 for version 1.8.5.  I hope to see it pass once a new version of Mockito is released. 
 
I welcome forking.

Test Doubles (Mocking Terminologies) 
 - http://tinyurl.com/testdoubles
 
Mockito Blog Entry by Its Author
 - http://monkeyisland.pl/2008/01/14/mockito/
 
Latest Mockito API
 - http://mockito.googlecode.com/svn/tags/latest/javadoc/org/mockito/Mockito.html
 
More in Mockito Matchers
 - http://mockito.googlecode.com/svn/tags/latest/javadoc/org/mockito/Matchers.html
 
Aspect Oriented Programming with Spring
 - http://static.springsource.org/spring/docs/3.0.x/reference/aop.html
 
PowerMock API
 - http://code.google.com/p/powermock/wiki/MockitoUsage13
