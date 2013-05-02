package petrinet2.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import petrinet2.diagram.part.Petrinet2VisualIDRegistry;

/**
 * @generated
 */
public class Petrinet2NavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4004;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof Petrinet2NavigatorItem) {
			Petrinet2NavigatorItem item = (Petrinet2NavigatorItem) element;
			return Petrinet2VisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
