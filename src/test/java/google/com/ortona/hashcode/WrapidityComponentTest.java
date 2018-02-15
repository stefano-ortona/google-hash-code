/*
 * MIT License

 * Copyright (c) 2016 Meltwater

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package google.com.ortona.hashcode;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;

import org.junit.Test;

import google.com.ortona.hashcode.WrapidityComponent;

import uk.ac.ox.cs.diadem.env.testsupport.StandardTestcase;

/**
 * Unit test for simple DIADEM App.
 */
public class WrapidityComponentTest extends StandardTestcase {
  @Test
  public void testScratch() {
    assertTrue(true);
  }

  @Test(expected = AssertionError.class)
  public void testAssert() {
	
    assertFalse(WrapidityComponent.getBackMessage("message").equals("message"));
  }
  
  @Test
  public void testMainMethod() {
	
    WrapidityComponent.main((String[])Array.newInstance(String.class, 1));
    assertTrue(true);
  }

  @Test
  public void testConstructor() {
    assertNotNull(new WrapidityComponent());
  }

}
