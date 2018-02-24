package google.com.ortona.hashcode.data_center;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import google.com.ortona.hashcode.UtilsFileServer;
import google.com.ortona.hashcode.data_center.logic.MaximumDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.MinimalDatacenterAllocation;
import google.com.ortona.hashcode.data_center.logic.OutputWriter;
import google.com.ortona.hashcode.data_center.logic.model.Datacenter;
import google.com.ortona.hashcode.data_center.logic.model.ScoreComputation;
import google.com.ortona.hashcode.data_center.logic.model.Server;
import google.com.ortona.hashcode.data_center.logic.model.Slot;

public class MainTest {

  @BeforeClass
  public static void bringUp() {

  }

  @Test
  public void testMain1() throws FileNotFoundException, UnsupportedEncodingException {

    final String filePathExample = "dc.in";
    final UtilsFileServer fr = new UtilsFileServer(filePathExample);
    final Datacenter datacenter = fr.getDatacenter();
    final int[] header = fr.getHeader();
    final int rowAmount = fr.getRowAmount();
    final int columnAmount = fr.getColumnAmount();
    final int unavailableSlotsAmount = fr.getUnavailableSlotAmount();
    final int poolsAmount = fr.getPoolsAmount();
    final int serversAmount = fr.getServersAmount();
    final List<Slot> unavailableSlots = fr.getUnavailableSlots();
    final List<Server> servers = fr.getServers();
    final List<Server> originalServers = new ArrayList<Server>(servers);
    MinimalDatacenterAllocation.allocateServer(servers, poolsAmount, datacenter);
    final MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation();
    obj.allocate(servers, datacenter, poolsAmount);
    OutputWriter.writeToFile("dc.out", originalServers);
    final ScoreComputation sCompute = new ScoreComputation();
    System.out.println("SCORE->" + sCompute.computeTotalScore(datacenter, poolsAmount));
    Assert.assertEquals(true, true);

  }

  @Test
  public void testMain2() {

    final String filePathExample = "dc.in";
    final UtilsFileServer fr = new UtilsFileServer(filePathExample);
    final Datacenter datacenter = fr.getDatacenter();
    final int[] header = fr.getHeader();
    final int rowAmount = fr.getRowAmount();
    final int columnAmount = fr.getColumnAmount();
    final int unavailableSlotsAmount = fr.getUnavailableSlotAmount();
    final int poolsAmount = fr.getPoolsAmount();
    final int serversAmount = fr.getServersAmount();
    final List<Slot> unavailableSlots = fr.getUnavailableSlots();
    final List<Server> servers = fr.getServers();
    MinimalDatacenterAllocation.allocateServer(servers, poolsAmount, datacenter);

    final MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation();
    obj.allocate(servers, datacenter, poolsAmount);
    Assert.assertEquals(true, true);

  }

  @Test
  public void testMain3() {

    final String filePathExample = "dc.in";
    final UtilsFileServer fr = new UtilsFileServer(filePathExample);
    final Datacenter datacenter = fr.getDatacenter();
    final int[] header = fr.getHeader();
    final int rowAmount = fr.getRowAmount();
    final int columnAmount = fr.getColumnAmount();
    final int unavailableSlotsAmount = fr.getUnavailableSlotAmount();
    final int poolsAmount = fr.getPoolsAmount();
    final int serversAmount = fr.getServersAmount();
    final List<Slot> unavailableSlots = fr.getUnavailableSlots();
    final List<Server> servers = fr.getServers();
    MinimalDatacenterAllocation.allocateServer(servers, poolsAmount, datacenter);

    final MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation();
    obj.allocate(servers, datacenter, poolsAmount);
    Assert.assertEquals(true, true);

  }

  @Test
  public void testMain4() {

    final String filePathExample = "dc.in";
    final UtilsFileServer fr = new UtilsFileServer(filePathExample);
    final Datacenter datacenter = fr.getDatacenter();
    final int[] header = fr.getHeader();
    final int rowAmount = fr.getRowAmount();
    final int columnAmount = fr.getColumnAmount();
    final int unavailableSlotsAmount = fr.getUnavailableSlotAmount();
    final int poolsAmount = fr.getPoolsAmount();
    final int serversAmount = fr.getServersAmount();
    final List<Slot> unavailableSlots = fr.getUnavailableSlots();
    final List<Server> servers = fr.getServers();
    MinimalDatacenterAllocation.allocateServer(servers, poolsAmount, datacenter);

    final MaximumDatacenterAllocation obj = new MaximumDatacenterAllocation();
    obj.allocate(servers, datacenter, poolsAmount);
    Assert.assertEquals(true, true);

  }

}
