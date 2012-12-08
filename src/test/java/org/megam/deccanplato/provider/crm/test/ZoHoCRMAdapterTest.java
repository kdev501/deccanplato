package org.megam.deccanplato.provider.crm.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.megam.deccanplato.provider.ProviderRegistry;
import org.megam.deccanplato.provider.core.AdapterAccessException;
import org.megam.deccanplato.provider.core.DataMap;
import org.megam.deccanplato.provider.core.RequestData;
import org.megam.deccanplato.provider.crm.test.common.CommonTest;
import org.megam.deccanplato.provider.zoho.crm.ZohoAdapterAccess;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class ZoHoCRMAdapterTest {

	private static final String ZOHOCRM = "zohocrm";

	@Test
	public void zohoTest() {

		GenericApplicationContext ctx = new GenericApplicationContext();
		XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(ctx);
		xmlReader.loadBeanDefinitions(new ClassPathResource(
				"applicationContext.xml"));
		ctx.refresh();
		ProviderRegistry registry = (ProviderRegistry) ctx.getBean("registry");

		List<String> busiMethod = new ArrayList<String>();
		busiMethod.add("user");
		busiMethod.add("account");
		busiMethod.add("lead");
		busiMethod.add("campaign");
		busiMethod.add("call");
		busiMethod.add("case");
		busiMethod.add("contact");
		busiMethod.add("event");
		busiMethod.add("potential");
		busiMethod.add("solution");
		busiMethod.add("task");
		List<String> busiActivity = new ArrayList<String>();
		busiActivity.add("create");
		busiActivity.add("list");
		busiActivity.add("update");
		busiActivity.add("delete");
		for (String function : busiMethod) {
			for (String activity : busiActivity) {
				CommonTest ctest = new CommonTest();
				RequestData reqData;
				reqData = ctest.commonTest(function, activity, ZOHOCRM);
				if (function.equals("user")
						&& activity.equals("create")) {
					testAdapterAccess(reqData);
				}
				ctest.testBusinessImpl();
			}
		}

	}

	public void testAdapterAccess(RequestData reqData) {

		ZohoAdapterAccess saa = new ZohoAdapterAccess();
		try {
			DataMap dmap = saa.authenticate(reqData.getGeneral());
		} catch (AdapterAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
