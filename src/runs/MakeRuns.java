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
            String[] tmpItems = new String[tmpSize];

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
            int i = 0;

            while (in != null) {
                if (tmpSize > 0) {
                    String root = minHeap.root();
                    if (root.compareTo(out) >= 0) {
                        out = minHeap.replace(in);
                        writer.println(out);
                        in = reader.readLine();
                    } else {
                        minHeap.swap(1, tmpSize);
                        tmpSize--;
                        tmpItems[i] = minHeap.getLastItem();
                        minHeap.setLastItem(null);
                        minHeap.setSize(tmpSize);
                        minHeap.minHeap();
                        i++;
                    }
                }
                else {
                    writer.println("<>");
                    tmpSize = maxSize - 1;
                    for (String item : tmpItems)
                    {
                        minHeap.insert(item);
                    }
                    minHeap.minHeap();
                    numRuns++;
                    out = minHeap.replace(in);            writer.println("<>");
                    numRuns++;
                    in = reader.readLine();
                }
            }

            for (String item : tmpItems)
            {
                if (item != null)
                    minHeap.insert(item);
            }

            heapify(minHeap);
            writer.println("<>");
            numRuns++;
            tmpSize = maxSize - 1;
            while (tmpSize > 0) {
                    out = minHeap.remove();
                    writer.println(out);
                    tmpSize--;
                }

//            writer.println("<>");
//            numRuns++;
//            int j = 0;
//
//            while (minHeap.getSize() < tmpSize && tmpItems[j] != null) {
//                minHeap.insert(tmpItems[j]);
//                j++;
//            }
//
//            minHeap.minHeap();
//
//            for (int k = 1; k < tmpSize; k++)
//            {
//                out = minHeap.remove();
//                writer.println(out);
//            }
//
//            writer.println("<>");
//            numRuns++;
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

    public static void replacementSelection(MinHeap minHeap, String in, PrintWriter writer)
    {
        String out = minHeap.replace(in);
        writer.println(out);
    }

    public static void reduceHeap(MinHeap minHeap, int size) {
        minHeap.swap(1, size);
    }
}
