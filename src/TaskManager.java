
import java.util.ArrayList;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {

    private ArrayList<Task> tasks;
    private static final String FILE_NAME = "tasks.txt";

    public TaskManager() {
        tasks = new ArrayList<>();
        loadTasks();
        sortTasks();
    }

    
    private void sortTasks() {
        tasks.sort(
            Comparator.comparingInt(task ->
                task.isCompleted() ? 4 : task.getPriority()
            )
        );
    }

   
    public void addTask(String name, int priority) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Gorev adi bos olamaz.");
            return;
        }

        if (priority < 1 || priority > 3) {
            System.out.println("Oncelik 1, 2 veya 3 olmali.");
            return;
        }

        tasks.add(new Task(name.trim(), priority));
        sortTasks();
        saveTasks();

        System.out.println("Gorev eklendi!");
    }

    
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Henuz gorev yok.");
            return;
        }

        sortTasks();

        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ". ");
            tasks.get(i).showTask();
        }
    }

    
    public void listTasksByStatus(boolean completedStatus) {
        sortTasks();
        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task.isCompleted() == completedStatus) {
                System.out.print((i + 1) + ". ");
                task.showTask();
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                completedStatus
                ? "Tamamlanmis gorev bulunamadi."
                : "Bekleyen gorev bulunamadi."
            );
        }
    }

    
    public void completeTask(int number) {
        if (number < 1 || number > tasks.size()) {
            System.out.println("Gecersiz gorev numarasi.");
            return;
        }

        Task task = tasks.get(number - 1);

        if (task.isCompleted()) {
            System.out.println("Bu gorev zaten tamamlanmis.");
            return;
        }

        task.completeTask();
        sortTasks();
        saveTasks();

        System.out.println("Gorev tamamlandi!");
    }

    
    public void editTask(int number, String newName) {
        if (number < 1 || number > tasks.size()) {
            System.out.println("Gecersiz gorev numarasi.");
            return;
        }

        if (newName == null || newName.trim().isEmpty()) {
            System.out.println("Yeni gorev adi bos olamaz.");
            return;
        }

        tasks.get(number - 1).editName(newName.trim());
        saveTasks();

        System.out.println("Gorev guncellendi!");
    }

    
    public void changePriority(int number, int newPriority) {
        if (number < 1 || number > tasks.size()) {
            System.out.println("Gecersiz gorev numarasi.");
            return;
        }

        if (newPriority < 1 || newPriority > 3) {
            System.out.println("Oncelik 1, 2 veya 3 olmali.");
            return;
        }

        Task task = tasks.get(number - 1);

        if (task.isCompleted()) {
            System.out.println(
                "Tamamlanmis gorevin onceligi degistirilemez."
            );
            return;
        }

        task.setPriority(newPriority);

        sortTasks();
        saveTasks();

        System.out.println(
            "Gorev onceligi guncellendi!"
        );
    }

   
    public void deleteTask(int number) {
        if (number < 1 || number > tasks.size()) {
            System.out.println("Gecersiz gorev numarasi.");
            return;
        }

        Task removed = tasks.remove(number - 1);
        saveTasks();

        System.out.println(removed.getName() + " silindi!");
    }

    
    private void saveTasks() {
        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Task task : tasks) {
                writer.write(
                    task.isCompleted() + "\t"
                    + task.getPriority() + "\t"
                    + task.getName()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Dosyaya kayit yapilamadi.");
        }
    }

   
    private void loadTasks() {
        try (BufferedReader reader =
                 new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t", 3);

              
                if (parts.length == 3) {
                    boolean completed =
                        Boolean.parseBoolean(parts[0]);

                    int priority;

                    try {
                        priority = Integer.parseInt(parts[1]);
                    } catch (NumberFormatException e) {
                        priority = 2;
                    }

                    Task task = new Task(parts[2], priority);

                    if (completed) {
                        task.completeTask();
                    }

                    tasks.add(task);

              
                } else if (parts.length == 2) {
                    boolean completed =
                        Boolean.parseBoolean(parts[0]);

                    Task task = new Task(parts[1], 2);

                    if (completed) {
                        task.completeTask();
                    }

                    tasks.add(task);
                }
            }

        } catch (IOException e) {
            
        }
    }
}
