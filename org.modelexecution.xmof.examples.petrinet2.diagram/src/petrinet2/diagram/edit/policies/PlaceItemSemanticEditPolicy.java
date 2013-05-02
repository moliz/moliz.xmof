package petrinet2.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import petrinet2.diagram.edit.commands.TransitionInputCreateCommand;
import petrinet2.diagram.edit.commands.TransitionInputReorientCommand;
import petrinet2.diagram.edit.commands.TransitionOutputCreateCommand;
import petrinet2.diagram.edit.commands.TransitionOutputReorientCommand;
import petrinet2.diagram.edit.parts.TransitionInputEditPart;
import petrinet2.diagram.edit.parts.TransitionOutputEditPart;
import petrinet2.diagram.part.Petrinet2VisualIDRegistry;
import petrinet2.diagram.providers.Petrinet2ElementTypes;

/**
 * @generated
 */
public class PlaceItemSemanticEditPolicy extends
		Petrinet2BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public PlaceItemSemanticEditPolicy() {
		super(Petrinet2ElementTypes.Place_2002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (Petrinet2VisualIDRegistry.getVisualID(incomingLink) == TransitionOutputEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (Petrinet2VisualIDRegistry.getVisualID(incomingLink) == TransitionInputEditPart.VISUAL_ID) {
				DestroyReferenceRequest r = new DestroyReferenceRequest(
						incomingLink.getSource().getElement(), null,
						incomingLink.getTarget().getElement(), false);
				cmd.add(new DestroyReferenceCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (Petrinet2ElementTypes.TransitionOutput_4001 == req.getElementType()) {
			return null;
		}
		if (Petrinet2ElementTypes.TransitionInput_4002 == req.getElementType()) {
			return null;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (Petrinet2ElementTypes.TransitionOutput_4001 == req.getElementType()) {
			return getGEFWrapper(new TransitionOutputCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (Petrinet2ElementTypes.TransitionInput_4002 == req.getElementType()) {
			return getGEFWrapper(new TransitionInputCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case TransitionOutputEditPart.VISUAL_ID:
			return getGEFWrapper(new TransitionOutputReorientCommand(req));
		case TransitionInputEditPart.VISUAL_ID:
			return getGEFWrapper(new TransitionInputReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
