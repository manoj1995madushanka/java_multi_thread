why we need multithread? \
&ensp;    responsiveness -> concurrency \
&ensp;    performance -> parallelism \

examples of poor responsiveness \
&ensp;    waiting for customer support \
&ensp;    late response from a person \
&ensp;    no feedback from a application \

concurrency =multitasking \

we don't need multiple cpu cores for achieve multi tasking \

Context Switch Cost \
&ensp;    context switch is not cheap, and is the price of multitasking(concurrency) \
&ensp;    Same as we humans when we multitask - takes time to focus \
&ensp;    Each thread consumes resources in the CPU and memory \

When we switch to a different thread: \
&ensp;    Store data for one thread \
&ensp;    Restore data for another thread \

Thread scheduling - Dynamic Priority \
&ensp;    Dynamic Priority = Stati Priority + Bonus \

Static Priority is set by the developer programmatically \
Bonus is adjusted by the operating system in every epoch, for each thread \
Using dynamic priority, the OS will give preference for interactive threads(such as user interface threads) \
OS will give preference to threads that did not complete in the last epochs or did not get enogh time to run - preventing starvation \

When to prefer multithreaded architecture \
&ensp;    prefer if the tasks share a lot of data \
&ensp;    threads are much faster to create and destroy \
&ensp;    switching between threads of the same process is faster(shorter context switches) \


======================Thread Interruption========= \
why we need to do thread interruption \
&ensp;    Threads consume resources \
        Memory and kernal resources \
        CPU cycles and cache memory \
&ensp;    If a thread finished its work, but the application is still running, we want to clean up the thread's resources \
&ensp;    If a thread is misbehaving, we want to stop it \
&ensp;    By default, the application will not stop as long as at least one thread is still running \

When can we interrupt a Thread \
&ensp;    If the thread is executing a method that throws an InterruptedException \
&ensp;    If the thread's code is handling the interrupt signal explicitly \

When thread is interrupt that need to handle inside the thread otherwise it is not interrupt \

Daemon Thread
&ensp;    Background threads that do not prevent the application from existing if the main thread terminates. \

Daemon Threads Scenarios \
&ensp;    Background tasks, that should not block our application from terminating. \
        file saving thread in a text editor \
&ensp;    Code in a worker thread is not under our control, and we don't want it to block our application from terminating \
        worker thread that uses an external library \

If the method does not respond to the interrupt signal by throwing the interruptedException, we
need to check for that signal and handle it ourselves \

To prevent a thread from blocking our app from exiting, we set the thread to be a Daemon thread. \

===================================Joining Thread============================= \
Why we need thread coordination? \
&ensp;    Different threads run independently \
&ensp;    Order of execution is out of our control \

Thread coordination - Naive solution \
&ensp;    Thread B runs in a loop and keeps checking if Thread A's result is ready. \

we can use thread.join() method to coordinate threads \

=================================Performance Optimization==================== \
Latency - The time to completion of a task. measured in time units. \
Throughput - The amount of tasks completed in a given period. Measured in tasks/time unit \

================================Memory Regions=============================== \
Heap(shared) - objects, class members, static variables \
Stack(exclusive) - local primitive types, local reference \

===============================Atomic Operations============================ \
An operation or a set of operations is considered atomic, if it appears to the rest of \
the system as if it occurred at once \
Single step - "all or nothing" \
No intermediate states \

All assignment to primitive types are atomic except long and double \
java.util.concurrent.atomic package useful for atomic operations \

================================Critical Section============================== \
Synchronized Keyword \
&ensp;    Locking mechanism \
&ensp;    Used to restrict access to a critical section or entire method to a single thread at a time \
&ensp;    Synchronized block is called reentrant \
&ensp;    A thread cannot prevent itself from entering a critical section \

=====================Race Condition and Data Race==================================== \
Race Condition \
&ensp;    condition when multiple threads are accessing a shared resources \
&ensp;    At least one thread is modifying the resource \
&ensp;    The timing of threads scheduling may cause incorrect results \
&ensp;    The core of the problem is non-atomic operations performed on the shared resource \
Solution - Race Condition \
    Identification of the critical section where the race condition is happening. \
    Protection of the critical section by a synchronized block \

