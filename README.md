# selenium-ci-test
Selenium Server/Grid Hub Start

java -jar selenium-server-standalone-3.14.jar -role hub

Set chromedriver on Grid

java -Dwebdriver.chrome.driver=chromedriver -jar selenium-server-standalone-3.14.jar -role webdriver -hub http://192.168.0.18:4444/grid/register/ -port 5566

java -Dwebdriver.chrome.driver=<file path of your chromedriver.exe> -jar <file path of your selenium standalone jar file> -role node -hub <url of your hub>eg:

java -Dwebdriver.chrome.driver=chromedriver -jar selenium-server-standalone-3.141.59.jar  -role node -hub http://localhost:4444/grid/register -port 5566

Firefox
java -Dwebdriver.chrome.driver=geckodriver -jar selenium-server-standalone-3.14.jar -role webdriver -hub http://192.168.0.18:4444/grid/register/ -port 5567