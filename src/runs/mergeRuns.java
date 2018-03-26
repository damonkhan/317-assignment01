package runs;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;


class mergeRuns {

    public static void main(String args[]){
	if(!(args.length == 2)){
	    System.err.println("Usage: <size> <textFile>");
	    return;
	}
	try{
	    int maxSize = Integer.parseInt(args[0]);
	    String filepath = "/Users/RileyCochrane/Desktop/" + args[1];
	    File file = new File(filepath);
	    if(!(file.exists())){
		 System.err.println("ERROR: file not found");
		 return;
	    }
	    
	    List<String>[] listArray = new List[maxSize];
	    //Creates an Array of ArrayLists based on the maxSize
	    for(int i=0; i<maxSize; i++){		
		List<String> newList = new ArrayList<>();
		listArray[i] = newList;
	    }
	    //Need to add runs to the List
	    FileReader fileReader = new FileReader(file);
	    BufferedReader reader = new BufferedReader(fileReader);
	    String line = reader.readLine();	    
	    List<String> currentList = listArray[0];
	    int change = 0;	
	    while(line!=null){
		//Change to next list - it is the next run
		if((line.compareTo("<>") == 0)){
		    if(change == (maxSize -1)){
			change = 0;
		    }
		    else {
			change++;
		    }
		    currentList = listArray[change];
		}
		else {
		    currentList.add(line);
		}
		line = reader.readLine();		
	    }
	    //now write to individual files
	    String outgoingPath = "/Users/RileyCochrane/Desktop/runs/";
	    File outgoing = new File(outgoingPath);
	    FileWriter fileWriter;
	    BufferedWriter writer;
	    //List of the temporary file names
	    List<String> fileNames = new ArrayList<String>(listArray.length);
	    for(int i = 0; i<listArray.length; i++){
		String tempName = "file" + Integer.toString(i);
		File tmpFile = File.createTempFile(tempName, ".txt", outgoing);
		//Add fileName to A list of FileNames to retrieve later
		fileNames.add(tmpFile.getName());
		fileWriter = new FileWriter(tmpFile);
		writer = new BufferedWriter(fileWriter);
		List<String> current = listArray[i];
		//Write out contents of current list in listarray to current temp File
		for(int j= 0; j<current.size(); j++){
		    writer.write(current.get(j));
		    writer.newLine();
		}
		writer.close();
	    }
	    //output file names for testing
	    for(int i = 0; i<fileNames.size(); i++){
		System.err.println(fileNames.get(i));
	    }
	    //Get first element from all lists and put into heap
	    MinHeap heap = new MinHeap(maxSize);
	    for(int i = 0; i<maxSize; i++){
		String fileName = "/Users/RileyCochrane/Desktop/runs/"
		    + fileNames.get(i);
		File inputFile = new File(fileName);
		FileReader inputReader = new FileReader(inputFile);
		BufferedReader br = new BufferedReader(inputReader);
		String newLine = br.readLine();
		heap.insert(newLine);
	    }
	     heap.print();
	    heapify(heap);
	    System.err.println(" HEAPIFIED");
	    heap.print();
	    

	    /*
	    //Get all the elements from every inner List
	    for(int i = 0; i<listArray.length; i++){
	        List<String> current = listArray[i];
		System.err.println("CURRENT LIST " + i);
		for(int j = 0; j<current.size(); j++){
		    System.err.println(current.get(j));
		}
	    }*/
	    /*
	    minHeap heap = new minHeap(maxSize);
	    for(int i = 0; i<maxSize; i++){
		if(line != null){
		    heap.insert(line);
		    line = reader.readLine();
		}
		else{
		    break;
		}
	    }
	    heap.print();
	    heapify(heap);
	    System.err.println("REPRTINED HEAPIFIED");
	    heap.print();
	    */
	}
	catch(Exception e){
	    System.err.println("Exception Error: " + e);
	}
    }
    public static void heapify(MinHeap heap) {
        // Do it twice for sanity...
        for (int i = 0; i < 2; i++) {
            heap.minHeap();
        }

    }
}
