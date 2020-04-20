package test.authors;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
   AppendAuthorTest.class,
   CreateAuthorTest.class,
   GetAuthorIdByNameTest.class,
   ReadAuthorTest.class,
   UpdateAuthorTest.class
})

public class AuthorTest {   
}  