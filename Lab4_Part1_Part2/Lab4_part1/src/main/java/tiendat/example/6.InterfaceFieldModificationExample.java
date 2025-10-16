package tiendat.example;

final class AppConstants {
    private AppConstants() {
    }

    static final int MAX_USERS = 100;
}

class InterfaceFieldModificationExample {
    public static void main(String[] args) {
        // AppConstants.MAX_USERS = 200; // Compile-time error (final constant)
        // Example is intentionally minimal; no output to avoid S106 (use a logger if
        // needed).
    }
}
