package application.view;


import application.exception.AlreadyExistingEntityException;
import application.exception.NonExistingEntityException;

import java.sql.SQLException;

public interface Command {
    String execute() throws AlreadyExistingEntityException, NonExistingEntityException, SQLException;
}
