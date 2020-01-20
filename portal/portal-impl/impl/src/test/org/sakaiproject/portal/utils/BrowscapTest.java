package org.sakaiproject.portal.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class BrowscapTest {
  
  private BrowscapImpl browscap;
  
  
    @Before
    public void setUp() {
      browscap = new BrowscapImpl();
      browscap.init();
    }
    
    @Test
    public void testBrowscap() {
      Assert.assertNull(browscap.getBrowcap(""));
      String linux = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36";
      String mac = "Mozilla/5.0 (X11; CrOS x86_64 12105.100.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.144 Safari/537.36";
      String we = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36 ";
      String mac2 = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.1 Safari/605.1.15";
      String ipad = "Mozilla/5.0 (iPad; CPU OS 10_3_3 like Mac OS X) AppleWebKit/603.3.8 (KHTML, like Gecko) Mobile/14G60";
      Assert.assertEquals(browscap.getBrowcap(linux).getHost(), linux);
      
      List<String> list = new ArrayList<>();
      list.add(linux);
      list.add("   ");
      list.add(mac2);
      list.add(mac);
      list.add(we);
      list.add(ipad);
      Assert.assertEquals(browscap.getBrowcap(list).size(), 5);
       
    }

}
