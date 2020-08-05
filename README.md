# Crypto
An encryption/decryption library with many pipeable algorithms 
****
The eventual goal is to implement random layers of encryption with different encryption algorithms.
The use of this project has no intention of being used in the real world, because in a cryptological sense
the methods and systems this project uses to encrypt data is very primitive and could easily be broken.
Methods such as AES would be the go-to standard algorithm to use in real applications.
****
Tutorial: 
```java
// Create a new group of algorithms, stream ciphers and block ciphers are not interchangable
// Generic paremeter takes either a CipherStream or a CipherBlock both implementing the Crypto interface, and the resulting algorithms must be of either type
Group group1 = new CipherGroup<CipherStream>(Algorithms.ALWAYSADDONE, Algorithms.BASICSTREAM); // <-- Takes in multiple algorithms to pipe

CipherStream alwaysAddOne = new AlwaysAddOne();
CipherStream basicStream = new BasicCrypto();
// Groups can also be created with algorithm objects, can be preferable if you want to modify the parameters of an algorithm
Group group2 = new CipherGroup<CipherStream>(alwaysAddOne, basicCrypto);

// Create a StreamManager to handle io operations and buffering
StreamManager streamManager = new StreamManager(new File("./plaintext.txt"), new File("./encrypted.txt"), group, 2048); // <-- pass in an input file, an output file, and a group. Block size is optional, but 1KB by default

// Execute the pipeline in the order the algorithm references were passed
streamManager.executeGroup(true); // Optional boolean to decrypt the input contents, false by default

// Switch the input and output files
streamManager.swap();

// Change the input file 
streamManager.changeIn(new File("./AnotherInputFile"));

// Change the output file
streamManager.changeOut(new File("./AnotherOutputFile"));

// Encrypts and dumps output, then decrypts the encrypted file into decrypted.txt
streamManager.executeGroup().swap().changeOut(new File("./decrypted.txt")).executeGroup(true); // Manager methods are chainiable 
```
****
![alt text](/images/diagram.png?raw=true)
