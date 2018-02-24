package google.com.ortona.hashcode.data_center.logic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import google.com.ortona.hashcode.data_center.logic.model.Server;

public class OutputWriter {

	public static void writeToFile(String outputFileName, List<Server> servers) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");

		for (Server server : servers) {
			if (server.getInitialSlot() == null) {
				writer.println("x");
			} else {
				writer.println(server.getInitialSlot().getRow() + " " + server.getInitialSlot().getColumn()+ " " +server.getPool());
			}
		}
		
		writer.close();
	}

}
