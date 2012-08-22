/**
 */
package testlang;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.uml2.uml.ExecutableNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assertion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link testlang.Assertion#getOclExpression <em>Ocl Expression</em>}</li>
 *   <li>{@link testlang.Assertion#getOperator <em>Operator</em>}</li>
 *   <li>{@link testlang.Assertion#getNode <em>Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see testlang.TestlangPackage#getAssertion()
 * @model
 * @generated
 */
public interface Assertion extends EObject {
	/**
	 * Returns the value of the '<em><b>Ocl Expression</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Expression</em>' attribute.
	 * @see #setOclExpression(String)
	 * @see testlang.TestlangPackage#getAssertion_OclExpression()
	 * @model default=""
	 * @generated
	 */
	String getOclExpression();

	/**
	 * Sets the value of the '{@link testlang.Assertion#getOclExpression <em>Ocl Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Expression</em>' attribute.
	 * @see #getOclExpression()
	 * @generated
	 */
	void setOclExpression(String value);

	/**
	 * Returns the value of the '<em><b>Operator</b></em>' attribute.
	 * The literals are from the enumeration {@link testlang.TempOperator}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operator</em>' attribute.
	 * @see testlang.TempOperator
	 * @see #setOperator(TempOperator)
	 * @see testlang.TestlangPackage#getAssertion_Operator()
	 * @model required="true"
	 * @generated
	 */
	TempOperator getOperator();

	/**
	 * Sets the value of the '{@link testlang.Assertion#getOperator <em>Operator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operator</em>' attribute.
	 * @see testlang.TempOperator
	 * @see #getOperator()
	 * @generated
	 */
	void setOperator(TempOperator value);

	/**
	 * Returns the value of the '<em><b>Node</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' reference.
	 * @see #setNode(ExecutableNode)
	 * @see testlang.TestlangPackage#getAssertion_Node()
	 * @model
	 * @generated
	 */
	ExecutableNode getNode();

	/**
	 * Sets the value of the '{@link testlang.Assertion#getNode <em>Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node</em>' reference.
	 * @see #getNode()
	 * @generated
	 */
	void setNode(ExecutableNode value);

} // Assertion
