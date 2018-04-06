package runs;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

class mergeRunsFinal {
    public static void main(String args[]){
	if(!(args.length == 2)){
	    System.out.println("Usage: <size> <textFile>");
	    return;
	}
	try{
	    int maxSize = Integer.parseInt(args[0]);
	    String filepath = new File("").getAbsolutePath() + "/runs/" + args[1];
	    File input = new File(filepath);
	    if(!(input.exists())){
		System.out.println("ERROR: file not found");
		return;
	    }
	    //Create as many files specified by maxSize
	    String outgoingPath = new File("").getAbsolutePath() + "/runs/";
	    File outgoing = new File(outgoingPath);
	    //File list to hold all the files
	    List<File> files = new ArrayList<File>();
	    for(int i = 0; i<maxSize; i++){
		String name = "file" + Integer.toString(i);
		File tempFile = File.createTempFile(name, ".txt", outgoing);
		files.add(tempFile);
	    }
	    //Distribute runs across files
	    FileReader inputFileReader = new FileReader(input);
	    BufferedReader inputReader = new BufferedReader(inputFileReader);
	    FileWriter outputFileWriter = new FileWriter(files.get(0));
	    BufferedWriter outputWriter = new BufferedWriter(outputFileWriter);
	    String line = inputReader.readLine();	    
	    int change = 0;
	    while(line != null){
		if(line.compareTo("<>") == 0){
		    //change to next file - now reading next run
		    change ++;
		    //Loop back to first file if greater than maxSize
		    if(change > maxSize)
			change = 0;
		    outputWriter.close();
		    //Set the outputWriter to the next file
		    outputFileWriter = new FileWriter(files.get(change));
		    outputWriter = new BufferedWriter(outputFileWriter);
		}
		else{
		    //Write the line
		    outputWriter.write(line);
		    outputWriter.newLine();
		}
		//get the next line
		line = inputReader.readLine();
	    }
	    //Initialise heap with first values of files
	    MinHeap heap = new MinHeap(maxSize);
	    change = 0;
	    FileReader outputFileReader = new FileReader(files.get(change));
	    BufferedReader outputReader = new BufferedReader(outputFileReader);
	    line = outputReader.readLine();
	    //Loop to fill elements
	    while(heap.getSize() < maxSize){
		if(line != null){
		    //insert both line and fileName to remember file it came from
		    heap.insert(line, files.get(change).getName());		    
		    heapify(heap);
		    //remove line from the file - already inserted
		    removeLine(files.get(change), line);
		}
		//increment and loop back if greater than maxsize
		change++;
		if(change >= maxSize)
		    change = 0;
		outputReader.close();
		//Get next file
		outputFileReader = new FileReader(files.get(change));
		outputReader = new BufferedReader(outputFileReader);
		//read the next line
		line = outputReader.readLine();
	    }
	    //Now have Heap as large as maxSize
	    //list to keep track of what files are empty & overall boolean
	    List<Boolean> empty = makeEmptyList(files);
	    boolean areFilesEmpty = checkEmpty(empty);
	    //clear input file - datum now in other files
	    input.delete();
	    FileWriter outputFileFinal = new FileWriter(input);
	    BufferedWriter outputFinal = new BufferedWriter(outputFileFinal);

	    //Loop while files are not empty
	    while(areFilesEmpty){
		//Get front element and split between datum and file
		String original = heap.removeFront();
		String[] split = original.split(" ");
		
	    }
	    heap.print();
	}
	catch(Exception e){
	    System.err.println("MAIN EXCEPTION: " + e);
	}
    }
    //Min Heapifys the heap
    //Twice just to double check it correctly heapifys but it should do
    public static void heapify(MinHeap heap) {
        // Do it twice for sanity...
        for (int i = 0; i < 2; i++) {
            heap.minHeap();
        }
    }
    //Removes the first line of a given file
    //Compares to first line to double check
    //Then rewrites all other datum to new file and renames it to original
    public static void removeLine(File file, String line){
	try{
	    String fileDirectory = new File("").getAbsolutePath() + "/runs/";
	    File directory = new File(fileDirectory);
	    File newFile = File.createTempFile("newFile", ".txt", directory);
	    //Write old contents to new file
	    FileReader input = new FileReader(file);
	    BufferedReader reader = new BufferedReader(input);
	    FileWriter output = new FileWriter(newFile);
	    BufferedWriter writer = new BufferedWriter(output);
	    String inputLine = reader.readLine();
	    if(line.compareTo(inputLine) == 0){
		//lines are the same so get next line
		inputLine = reader.readLine();
		while(inputLine != null){
		    //write line to new file
		    writer.write(inputLine);
		    writer.newLine();
		    //get next line
		    inputLine = reader.readLine();
		}
	    }
	    reader.close();
	    writer.close();
	    //change new file to old file name
	    File fileFinal = new File(file.getAbsolutePath());
	    boolean success = newFile.renameTo(fileFinal);
	}
	catch(Exception e){
	    System.err.println("removeLine exception: " + e);
	}
    }
    //Creates a boolean list of which files still have datum to read
    //Loops through all files, checks if first line is null or not
    //If file still has datum then current empty element is true, false if not
    //returns a boolean list
    public static List<Boolean> makeEmptyList(List<File> files){
        List<Boolean> empty = new ArrayList<Boolean>();
	try{
	    //loop through all the files
	    for(int i = 0; i<files.size(); i++){
		File current = files.get(i);
		FileReader currentFileReader = new FileReader(current);
		BufferedReader currentReader = new BufferedReader(currentFileReader);
		//read first element of file if empty mark false
		String line = currentReader.readLine();
		if(line == null)
		    empty.add(false);
		else{		    
		    empty.add(true);
		}
	    }
	}
	catch(Exception e){
	    System.err.println("Make Empty List Exception: " + e);
	}
	return empty;
    }
    //Checks empty list to see if there is more datum to read
    //Result is a boolean, true if more datum to read in files, false if not
    public static Boolean checkEmpty(List<Boolean> empty){
	boolean result = false;
	try{
	    for(int i = 0; i<empty.size(); i++){
		if(empty.get(i) == true)
		    result = true;
	    }
	}
	catch(Exception e){
	    System.out.println("Check Empty Exception: " + e);
	}
	return result;
    }
}
