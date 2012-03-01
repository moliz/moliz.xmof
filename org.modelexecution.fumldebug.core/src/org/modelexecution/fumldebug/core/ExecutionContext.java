package org.modelexecution.fumldebug.core;

import org.modelexecution.fumldebug.core.impl.ExecutionEventProviderImpl;

import fUML.Semantics.Loci.LociL1.Executor;
import fUML.Semantics.Loci.LociL1.Locus;
import fUML.Semantics.Classes.Kernel.RedefinitionBasedDispatchStrategy;
import fUML.Semantics.CommonBehaviors.Communications.FIFOGetNextEventStrategy;
import fUML.Semantics.Loci.LociL1.FirstChoiceStrategy;
import fUML.Semantics.Loci.LociL3.ExecutionFactoryL3;

import fUML.Syntax.Classes.Kernel.PrimitiveType;

public class ExecutionContext {

	private static ExecutionContext instance = new ExecutionContext();
	
	private ExecutionEventProvider eventprovider;
	
	private Locus locus = null;
		
	private ExecutionContext()
	{
		/*
		 * Locus initialization
		 */
		this.locus = new Locus();
		this.locus.setFactory(new ExecutionFactoryL3());  // Uses local subclass for ExecutionFactory
		this.locus.setExecutor(new Executor());

		this.locus.factory.setStrategy(new RedefinitionBasedDispatchStrategy());
		this.locus.factory.setStrategy(new FIFOGetNextEventStrategy());
		this.locus.factory.setStrategy(new FirstChoiceStrategy());
	
		this.createPrimitiveType("Boolean");
		this.createPrimitiveType("String");
		this.createPrimitiveType("Integer");
		this.createPrimitiveType("UnlimitedNatural");
		
		/*
		 * 
		 */
		
	}
	
	public static ExecutionContext getInstance(){
		return instance;
	}
	
	private PrimitiveType createPrimitiveType(String name) {
		PrimitiveType type = new PrimitiveType();
		type.name = name;
		this.locus.factory.addBuiltInType(type);
		return type;
	}
		
	/**
	 * TODO
	 * Does it make sense to create one event provider per activity execution
	 * and to maintain a dictionary or something for active executions?
	 * @return
	 */
	public ExecutionEventProvider getExecutionEventProvider(){
		if(this.eventprovider == null) {
			this.eventprovider = new ExecutionEventProviderImpl();
		}
		return this.eventprovider;
	}
	
	public Locus getLocus()
	{
		return this.locus;
	}
}
