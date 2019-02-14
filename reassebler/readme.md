# 'Reassembler' : the useful tool which helps to defragment line of text file.! 


 Overview : The application reassembles a given set of text fragments into their original sequence. The aim is to defragment each line according to an overlapping algorithm. 
 The approach consists of :
 	
    1.Reading each line as a list of fragments 
    2.Defragmenting it : Processing it in order to calculate the overlapping matches and 							recoursively merge the fragments in relationship.
    3.Producing a fixed file as result of the defragmentation.


###1. configuration 

Prepare a text file with a bunch of fragments spreaded out over each line. The fragments have to be separated by ;(or whatever pattern specified as input args) for each line

A) Example  : 
	
	"O draconia;conian devil! Oh la;h lame sa;saint!"
	
   Expected result :
   
	"O draconian devil! Oh lame saint!"
	
B) More complex example :

	 "aliqua;psum quia dolor sit amet, consectetur, a;Neque porro quisquam est, qu;Neque porro qui;Neque porro quisquam est, qui dolorem i;lorem ipsum quia dolor sit amet;ctetur, adipisci velit, sed quia non numq"

   Result :
   
   
	"Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numq"
   
   

###2. Run

Default configuration (Default in/out)

	java -jar reassembler-0.0.1-SNAPSHOT.jar 

Custom configuration (Specify the path of the File to be imported and a rows separator Ex. ';')
  
    java -jar reassembler-0.0.1-SNAPSHOT.jar input/myFragmentedFile.txt ; output/myDeFragmentedFile.txt
 
 NB: the folder is to be in the same root of the executable.
 

###3. Testing - Run Unit Tests 
  For running tests use maven  :

	maven clean test









