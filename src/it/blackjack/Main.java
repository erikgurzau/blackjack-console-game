package it.blackjack;
import it.blackjack.game.BlackJack;
import it.blackjack.player.Player;
import it.blackjack.game.utils.OutputColors;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BlackJack bj;
        Player player = new Player();
        int risp, bet, match = 0;


        try{
            do {
                System.out.println(
                "\n―――――――――――――\n  " +
                "♥♦ Black Jack ♣♠" +
                "\n―――――――――――――");
                System.out.println("(1) - Gioca");
                System.out.println("(2) - Ricarica conto");
                System.out.println("(3) - Visualizza conto");
                System.out.println("(0) - Esci");
                System.out.print("\nRisposta: ");
                risp = sc.nextInt();

                switch (risp){
                    case 1:
                        if (match == 0) {
                            System.out.print("\nInserisci il tuo nome: ");
                            player.setName(sc.next());
                            match++;
                        }
                        if (player.getMoney() > BlackJack.MIN_BET){
                            System.out.println(OutputColors.ANSI_YELLOW + "\n☛ Il tuo conto: €" + player.getMoney() + OutputColors.ANSI_RESET);

                            System.out.println("\n[min."+BlackJack.MIN_BET+" max."+BlackJack.MAX_BET+"]");
                            System.out.print("Inserisci la puntata: ");
                            bet = sc.nextInt();
                            while (bet > player.getMoney() || bet > BlackJack.MAX_BET || bet < BlackJack.MIN_BET){
                                System.out.print(OutputColors.ANSI_RED + "⚠ Attenzione ⚠ Inserisci una puntata tra " + BlackJack.MIN_BET + " e "
                                        + (Math.min(player.getMoney(), BlackJack.MAX_BET)) + ": " + OutputColors.ANSI_RESET);
                                bet = sc.nextInt();
                            }

                            player.setBet(bet);

                            bj = new BlackJack(player);
                            bj.run();
                        }
                        else System.out.println(OutputColors.ANSI_RED + "\n ⚠ Ricarica il tuo conto! ⚠ Non hai abbastanza soldi per giocare." + OutputColors.ANSI_RESET);
                    break;

                    case 2:
                        if (player.getMoney() < BlackJack.MIN_BET) {
                            player.resetMoney();
                            System.out.println(OutputColors.ANSI_BLUE + "\n✔ Ricarica effettuata con successo! " + OutputColors.ANSI_RESET);
                        }
                        else System.out.println(OutputColors.ANSI_RED + "\n⚠ Attenzione ⚠ Hai già i soldi per poter giocare." + OutputColors.ANSI_RESET);
                    break;

                    case 3:
                        System.out.println(OutputColors.ANSI_YELLOW + "\n☛ Il tuo conto: €" + player.getMoney() + OutputColors.ANSI_RESET);
                        break;
                }
            }while (risp != 0);
        }catch (InputMismatchException e) { }
        System.out.println("\nGrazie per aver giocato!");
        System.out.println("\nRealizato da Erik Gurzau\n© 2021 Erik Gurzau. Tutti i diritti riservati.");

    }
}
