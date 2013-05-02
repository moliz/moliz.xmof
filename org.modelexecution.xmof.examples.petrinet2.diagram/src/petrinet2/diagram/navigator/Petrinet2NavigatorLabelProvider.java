package petrinet2.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
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

import petrinet2.diagram.edit.parts.NetEditPart;
import petrinet2.diagram.edit.parts.PlaceEditPart;
import petrinet2.diagram.edit.parts.PlaceNameEditPart;
import petrinet2.diagram.edit.parts.TransitionEditPart;
import petrinet2.diagram.edit.parts.TransitionInputEditPart;
import petrinet2.diagram.edit.parts.TransitionNameEditPart;
import petrinet2.diagram.edit.parts.TransitionOutputEditPart;
import petrinet2.diagram.part.Petrinet2DiagramEditorPlugin;
import petrinet2.diagram.part.Petrinet2VisualIDRegistry;
import petrinet2.diagram.providers.Petrinet2ElementTypes;
import petrinet2.diagram.providers.Petrinet2ParserProvider;

/**
 * @generated
 */
public class Petrinet2NavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		Petrinet2DiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		Petrinet2DiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof Petrinet2NavigatorItem
				&& !isOwnView(((Petrinet2NavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof Petrinet2NavigatorGroup) {
			Petrinet2NavigatorGroup group = (Petrinet2NavigatorGroup) element;
			return Petrinet2DiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof Petrinet2NavigatorItem) {
			Petrinet2NavigatorItem navigatorItem = (Petrinet2NavigatorItem) element;
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
		switch (Petrinet2VisualIDRegistry.getVisualID(view)) {
		case TransitionInputEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://petrinet2/1.0?Transition?input", Petrinet2ElementTypes.TransitionInput_4002); //$NON-NLS-1$
		case TransitionOutputEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://petrinet2/1.0?Transition?output", Petrinet2ElementTypes.TransitionOutput_4001); //$NON-NLS-1$
		case PlaceEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://petrinet2/1.0?Place", Petrinet2ElementTypes.Place_2002); //$NON-NLS-1$
		case TransitionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://petrinet2/1.0?Transition", Petrinet2ElementTypes.Transition_2001); //$NON-NLS-1$
		case NetEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://petrinet2/1.0?Net", Petrinet2ElementTypes.Net_1000); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = Petrinet2DiagramEditorPlugin
				.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& Petrinet2ElementTypes.isKnownElementType(elementType)) {
			image = Petrinet2ElementTypes.getImage(elementType);
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
		if (element instanceof Petrinet2NavigatorGroup) {
			Petrinet2NavigatorGroup group = (Petrinet2NavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof Petrinet2NavigatorItem) {
			Petrinet2NavigatorItem navigatorItem = (Petrinet2NavigatorItem) element;
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
		switch (Petrinet2VisualIDRegistry.getVisualID(view)) {
		case TransitionInputEditPart.VISUAL_ID:
			return getTransitionInput_4002Text(view);
		case TransitionOutputEditPart.VISUAL_ID:
			return getTransitionOutput_4001Text(view);
		case PlaceEditPart.VISUAL_ID:
			return getPlace_2002Text(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_2001Text(view);
		case NetEditPart.VISUAL_ID:
			return getNet_1000Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getPlace_2002Text(View view) {
		IParser parser = Petrinet2ParserProvider.getParser(
				Petrinet2ElementTypes.Place_2002,
				view.getElement() != null ? view.getElement() : view,
				Petrinet2VisualIDRegistry.getType(PlaceNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			Petrinet2DiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getTransition_2001Text(View view) {
		IParser parser = Petrinet2ParserProvider.getParser(
				Petrinet2ElementTypes.Transition_2001,
				view.getElement() != null ? view.getElement() : view,
				Petrinet2VisualIDRegistry
						.getType(TransitionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			Petrinet2DiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
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
	private String getTransitionInput_4002Text(View view) {
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
		return NetEditPart.MODEL_ID.equals(Petrinet2VisualIDRegistry
				.getModelID(view));
	}

}
