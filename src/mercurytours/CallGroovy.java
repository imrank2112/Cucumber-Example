package mercurytours;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;

import org.codehaus.groovy.control.CompilationFailedException;

public class CallGroovy {


	public static void main(String[] args) throws InstantiationException, IllegalAccessException
	{
		GroovyClassLoader classLoader = new GroovyClassLoader();
		StringBuilder groovyScript = new StringBuilder();
		groovyScript.append("class Sample {");
		groovyScript.append("  String sayIt(name) { \"Groovy says: Cool $name!\" }");
		groovyScript.append("}");


		// Load string as Groovy script class.
		Class groovy = classLoader.parseClass(groovyScript.toString());
		GroovyObject groovyObject=null;
		try {
			groovyObject = (GroovyObject)groovy.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = groovyObject.invokeMethod("sayIt",new Object[] {"Imran	"}).toString();
		System.out.println(output);

		// Load Groovy script file.
		try {
			groovy = classLoader.parseClass(new File("D:\\ETL\\test.groovy"));
		} catch (CompilationFailedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		groovyObject = (GroovyObject) groovy.newInstance();
		Object output1 = groovyObject.invokeMethod("hello_world",null);

	}

}
