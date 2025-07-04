# Read YAML files with Jackson library

This project uses Jackson library to read a nested yaml file that could change with frequency, let's say for a dynamic content, for cases where certain parameters are deleted, changed or new ones are introduced and that changes the structure of the file. 

The `YmlReader` util class contains the function to read a "dynamic" yml file and you can find an example of how to use it int the `YamlWithJacksonDemo` class.

Happy coding!

## Prerequisites
- Java Development Kit (JDK) 8 or later
- Maven or Gradle build tool
- Jackson library (using version [2.18.2](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.18.2))

## Step-by-Step to run the project

### Clone the Repository
```bash
git clone https://github.com/ypdev19/java-quests-fiesta.git
```

### Install dependencies

Maven
```bash
mvn clean install
```

Gradle
```bash
gradle build
```

## Contributing
Contributions to this project are welcome. If you'd like to contribute, please fork the repository and submit a pull request with your changes.

## License
This project is licensed under the Apache License 2.0.
