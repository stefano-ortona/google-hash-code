/*
 * MIT License

 * Copyright (c) 2016
 */
package google.com.ortona.hashcode;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import google.com.ortona.hashcode.data_center.logic.model.*;


/**
 * Unit test
 */
public class UtilsFileServerTest {

    static private final String filePathExample = "server-test.in";
    static private UtilsFileServer fr = new UtilsFileServer(filePathExample);
    static private Datacenter datacenter = fr.getDatacenter();
    static private int[] actualHeader = new int[]{2, 5, 1, 2, 5};
    static private int[] header = fr.getHeader();
    static private List<Slot> unavailableSlots = fr.getUnavailableSlots();
    static private List<Slot> actualUnavailableSlots = new ArrayList<>();
    static private List<Server> servers = fr.getServers();
    static private List<Server> actualServers = new ArrayList<>();


    @BeforeClass
    public static void bringUp() {

        actualUnavailableSlots.add(new Slot(0, 0));

        actualServers.add(new Server(0, 3, 10));
        actualServers.add(new Server(1, 3, 10));
        actualServers.add(new Server(2, 2, 5));
        actualServers.add(new Server(3, 1, 5));
        actualServers.add(new Server(4, 1, 1));
    }

    @Test
    public void testHeaderServer() {
        testHeader(header, actualHeader);
    }

    private void testHeader(int[] actualHeader, int[] header) {
        for (int i = 0; i < actualHeader.length; i++) {
            Assert.assertEquals(header[i], actualHeader[i]);
        }
    }

    @Test
    public void testUnavailableSlotsSizeServer() {
        int amount = fr.getUnavailableSlotAmount();
        Assert.assertEquals(unavailableSlots.size(), amount);
    }

    @Test
    public void testServersSizeServer() {
        int amount = fr.getServersAmount();
        Assert.assertEquals(servers.size(), amount);
    }

    @Test
    public void testFileIsAllRead() {
        int fileSize = fr.getFile().length;
        Assert.assertEquals(fileSize, unavailableSlots.size() + servers.size() + 1);
        Assert.assertEquals(fileSize, fr.getServersAmount() + fr.getUnavailableSlotAmount() + 1);
    }

    @Test
    public void testPrintUnavailableSlots() {
        for (Slot s : unavailableSlots) {
            System.out.println(s.toString());
        }
    }

    @Test
    public void testPrintServers() {
        for (Server s : servers) {
            System.out.println(s.toString());
        }
    }

}
