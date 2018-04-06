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
	    System.out.println("Usage: <size> <textFile>");
	    return;
	}
	try{
	    int maxSize = Integer.parseInt(args[0]);
	    //Relative??
	    String filepath = "Users/RileyCochrane/Desktop/" + args[1];
	    File input = new File(filepath);
	    if(!(input.exists())){
		System.out.println("ERROR: file not found");
		return;
	    }
	    //Create as many files specified by maxSize
	    String outgoingPath = "Users/RileyCochrane/Desktop/runs/";
	    File outgoing = new File(outgoingPath);
	    //File list to hold all the files
	    List<File> files = new ArrayList<File>(maxSize);
	    for(int i = 0; i<files.size(); i++){
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
		    if(change < maxSize)
			change = 0;
		    outputFileWriter = new FileWriter(files.get(change));
		    outputWriter = new BufferedWriter(outputFileWriter);
		}
		else{
		    outputWriter.write(line);
		    outputWriter.newLine();
		}		
		line = inputReader.readLine();
	    }
	    
			    
	}
	catch(Exception e){
	    System.err.println("MAIN EXCEPTION: " + e);
	}
    }
}
