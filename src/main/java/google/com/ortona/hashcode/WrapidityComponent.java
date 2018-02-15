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

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.ac.ox.cs.diadem.env.configuration.Configuration;
import uk.ac.ox.cs.diadem.env.configuration.ConfigurationFacility;

/**
 * Hello world!
 * 
 * @author <a href="mailto:${developer-email}">${developer-name}</a>
 * @version 1.0-SNAPSHOT
 **/
public class WrapidityComponent {

	private static final Logger LOGGER = LoggerFactory.getLogger(WrapidityComponent.class);

	public static void main(final String[] args) {

		final Configuration config = ConfigurationFacility.getConfiguration();

		// Example of logging statement
		LOGGER.info(getBackMessage("Hello World!"));

		// Example of configuration access
		LOGGER.info("Current Platform Name '{}'", config.getString("platform.name"));

		// Example of resource loading
		final File resourceFile = new File(WrapidityComponent.class.getResource("appResource.txt").getFile());
		LOGGER.info("Name of the Resource '{}'", resourceFile.getName());
	}

	/**
	 * @param The
	 *            message to get back
	 * @return the message
	 */
	public static String getBackMessage(String message) {
		return message;
	}
}