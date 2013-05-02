package petrinet2.diagram.part;

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

import petrinet2.Net;
import petrinet2.Petrinet2Package;
import petrinet2.Place;
import petrinet2.Transition;
import petrinet2.diagram.edit.parts.NetEditPart;
import petrinet2.diagram.edit.parts.PlaceEditPart;
import petrinet2.diagram.edit.parts.TransitionEditPart;
import petrinet2.diagram.edit.parts.TransitionInputEditPart;
import petrinet2.diagram.edit.parts.TransitionOutputEditPart;
import petrinet2.diagram.providers.Petrinet2ElementTypes;

/**
 * @generated
 */
public class Petrinet2DiagramUpdater {

	/**
	 * @generated
	 */
	public static List<Petrinet2NodeDescriptor> getSemanticChildren(View view) {
		switch (Petrinet2VisualIDRegistry.getVisualID(view)) {
		case NetEditPart.VISUAL_ID:
			return getNet_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Petrinet2NodeDescriptor> getNet_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Net modelElement = (Net) view.getElement();
		LinkedList<Petrinet2NodeDescriptor> result = new LinkedList<Petrinet2NodeDescriptor>();
		for (Iterator<?> it = modelElement.getTransitions().iterator(); it
				.hasNext();) {
			Transition childElement = (Transition) it.next();
			int visualID = Petrinet2VisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == TransitionEditPart.VISUAL_ID) {
				result.add(new Petrinet2NodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getPlaces().iterator(); it.hasNext();) {
			Place childElement = (Place) it.next();
			int visualID = Petrinet2VisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PlaceEditPart.VISUAL_ID) {
				result.add(new Petrinet2NodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Petrinet2LinkDescriptor> getContainedLinks(View view) {
		switch (Petrinet2VisualIDRegistry.getVisualID(view)) {
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
	public static List<Petrinet2LinkDescriptor> getIncomingLinks(View view) {
		switch (Petrinet2VisualIDRegistry.getVisualID(view)) {
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
	public static List<Petrinet2LinkDescriptor> getOutgoingLinks(View view) {
		switch (Petrinet2VisualIDRegistry.getVisualID(view)) {
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
	public static List<Petrinet2LinkDescriptor> getNet_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Petrinet2LinkDescriptor> getTransition_2001ContainedLinks(
			View view) {
		Transition modelElement = (Transition) view.getElement();
		LinkedList<Petrinet2LinkDescriptor> result = new LinkedList<Petrinet2LinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Transition_Output_4001(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Transition_Input_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Petrinet2LinkDescriptor> getPlace_2002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Petrinet2LinkDescriptor> getTransition_2001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<Petrinet2LinkDescriptor> getPlace_2002IncomingLinks(
			View view) {
		Place modelElement = (Place) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<Petrinet2LinkDescriptor> result = new LinkedList<Petrinet2LinkDescriptor>();
		result.addAll(getIncomingFeatureModelFacetLinks_Transition_Output_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingFeatureModelFacetLinks_Transition_Input_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Petrinet2LinkDescriptor> getTransition_2001OutgoingLinks(
			View view) {
		Transition modelElement = (Transition) view.getElement();
		LinkedList<Petrinet2LinkDescriptor> result = new LinkedList<Petrinet2LinkDescriptor>();
		result.addAll(getOutgoingFeatureModelFacetLinks_Transition_Output_4001(modelElement));
		result.addAll(getOutgoingFeatureModelFacetLinks_Transition_Input_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<Petrinet2LinkDescriptor> getPlace_2002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<Petrinet2LinkDescriptor> getIncomingFeatureModelFacetLinks_Transition_Output_4001(
			Place target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<Petrinet2LinkDescriptor> result = new LinkedList<Petrinet2LinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == Petrinet2Package.eINSTANCE
					.getTransition_Output()) {
				result.add(new Petrinet2LinkDescriptor(setting.getEObject(),
						target, Petrinet2ElementTypes.TransitionOutput_4001,
						TransitionOutputEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<Petrinet2LinkDescriptor> getIncomingFeatureModelFacetLinks_Transition_Input_4002(
			Place target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<Petrinet2LinkDescriptor> result = new LinkedList<Petrinet2LinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() == Petrinet2Package.eINSTANCE
					.getTransition_Input()) {
				result.add(new Petrinet2LinkDescriptor(setting.getEObject(),
						target, Petrinet2ElementTypes.TransitionInput_4002,
						TransitionInputEditPart.VISUAL_ID));
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<Petrinet2LinkDescriptor> getOutgoingFeatureModelFacetLinks_Transition_Output_4001(
			Transition source) {
		LinkedList<Petrinet2LinkDescriptor> result = new LinkedList<Petrinet2LinkDescriptor>();
		for (Iterator<?> destinations = source.getOutput().iterator(); destinations
				.hasNext();) {
			Place destination = (Place) destinations.next();
			result.add(new Petrinet2LinkDescriptor(source, destination,
					Petrinet2ElementTypes.TransitionOutput_4001,
					TransitionOutputEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<Petrinet2LinkDescriptor> getOutgoingFeatureModelFacetLinks_Transition_Input_4002(
			Transition source) {
		LinkedList<Petrinet2LinkDescriptor> result = new LinkedList<Petrinet2LinkDescriptor>();
		for (Iterator<?> destinations = source.getInput().iterator(); destinations
				.hasNext();) {
			Place destination = (Place) destinations.next();
			result.add(new Petrinet2LinkDescriptor(source, destination,
					Petrinet2ElementTypes.TransitionInput_4002,
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
		public List<Petrinet2NodeDescriptor> getSemanticChildren(View view) {
			return Petrinet2DiagramUpdater.getSemanticChildren(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<Petrinet2LinkDescriptor> getContainedLinks(View view) {
			return Petrinet2DiagramUpdater.getContainedLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<Petrinet2LinkDescriptor> getIncomingLinks(View view) {
			return Petrinet2DiagramUpdater.getIncomingLinks(view);
		}

		/**
		 * @generated
		 */
		@Override
		public List<Petrinet2LinkDescriptor> getOutgoingLinks(View view) {
			return Petrinet2DiagramUpdater.getOutgoingLinks(view);
		}
	};

}
