
public class Task {

    private String name;
    private boolean completed;
    private int priority;

    public Task(String name, int priority) {
        this.name = name;
        this.completed = false;
        setPriority(priority);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void completeTask() {
        completed = true;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority >= 1 && priority <= 3) {
            this.priority = priority;
        } else {
            this.priority = 2;
        }
    }

    public String getPriorityName() {
        switch (priority) {
            case 1:
                return "ACIL";
            case 2:
                return "ORTA";
            case 3:
                return "DUSUK";
            default:
                return "ORTA";
        }
    }

    public void editName(String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            name = newName.trim();
        }
    }

    public void showTask() {
        String status = completed ? "Tamamlandi" : "Bekliyor";

        System.out.println(
            name + " - " + status
            + " - Oncelik: " + getPriorityName()
        );
    }
}
