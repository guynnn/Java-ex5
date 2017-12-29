# Java-ex5


=============================
=      File description     =
=============================
1. README                          this file.
2. DirectoryProcessor.java         the main class.
3. Parsing.java                    reads the commands file.
4. Section.java                    represents a sub-section of the commands file.
5. MissingSectionException.java    exception for a missing part of the sub-section.
6. Filter.java                     abstract class that represents the filter command.
7. FilterFactory.java              Builds the different kind of filter objects.
8. AllFilter.java                  filters all the files.
9. BetweenFilter.java              filter all the files that between two values.
10.ContainsFilter.java             filters the files that their name contained in a certain value.
11.ExecutableFilter.java           filters the files that are executable.
12.FileFilter.java                 filters all the files that their name equals to a certain value.
13.GreaterThanFilter.java          filters all the file that greater than a certain value.
14.HiddenFilter.java               filters all the files that are hidden.
15.PrefixFilter.java               filters all the files that start with a certain value.
16.SmallerThanFilter.java          filters all the files that smaller than a certain value.
17.SuffixFilter.java               filters all the files that end with a certain value.
18.WritableFilter.java             filters all the files that are writable.
19.FilterException.java            exception for a bad filter command.
20.Order.java                      abstract class that represents the order command.
21.OrderFactory.java               builds the different kind of order objects.
22.AbsOrder.java                   orders the files by their absolute path.
23.SizeOrder.java                  orders the files by their size.
24.TypeOrder.java                  orders the files by their type.
25.OrderException.java             exception for bad order command.

=============================
=          Design           =
=============================
The program is given a commands file and a directory, and it prints in a certain order the matched file names,
according to the commands that in the commands file. Our main class is DirectoryProcessor, which use
Parsing's static method to read the commands file and make Section objects according to the commands.
Each section in the commands file is a Section object. Section holds a Filter and an Order objects as data
members, and use them to print the correct output.
All filters object extend Filter. In order to filter the correct files with the NOT suffix, I used the XOR
operator with the corresponding condition.
All orders object extend Order. In order to order the filtered files, I used an ArrayList of files as data
structure (easy to add new values, and no need to set a final size). To the actual sorting operation I used
the static method Array.sort, with the help of lambda expression. I solved the REVERSE suffix problem by
multiplying the value that lambda expression returned by -1 when needed, and this way the array is sorted
in the opposite direction easily. Ordering and Filtering packages has only 1 exception each, that used when
the command was not correct. FilterFactory and OrderFactory throw FilterException and OrderException
respectively to Parsing method, then Parsing catch them and Builds new Section with warning message.
fileProcessing has 1 exception, that is used when FILTER or ORDER sub-section were missing. Parsing throws
it to DirectoryProcessor, then it catch the exception and terminates the program.
I chose this design because it easy to add new order or filter classes (single choice), and there's no need
to change existing classes but the Factory classes, and it easy to understand because every section in the
commands file has its section object, with filter and order objects in it.

=============================
=  Implementation details   =
=============================

=============================
=    Answers to questions   =
=============================
