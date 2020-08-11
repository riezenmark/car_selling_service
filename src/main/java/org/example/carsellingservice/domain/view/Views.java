package org.example.carsellingservice.domain.view;

/**
 * Класс отображений с помощью JsonView. Используется для разрешения циклических ссылок при передаче Json'а.
 */
public final class Views {

    /**
     * Отображение идентификатора.
     */
    public interface Id { }

    /**
     * Отображение значащих полей.
     */
    public interface Name { }

    /**
     * Отображение электронной почты пользователя.
     */
    public interface Email { }

    /**
     * Отображение идентификатора и значащих полей.
     */
    public interface IdName extends Id, Name { }

    /**
     * Отображеие идентификатора, значащих полей и электронной почты пользователя.
     */
    public interface IdNameEmail extends IdName, Email { }
}
