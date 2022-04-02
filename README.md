# COSC2288 Assignment 1

This project is Matt Kellock's assignment 1 submission, an ordering application.
It is built in Java utilising Maven as the build tool.

## Installation

Please ensure you have the following installed prior to execution:

- Java 1.8
- Maven

## Test

Run the following to execute unit tests

```bash
# Test the application
mvn test
```

## Compile

Run the following to compile the application

```bash
# Build the application for execution
mvn compile
```

## Execution

```bash
# Execute the application
mvn exec:java -Dexec.mainClass=com.cosc2288.App -Dexec.args="-d /Users/mattkellock/git/cosc2288-1/Discounts.csv -r /Users/mattkellock/git/cosc2288-1/Restaurants-2022.txt"
```

## Contributing

Sorry, no contributions are accepted as this is a university assignment.

## License

[GNU General Public License v3.0](https://choosealicense.com/licenses/gpl-3.0/)
