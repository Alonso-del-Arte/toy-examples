# Toy Examples

NOTE: The `collections` package had become unwieldy and not exactly a toy 
example. On July 22, 2025, I decided to remove it.

NOTE: On January 12, 2026, I have decided to remove the `bankaccounts` package 
before February. As of February 19, I still haven't done it, it slipped my mind.

This repository is a random assortment of classic object-oriented programming 
(OOP) toy examples, some more fleshed out than others, to illustrate basic 
concepts of OOP.

For information regarding Hacktoberfest, see [CONTRIBUTING.md](CONTRIBUTING.md). 
Follow those guidelines to ensure not just that your contribution is accepted 
for Hacktoberfest, but also that it persists in the main branch after 
Hacktoberfest.

I've decided that this project should not be on the latest long term support 
(LTS) version of Java, but it shouldn't be too far behind either. So it's on 
Java 21 for the JDK and Java 17 for language features.

## The four pillars of OOP

* **Abstraction** &mdash; FINISH WRITING
* **Encapsulation** &mdash; Objects encapsulate their state and forbid changes 
except as prescribed by the defining class. See the `Odometer` class in the 
`vehicles.parts` package. An `Odometer` instance allows its mileage to be 
advanced but not rolled back.
* **Inheritance** &mdash; Classes inherit properties from base classes. The 
`animals` package definitely contains toy examples of this. Inheritance need not 
be deep to be useful. In the more practical examples, inheritance tends to 
average two or three levels: a subclass extends an abstract class, which in turn 
implicitly extends `java.lang.Object`.
* **Polymorphism** &mdash; FINISH WRITING

## SOLID principles

* **Single responsibility** &mdash; Each class should only have one 
responsibility. For example, the `getBalance()` function in the 
`CheckingAccount` class should only get the balance and not do anything else 
(such as verifying all applicable fees have been assessed.)
* **Open/Closed** &mdash; FINISH WRITING
* **Liskov substitution** &mdash; Barbara Liskov was brought into this to make 
the acronym work. FINISH WRITING
* **Interface segregation** &mdash; Interfaces should not force implementing 
classes to implement more than is necessary. For example, suppose we need the 
`FlyingSquirrel` class and a few other classes to implement `glide()` from an 
interface. But if we have those classes implement `FlightCapable`, they also 
have to have an implementation for `fly()` or be declared abstract, and we don't 
want either of those because flying squirrels don't actually fly, they glide. 
Animals like eagles and hawks fly but they can also glide. So what we do is that 
we separate `glide()` to the `GlideCapable` interface and then the 
`FlightCapable` interface can inherit `glide()` from `GlideCapable`.
* **Dependency inversion** &mdash; FINISH WRITING

## CUPID principles

Some people have questioned SOLID, arguing that it's not as relevant today as 
when it was introduced. Dan North has proposed one alternative to SOLID, CUPID.

* **Composable** &mdash; "plays well with others" FINISH WRITING
* **Unix philosophy** &mdash; "does one thing well" This one goes beyond the 
single responsibility principle in SOLID in that the FINISH WRITING
* **Predictable** &mdash; "does what you expect" FINISH WRITING
* **Idiomatic** &mdash; "feels natural"  FINISH WRITING
* **Domain-based** &mdash; "the solution domain models the problem domain in 
language and structure"  FINISH WRITING

