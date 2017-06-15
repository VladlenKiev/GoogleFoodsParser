# GoogleFoodsParser
1) Finding 1000 most active users (profile names)
2) Finding 1000 most commented food items (item ids).
3) Finding 1000 most used words in the reviews
4) Program translate all the reviews using Google Translate API. You can send up to 1000 characters per HTTP call. 

I am used:
1.	JVM 1.8. 
2.	I running program in four Thread to solve stages 1-3 and preprocessing for 4-rth task.
3.	I set limit for RAM used running (compilling conf set to Xmx396m Xms128m XpermGen128m).That mean you could running this app on PC with 500 MB.
4.	I haven’t done JUnit tests.
5.	I use google-api-services-translate for translating.
6.	I use java-sizeof to get know what size of RAM objects using.

I have done:
I used mine an algorithm to optimize requests to the Google server. At the entrance, I have a ArrayList of Reviews, each of these review has a different size. If these comments are opted in the original form, then one query will have a length of 100 characters and the other will have a length of 300 characters. I solved the problem, when I made every request for a translation up to 1000 characters. At the same time, out of 570 thousand reviews, I generated 47 thousand requests to Google. On the side of Google I sent only 10 thousand characters (10 queries) the result is lower.

<b>Urgent:</b>
I used my API-KEY, to start you need to insert your api-key into the AIP-KEY.txt file, which is placed in the same folder as the main program (GoogleFoodsParser-1.0-jar-with-dependencies.jar).
<b>Run:</b>
Running with the command line translate = true will start the module with the translation:
Java -jar GoogleFoodsParser-1.0-jar-with-dependencies.jar translate = true
Running the java -jar command GoogleFoodsParser-1.0-jar-with-dependencies.jar will run the program without Translate.
