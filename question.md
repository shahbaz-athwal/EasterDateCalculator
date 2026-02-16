Here is the content of the assignment formatted in Markdown.

# Assignment 1: COMP3663

**Team Assignment** (teams of 2–3 students; submit one ZIP per team)

---

## Problem 1

The date of Easter in a given year is computed using an intermediate value called `dateValue`, defined as:
`dateValue = 22 + A + B`, where A and B are determined as follows:

- **A** = the remainder of `(19C + 24) / 30`
- **B** = the remainder of `(2D + 4E + 6A + 5) / 7`
- **C** = the remainder of `(year / 19)`
- **D** = the remainder of `(year / 4)`
- **E** = the remainder of `(year / 7)`

This approach works for any year between 1900 and 2100. There are some special corrections and methods to consider below. It computes an integer date value which is the day in March for Easter.

However, if the result is one of the years **1954, 1981, 2049, or 2076**, then reduce the `dateValue` by 7 before converting it to a calendar date. And, if the date value is > 31, then the result is a day in April, so the date is calculated as `date - 31`.

### Specification 1: Special correction

If the year is one of: **1954, 1981, 2049, 2076**, then reduce `dateValue` by 7 before converting it to a calendar date.

### Specification 2: Convert `dateValue` to a calendar date

- If `dateValue` ≤ 31 (i.e., `22 + A + B ≤ 31`), then Easter falls in **March** on day `dateValue`.
- If `dateValue` > 31 (i.e., `22 + A + B > 31`), then Easter falls in **April** on day `dateValue - 31`.

`Easter.java` uses a class method called `easterDate` defined in the class `EasterCalculator` to display an easter date given a year. The year given must be between 1900 and 2100. The header of the method is as follows:

```java
public static MyDate easterDate(int year)
// returns a date corresponding to the easter day of
// the year given if 1900 <= year <= 2100
// returns null if not
```

### Question 1.1

Using the Equivalence Class Partitioning (ECP) and the Boundary Value Analysis (BVA) approaches, design black box tests for the method `easterDate`. Your ECP and BVA must be based on the method specification and input constraints. Show:

- Your equivalence classes with a short descriptive note.
- The boundary values that should be checked.

**Format:**

| Input Condition | Valid Classes | Invalid Classes | Boundary Values |
| :-------------- | :------------ | :-------------- | :-------------- |
| ...             | ...           | ...             | ...             |

### Question 1.2

Write enough test cases to cover all the equivalence classes, and boundary values identified in question 1.1.

Provide a table showing the link between your test data and the equivalence classes. This table should have the following format:

| Test Case Number | Test Data | Expected results | Covers ECP class(es) | Covers Boundary Value(s) |
| :--------------- | :-------- | :--------------- | :------------------- | :----------------------- |
| ...              | ...       | ...              | ...                  | ...                      |

_You can find the dates of Easter at [https://tlarsen2.tripod.com/anthonypolumbo/apeasterdates.html](https://tlarsen2.tripod.com/anthonypolumbo/apeasterdates.html) or [https://www.census.gov/data/software/x13as/genhol/easter-dates.html](https://www.census.gov/data/software/x13as/genhol/easter-dates.html)._

### Question 1.3

Implement your test suite using the JUnit test tool. You are required to hand in the source code of your test suite.

---

## Question 2

You’ve joined a small team building **AcadiaEats**, a campus food-ordering app. Students use it to pick a meal combo and pay, and your job is to keep the design flexible because the university changes vendors and systems a lot. Right now, the app has two moving parts: (1) building the meal and (2) taking payment.

### Part 1: Building the meal

When a student places an order, they build a `Meal`. A `Meal` always has a Main, and it may include optional extras:

- **Main (required):** Burger, Wrap, or Salad
- **Side (optional):** Fries, Soup, or Fruit
- **Drink (optional):** Water, Soda, or Coffee
- **Size (optional):** Small, Medium, Large

The product team also wants “one-tap” preset meals like **Kids Meal**, **Athlete Meal**, and **Budget Meal** (each preset is just a different combination of the same parts).

### Part 2: The dining vendor can change (families of matching items)

Here’s the twist: the university can contract different dining vendors each semester.

- **Vendor A** has its own internal item classes and rules (e.g., `VendorABurger`, `VendorAFries`, `VendorACoffee`…)
- **Vendor B** has a different but compatible set (`VendorBBurger`, `VendorBFries`, `VendorBCoffee`…)

The app should be able to switch vendors without rewriting the meal-building logic. In other words: your design should produce a **consistent family of matching items** based on which vendor is active.

### Part 3: Payment methods (chosen at runtime)

After the meal is built, the student pays. The app currently supports:

- Card
- Campus Wallet

More payment options may be added later.

### Part 4: A vendor card library you cannot change

For card payments, the university mandates a third-party library that your team is not allowed to modify:

- `ThirdPartyCardAPI.charge(amountInCents: int): String`
  - Returns `"APPROVED"` or `"DECLINED"`

But your application code expects something simpler and consistent:

- `PaymentProcessor.pay(amount: double): boolean`

### Requirements

Design a solution that uses **all** of these design patterns together in your design:

- Builder
- Abstract Factory
- Factory (Factory Method is fine)
- Adapter

**Your solutions include:**

1.  **One UML class diagram:** Label the roles for each pattern (e.g., Builder, Director, AbstractFactory, Adapter, Adaptee, Creator…).
2.  **Explain the flow for this specific story:** for example, _“A student selects Vendor B, chooses the Athlete Meal, and then pays $12.50 by card.”_

Your bullets should describe what gets created and which object talks to which, in order.

_Note that you don’t need to write full code. Focus on roles, relationships, and call flow. The goal is a design that is easy to extend (new vendors, new meal parts, new payment methods), i.e., The subjects which were discussed in class._
