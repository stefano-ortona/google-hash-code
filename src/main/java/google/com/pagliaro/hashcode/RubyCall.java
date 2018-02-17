package google.com.pagliaro.hashcode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RubyCall {

 public static void main(String[] args) throws NoSuchMethodException, ScriptException, IOException {

  //create a script engine manager
  ScriptEngineManager manager = new ScriptEngineManager();
  
  //create a RubyScript engine
  ScriptEngine engine = manager.getEngineByName("ruby");
  
  System.out.println("Started java execution");
  engine.eval(Files.newBufferedReader(Paths.get(System.getProperty("user.dir") + "/src/main/java/google/com/pagliaro/hashcode/script.rb"), StandardCharsets.UTF_8));
  System.out.println("Evaluated ruby file");
  
  System.out.println("Started ruby invocation");
  Invocable inv = (Invocable)engine;
  long a = (long) inv.invokeFunction("ping", "test");
  System.out.println("Ruby file output:\n"+a);
 
 }

}