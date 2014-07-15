package org.modelexecution.fumldebug.eval;

import org.junit.Assert;

import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.Classes.Kernel.StringValue;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.Loci.LociL1.Locus;

public class FindItemScenarioAssert extends PetstoreAssert {

	private ParameterValueList output;

	public FindItemScenarioAssert(Locus locus, ParameterValueList output) {
		super(locus);
		this.output = output;
	}

	public void doAssert() {
		Assert.assertEquals(1, output.size());
		Assert.assertEquals(1, output.get(0).values.size());
		Assert.assertTrue(output.get(0).values.get(0) instanceof Reference);
		Object_ item_poodle = ((Reference) output.get(0).values.get(0)).referent;
		Assert.assertEquals("Item", item_poodle.types.get(0).name);
		Assert.assertEquals(1, getFeatureValue(item_poodle, "name").size());
		Assert.assertEquals(
				"Poodle",
				((StringValue) getFeatureValue(item_poodle, "name").get(0)).value);
	}
}
