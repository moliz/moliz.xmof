package petrinet.diagram.navigator;

import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import petrinet.Place;
import petrinet.diagram.edit.parts.NetEditPart;
import petrinet.diagram.edit.parts.PlaceEditPart;
import petrinet.diagram.edit.parts.TransitionEditPart;
import petrinet.diagram.edit.parts.TransitionInputEditPart;
import petrinet.diagram.edit.parts.TransitionOutputEditPart;
import petrinet.diagram.part.PetrinetDiagramEditorPlugin;
import petrinet.diagram.part.PetrinetVisualIDRegistry;
import petrinet.diagram.providers.PetrinetElementTypes;

/**
 * @generated
 */
public class PetrinetNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		PetrinetDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		PetrinetDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof PetrinetNavigatorItem
				&& !isOwnView(((PetrinetNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof PetrinetNavigatorGroup) {
			PetrinetNavigatorGroup group = (PetrinetNavigatorGroup) element;
			return PetrinetDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof PetrinetNavigatorItem) {
			PetrinetNavigatorItem navigatorItem = (PetrinetNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (PetrinetVisualIDRegistry.getVisualID(view)) {
		case NetEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://petrinet/1.0?Net", PetrinetElementTypes.Net_1000); //$NON-NLS-1$
		case TransitionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://petrinet/1.0?Transition", PetrinetElementTypes.Transition_2001); //$NON-NLS-1$
		case PlaceEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://petrinet/1.0?Place", PetrinetElementTypes.Place_2002); //$NON-NLS-1$
		case TransitionInputEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://petrinet/1.0?Transition?input", PetrinetElementTypes.TransitionInput_4002); //$NON-NLS-1$
		case TransitionOutputEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://petrinet/1.0?Transition?output", PetrinetElementTypes.TransitionOutput_4001); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = PetrinetDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& PetrinetElementTypes.isKnownElementType(elementType)) {
			image = PetrinetElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof PetrinetNavigatorGroup) {
			PetrinetNavigatorGroup group = (PetrinetNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof PetrinetNavigatorItem) {
			PetrinetNavigatorItem navigatorItem = (PetrinetNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (PetrinetVisualIDRegistry.getVisualID(view)) {
		case NetEditPart.VISUAL_ID:
			return getNet_1000Text(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_2001Text(view);
		case PlaceEditPart.VISUAL_ID:
			return getPlace_2002Text(view);
		case TransitionInputEditPart.VISUAL_ID:
			return getTransitionInput_4002Text(view);
		case TransitionOutputEditPart.VISUAL_ID:
			return getTransitionOutput_4001Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getTransitionInput_4002Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getPlace_2002Text(View view) {
		Place domainModelElement = (Place) view.getElement();
		if (domainModelElement != null) {
			return String.valueOf(domainModelElement.getInitialTokens());
		} else {
			PetrinetDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 2002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTransition_2001Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getTransitionOutput_4001Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getNet_1000Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return NetEditPart.MODEL_ID.equals(PetrinetVisualIDRegistry
				.getModelID(view));
	}

}
