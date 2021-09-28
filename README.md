# Selenium Grid

### Hub Url:
http://<hub-machine-ip>:4444/wd/hub

## Docker command for hub:

docker pull selenium/hub
docker run -d -p 4444:4444 --name selenium-hub selenium/hub

## Command to grid console

http://<docker host ip>:4444/grid/console

## Docker command for chrome node:

docker run -d --link selenium-hub:hub selenium/node-chrome

## Docker command for firefox node:

docker run -d --link selenium-hub:hub selenium/node-firefox

## Docker command for jenkins setup:

docker run -d -v jenkins_home:/var/jenkins_home -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts-jdk11
  
## Docker compose up command:  

docker-compose up  (run this on root folder)  
  
