# Assignment 1 — Question 2: Answers

## AcadiaEats — Design Patterns Solution

### Design Patterns Applied

| Pattern              | Applied To                          | Purpose                                                                                    |
| :------------------- | :---------------------------------- | :----------------------------------------------------------------------------------------- |
| **Builder**          | Meal construction (Part 1)          | Assembles a `Meal` step-by-step; a Director provides preset "one-tap" meals                |
| **Abstract Factory** | Vendor item families (Part 2)       | Produces a consistent family of vendor-specific items without coupling to concrete classes |
| **Factory Method**   | Payment processor creation (Part 3) | Each Creator subclass decides which `PaymentProcessor` to instantiate at runtime           |
| **Adapter**          | Third-party card library (Part 4)   | Wraps the immutable `ThirdPartyCardAPI` behind the app's `PaymentProcessor` interface      |

### Section 2: Flow Explanation

**Story:** _"A student selects Vendor B, chooses the Athlete Meal, and then pays $12.50 by card."_

#### Step-by-step call flow

1. **Select vendor.** The app determines that Vendor B is the active dining vendor for this semester. It instantiates a **`VendorBFactory`** (a ConcreteFactory in the Abstract Factory pattern).

2. **Create the builder.** The app creates a **`ConcreteMealBuilder`**, passing the `VendorBFactory` to its constructor. This ties all subsequent item creation to Vendor B's product family.

3. **Create the director.** The app creates a **`MealDirector`**, passing the `ConcreteMealBuilder` as its builder.

4. **Build the preset meal.** The student taps "Athlete Meal." The app calls **`director.makeAthleteMeal()`**. Inside this method, the Director issues a fixed sequence of builder calls:
   - `builder.setMain(WRAP)` -- the builder delegates to `vendorBFactory.createMain(WRAP)`, which returns a **`VendorBWrap`** (a ConcreteProduct).
   - `builder.setSide(FRUIT)` -- the builder delegates to `vendorBFactory.createSide(FRUIT)`, which returns a **`VendorBFruit`**.
   - `builder.setDrink(WATER)` -- the builder delegates to `vendorBFactory.createDrink(WATER)`, which returns a **`VendorBWater`**.
   - `builder.setSize(LARGE)` -- sets the meal size directly.

5. **Assemble the meal.** The Director calls **`builder.build()`**, which assembles and returns a **`Meal`** object containing the four Vendor B items.

6. **Select payment method.** The student chooses "Pay by Card." The app uses a **`CardPaymentCreator`** (a ConcreteCreator in the Factory Method pattern) and calls **`creator.createProcessor()`**.

7. **Factory method returns the adapter.** `CardPaymentCreator.createProcessor()` instantiates and returns a **`CardPaymentAdapter`** (the Adapter), which internally holds a reference to the **`ThirdPartyCardAPI`** (the Adaptee).

8. **Process payment.** The app calls **`processor.pay(12.50)`** on the returned `CardPaymentAdapter`.

9. **Adapt the call.** Inside `CardPaymentAdapter.pay(12.50)`:
   - Converts the dollar amount (`12.50`) to cents (`1250`).
   - Calls **`ThirdPartyCardAPI.charge(1250)`** on the Adaptee.
   - The third-party API returns `"APPROVED"`.
   - The adapter translates `"APPROVED"` to `true` and returns it.

10. **Confirm order.** The app receives `true`, confirming the payment succeeded, and displays the order confirmation to the student.

#### Why this design is easy to extend

- **New vendor (e.g., Vendor C):** Add a new `VendorCFactory` implementing `VendorFactory`, plus its ConcreteProduct classes. No existing code changes.
- **New meal part (e.g., Dessert):** Add a `Dessert` interface and an `addDessert()` method to `MealBuilder`. Each ConcreteFactory gains a `createDessert()` method.
- **New payment method (e.g., Mobile Pay):** Add a `MobilePayCreator` (ConcreteCreator) and a `MobilePayProcessor` (implementing `PaymentProcessor`). No existing code changes.
- **New preset meal:** Add a new method to `MealDirector` (e.g., `makeVeganMeal()`). The builder and factory remain unchanged.
