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
        int numRuns = 0;
        String input = "/Users/damonkhan/Desktop/" + args[1];
        String[] parts = args[1].split("\\.");
        String output = "/Users/damonkhan/Desktop/" + parts[0] + ".runs";
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
            List<String> tmpItems = new ArrayList<>();
            String in = reader.readLine();

            // Build the heap
            for (int i = 1; i < maxSize; i++)
            {
                if (in != null)
                    minHeap.insert(in);

                if (in == null)
                    break;

                in = reader.readLine();

            }

            heapify(minHeap);
            int tmpSize = minHeap.getSize();

            // output of first datum in first run
            if (in != null) {
                out = minHeap.replace(in);
            } else {
                // no data to be read in, remove root from heap and reheap
                out = minHeap.remove();
                tmpSize--;
            }

            // write datum to file
            writer.println(out);
            in = reader.readLine();
            String root;


            // while there is data to be read or in heap
            while (in != null || tmpSize > 0 || tmpItems.size() > 0) {
                // break when no data to be read or in heap
                if (in == null && minHeap.getSize() == 0) {
                    if (tmpItems.size() == 0)
                        break;
                    else {
                        // output final run then break
                        for (String item : tmpItems) {
                            minHeap.insert(item);
                        }
                        minHeap.downHeapify(1);
                        writer.println("<>");
                        numRuns++;
                        while (minHeap.getSize() > 0) {
                            out = minHeap.remove();
                            writer.println(out);
                        }
                        break;
                    }
                }

                // if there is data in the heap
                if (tmpSize > 0) {
                    // compare the root to last output data
                    root = minHeap.root();
                    if (root.compareTo(out) >= 0) {
                        if (in != null) {
                            // replace root if there is data to be read in
                            out = minHeap.replace(in);
                            in = reader.readLine();
                        } else {
                            // otherwise, remove root and reheap
                            out = minHeap.remove();
                            tmpSize--;
                        }
                        writer.println(out);
                    }
                    else {
                        // swap root with last item and reduce notional size of heap
                        minHeap.swap(1, tmpSize);
                        tmpItems.add(minHeap.getItem(tmpSize));
                        tmpSize--;
                        minHeap.setItem(tmpSize, null);
                        minHeap.setSize(tmpSize);
                        minHeap.downHeapify(1);
                    }
                }
                // no data in the heap, so rebuild new heap
                else {
                    int i = 0;
                    for (String item : tmpItems) {
                        minHeap.insert(item);
                    }
                    tmpItems.removeAll(tmpItems);
                    minHeap.downHeapify(1);
                    tmpSize = minHeap.getSize();
                    writer.println("<>");
                    numRuns++;

                    // if there is data to be read in
                    if (in != null) {
                        // then replace root with new datum
                        out = minHeap.replace(in);
                        in = reader.readLine();
                        writer.println(out);
                    }
                    else {
                        // otherwise output min value
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
}
