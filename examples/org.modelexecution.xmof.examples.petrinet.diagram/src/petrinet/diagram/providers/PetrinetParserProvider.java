package petrinet.diagram.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

import petrinet.PetrinetPackage;
import petrinet.diagram.edit.parts.PlaceNameEditPart;
import petrinet.diagram.edit.parts.TransitionNameEditPart;
import petrinet.diagram.parsers.MessageFormatParser;
import petrinet.diagram.part.PetrinetVisualIDRegistry;

/**
 * @generated
 */
public class PetrinetParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser transitionName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getTransitionName_5001Parser() {
		if (transitionName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { PetrinetPackage.eINSTANCE
					.getTransition_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			transitionName_5001Parser = parser;
		}
		return transitionName_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser placeName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getPlaceName_5002Parser() {
		if (placeName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { PetrinetPackage.eINSTANCE
					.getPlace_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			placeName_5002Parser = parser;
		}
		return placeName_5002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case TransitionNameEditPart.VISUAL_ID:
			return getTransitionName_5001Parser();
		case PlaceNameEditPart.VISUAL_ID:
			return getPlaceName_5002Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(PetrinetVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(PetrinetVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (PetrinetElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
