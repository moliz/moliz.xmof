package petrinet2.diagram.edit.policies;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import petrinet2.diagram.edit.commands.PlaceCreateCommand;
import petrinet2.diagram.edit.commands.TransitionCreateCommand;
import petrinet2.diagram.providers.Petrinet2ElementTypes;

/**
 * @generated
 */
public class NetItemSemanticEditPolicy extends
		Petrinet2BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NetItemSemanticEditPolicy() {
		super(Petrinet2ElementTypes.Net_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (Petrinet2ElementTypes.Transition_2001 == req.getElementType()) {
			return getGEFWrapper(new TransitionCreateCommand(req));
		}
		if (Petrinet2ElementTypes.Place_2002 == req.getElementType()) {
			return getGEFWrapper(new PlaceCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
				.getEditingDomain();
		return getGEFWrapper(new DuplicateAnythingCommand(editingDomain, req));
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}

	}

}
