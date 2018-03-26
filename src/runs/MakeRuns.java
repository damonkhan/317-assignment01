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
        int tmpSize = maxSize - 1;
        int numRuns = 0;
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

            heapify(minHeap);
            String in = reader.readLine();
            out = minHeap.replace(in);
            writer.println(out);
            in = reader.readLine();

            while (in != null) {
                while (tmpSize > 0) {
                    if (in.compareTo(out) >= 0)
                    {
                        out = minHeap.replace(in);
                        writer.println(out);
                    }
                    else {
                        minHeap.swap(1, tmpSize);
                        minHeap.minHeap();
                        tmpSize--;
                    }
                }
                writer.println("**");
                minHeap.minHeap();
                tmpSize = maxSize - 1;
                numRuns++;
            }


//            printHeap(minHeap);
//            out = minHeap.replace("g");
//            writer.println(out);
//            printHeap(minHeap);
//            out = minHeap.replace("h");
//            writer.println(out);
//            printHeap(minHeap);

            writer.close();
            System.err.println("number of runs: " + Integer.toString(numRuns));
        }
        catch (Exception e) {
            System.err.println(e);
        }

    }

    public static void heapify(MinHeap heap) {
        // Do it twice for sanity...
        for (int i = 0; i < 2; i++) {
            heap.minHeap();
        }
    }

    public static void printHeap(MinHeap heap) {
        System.out.println("The Min Heap is ");
        heap.print();
        System.out.println("Next out is \"" + heap.minValue() + "\"");
        System.out.println();
    }
}
