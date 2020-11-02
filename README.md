# kata-spring-db

## Database password encryption

Using the library [Jasypt](http://www.jasypt.org/ "Jasypt's Homepage") to encrypt the database password. The steps to use it are the following:

1. Create the encrypted text for your password
1.1 Locate the jasypt-1.9.3.jar on you machine after building the code
1.2 Execute the encryption command:
    
    java -cp ~/jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="YOUR_PASSWORD" password="YOUR_SECRET_PHRASE" algorithm=PBEWITHMD5ANDDES

If everything goes well, you should see something like:

    ----ENVIRONMENT-----------------
    
    Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.251-b08
    
    ----ARGUMENTS-------------------
    
    algorithm: PBEWITHMD5ANDDES
    input: YOUR_PASSWORD
    password: YOUR_SECRET_PHRASE
    
    ----OUTPUT----------------------
    
    xOCNEJG0UTpAm5xznOlD4yB7Etktr1cP+qXmD5ZFpic=

2. Configure the encrypted password on the application.properties file

In order for Spring to configure Jasypt, you need to add the following properties:

    jasypt.encryptor.iv-generator-classname=org.jasypt.iv.NoIvGenerator
    jasypt.encryptor.algorithm=PBEWithMD5AndDES
    
(Notice the algorithm is the same that you use to encrypt the password)    

3. Mark the password as encoded on the properties:

    spring.datasource.password=ENC(Et5ZFpicktr1cP+qXmDEJ=/G0UXmD=xOCNktr1cP+qTpAm5xznO%/lD4yB7)

You have to surround with "ENC()" the output of the encryption.

4. Start the service with the proper environment variable.
You have to use 

    -Djasypt.encryptor.password=YOUR_SECRET_PHRASE