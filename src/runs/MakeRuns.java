//////////////////////////////////////////////////////////////////////////////
//                                                                          //
// Authors: Damon Khan 1265776 & Riley Cochrane                              //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////


package runs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
//            String[] tmpItems = new String[tmpSize];
            List<String> tmpItems = new ArrayList<>();

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
            String root = minHeap.root();


            while (in != null || tmpSize > 0) {
                if (in == null && minHeap.getSize() == 0) {
                    break;
                }
                if (tmpSize > 0) {
                    root = minHeap.root();
                    if (root.compareTo(out) >= 0) {
                        if (in != null) {
                            out = minHeap.replace(in);
                            in = reader.readLine();
                        } else {
                            out = minHeap.remove();
                        }
                        writer.println(out);
                    }
                    else {
                        minHeap.swap(1, tmpSize);
                        tmpItems.add(minHeap.getItem(tmpSize));
                        tmpSize--;
                        minHeap.setItem(tmpSize, null);
                        minHeap.setSize(tmpSize);
                        minHeap.downHeapify(1);
                    }
                }
                else {
                    int i = 0;
                    for (String item : tmpItems) {
                        minHeap.insert(item);
                    }
                    tmpItems.removeAll(tmpItems);
                    minHeap.downHeapify(1);
                    tmpSize = maxSize - 1;
                    writer.println("<>");
                    numRuns++;

                    if (in != null) {
                        out = minHeap.replace(in);
                        writer.println(out);
                    }
                    else {
                        while (tmpSize > 0) {
                            out = minHeap.remove();
                            tmpSize--;
                        }
                    }
                }
            }
            writer.println("<>");
            numRuns++;
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
