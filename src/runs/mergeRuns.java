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
	    String filepath = "/Users/damonkhan/Desktop/" + args[1];
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
	    String outgoingPath = "/Users/damonkhan/Desktop/runs/";
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
	    //Making sure that when element is inserted it is removed from file
	    MinHeap heap = new MinHeap(maxSize);
	    for(int i = 0; i<maxSize; i++){
		String fileName = "/Users/damonkhan/Desktop/runs/"
		    + fileNames.get(i);
		File inputFile = new File(fileName);
		FileReader inputReader = new FileReader(inputFile);
		BufferedReader br = new BufferedReader(inputReader);
		String newLine = br.readLine();
		heap.insert(newLine, fileNames.get(i));
		removeLine(inputFile, newLine);
		
	    }
	    heapify(heap);
	    heap.print();
	    String front = heap.removeFront();
	    String[] frontFile = front.split(" ");
	    System.err.println(frontFile[0] + " Came from " + frontFile[1]);
	    heap.replaceFront("TEST", "filename");
	    heap.print();
	    
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
    public static void removeLine(File file, String line){
	try{
	    String name = "newFile";
	    String outputDir = "/Users/damonkhan/Desktop/runs/output/";
	    File output = new File(outputDir);
	    File outputFile = File.createTempFile(name, ".txt", output);
	    //Create a writer to write contents to new file
	    FileWriter outputWriter = new FileWriter(outputFile);
	    BufferedWriter writer = new BufferedWriter(outputWriter);
	    //Create reader to read through file given
	    FileReader input = new FileReader(file);
	    BufferedReader reader = new BufferedReader(input);
	    //Read the first line of the file
	    String readLine = reader.readLine();
	    if((line.compareTo(readLine) == 0)){
		//if line is line to remove ie first, then write out from next line
		readLine = reader.readLine();
		while(readLine!=null){
		    writer.write(readLine);
		    writer.newLine();
		    readLine = reader.readLine();
		}		
	    }
	    reader.close();
	    writer.close();
	    
	    File newName = new File(file.getAbsolutePath());
	    boolean success = outputFile.renameTo(newName);
	}
	catch(Exception e){
	    System.err.println("EXCEPTION: " + e);
	}
    }

    public String getNext(File file, String element){
	String s = "";
	return s;
    }
}
