package ch.scs.jumpstart.bankkata.solution;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class SolutionAccountAcceptanceTest {
  private static final LocalDate DATE_1 = LocalDate.parse("2021-01-01");
  private static final LocalDate DATE_2 = LocalDate.parse("2021-06-12");
  private static final LocalDate DATE_3 = LocalDate.parse("2021-06-15");
  private static final LocalDate DATE_4 = LocalDate.parse("2021-06-16");

  @Test
  public void print_the_ledger_of_the_account() {
    SolutionConsole console = mock(SolutionConsole.class);
    var consoleAccountPrinter = new SolutionConsoleAccountPrinter(console);
    var clock = mock(SolutionClock.class);
    when(clock.getLocalDate()).thenReturn(DATE_1, DATE_2, DATE_3, DATE_4);
    var account = new SolutionAccount(clock, consoleAccountPrinter);

    account.deposit(100);
    account.withdraw(75);
    account.deposit(1500);
    account.withdraw(250);
    account.print();

    var inOrder = inOrder(console);
    inOrder.verify(console).printLine("Date || Amount || Balance");
    inOrder.verify(console).printLine(DATE_4 + " || -250 || 1275");
    inOrder.verify(console).printLine(DATE_3 + " || 1500 || 1525");
    inOrder.verify(console).printLine(DATE_2 + " || -75 || 25");
    inOrder.verify(console).printLine(DATE_1 + " || 100 || 100");
    inOrder.verifyNoMoreInteractions();
  }
}
