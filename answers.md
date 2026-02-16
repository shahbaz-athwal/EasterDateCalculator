# Assignment 1 — Problem 1: Answers

## Question 1.1 — Equivalence Class Partitioning (ECP) & Boundary Value Analysis (BVA)

### Equivalence Classes

| ID  | Type    | Description |
|:----|:--------|:------------|
| EC1 | Valid   | 1900 ≤ year ≤ 2100 — year is within the accepted range |
| EC2 | Invalid | year < 1900 — year is below the valid range; method returns `null` |
| EC3 | Invalid | year > 2100 — year is above the valid range; method returns `null` |
| EC4 | Valid   | year ∈ {1954, 1981, 2049, 2076} — special correction years where `dateValue` is reduced by 7 |
| EC5 | Valid   | Valid year NOT in {1954, 1981, 2049, 2076} — normal computation (no correction) |
| EC6 | Valid   | `dateValue` ≤ 31 — Easter falls in **March** |
| EC7 | Valid   | `dateValue` > 31 — Easter falls in **April** (day = dateValue − 31) |

### Boundary Values

| ID  | Value | Rationale |
|:----|:------|:----------|
| BV1 | 1899  | Just below the lower boundary of the valid range |
| BV2 | 1900  | Lower boundary (first valid year) |
| BV3 | 1901  | Just above the lower boundary |
| BV4 | 2099  | Just below the upper boundary |
| BV5 | 2100  | Upper boundary (last valid year) |
| BV6 | 2101  | Just above the upper boundary of the valid range |

### Combined ECP / BVA Table (as requested)

| Input Condition | Valid Classes | Invalid Classes | Boundary Values |
|:----------------|:--------------|:----------------|:----------------|
| Year range (1900–2100) | EC1: 1900 ≤ year ≤ 2100 | EC2: year < 1900 | BV1 = 1899, BV2 = 1900, BV3 = 1901 |
|                        |                          | EC3: year > 2100 | BV4 = 2099, BV5 = 2100, BV6 = 2101 |
| Special correction year | EC4: year ∈ {1954, 1981, 2049, 2076} | — | — |
| Normal (non-special) year | EC5: valid year ∉ {1954, 1981, 2049, 2076} | — | — |
| Easter month (output) | EC6: dateValue ≤ 31 → March | — | — |
|                       | EC7: dateValue > 31 → April | — | — |

---

## Question 1.2 — Test Cases

### Test Case Table

| Test Case # | Test Data (year) | Expected Result | Covers ECP Class(es) | Covers Boundary Value(s) |
|:------------|:-----------------|:----------------|:---------------------|:-------------------------|
| TC1  | 1899 | `null`    | EC2                | BV1 |
| TC2  | 1900 | April 15  | EC1, EC5, EC7      | BV2 |
| TC3  | 1901 | April 7   | EC1, EC5, EC7      | BV3 |
| TC4  | 2099 | April 12  | EC1, EC5, EC7      | BV4 |
| TC5  | 2100 | March 27  | EC1, EC5, EC6      | BV5 |
| TC6  | 2101 | `null`    | EC3                | BV6 |
| TC7  | 1954 | April 18  | EC1, EC4, EC7      | — |
| TC8  | 1981 | April 19  | EC1, EC4, EC7      | — |
| TC9  | 2049 | April 18  | EC1, EC4, EC7      | — |
| TC10 | 2076 | April 19  | EC1, EC4, EC7      | — |
| TC11 | 2008 | March 23  | EC1, EC5, EC6      | — |
| TC12 | 2000 | April 23  | EC1, EC5, EC7      | — |

### Justification for Each Test Case

- **TC1 (year = 1899):** Tests an invalid year just below the lower boundary. Expects `null`. Covers EC2 and BV1.
- **TC2 (year = 1900):** Tests the lower boundary itself — a valid, non-special year whose Easter falls in April. Covers EC1, EC5, EC7, and BV2.
- **TC3 (year = 1901):** Tests one above the lower boundary — a valid, non-special year with an April Easter. Covers EC1, EC5, EC7, and BV3.
- **TC4 (year = 2099):** Tests one below the upper boundary — a valid, non-special year with an April Easter. Covers EC1, EC5, EC7, and BV4.
- **TC5 (year = 2100):** Tests the upper boundary itself — a valid, non-special year whose Easter falls in March. Covers EC1, EC5, EC6, and BV5.
- **TC6 (year = 2101):** Tests an invalid year just above the upper boundary. Expects `null`. Covers EC3 and BV6.
- **TC7 (year = 1954):** Tests the first special correction year. The `dateValue` is reduced by 7, resulting in an April date. Covers EC1, EC4, EC7.
- **TC8 (year = 1981):** Tests the second special correction year. Covers EC1, EC4, EC7.
- **TC9 (year = 2049):** Tests the third special correction year. Covers EC1, EC4, EC7.
- **TC10 (year = 2076):** Tests the fourth special correction year. Covers EC1, EC4, EC7.
- **TC11 (year = 2008):** Tests a normal (non-special) year where Easter falls in March (dateValue ≤ 31). Covers EC1, EC5, EC6.
- **TC12 (year = 2000):** Tests a normal (non-special) year where Easter falls in April (dateValue > 31). Covers EC1, EC5, EC7.

### Coverage Summary

| Class / BV | Covered By |
|:-----------|:-----------|
| EC1 | TC2, TC3, TC4, TC5, TC7–TC12 |
| EC2 | TC1 |
| EC3 | TC6 |
| EC4 | TC7, TC8, TC9, TC10 |
| EC5 | TC2, TC3, TC4, TC5, TC11, TC12 |
| EC6 | TC5, TC11 |
| EC7 | TC2, TC3, TC4, TC7, TC8, TC9, TC10, TC12 |
| BV1 | TC1 |
| BV2 | TC2 |
| BV3 | TC3 |
| BV4 | TC4 |
| BV5 | TC5 |
| BV6 | TC6 |

All equivalence classes and boundary values are covered by the 12 test cases.
