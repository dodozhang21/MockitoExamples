- autowiring  @InjectMocks
- deep stub (look up mockito defaults) (doesn't work on generics yet http://code.google.com/p/mockito/issues/detail?id=251)
- thenReturn vs doReturn for voids
- any matcher (Warning on argument matchers:If you are using argument matchers, all arguments have to be provided by matchers.)
- chained returns
- etc http://mockito.googlecode.com/svn/tags/1.8.5/javadoc/org/mockito/Mockito.html

TODO:
add powermock example with newing http://www.gitshah.com/2010/05/how-to-mock-constructors-using.html http://code.google.com/p/powermock/wiki/MockitoUsage13 (syntactically harmonious with mockito)
add aop example of hybrid testing with spring test & mockito (5/9/2011 DONE)
add example for callback using Answer? Future stuff?