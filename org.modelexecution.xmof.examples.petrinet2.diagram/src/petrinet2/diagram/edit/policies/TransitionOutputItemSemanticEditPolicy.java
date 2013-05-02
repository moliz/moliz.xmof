package petrinet2.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyReferenceCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;

import petrinet2.diagram.providers.Petrinet2ElementTypes;

/**
 * @generated
 */
public class TransitionOutputItemSemanticEditPolicy extends
		Petrinet2BaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public TransitionOutputItemSemanticEditPolicy() {
		super(Petrinet2ElementTypes.TransitionOutput_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return getGEFWrapper(new DestroyReferenceCommand(req));
	}

}
