package google.com.pagliaro.hashcode;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import google.com.ortona.hashcode.App;

public class RubyWrapper {

 private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

 public static void main(String[] args) throws NoSuchMethodException, ScriptException, IOException {
	 
  //create a script engine manager
  ScriptEngineManager manager = new ScriptEngineManager();
  
  //create a RubyScript engine
  ScriptEngine engine = manager.getEngineByName("ruby");
  
  LOGGER.info("Started java execution");
  engine.eval(Files.newBufferedReader(Paths.get(System.getProperty("user.dir") + "/src/main/java/google/com/pagliaro/hashcode/pizza/script.rb"), StandardCharsets.UTF_8));
  LOGGER.info("Evaluated ruby file");
  
  LOGGER.info("Started ruby invocation");
  Invocable inv = (Invocable)engine;
  String input = System.getProperty("user.dir") + "/src/main/java/google/com/pagliaro/hashcode/pizza/files/example.in";
  String output = System.getProperty("user.dir") + "/src/main/java/google/com/pagliaro/hashcode/pizza/files/example.out";
  inv.invokeFunction("validate_output", input, output);
  LOGGER.info("Completed ruby invocation");
  
 }

}