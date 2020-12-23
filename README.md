# Shannon-Fano Coding

In this project, there are *four files* to do coding.

One of these files are **Node.java** file.
This file contain the node properties, such as left child of the node or possibility of node's key.

**FileOperations.java** file is also an utility file to doing file operations, such as read original file or write an object to specified file that you want.

**Encode.java** file is a leading file of this project. Because this is file which coding original file (This file is *file.txt* file. You can replace your file with it).

You can execute this file on your command line with the following commands:

Compile command:

```
javac Encode.java
```

Execute command:

```
java Encode
```

Well, but how does it work? In other word how does _Shannon-Fano Coding Algorithm_ work?

> **How does Shannon-Fano Algorithm work?**

Shannon-Fano Coding Algorithm is a data compression algorithm. It uses entropy coding technique. That means for each letter can be represent with 0 and 1 as ASCII. But there is an import difference between ASCII and Shannon-Fano Algorithm. This difference is that ASCII table represents each symbol with a number according to its Alphabetical order. Shannon-Fano Algorithm represents according to frequency of this letter, in the whole file. To do this, Shannon-Fano Algorithm uses a tree.

*An example:*

We have a word that contain _abcde_ letters and we want to coding this word. Frequency of this letters are;

a -> 0.35, </p>
b -> 0.17, </p>
c -> 0.17, </p>
d -> 0.16, </p>
e -> 0.15. </p>

At this point we should generate a balanced tree according as this freqeuncies and we have to sign left side of tree with zeros, right side of three with ones and also each subtree's sides.

![Shannon-Fano tree visualization](https://github.com/recep-yildirim/Shannon-Fano-Algorithm/blob/master/Image/tree.png)

Finally we get codes for each letters.

a represents by 00, </p>
b represents by 01, </p>
c represents by 10, </p>
d represents by 110, </p>
e represents by 111. </p>

If we had a word like **aaabbccde**, it was going to represent **00000001011010110111** or we had a word like **bbccaaaed**, it was going to represent **01011010000000111110**.

End of all you will get a two file. First file is **encoded** file and second file is **codes** file.

Encoded file contains your compressed file and codes file contains each letter's properties (**node class**) for using decoding operation.

**Decode.java** file do this decoding operation (It uses _encode.txt_ and _codes.bin_ files). To decode this encoded file, we are going to use codes in _codes.bin_ file.

Recall that:

a -> 00, </p>
b -> 01, </p>
c -> 10, </p>
d -> 110, </p>
e -> 111. </p>

Our encoded file was **00000001011010110111** for **aaabbccde**. Let's try turn this code to the original form.

Steps :
1- We begin first code. </p>

2- We check code list to find letter which represent with our first code. </p>

&emsp; 2.1- If there is no any letter that represent with our first code we take first two code and we turn step two. If still there is no, we take our first three code e.t.c.

&emsp; 2.2- If there is, we write our corresponding letter to the _decode.txt_ file.

3- We remove codes that we have encoded and we continue through rest of codes.

For example:

We look at first code. First code is *0*, but *0* not in our code list. Therefore, we look at first two code and this is *00*. *00* equals *a*, thus our first letter **a** and we remove *00* from encoded file. Then again we look at first code and again first code not in the code list. Therefore, we look at first two code and first two code represent **a**.
According as our first three letter *aaa* and we continue like that through encoded file.

End of the decoding, we get **aaabbccde** and our operation had finished.

To execute **Decode.java** file, write following commands on your command line:

Compile command:

```
javac Decode.java
```

Execute command:

```
java Decode
```

You can also create documentation with the following commands:

```
javadoc **which file that you want to generate documentation**

```
