package application.view;

public class ExitCommand implements Command {
    @Override
    public String execute() {
        return "Exiting menu.";
    }
}
