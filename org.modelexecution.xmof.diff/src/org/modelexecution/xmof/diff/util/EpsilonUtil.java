package org.modelexecution.xmof.diff.util;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.ecl.MatchRule;
import org.eclipse.epsilon.ecl.MatchRules;
import org.eclipse.epsilon.ecl.execute.EclOperationFactory;
import org.eclipse.epsilon.ecl.execute.context.IEclContext;
import org.eclipse.epsilon.ecl.trace.Match;
import org.eclipse.epsilon.ecl.trace.MatchTrace;
import org.eclipse.epsilon.emc.emf.InMemoryEmfModel;
import org.eclipse.epsilon.eol.AbstractModule;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.EolSystem;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.types.EolClasspathNativeTypeDelegate;
import org.eclipse.epsilon.erl.rules.INamedRule;

public class EpsilonUtil {

	public static void setVariableToMdule(EolModule module, String name,
			Object value) {
		setVariableToMdule(module.getContext(), name, value);
	}

	public static void setVariableToMdule(EclModule module, String name,
			Object value) {
		setVariableToMdule(module.getContext(), name, value);
	}

	private static void setVariableToMdule(IEolContext context, String name,
			Object value) {
		Variable variable = Variable.createReadOnlyVariable(name, value);
		context.getFrameStack().put(variable);
	}

	public static EolModule createEolModule(File eolFile, IModel... models) {
		EolModule module = new EolModule();

		boolean parsingSuccessful = EpsilonUtil.setEpsilonFileToModule(module,
				eolFile);

		if (parsingSuccessful) {
			for (IModel model : models)
				module.getContext().getModelRepository().addModel(model);
			return module;
		} else {
			return null;
		}
	}

	public static void setNativeTypeDelegateToModule(EolModule module,
			ClassLoader classLoader) {
		setNativeTypeDelegateToModule(module.getContext(), classLoader);
	}

	public static void setNativeTypeDelegateToModule(EclModule module,
			ClassLoader classLoader) {
		setNativeTypeDelegateToModule(module.getContext(), classLoader);
	}

	private static void setNativeTypeDelegateToModule(IEolContext context,
			ClassLoader classLoader) {
		EolClasspathNativeTypeDelegate nativeDelegate = new EolClasspathNativeTypeDelegate(
				classLoader);
		context.getNativeTypeDelegates().add(nativeDelegate);
	}

	public static EclModule createEclModule(File eclFile,
			Resource leftResource, String leftResourceName,
			Resource rightResource, String rightResourceName,
			EPackage... ePackages) {
		return createEclModule(eclFile, leftResource, leftResourceName,
				rightResource, rightResourceName, Arrays.asList(ePackages));
	}

	public static EclModule createEclModule(File eclFile,
			Resource leftResource, String leftResourceName,
			Resource rightResource, String rightResourceName,
			Collection<EPackage> ePackages) {
		EclModule module = new EclModule();
		EpsilonUtil.setModelsToModule(module, leftResource, leftResourceName,
				rightResource, rightResourceName, ePackages);
		boolean parsingSuccessful = EpsilonUtil.setEpsilonFileToModule(module,
				eclFile);
		if (parsingSuccessful)
			return module;
		else
			return null;
	}

	private static void setModelsToModule(EclModule module,
			Resource leftResource, String leftResourceName,
			Resource rightResource, String rightResourceName,
			Collection<EPackage> ePackages) {
		InMemoryEmfModel leftModel = EpsilonUtil.createInMemoryEmfModel(
				leftResourceName, leftResource, ePackages);
		InMemoryEmfModel rightModel = EpsilonUtil.createInMemoryEmfModel(
				rightResourceName, rightResource, ePackages);

		EpsilonUtil.addModelToModule(module, leftModel);
		EpsilonUtil.addModelToModule(module, rightModel);
	}

	public static InMemoryEmfModel createInMemoryEmfModel(String name,
			Resource resource, EPackage... ePackages) {
		return createInMemoryEmfModel(name, resource, Arrays.asList(ePackages));
	}

	public static InMemoryEmfModel createInMemoryEmfModel(String name,
			Resource resource, Collection<EPackage> ePackages) {
		InMemoryEmfModel model = new InMemoryEmfModel(name, resource, ePackages);
		model.setName(name);
		return model;
	}

	private static void addModelToModule(EclModule module,
			InMemoryEmfModel model) {
		module.getContext().getModelRepository().addModel(model);
	}

	private static boolean setEpsilonFileToModule(AbstractModule module,
			File file) {
		try {
			module.parse(file);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static MatchTrace executeModule(EclModule module) {
		try {
			Object result = module.execute();
			if (result instanceof MatchTrace) {
				MatchTrace matchTrace = (MatchTrace) result;
				return matchTrace;
			}
		} catch (EolRuntimeException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static void setMatchTraceToModule(EclModule module, MatchTrace matchTrace) {
		module.getContext().setMatchTrace(matchTrace);
	}

	
	public static MatchRule getSemanticMatchRule(EclModule moduleSemantics, Object left, Object right) {
		MatchRules matchRules = null;
		try {
			matchRules = moduleSemantics.getMatchRules().getRulesFor(left, right, moduleSemantics.getContext(), true);
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}
		if(matchRules != null) {
			if(matchRules.size() > 0) {
				INamedRule matchRule = matchRules.get(0);
				if (matchRule instanceof MatchRule)
					return (MatchRule)matchRule;
			}
		}
		return null;
	}
	
	public static void initEclModule(EclModule module) {
		prepareContext(module);
		
		IEclContext context = module.getContext();
		context.setOperationFactory(new EclOperationFactory());
		
		context.getFrameStack().put(Variable.createReadOnlyVariable("matchTrace", context.getMatchTrace()));
		context.getFrameStack().put(Variable.createReadOnlyVariable("context", context));
		context.getFrameStack().put(Variable.createReadOnlyVariable("self", module));
	}
	
	private static void prepareContext(EclModule module) {
		IEclContext context = module.getContext();
		
		final EolSystem system = new EolSystem();
		system.setContext(context);

		context.setModule(module);
		context.getFrameStack().putGlobal(Variable.createReadOnlyVariable("null", null));
		context.getFrameStack().putGlobal(Variable.createReadOnlyVariable("System",system));
	}
	
	public static Match matchRule(EclModule module, MatchRule matchRule, Object left, Object right) {
		Match match = null;
		try {
			match = matchRule.match(left, right, module.getContext(), false, null, true);
		} catch (EolRuntimeException e) {
			e.printStackTrace();
		}
		return match;
	}
}
