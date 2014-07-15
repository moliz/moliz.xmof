package org.modelexecution.fumldebug.eval;

import java.util.Set;

import org.junit.Assert;

import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.Loci.LociL1.Locus;

public class LoginScenarioAssert extends PetstoreAssert {

	private ParameterValueList output;
	
	public LoginScenarioAssert(Locus locus, ParameterValueList output) {
		super(locus);
		this.output = output;
	}
	
	public void doAssert() {
		Assert.assertEquals(1, output.size());
		Assert.assertEquals(1, output.get(0).values.size());
		Assert.assertEquals(1,
				((IntegerValue) output.get(0).values.get(0)).value);

		Set<Object_> objects = getObjects("ApplicationController");
		Assert.assertEquals(1, objects.size());
		Object_ applicationController = objects.iterator().next();
		objects = getObjects("Session");
		Assert.assertEquals(1, objects.size());
		Object_ session = objects.iterator().next();
		Set<Link> links = getLinks("applicationController_session_1");
		Assert.assertEquals(1, links.size());
		Link sessionlink = links.iterator().next();
		Assert.assertEquals(1, getFeatureValue(sessionlink, "sessions").size());
		Assert.assertEquals(
				session,
				((Reference) getFeatureValue(sessionlink, "sessions").get(0)).referent);
		Assert.assertEquals(1,
				getFeatureValue(sessionlink, "applicationController").size());
		Assert.assertEquals(
				applicationController,
				((Reference) getFeatureValue(sessionlink,
						"applicationController").get(0)).referent);

		Assert.assertEquals(0,
				getFeatureValue(applicationController, "foundCustomer").size()); 	
	}
}
