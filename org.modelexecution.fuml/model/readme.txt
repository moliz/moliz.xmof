This file documents the process for obtaining a cleaned fUML.ecore metamodel.

1. Fetch the current cMOF metamodel from the OMG website (e.g., http://www.omg.org/spec/FUML/1.0/)

2. Replace primitive types and save this file to fUML_<date>_fixedPrimitiveTypes.cmof
   Primitive Type replacements: Search and replace the following
   "http://schema.omg.org/spec/UML/2.2/uml.xml#String" with "pathmap://UML_LIBRARIES/EcorePrimitiveTypes.library.uml#EString"
   "http://schema.omg.org/spec/UML/2.2/uml.xml#Boolean" with "pathmap://UML_LIBRARIES/EcorePrimitiveTypes.library.uml#EBoolean"
   "http://schema.omg.org/spec/UML/2.2/uml.xml#Integer" with "pathmap://UML_LIBRARIES/EcorePrimitiveTypes.library.uml#EInt"
   "http://schema.omg.org/spec/UML/2.2/uml.xml#UnlimitedNatural" with "pathmap://UML_LIBRARIES/EcorePrimitiveTypes.library.uml#EInt"

2. Convert this fixed cmof file into an eMOF-compliant ecore metamodel: fUML_original.ecore
   New -> EMF Generator Model
   Select the cmof file as input
   Save the resulting ecore file as fUML_original.ecore
   
3. Run the transformFUMLOriginal2FUMLClean.mwe2 workflow file
   This workflow will read fUML_original.ecore and write fUML.ecore
   
4. Fix relative path issue: search for "model/fUML.ecore" in fUML.ecore and replace it with ""
   The output, fUML.ecore, should be a cleaned version of fUML_original.ecore
   Open the fuml.genmodel to check whether it shows no error
   
   
4. Generate the code using the fuml.genmodel