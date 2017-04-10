package petrinet.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

import petrinet.Net;
import petrinet.PetrinetPackage;
import petrinet.Place;
import petrinet.Transition;
import petrinet.diagram.edit.parts.NetEditPart;
import petrinet.diagram.edit.parts.PlaceEditPart;
import petrinet.diagram.edit.parts.TransitionEditPart;
import petrinet.diagram.edit.parts.TransitionInputEditPart;
import petrinet.diagram.edit.parts.TransitionOutputEditPart;
import petrinet.diagram.providers.PetrinetElementTypes;

/**
 * @generated
 */
public class PetrinetDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<PetrinetNodeDescriptor> getSemanticChildren(View view) {
		switch (PetrinetVisualIDRegistry.getVisualID(view)) {
		case NetEditPart.VISUAL_ID:
			return getNet_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<PetrinetNodeDescriptor> getNet_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Net modelElement = (Net) view.getElement();
		LinkedList<PetrinetNodeDescriptor> result = new LinkedList<PetrinetNodeDescriptor>();
		for (Iterator<?> it = modelElement.getTransitions().iterator(); it
				.hasNext();) {
			Transition childElement = (Transition) it.next();
			int visualID = PetrinetVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == TransitionEditPart.VISUAL_ID) {
				result.add(new PetrinetNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getPlaces().iterator(); it.hasNext();) {
			Place childElement = (Place) it.next();
			int visualID = PetrinetVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PlaceEditPart.VISUAL_ID) {
				result.add(new PetrinetNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getContainedLinks(View view) {
		switch (PetrinetVisualIDRegistry.getVisualID(view)) {
		case NetEditPart.VISUAL_ID:
			return getNet_1000ContainedLinks(view);
		case TransitionEditPart.VISUAL_ID:
			return getTransition_2001ContainedLinks(view);
		case PlaceEditPart.VISUAL_ID:
			return getPlace_2002ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getIncomingLinks(View view) {
		switch (PetrinetVisualIDRegistry.getVisualID(view)) {
		case TransitionEditPart.VISUAL_ID:
			return getTransition_2001IncomingLinks(view);
		case PlaceEditPart.VISUAL_ID:
			return getPlace_2002IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getOutgoingLinks(View view) {
		switch (PetrinetVisualIDRegistry.getVisualID(view)) {
		case TransitionEditPart.VISUAL_ID:
			return getTransition_2001OutgoingLinks(view);
		case PlaceEditPart.VISUAL_ID:
			return getPlace_2002OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getNet_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getTransition_2001ContainedLinks(
			View view) {
		Transition modelElement = (Transition) view.getElement();
		LinkedList<PetrinetLinkDescriptor> result = new LinkedList<PetrinetLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Transition_Output_4001(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Transition_Input_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getPlace_2002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getTransition_2001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getPlace_2002IncomingLinks(
			View view) {
		Place modelElement = (Place) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<PetrinetLinkDescriptor> result = new LinkedList<PetrinetLinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Transition_Output_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Transition_Input_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getTransition_2001OutgoingLinks(
			View view) {
		Transition modelElement = (Transition) view.getElement();
		LinkedList<PetrinetLinkDescriptor> result = new LinkedList<PetrinetLinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Transition_Output_4001(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Transition_Input_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<PetrinetLinkDescriptor> getPlace_2002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<PetrinetLinkDescriptor> getIncomingFeatureModelFacetLinks_Transition_Output_4001(
			Place target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<PetrinetLinkDescriptor> result = new LinkedList<PetrinetLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == PetrinetPackage.eINSTANCE
					.getTransition_Output()) {
				result.add(new PetrinetLinkDescriptor(setting.getEObject(),
						target, PetrinetElementTypes.TransitionOutput_4001,
						TransitionOutputEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<PetrinetLinkDescriptor> getIncomingFeatureModelFacetLinks_Transition_Input_4002(
			Place target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<PetrinetLinkDescriptor> result = new LinkedList<PetrinetLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == PetrinetPackage.eINSTANCE
					.getTransition_Input()) {
				result.add(new PetrinetLinkDescriptor(setting.getEObject(),
						target, PetrinetElementTypes.TransitionInput_4002,
						TransitionInputEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<PetrinetLinkDescriptor> getOutgoingFeatureModelFacetLinks_Transition_Output_4001(
			Transition source) {
		LinkedList<PetrinetLinkDescriptor> result = new LinkedList<PetrinetLinkDescriptor>();
		for (Iterator<?> destinations = source.getOutput().iterator(); destinations
				.hasNext();) {
			Place destination = (Place) destinations.next();
			result.add(new PetrinetLinkDescriptor(source, destination,
					PetrinetElementTypes.TransitionOutput_4001,
					TransitionOutputEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<PetrinetLinkDescriptor> getOutgoingFeatureModelFacetLinks_Transition_Input_4002(
			Transition source) {
		LinkedList<PetrinetLinkDescriptor> result = new LinkedList<PetrinetLinkDescriptor>();
		for (Iterator<?> destinations = source.getInput().iterator(); destinations
				.hasNext();) {
			Place destination = (Place) destinations.next();
			result.add(new PetrinetLinkDescriptor(source, destination,
					PetrinetElementTypes.TransitionInput_4002,
					TransitionInputEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		 * @generated
		 */
		@Override
		public List<PetrinetNodeDescriptor> getSemanticChildren(View view) {
			return PetrinetDiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<PetrinetLinkDescriptor> getContainedLinks(View view) {
			return PetrinetDiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<PetrinetLinkDescriptor> getIncomingLinks(View view) {
			return PetrinetDiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<PetrinetLinkDescriptor> getOutgoingLinks(View view) {
			return PetrinetDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
