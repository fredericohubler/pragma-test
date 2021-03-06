PragmaBrewery Code Challenge
==
Quickstart
--
### Prerequisites
* Have Maven installed

### How to run
1. Clone git repository `https://github.com/fredericohubler/pragma-test.git`
2. Open the project folder on terminal
3. Run command `mvn package`
4. Run command `cd target`
5. Run command `java -jar pragma-challenge-1.0-SNAPSHOT-jar-with-dependencies.jar`
6. Open static HTML located on `src\main\resources\main.html`

The Problem
--
>Meet Shane. He works at PragmaBrewery, a boutique microbrewery in a rural area of Australia,
>and is the creator of 6 unique beer varieties. Shane is responsible for driving the large transport
>truck, delivering goods from the brewery to a number of pubs across Sydney each week.
>
>Each beer has its own specific refrigeration needs while being transported:
>
>>- Beer 1 (Pilsner): 4°C - 6°C
>>- Beer 2 (IPA): 5°C - 6°C
>>- Beer 3 (Lager): 4°C - 7°C
>>- Beer 4 (Stout): 6°C - 8°C
>>- Beer 5 (Wheat beer): 3°C - 5°C
>>- Beer 6 (Pale Ale): 4°C - 6°C
>
>The refrigerated truck is loaded with multiple containers with beer bottles inside, each
>container set to a specific temperature and each containing a thermometer sensor.
>
>While driving, Shane is alerted if any of the containers fall outside of the temperature range.
>Unfortunately, this is common due to factors such as opening the doors to unload, the heat of
>the Sydney summer or sometimes due to forgetting the container doors ajar.
>
>Develop a solution that allows Shane to be aware of the current temperature of each container
>and notifies him when the temperatures are outside the correct range.
>
> **Constraints**
>
>● You must use JavaScript technology for most of the solution
>
>● The solution must be implemented with an acceptable level of automated tests; we
>should be able to verify it from the command line.
>
>● There must be a front-end component (ie. a web or command-line user interface of
>some sort) as well as a back-end component.
>
>● The back-end must use HTTP REST to communicate with the clients.
>
>● You must not use a code generator (no create-react-app, Angular-CLI, Rails etc.); we
>want to assess your software design skills.
>
>● You are free to use libraries, but not frameworks, for the same reason above.
>
>● The solution must run via the command-line and we should be able to boot it with a
>single command. The less dependencies on the operating system, the better. Once
>the minimum requirements are met, we must be able to boot it with a one-liner.
>
>● A database server isn’t required, if needed, mock the data in any application layer.
>
>Version 3.0 - April 2019
>
>Instructions
>1. Use your time to deliver a solution that showcases your coding skills and the level of
>quality you expect (but no need to gold plate it).
>2. In a real-life scenario, you would ask questions to clarify any doubts but for this
>challenge, document the questions you would ask and provide your own answers in
>the readme file.
>3. Once complete, send us a link to the package for download (if you are using a private
>repository in BitBucket/GitHub please share it with talent@pragma.team)
>4. Make sure your package contains a readme file with all relevant information necessary
>to run your solution, including:
>○ What are the highlights of your logic/code writing style?
>○ What could have been done in a better way? What would you do in version
>2.0?
>○ What were the questions you would ask and your own answers/assumptions?
>○ Any other notes you feel relevant for the evaluation of your solution.

