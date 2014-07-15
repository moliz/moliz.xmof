package org.modelexecution.fumldebug.eval;

import java.util.Set;

import org.junit.Assert;

import fUML.Semantics.Classes.Kernel.IntegerValue;
import fUML.Semantics.Classes.Kernel.Link;
import fUML.Semantics.Classes.Kernel.Object_;
import fUML.Semantics.Classes.Kernel.Reference;
import fUML.Semantics.CommonBehaviors.BasicBehaviors.ParameterValueList;
import fUML.Semantics.Loci.LociL1.Locus;

public class BuyScenarioAssert extends PetstoreAssert {

	private ParameterValueList output;
	
	public BuyScenarioAssert(Locus locus, ParameterValueList output) {
		super(locus);
		this.output = output;
	}
	
	public void doAssert() {
		Object_ liz = getCustomerByLogin("liz");
		Assert.assertNotNull(liz);
		
		Object_ poodleItem = getItemByName("Poodle");
		Assert.assertNotNull(poodleItem);
		
		Set<Object_> orderObjects = getObjects("Order");
		// there is one order
		Assert.assertEquals(1, orderObjects.size()); 
		Object_ order = orderObjects.iterator().next();
		
		Assert.assertEquals(1, output.size());
		Assert.assertEquals(1, output.get(0).values.size());
		Assert.assertTrue(output.get(0).values.get(0) instanceof Reference); 
		// the order corresponds to the output
		Assert.assertEquals(order, ((Reference)output.get(0).values.get(0)).referent); 
		
		Set<Object_> orderLines = getObjects("OrderLine");
		// there is one orderLines
		Assert.assertEquals(1, orderLines.size()); 
		
		Set<Link> orderLinks = getLinks("order_customer_1");
		// there is one link between Customer and Order
		Assert.assertEquals(1, orderLinks.size()); 
		
		Set<Link> orderLineLinks = getLinks("order_orderLine_1");
		// there is one links between Order and OrderLine
		Assert.assertEquals(1, orderLineLinks.size()); 
		
		Set<Object_> lizOrders = getLinkedObjects("order_customer_1",
				liz, "customer");
		// liz has one order
		Assert.assertEquals(1, lizOrders.size()); 		
		Object_ lizOrder = lizOrders.iterator().next();
		
		Set<Object_> lizOrderLines = getLinkedObjects("order_orderLine_1", lizOrder, "order");
		// liz order contains one orderLine
		Assert.assertEquals(1, lizOrderLines.size()); 

		Object_ orderLine = lizOrderLines.iterator().next();
		Set<Object_> items = getLinkedObjects("orderLine_item_1", orderLine, "orderLine");
		Assert.assertEquals(1, items.size());
		Object_ item = items.iterator().next();
		// liz ordered a Poodle
		Assert.assertEquals(poodleItem, item);

		// the quantity of the orderLine for Poodle is set
		Assert.assertEquals(1, getFeatureValue(orderLine, "quantity").size());
		// liz ordered 1 Poodle
		Assert.assertEquals(1,
				((IntegerValue) getFeatureValue(orderLine, "quantity")
						.get(0)).value); 

		Set<Object_> carts = getObjects("Cart");
		// no cart exists
		Assert.assertEquals(0, carts.size()); 

		Set<Object_> cartitems = getObjects("CartItem");
		// no cartItem exists
		Assert.assertEquals(0, cartitems.size()); 	
	}
}
