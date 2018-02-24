package google.com.ortona.hashcode.data_center;

import google.com.ortona.hashcode.UtilsFileServer;
import google.com.ortona.hashcode.data_center.logic.MinimalDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.Slot;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class MainTest {

    static private final String filePathExample = "dc.in";
    static private UtilsFileServer fr = new UtilsFileServer(filePathExample);
    static private Datacenter datacenter = fr.getDatacenter();
    static private int[] header = fr.getHeader();
    static private int rowAmount = fr.getRowAmount();
    static private int columnAmount = fr.getColumnAmount();
    static private int unavailableSlotsAmount = fr.getUnavailableSlotAmount();
    static private int poolsAmount = fr.getPoolsAmount();
    static private int serversAmount = fr.getServersAmount();
    static private List<Slot> unavailableSlots = fr.getUnavailableSlots();
    static private List<Server> servers = fr.getServers();

    @BeforeClass
    public static void bringUp() {

    }

    @Test
    public void testMain() {
        MinimalDatacenterAllocation.allocateServer(servers, poolsAmount, datacenter);
        

    }

}
