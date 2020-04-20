package test.files;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	ReadFileTest.class,
   CreateFileTest.class,
   ReadFileTest.class,
   DeleteFileTest.class
})

public class FileTest {   
}  