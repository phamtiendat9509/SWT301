package tiendat.example;

import java.util.logging.Level;
import java.util.logging.Logger;

interface Printer {
    void print(String message);
}

final class ConsolePrinter implements Printer {
    private static final Logger LOGGER = Logger.getLogger(ConsolePrinter.class.getName());

    @Override
    public void print(String message) {
        LOGGER.log(Level.INFO, message);
    }
}

final class Report {
    private final Printer printer;

    Report(Printer printer) {
        this.printer = printer;
    }

    void generate() {
        printer.print("Generating report...");
    }
}
