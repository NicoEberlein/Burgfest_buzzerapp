# BuzzerPi

Initially built for CVJM Bayern this java application can be used generally for reallife minigames using up to three buzzers connected to a RaspberryPi.
It is possible to implement additional gamemodes. The application also comes with a frontend to configure the gamemodes and to display the scores publicly.

## How to build the application 

1. Install required dependency: `apt install pigpio openjdk-17-jdk-headless`
2. Make sure pigpio is disabled and not running (app will start its own instance of pigpio)
    1. `systemctl stop pigpiod`
    2. `systemctl disable pigpiod`
3. Building the executable jar file: `./mvnw install`

> **Note**  
> - Every command above must be executed as root  
> - The third step must be executed every time the source code is changed

## How to connect the buzzers

Each buzzer is connected with two gpio pins. One voltage output and one input which checks if voltage is applied and therefore can detect if the buzzer is pressed. The standard pins are (in BCM-format) configured in `src/main/ressources/application.properties` and can of course be changed in this file.

## How to access score and settings frontend

The jar contains its own web server which runs on standard http port 80. To change the port simply change the `server.port`-property in `src/main/ressources/application.properties`

To access the settings-page in order to set the team names and the gamemode visit: `http://<IP>/settings`  
To access the score-page visit `http://<IP>/`

## Which Gamemodes are implemented yet?

### Counter

Simply count the buzzer presses of each team. The score of each team can also be modified using the settings-webinterface.

### WhoWasFirst

Shows which buzzer was pressed first. Can be used e.g. for a quiz where the first person to press the buzzer is allowed to answer first. The background color of the score page changes to the corresponding team color.


##  How to modify webapp frontend

The frontend ist specifically designed for my personal use case. If you want to modify style or appearance of the frontend files you can find
  * the javascripts in `src/main/ressources/static/js/`
  * the stylesheets in `src/main/ressources/static/css/`
  * the html files in `src/main/ressources/templates/`
  * the images in `src/main/ressources/static/images/`

> **Note**  
> Remember to build a new jar file after changing contents of these files.

## How to contribute

1. Fork this project
2. Write code
3. Make pull request

There are still some features I want to implement in the future. I will add some issues to this project soon.