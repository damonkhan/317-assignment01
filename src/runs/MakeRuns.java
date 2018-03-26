//////////////////////////////////////////////////////////////////////////////
//                                                                          //
// Authors: Damon Khan 1265776 & Riley Cochrane                              //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////


package runs;

import java.io.*;

class MakeRuns {

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Usage: <size> <textFile>");
            return;
        }

        // Initialize all the parts...
        int maxSize = Integer.parseInt(args[0]) + 1;
        String input = "/Users/damonkhan/Desktop/" + args[1];
        String output = "/Users/damonkhan/Desktop/" + args[1] + ".runs";
        MinHeap minHeap = new MinHeap(maxSize);
        File inputFile = new File(input);
        File outputFile = new File(output);
        String out = "";
        FileReader fReader;
        BufferedReader reader;
        PrintWriter writer;


        try {


            if (!inputFile.exists()) {
                System.out.println("Error: file not found");
                return;
            }

            fReader = new FileReader(inputFile);
            reader = new BufferedReader(fReader);
            writer = new PrintWriter(outputFile);

            // Build the heap
            for (int i = 1; i < maxSize; i++)
            {
                minHeap.insert(reader.readLine());
            }

            Heapify(minHeap);
            System.out.println("The Min Heap is ");
            minHeap.print();
            System.out.println("Next out is \"" + minHeap.minValue() + "\"");
            System.out.println();

            out = minHeap.replace("g");
            writer.println(out);
            System.out.println("The Min Heap is ");
            minHeap.print();
            System.out.println("Next out is \"" + minHeap.minValue() + "\"");

            out = minHeap.replace("h");
            writer.println(out);
            System.out.println("The Min Heap is ");
            minHeap.print();
            System.out.println("Next out is \"" + minHeap.minValue() + "\"");

            writer.close();
        }
        catch (Exception e) {
            System.err.println(e);
        }

    }

    public static void Heapify(MinHeap heap) {
        // Do it twice for sanity...
        for (int i = 0; i < 2; i++) {
            heap.minHeap();
        }
    }
}
