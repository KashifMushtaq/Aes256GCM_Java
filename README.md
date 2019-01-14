# Java Version of AES 256 GCM

### Cross Platform AES 256 GCM Encryption / Decryption (C#, C++ - Crypto++ & Java)

![C++ Version](https://github.com/KashifMushtaq/AES_GCM_256_C/blob/master/JavaTest.png)

### Introduction

While working in security, identity management and data protection fields for a while, I found a very few working examples in the public domain on cross platform encryption based on AES 256 GCM algorithm. This is the same algorithm used by Google when you access Gmail, etc.

This article may help you implement very strong cross platform encryption / decryption. The sample code is in C++, C# and Java. However, Java through JNI (Java Native Interface) and C# through COM, can call native C++ code, which in my testing appears to be much faster as compared to pure Java or C# implementations. Still there are times when one wants to do it without calling a native C++ layer.

For C#, to achieve AES 256 GCM encryption, I used Bouncy Castle cryptographic libraries. The code snippets available with this article work perfectly for encryption and decryption across various platforms. I tested it to be working on Linux (using Mono Framework) and Windows.

For C++ layer, I utilized Crypto++. This library is cross platform compatible (Windows, Linux and others like Solaris etc.). Crypto++ is a robust and very well implemented open source cryptographic library.

This article is not intended for beginners nor is it to teach AES GCM algorithm.

This article sort of provides you a sample code to implement with your own modifications.

C++ is a little complicated. Download Crypto++ source code. Create a console project and add existing Crypto++ project to solution. Then set your console project as startup project and set build dependency order.

Copy paste code from the article and correct header files paths (like pch.h) You can add source code directories in project properties as well to fix paths. In the same way, you can correct path to Crypto++ compiled library or add it to your project properties.

Another purpose of this article is to combine all three major programming languages sample code at one place. You don't have to search through thousands of individual samples, some of them do not work as intended. The code sample here works without any issue.

### Background

[Cross Platform AES 256 GCM Encryption and Decryption (C++, C# and Java)](https://www.codeproject.com/Articles/1265115/Cross-Platform-AES-256-GCM-Encryption-Decryption)

You can also read more about Crypto++ AES GCM implementation or algorithm itself here and [here](https://www.cryptopp.com/).

### For Java
### Please add the following JAR to your Java project or class path:
### bcprov-jdk15on-160.jar
Similarly, details about BouncyCastle can be found [here](https://www.bouncycastle.org/java.html).


### [C# Verion](https://github.com/KashifMushtaq/AesGcm256)
### [C++ Verion](https://github.com/KashifMushtaq/AES_GCM_256_C)
### [Java Verion](https://github.com/KashifMushtaq/Aes256GCM_Java)