Data Race \
    Compiler and CPU may execute the instructions out of order to optimize performance and utilization \
    They will do so while maintaining the logical correctness of the code \
    Out of order execution by the compiler and CPU are important feature to speed up the code \
    CPU re-arrages instructions for better hardware units utilization \
    The compiler re-arranges instructions for better \
        branch prediction \
        vectorization(parallel instruction execution) \
        prefetching instructions(better cache performance) \
Solutions - Data Race \
    synchronization of methods which modify shared variable \
    declaration of shared variables with the volatile keyword \

=========================Problems of MultiThread============================ \
Two problems with multithreaded applications \
    Race Conditions \
    Data Race \
Both involve \
    Multiple threads \
    At least one is modifying a shared variable \
Both problems may result in unexpected and incorrect results \
Synchronized - solves both race condition and data race, but has performance penalty \
Volatile - solves race condition for read/write from/to long and double \
           solves all data races by guaranteeing order \

=========================Lock Strategies================================ \
Fine-Grained Locking (better parallelism but deadlock can be happened) \
    should have separate lock for each shared resource \
Coarse-Grained Locking (not best for performance) \
    one lock for all shared resource \

==========================DeadLocks==================================== \
conditions for deadlocks \
    mutual exclusion - only one thread can have exclusive access to a resource \
    hold and wait - at least one thread is holding a resource and is waiting for another resource \
    non-preemptive allocation - a resource is released only after the thread is done using it \
    circular wait - a chain of at least two threads each one is holding one resource and waiting for another resource \

solution \
    avoid circular wait - enforce a strict order in lock acquisition \
    maybe hard to accomplish if there are many locks in different places \
    lock resources in the same order everywhere \

deadlock detection techniques \
    watchdog \
    thread interruption (not possible with synchronized) \
    tryLock operations (not possible with synchronized) \

==========================ReentrantLock=============================== \
works like the synchronized keyword applied on an object \
requires explicit locking and unlocking \
most important feature of reentrant lock is tryLock functionality \
    it returns true and acquires a lock if available \
    returns false and does not get suspended, if the lock is unavailable \
    we avoid blocking the real time thread \
    kept application responsive \
    performed operations atomically \


================================ReentrantReadWriteLock======================== \
Race conditions require \
    multiple threads sharing a resource \
    at least one thread modifying the resource \
Solution - complete mutual exclusion \
    regardless of operation(read/write/both) \
    lock and allow only one thread to critical section \

Synchronized and reentrantLock don't allow multiple readers to access a shared resource concurrently \
ReentrantReadWriteLock allows multiple readers, read shared resource concurrently
Mutual exclusion between readers and writers \ \
    If write lock is acquired, no thread can acquire a read lock \
    If at least one thread holds a read lock no thread can acquire write lock \

======================Semaphore========================= \
Semaphore is like permit issuing authority \
Can be used to restrict the number of users to particular resource or group of resources \
Unlike the locks that allow only one user per resource,the Semaphore can restrict any given number of users to a resource \

Semaphore - Differences with locks \
    Semaphore doesn't have a notion of owner thread \
    Many threads can acquire a permit \
    The same thread can acquire the semaphore multiple times \
    The binary semaphore(initialized with 1) is not reentrant \
    Semaphore can be released by any thread \
    Even can be released by a thread that hasn't actually acquired it \

===============wait()================= \
Causes the current thread to wait until another thread wakes it up \
    In wait state the thread isn't consuming any CPU \
Two ways to wake up the waiting thread: \
    notify() - wakes up a single thread waiting on that object \
    notifyAll() - wakes up all the threads waiting on that object \

===============Atomic Operations=================== \
Read/Assignment on all primitive types(except for long and double) \ \
Read/Assignment on all references \
Read/Assignment on volatile long and double \
