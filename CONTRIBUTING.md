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

Use Maven for the build tool. This project is provisionally a Maven project only for the sake of Hacktoberfest 2022. The problem with Maven is that it's a bloated program that does way more than what it was originally intended for, so it adds a lot of its own complexity to a project.
