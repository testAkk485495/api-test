# Bhavnaben Marvel API Testing

Bhavnaben Rajput 
Project Title :  Bhavnaben Marvel API Testing

To run tests:

1. Upload project from github:
```
git clone https://github.com/shavrova/api-tests-demo.git
```
2. Download chromdriver from here:
https://chromedriver.chromium.org/downloads
Note: depends on chrome version on target machine

3. Put chromdriver.exe file to project root directory

4. Navigate to project root folder in windows comand line

5. Run one of next commands:

To run all test:
```
gradlew test -Dpublic.key=<public_key> -Dprivate.key=<private_key>

```
To run only api test:
```
gradlew -Dcucumber.options="--tags @API" test -Dpublic.key=<public_key> -Dprivate.key=<private_key>

```
To run only UI tests:
```
gradlew -Dcucumber.options="--tags @UI" test -Dpublic.key=<public_key> -Dprivate.key=<private_key>

```
Where <public_key> is public key and <private_key> is private key obtained from marvel website
