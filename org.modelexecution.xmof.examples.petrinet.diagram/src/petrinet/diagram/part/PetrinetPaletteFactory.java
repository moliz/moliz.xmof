package petrinet.diagram.part;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

import petrinet.diagram.providers.PetrinetElementTypes;

/**
 * @generated
 */
public class PetrinetPaletteFactory {

	/**
	 * @generated
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createPetrinet1Group());
	}

	/**
	 * Creates "petrinet" palette tool group
	 * @generated
	 */
	private PaletteContainer createPetrinet1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(
				Messages.Petrinet1Group_title);
		paletteContainer.setId("createPetrinet1Group"); //$NON-NLS-1$
		paletteContainer.add(createPlace1CreationTool());
		paletteContainer.add(createTransition2CreationTool());
		paletteContainer.add(createInput3CreationTool());
		paletteContainer.add(createOutput4CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPlace1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Place1CreationTool_title,
				Messages.Place1CreationTool_desc,
				Collections.singletonList(PetrinetElementTypes.Place_2002));
		entry.setId("createPlace1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(PetrinetDiagramEditorPlugin
				.findImageDescriptor("/org.modelexecution.xmof.examples.petrinet.edit/icons/custom/place16x16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTransition2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(
				Messages.Transition2CreationTool_title,
				Messages.Transition2CreationTool_desc,
				Collections.singletonList(PetrinetElementTypes.Transition_2001));
		entry.setId("createTransition2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(PetrinetDiagramEditorPlugin
				.findImageDescriptor("/org.modelexecution.xmof.examples.petrinet.edit/icons/custom/transition16x16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createInput3CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Input3CreationTool_title,
				Messages.Input3CreationTool_desc,
				Collections
						.singletonList(PetrinetElementTypes.TransitionInput_4002));
		entry.setId("createInput3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(PetrinetDiagramEditorPlugin
				.findImageDescriptor("/org.modelexecution.xmof.examples.petrinet.edit/icons/custom/connector16x16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createOutput4CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(
				Messages.Output4CreationTool_title,
				Messages.Output4CreationTool_desc,
				Collections
						.singletonList(PetrinetElementTypes.TransitionOutput_4001));
		entry.setId("createOutput4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(PetrinetDiagramEditorPlugin
				.findImageDescriptor("/org.modelexecution.xmof.examples.petrinet.edit/icons/custom/connector16x16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description,
				List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description,
				List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(relationshipTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}
}
