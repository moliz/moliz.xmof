package petrinet.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import petrinet.diagram.providers.PetrinetElementTypes;

/**
 * @generated
 */
public class TransitionInputItemSemanticEditPolicy extends
		PetrinetBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TransitionInputItemSemanticEditPolicy() {
		super(PetrinetElementTypes.TransitionInput_4002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
