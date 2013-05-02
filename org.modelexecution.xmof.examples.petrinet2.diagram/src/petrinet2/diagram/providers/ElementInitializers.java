package petrinet2.diagram.providers;

import petrinet2.diagram.part.Petrinet2DiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = Petrinet2DiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			Petrinet2DiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
