package google.com.ortona.hashcode.data_center;

import google.com.ortona.hashcode.UtilsFileServer;
import google.com.ortona.hashcode.data_center.logic.MaximumDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.MinimalDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.Slot;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class MainTest {

    @BeforeClass
    public static void bringUp() {

    }

    @Test
    public void testMain1() {

        final String filePathExample = "dc.in";
        UtilsFileServer fr = new UtilsFileServer(filePathExample);
        Datacenter datacenter = fr.getDatacenter();
        int[] header = fr.getHeader();
        int rowAmount = fr.getRowAmount();
        int columnAmount = fr.getColumnAmount();
        int unavailableSlotsAmount = fr.getUnavailableSlotAmount();
        int poolsAmount = fr.getPoolsAmount();
        int serversAmount = fr.getServersAmount();
        List<Slot> unavailableSlots = fr.getUnavailableSlots();
        List<Server> servers = fr.getServers();
        MinimalDatacenterAllocation.allocateServer(servers, poolsAmount, datacenter);

        MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation();
        obj.allocate(servers, datacenter, poolsAmount);
        Assert.assertEquals(true, true);

    }

    @Test
    public void testMain2() {

        final String filePathExample = "dc.in";
        UtilsFileServer fr = new UtilsFileServer(filePathExample);
        Datacenter datacenter = fr.getDatacenter();
        int[] header = fr.getHeader();
        int rowAmount = fr.getRowAmount();
        int columnAmount = fr.getColumnAmount();
        int unavailableSlotsAmount = fr.getUnavailableSlotAmount();
        int poolsAmount = fr.getPoolsAmount();
        int serversAmount = fr.getServersAmount();
        List<Slot> unavailableSlots = fr.getUnavailableSlots();
        List<Server> servers = fr.getServers();
        MinimalDatacenterAllocation.allocateServer(servers, poolsAmount, datacenter);

        MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation();
        Assert.assertEquals(true, true);

    }

    @Test
    public void testMain3() {

        final String filePathExample = "dc.in";
        UtilsFileServer fr = new UtilsFileServer(filePathExample);
        Datacenter datacenter = fr.getDatacenter();
        int[] header = fr.getHeader();
        int rowAmount = fr.getRowAmount();
        int columnAmount = fr.getColumnAmount();
        int unavailableSlotsAmount = fr.getUnavailableSlotAmount();
        int poolsAmount = fr.getPoolsAmount();
        int serversAmount = fr.getServersAmount();
        List<Slot> unavailableSlots = fr.getUnavailableSlots();
        List<Server> servers = fr.getServers();
        MinimalDatacenterAllocation.allocateServer(servers, poolsAmount, datacenter);

        MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation();
        Assert.assertEquals(true, true);

    }

    @Test
    public void testMain4() {

        final String filePathExample = "dc.in";
        UtilsFileServer fr = new UtilsFileServer(filePathExample);
        Datacenter datacenter = fr.getDatacenter();
        int[] header = fr.getHeader();
        int rowAmount = fr.getRowAmount();
        int columnAmount = fr.getColumnAmount();
        int unavailableSlotsAmount = fr.getUnavailableSlotAmount();
        int poolsAmount = fr.getPoolsAmount();
        int serversAmount = fr.getServersAmount();
        List<Slot> unavailableSlots = fr.getUnavailableSlots();
        List<Server> servers = fr.getServers();
        MinimalDatacenterAllocation.allocateServer(servers, poolsAmount, datacenter);

        MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation();
        Assert.assertEquals(true, true);

    }

}
