package petrinet.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import petrinet.diagram.part.PetrinetVisualIDRegistry;

/**
 * @generated
 */
public class PetrinetNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4004;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof PetrinetNavigatorItem) {
			PetrinetNavigatorItem item = (PetrinetNavigatorItem) element;
			return PetrinetVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
