package app.Data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatasetTest {

    // Test maak data dus een int array
    @Test
    void generateIntDataReturnsCorrectSizeAndNoNulls() {
        int size = 100;
        Integer[] data = Dataset.generateIntData(size);

        assertNotNull(data);
        assertEquals(size, data.length);

        for (Integer value : data) {
            assertNotNull(value);
        }
    }

    // Test of de waardes binnen de verwachte range vallen
    @Test
    void generateIntDataValuesAreWithinExpectedRange() {
        int size = 1000;
        Integer[] data = Dataset.generateIntData(size);

        for (Integer value : data) {
            assertTrue(value >= 0 && value < 100_000, "Value out of range: " + value);
        }
    }

    // Test dat er ook echt unieke namen worden teruggegeven
    @Test
    void getNamesReturnsNonEmptyUniqueNames() {
        String[] names = Dataset.getNames();

        assertNotNull(names);
        assertTrue(names.length > 0);

        // snelle uniqueness check
        for (int i = 0; i < names.length; i++) {
            assertNotNull(names[i]);
            assertFalse(names[i].isBlank());

            for (int j = i + 1; j < names.length; j++) {
                assertNotEquals(names[i], names[j],
                        "Duplicate name found: " + names[i]);
            }
        }
    }

    // Test dat mensen worden gegenereerd met juiste namen en een leeftijd
    @Test
    void generatePeopleReturnsPeopleMatchingNamesAndAgeRange() {
        Person[] people = Dataset.generatePeople();
        String[] names = Dataset.getNames();

        assertNotNull(people);
        assertEquals(names.length, people.length);

        for (int i = 0; i < people.length; i++) {
            assertNotNull(people[i]);
            assertNotNull(people[i].getName());
            assertEquals(names[i], people[i].getName());

            int age = people[i].getAge();
            assertTrue(age >= 20 && age <= 80, "Age out of range: " + age);
        }
    }
}
