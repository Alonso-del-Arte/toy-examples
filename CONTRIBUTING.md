# Contribution Guidelines

This project will be open to Hacktoberfest 2023. This year I want to be 
absolutely clear that I don't want to deal with Maven. I think it's obnoxious, 
obtrusive and adds way too much of its own complexity to the project.

And it's overkill for this project anyway, because this project is not supposed 
to have a hundred dependencies to keep track of. Issues asking for Maven will be 
flatly rejected. Same goes for pull requests.

To ask for dependencies besides JUnit is to completely miss the point of toy 
examples. These toy examples are about illustrating concepts of object-oriented 
programming, not demonstrating that there's a third party library for every 
purpose.

I will only consider pull requests pertaining to open issues or TODO comments. 
You need to have an integrated development environment (IDE) like IntelliJ IDEA 
or Apache NetBeans, with JUnit.

A pull request should not cause existing tests that were previously passing to 
fail, but it may contain intermediate commits with failing tests.

Do not delete any tests, unless you can absolutely demonstrate they're 
unnecessary (redundancy is not proof of being unnecessary, especially where 
inheritance and polymorphism are involved).

At first, I was using JUnit 4, I believe I have switched over the project to 
JUnit 5, but I'll have to check.

Use whatever build tool you like, just don't put any record of it in pull 
requests. These toy examples are not worth putting up with Maven headaches or 
Gradle headaches or sbt headaches. If you're using IntelliJ, it's probably 
easiest to just use IntelliJ's built-in build tool.

The only dependency for this Java 8 project is JUnit. If JDK 8 doesn't provide 
something you need, consider writing it from scratch.
