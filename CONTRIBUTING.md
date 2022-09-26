# Contribution Guidelines

This project is open to Hacktoberfest 2022.

I will only consider pull requests pertaining to open issues or TODO comments. 
You need to have an integrated development environment (IDE) like IntelliJ IDEA 
or Apache NetBeans, with JUnit.

A pull request should not cause any existing tests to fail, but it may contain 
intermediate commits with failing tests.

Do not delete any tests, unless you can absolutely demonstrate they're 
unnecessary (redundancy is not proof of being unnecessary, especially where 
inheritance and polymorphism are involved).

At first, I was using JUnit 4, I believe I have switched over the project to 
JUnit 5, but I'll have to check.

Use whatever build tool you like, just don't put any record of it in pull 
requests. These toy examples are not worth putting up with Maven headaches or 
Gradle headaches or sbt headaches. If you're using IntelliJ, it's probably 
easiest to just use IntelliJ's built-in build tool.
