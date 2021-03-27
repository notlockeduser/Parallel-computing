# Parallel-processing
 Work on the study and solution of problems associated with parallelism
 
 Tasks

1. Development and implementation of a parallel algorithm for problems with data parallelism.
Create a vector with N> = 10000 elements from random numbers. Find the maximum and minimum elements of vectors.

2. Using and synchronizing streams.
The program simulates the maintenance of two process threads with different parameters by two computer CPUs with one queue. If a second thread process is generated and the second processor is busy, the process is queued. If the process of the first thread is generated, then, if the first processor is busy processing the first thread, the process is sent for processing to the second processor. If the second processor is busy, the process is destroyed. If at the time of generation of the process of the first thread on the first processor is processed by the process of the second thread, the process is interrupted and returned to the queue. Determine the maximum queue length, the percentage of destroyed processes for the first thread, and the percentage of interrupted requests for the second thread.

3. Non-blocking algorithms.
Development and implementation of programs that simulate the following tasks:
- checksum using XOR for array elements of type int.
- minimum and maximum elements of the array of type long and their indices;
- modes and medians of elements of an array of type long and also their indices;
- scalar product of two vectors;
- your own task. Multiplying two vectors, and if the product is even then write in the first sum, no - the second.

4. Asynchronous calculations. Develop a program that uses the Java class CompletableFuture.
Create 2 arrays (or collections) with random numbers. In the first array - leave elements that are larger than the average value of the array, in the second - less. Sort arrays and merge into one sorted array those elements that are in one array and not in another.

5. Research of network capabilities of distributed systems and implementation of client-server application.
The server sends messages to the clients selected from the list. The list is saved in a file.
