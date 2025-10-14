package tiendat.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    private static AccountService service;

    @org.junit.jupiter.api.BeforeAll
    static void beforeAll() {
        service = new AccountService();
    }

    @ParameterizedTest(name = "Row {index}: {0},{1},{2} -> expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("registerAccount – đọc dữ liệu từ CSV đúng theo đề")
    void testRegisterAccount_FromCsv(String username, String password, String email, boolean expected) {
        boolean actual = service.registerAccount(username, password, email);
        assertEquals(expected, actual);
    }
}
