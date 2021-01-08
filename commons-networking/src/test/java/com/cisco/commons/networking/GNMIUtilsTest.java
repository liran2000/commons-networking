package com.cisco.commons.networking;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gnmi.Gnmi.Path;
import gnmi.Gnmi.PathElem;

public class GNMIUtilsTest {
	
	@Test
	public void parseGNMIPathStrTest() {
		testParseState();
		testParseProtocols();
	}

	private void testParseState() {
		String gnmiPathStr = "openconfig-interfaces/interfaces/interface[name=Ethernet/1/2/3]/state";
		Path parsedPath = GNMIUtils.parseGNMIPathStr(gnmiPathStr);
		String gnmiPathStrPostParse = GNMIUtils.toString(parsedPath);
		assertEquals(gnmiPathStr, gnmiPathStrPostParse);
		Path expectedPath = buildStateGNMIPath();
		assertEquals(expectedPath, parsedPath);
		assertEquals(expectedPath.toString(), parsedPath.toString());
	}
	
	private Path buildStateGNMIPath() {
		gnmi.Gnmi.Path.Builder pathBuilder = Path.newBuilder();
		pathBuilder.setOrigin("openconfig-interfaces");
		gnmi.Gnmi.PathElem.Builder interfacesElem = PathElem.newBuilder().setName("interfaces");
		pathBuilder.addElem(interfacesElem);
		gnmi.Gnmi.PathElem.Builder interfaceElem = PathElem.newBuilder().setName("interface");
		interfaceElem.putKey("name", "Ethernet/1/2/3");
		pathBuilder.addElem(interfaceElem);
		gnmi.Gnmi.PathElem.Builder stateElem = PathElem.newBuilder().setName("state");
		pathBuilder.addElem(stateElem);
		return pathBuilder.build();
	}
	
	private void testParseProtocols() {
		String gnmiPathStr = "/network-instances/network-instance[name=DEFAULT]/protocols/protocol[identifier=ISIS][name=65497]";
		Path parsedPath = GNMIUtils.parseGNMIPathStr(gnmiPathStr);
		String gnmiPathStrPostParse = GNMIUtils.toString(parsedPath);
		assertEquals(gnmiPathStr, gnmiPathStrPostParse);
		Path expectedPath = buildProtocolsGNMIPath();
		assertEquals(expectedPath, parsedPath);
		assertEquals(expectedPath.toString(), parsedPath.toString());
	}
	
	private Path buildProtocolsGNMIPath() {
		gnmi.Gnmi.Path.Builder pathBuilder = Path.newBuilder();
		gnmi.Gnmi.PathElem.Builder networkInstancesElem = PathElem.newBuilder().setName("network-instances");
		pathBuilder.addElem(networkInstancesElem);
		gnmi.Gnmi.PathElem.Builder networkInstanceElem = PathElem.newBuilder().setName("network-instance");
		networkInstanceElem.putKey("name", "DEFAULT");
		pathBuilder.addElem(networkInstanceElem);
		gnmi.Gnmi.PathElem.Builder protocolsElem = PathElem.newBuilder().setName("protocols");
		pathBuilder.addElem(protocolsElem);
		gnmi.Gnmi.PathElem.Builder protocolElem = PathElem.newBuilder().setName("protocol");
		protocolElem.putKey("identifier", "ISIS");
		protocolElem.putKey("name", "65497");
		pathBuilder.addElem(protocolElem);
		return pathBuilder.build();
	}
}
