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
            String[] tmpItems = new String[100];

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
                    for (int j = 0; j < tmpSize; j++)
                    {
                        minHeap.insert(tmpItems[j]);
                    }
                    minHeap.minHeap();
                    numRuns++;
                    out = minHeap.replace(in);
                    in = reader.readLine();
                }
            }

            writer.println("<>");
            numRuns++;
            tmpSize = maxSize - 1;
            int j = 0;

            while (minHeap.getSize() < tmpSize && tmpItems[j] != null) {
                minHeap.insert(tmpItems[j]);
                j++;
            }

            minHeap.minHeap();

            for (int k = 1; k < tmpSize; k++)
            {
                out = minHeap.remove();
                writer.println(out);
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
}
