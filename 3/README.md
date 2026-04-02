# Exercise 3: HTML Anonymizer

Java program that fetches web pages and rewrites links to redirect through a proxy.

## Run Tests
```bash
javac HTMLAnonymizer.java TestAnonymizer.java
java TestAnonymizer
```

## Usage
```bash
javac HTMLAnonymizer.java
java HTMLAnonymizer http://example.com
```

Transforms links like:
- `<a href="http://example.com/page">` 
- to: `<a href="http://localhost:8080/furtif?url=http%3A%2F%2Fexample.com%2Fpage">`

Handles absolute and relative URLs with proper URL encoding.

