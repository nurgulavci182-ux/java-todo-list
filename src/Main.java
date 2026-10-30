
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\n=== TODO LIST ===");
            System.out.println("1. Gorev ekle");
            System.out.println("2. Tum gorevleri listele");
            System.out.println("3. Gorevi tamamla");
            System.out.println("4. Gorev sil");
            System.out.println("5. Gorev duzenle");
            System.out.println("6. Bekleyen gorevleri listele");
            System.out.println("7. Tamamlanan gorevleri listele");
            System.out.println("8. Gorev onceligini degistir");
            System.out.println("9. Cikis");
            System.out.print("Secimin: ");

            String choice = input.nextLine();

            try {
                switch (choice) {

                    case "1": {
                        System.out.print("Gorev adi: ");
                        String name = input.nextLine();

                        System.out.println("Oncelik sec:");
                        System.out.println("1. ACIL");
                        System.out.println("2. ORTA");
                        System.out.println("3. DUSUK");
                        System.out.print("Oncelik: ");

                        int priority = Integer.parseInt(
                            input.nextLine()
                        );

                        manager.addTask(name, priority);
                        break;
                    }

                    case "2":
                        manager.listTasks();
                        break;

                    case "3":
                        manager.listTasks();
                        System.out.print("Gorev numarasi: ");

                        manager.completeTask(
                            Integer.parseInt(input.nextLine())
                        );
                        break;

                    case "4":
                        manager.listTasks();
                        System.out.print("Silinecek gorev numarasi: ");

                        manager.deleteTask(
                            Integer.parseInt(input.nextLine())
                        );
                        break;

                    case "5": {
                        manager.listTasks();
                        System.out.print("Duzenlenecek gorev numarasi: ");

                        int number = Integer.parseInt(
                            input.nextLine()
                        );

                        System.out.print("Yeni gorev adi: ");
                        String newName = input.nextLine();

                        manager.editTask(number, newName);
                        break;
                    }

                    case "6":
                        manager.listTasksByStatus(false);
                        break;

                    case "7":
                        manager.listTasksByStatus(true);
                        break;

                    case "8": {
                        manager.listTasksByStatus(false);

                        System.out.print(
                            "Onceligi degisecek gorev numarasi: "
                        );

                        int number = Integer.parseInt(
                            input.nextLine()
                        );

                        System.out.println("Yeni oncelik:");
                        System.out.println("1. ACIL");
                        System.out.println("2. ORTA");
                        System.out.println("3. DUSUK");
                        System.out.print("Secimin: ");

                        int newPriority = Integer.parseInt(
                            input.nextLine()
                        );

                        manager.changePriority(
                            number, newPriority
                        );
                        break;
                    }

                    case "9":
                        System.out.println("Program kapatiliyor...");
                        input.close();
                        return;

                    default:
                        System.out.println("Gecersiz secim.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                    "Lutfen gecerli bir sayi gir."
                );
            }
        }
    }
}


