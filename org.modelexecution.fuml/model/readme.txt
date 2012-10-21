This file documents the process for obtaining a cleaned fUML.ecore metamodel.

1. Fetch the current cMOF metamodel from the OMG website (e.g., http://www.omg.org/spec/FUML/1.0/)

2. Save this file to fUML_<date>.cmof
   Currently this is fUML_10-03-15.cmof

2. Convert the cmof file into an eMOF-compliant ecore metamodel: fUML_original.ecore
   New -> EMF Generator Model
   Select the cmof file as input
   Save the resulting ecore file as fUML_original.ecore
   
3. Run the transformFUMLOriginal2FUMLClean.mwe2 workflow file
   This workflow will read fUML_original.ecore and write fUML.ecore
   
4. Fix relative path issue: search for "model/fUML.ecore" in fUML.ecore and replace it with ""
   The output, fUML.ecore, should be a cleaned version of fUML_original.ecore
   Open the fuml.genmodel to check whether it shows no error
   
   
4. Generate the code using the fuml.genmodel