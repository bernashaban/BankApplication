package application.view;


import application.exception.NonExistingEntityException;

public interface EntityDialog<E> {
    E input() throws NonExistingEntityException;
}
