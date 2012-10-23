package execution;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.junit.Test;

import fumltesting.FumltestingPackage;
import fumltesting.TestCase;
import fumltesting.TestSuite;
import fumltesting.util.FumltestingResourceFactoryImpl;

public class TestExecutor {
	
	/** Utility class for executing fUML activities. */
	private FUMLExecutor executor;
	
	/** The resource set to be used for loading the model resource. */
	private ResourceSet resourceSet;
	/** The current resource. */
	private Resource resource;

	@Test
	public void test() {
		
		//Setting up fUML executor
		executor = new FUMLExecutor();
		
		//Setting up resource set and registering factories for UML and TestLang
		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry()
			.getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		resourceSet.getResourceFactoryRegistry()
			.getExtensionToFactoryMap().put("fumltesting", new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(FumltestingPackage.eNS_URI, FumltestingPackage.eINSTANCE);

		//Setting resource file
		File file = new File("example/example.fumltesting");
		URI uri = URI.createFileURI(file.getAbsolutePath());
		resource = resourceSet.getResource(uri, true);
		
		
		TestSuite suite = (TestSuite)resource.getContents().get(0);
		System.out.println("\nSystem under test: " + suite.getSystemUnderTest().getName());
		System.out.println("Number of tests: " + suite.getTests().size());
		
		for (int i = 0; i < suite.getTests().size(); i++) {
			TestCase testCase = suite.getTests().get(i);
			System.out.println("Running test: " + testCase.getName());
			
			Activity activity = testCase.getActivityUnderTest().getActivity();
			System.out.println("Running activity: " + activity.getName());
			
			executor.executeActivity(activity);
		}
	}
}