The Solution
--
### About
>The solution was implemented with a Microservice Architecture based on pure Java. Furthermore, considering the scale of the application, no database was used.
>
>The front-end was developed with Javascript, HTML and CSS. Using JQuery library to communicate with the back-end.
>
>In order to run the application tests through command line, execute the command `mvn test` on the project folder
>
>In order to download the RELEASE jar directly, go to the link: `https://github.com/fredericohubler/pragma-test/releases`
#### Dashboard 
> ![Image](https://i.imgur.com/Mquj1OK.png)
> 

### Implementation
>To further explain the developed solution, some observations must be made: 
>   * There is only one truck, so there is no need to implement more than one instance of the solution.
>   * Since there is only one truck, it was assumed it has a fixed amount of containers. In this solution, the fixed amount is 12.(This is not the best approach if you consider scalability, but considering the problem, it doesn't seem like the program will need to scale any further).

#### Technologies
>    **Junit 4**
>       Used for unit testing.
>
>    **Mockito 1.8**
>       Used to aid in unit testing by mocking some classes and responses.
>
>    **Jackson 2.9**
>       Used to serialize and deserialize JSON objects in the API's requests and responses.
>
>    **Maven**
>       Used to aid in dependency injection, build and test automation.
>
>    **JQuery**
>       Used to make HTTP requests from the front-end to the back-end
>
> No framework has been used, since that was one of the constraints of the challenge.
>
#### Endpoints
>The API contains three endpoints: 
>   * GET /containers
>   * GET /containers/{containerId}
>   * PUT /containers/{containerId}
>
>**GET /containers**
>> Returns a list with all the containers on the truck, informing each container's following information: number, current temperature, max and min temperature to emit an alert and current beer type
>
>**GET /containers/{containerId}**
>>Returns the following information about the container with the ID informed on the path: number, current temperature, max and min temperature to emit an alert and current beer type
>
>**PUT /containers/{containerId}**
>>Updates the container with the ID informed on the path with the following information received through the request body: beer type, current temperature.
>>
>>This Endpoint expects the following JSON:
```
{
  "beer": "PILSEN",        (accepted values are: PILSEN, IPA, LAGGER, STOUT, WHEAT_BEER, PALE_ALE)
  "currentTemperature": int
}
```
How to use
--

> When first started, all the containers will have null values until you update each one with a beer type and the container's current temperature. You dont have to manually input the maximum and minimum temperature on each container, just specify which beer will go on the container and the program will figure that out for you.
>To update a container, just fill the fields of the form displayed in the image below and press submit.
>When a container is with its current temperature above or below its beer type limits, the background will become red to warn the driver that the temperature is wrong. Otherwise, the background will be green. 
>
> ![Image](https://i.imgur.com/MQYEt79.png)
>
>
#### Form Validations
>The front-end will perform the following validations: 
>
>
> ![Image](https://i.imgur.com/jZ7qRtC.png)
>
>Happens when you dont inform the number of the container you are trying to update on your form.
>
---
> ![Image](https://i.imgur.com/yaLKpX0.png)
>
>Happens when you dont inform the current temperature of the container you are trying to update on your form.
>
---

Challenge Instruction
--
### Questions 
>`In a real-life scenario, you would ask questions to clarify any doubts but for this
challenge, document the questions you would ask and provide your own answers in
the readme file.`
>
> **Q:** How many containers does the truck have?
>>There is no way to know for sure. I assumed there was 12 containers, 2 for each type of beer.
>
> **Q:** The update endpoint should receive a beer type and set the min and max temperatures based on that, or should it receive min and max values separately?
>>Since the challenge provided information about each beer's temperature limits, I supposed it would be better to send just the beer type on an update.
>
> **Q:** Can the solution be implemented using Java instead of Javascript(as it is requested on the challenge)?
>>For the sake of this project, I assumed the answer was yes.
>
> **Q:** Should the endpoint to set the current temperature and the type of beer be together?
>>I assumed the answer would be no, since the only moment this endpoint would be used would be in inicialization.
>
### Code Highlights 
>**Framework-less solution**
>>By not using frameworks, it is possible to have more control over whats going on in the code, generating a specific solution to the problem in hand. Also, by not having a framework, build and deploy times tend to be faster.
>
>**Design Patterns**
>>The project is built with an MVC Architecture in mind, and have all of its layers well defined, this makes giving support to the project easier. Also, the objects from the project are divided in InputDTO, OutputDTO and a Model object, using the Converter Pattern to convert from one to another, thus further decoupling the projects modules.
>
>**Unit Tests**
>>By having Unit Tests, implementing new functionalities and correcting possible bugs can be done in a safer way.
>
### What would you do in version 2.0?
* Validate the values that are being sent on the Current Temperature field.
* Create a logic that sends -1 on the response length parameter when sending a header with code 204.
* On the front-end, instead of updating the screen by pulling each 5 seconds, do the update logic by opening a socket.
* Think of a better way to instanciate objects other than Singleton(this would make unit testing easier).
* Remove the notification logic from the front-end and put it on the back-end(thus centralizing all logic on the back-end).
* Find a better way to test the ContainerHandler class.
* Add integration tests.
* Centralize all configurations on a single file.
* Offer the front-end in a web page application instead of a static HTML file, thus separating the project on a front-end and a back-end one(this would better separate the responsibilities of the project).
* Create profiles(like dev, hlg, prod).
* Containerize the application.



